package eapli.base.warehousemanagement.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDock;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDockId;
import eapli.base.warehousemanagement.domain.Aisle.Accessibility;
import eapli.base.warehousemanagement.domain.Aisle.Aisle;
import eapli.base.warehousemanagement.domain.Aisle.AisleId;
import eapli.base.warehousemanagement.domain.Aisle.Square;
import eapli.base.warehousemanagement.domain.JsonFileConstants;
import eapli.base.warehousemanagement.domain.Row.AisleRow;
import eapli.base.warehousemanagement.domain.Row.RowId;
import eapli.base.warehousemanagement.domain.Row.ShelveQuantity;
import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.domain.Warehouse.WarehouseName;
import eapli.base.warehousemanagement.domain.Warehouse.WarehousePlant;
import eapli.base.warehousemanagement.dto.WarehouseDTO;
import eapli.base.warehousemanagement.dto.WarehouseMapper;
import eapli.base.warehousemanagement.repository.AgvDockRepository;
import eapli.base.warehousemanagement.repository.AisleRepository;
import eapli.base.warehousemanagement.repository.RowRepository;
import eapli.base.warehousemanagement.repository.WarehouseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportFileService {

    private final WarehouseRepository warehouseRepo = PersistenceContext.repositories().warehouses();
    private final AisleRepository aisleRepo = PersistenceContext.repositories().aisles();
    private final RowRepository rowRepo = PersistenceContext.repositories().rows();
    private final AgvDockRepository agvDockRepo =  PersistenceContext.repositories().agvDocks();
    private final WarehouseMapper warehouseMapper = new WarehouseMapper();
    private List<Aisle> aisleList = new ArrayList<>();
    private List<AisleRow> aisleRowList = new ArrayList<>();
    private List<AgvDock> agvDockList = new ArrayList<>();

    public List<WarehouseDTO> warehouses(){
        Iterable<Warehouse> iterable = this.warehouseRepo.findAll();
        List<Warehouse> list = new ArrayList<>();
        iterable.iterator().forEachRemaining(list::add);
        return warehouseMapper.toDTO(list);
    }

    public WarehouseDTO findWarehouseByName(String name){
        WarehouseName warehouseName = new WarehouseName(name);
        Warehouse warehouse = this.warehouseRepo.getWarehouseByName(warehouseName);
        return new WarehouseDTO(warehouse);
    }

    public Warehouse importPlantFromJsonFile(WarehouseDTO warehouseDTO, String path) throws IOException, ParseException {
        Warehouse warehouse = warehouseRepo.getWarehouseByName(new WarehouseName(warehouseDTO.getWarehouseName()));

        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(path));
        JSONObject mainJsonObject = (JSONObject) object;

        String description = (String) mainJsonObject.get(JsonFileConstants.PLANT_DESCRIPTION);
        long length = (long) mainJsonObject.get(JsonFileConstants.PLANT_LENGTH);
        long width = (long) mainJsonObject.get(JsonFileConstants.PLANT_WIDTH);
        long square = (long) mainJsonObject.get(JsonFileConstants.PLANT_SQUARE);
        String unit = (String) mainJsonObject.get(JsonFileConstants.PLANT_UNIT);

        WarehousePlant warehousePlant = new WarehousePlant(description, length, width, square, unit);

        importAisles(warehouse, mainJsonObject);
        importAgvDocks(warehouse, mainJsonObject);

        warehouse.setPlant(warehousePlant);
        this.warehouseRepo.save(warehouse);

        return warehouse;
    }

    public void importAisles(Warehouse warehouse, JSONObject jsonObject) {
        JSONArray aisles = (JSONArray) jsonObject.get(JsonFileConstants.PLANT_AISLES);

        for (int i = 0; i < aisles.size(); i++) {
            JSONObject aisle = (JSONObject) aisles.get(i);

            long id = (long) aisle.get(JsonFileConstants.ID);
            AisleId aisleId = new AisleId(id);

            JSONObject object = (JSONObject) aisle.get(JsonFileConstants.BEGIN_SQUARE);
            Square begin = importSquare(object);

            object = (JSONObject) aisle.get(JsonFileConstants.END_SQUARE);
            Square end = importSquare(object);

            object = (JSONObject) aisle.get(JsonFileConstants.DEPTH_SQUARE);
            Square depth = importSquare(object);

            String accessblt = (String) aisle.get(JsonFileConstants.PLANT_ACCESSIBILITY);
            Accessibility accessibility = new Accessibility(accessblt);

            Aisle asl = new Aisle(aisleId, begin, end, depth, accessibility, warehouse);
            aisleRepo.save(asl);
            aisleList.add(asl);

            // Rows
            JSONArray rows = (JSONArray) aisle.get(JsonFileConstants.PLANT_ROWS);

            for (int j = 0; j < rows.size(); j++) {
                JSONObject row = (JSONObject) rows.get(j);

                long rId = (long) row.get(JsonFileConstants.ID);
                RowId rowId = new RowId(rId);

                JSONObject beginObject = (JSONObject) row.get(JsonFileConstants.BEGIN_SQUARE);
                Square rowBeginSquare = importSquare(beginObject);

                JSONObject endObject = (JSONObject) row.get(JsonFileConstants.END_SQUARE);
                Square rowEndSquare = importSquare(endObject);

                long shelveQuantity = (long) row.get(JsonFileConstants.SHELVES_QUANTITY);
                ShelveQuantity shelves = new ShelveQuantity(shelveQuantity);

                AisleRow r = new AisleRow(rowId, rowBeginSquare, rowEndSquare, shelves, asl);
                rowRepo.save(r);
                aisleRowList.add(r);
            }
        }
    }

    public void importAgvDocks(Warehouse warehouse, JSONObject jsonObject) {
        JSONArray agvDocks = (JSONArray) jsonObject.get(JsonFileConstants.PLANT_AGVDOCKS);

        for (int j = 0; j < agvDocks.size(); j++) {
            JSONObject agvDock = (JSONObject) agvDocks.get(j);

            String id = (String) agvDock.get(JsonFileConstants.ID);
            AgvDockId agvDockId = new AgvDockId(id);

            JSONObject beginObject = (JSONObject) agvDock.get(JsonFileConstants.BEGIN_SQUARE);
            Square begin = importSquare(beginObject);

            JSONObject endLObject = (JSONObject) agvDock.get(JsonFileConstants.END_SQUARE);
            Square end = importSquare(endLObject);

            JSONObject depthObject = (JSONObject) agvDock.get(JsonFileConstants.DEPTH_SQUARE);
            Square depth = importSquare(depthObject);

            String accessBlt = (String) agvDock.get(JsonFileConstants.PLANT_ACCESSIBILITY);
            Accessibility accessibility = new Accessibility(accessBlt);

            AgvDock dock = new AgvDock(agvDockId, begin, end, depth, accessibility, warehouse);
            agvDockRepo.save(dock);
            agvDockList.add(dock);
        }
    }

    public Square importSquare(JSONObject jsonObject){
        long lsquare = (long) jsonObject.get(JsonFileConstants.SQUARE_LENGTH);
        long wsquare = (long) jsonObject.get(JsonFileConstants.SQUARE_WIDTH);
        return new Square(lsquare, wsquare);
    }

    public List<Aisle> getAisleList() {
        return aisleList;
    }

    public List<AisleRow> getRowList() {
        return aisleRowList;
    }

    public List<AgvDock> getAgvDockList() {
        return agvDockList;
    }
}
