package eapli.base.warehousemanagement.presentation;

import eapli.base.warehousemanagement.application.SetWarehousePlantController;
import eapli.base.warehousemanagement.dto.WarehouseDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class SetWarehousePlantUI extends AbstractUI {

    private final SetWarehousePlantController controller = new SetWarehousePlantController();

    @Override
    public boolean doShow() {

        try {
            final List<String> warehouses = this.controller.warehouses();
            final SelectWidget<String> selector = new SelectWidget<>("Please select the warehouse you wish to set up the plant: ", warehouses);
            selector.show();
            final String selectedWarehouse = selector.selectedElement();
            WarehouseDTO warehouseDTO = this.controller.findWarehouseByName(selectedWarehouse);
            final String path = Console.readLine("Please type the path of the file you wish to import ");

            try {
                WarehouseDTO wDtO = this.controller.importFile(warehouseDTO, path);
                System.out.println("\n----------- Warehouse -----------" + "\n");
                System.out.println(wDtO.showInformation() + "\n");

                System.out.println("---------- Aisles ----------");
                for (Object aisle : this.controller.showAislesInformation()) {
                    System.out.println(aisle.toString());
                }

                System.out.println();
                System.out.println("----------- Rows -----------");
                for (Object row : this.controller.showRowsInformation()) {
                    System.out.println(row.toString());
                }

                System.out.println();
                System.out.println("--------- AGV Docks ---------");
                for (Object agvDock : this.controller.showAgvDocksInformation()) {
                    System.out.println(agvDock.toString());
                }
                System.out.println();

            } catch (IOException | ParseException e) {
                System.out.println("Something went wrong when importing the file!");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Set up a Warehouse Plant (by uploading a JSON file)";
    }
}
