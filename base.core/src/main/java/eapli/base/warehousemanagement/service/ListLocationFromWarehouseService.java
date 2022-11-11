package eapli.base.warehousemanagement.service;

import eapli.base.infrastructure.persistence.PersistenceContext;

import eapli.base.warehousemanagement.domain.Row.AisleRow;
import eapli.base.warehousemanagement.dto.RowDTO;
import eapli.base.warehousemanagement.dto.RowMapper;
import eapli.base.warehousemanagement.repository.RowRepository;

import eapli.framework.domain.repositories.TransactionalContext;

import java.util.ArrayList;
import java.util.List;


public class ListLocationFromWarehouseService {
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final RowRepository repo = PersistenceContext.repositories().rows(txCtx);
    private List<AisleRow> listAisleRow = new ArrayList<>();
    private List<AisleRow> listAisleRow1 = new ArrayList<>();


    public List<RowDTO> listRow(){
        listAisleRow = repo.getAllRow();
        RowMapper map = new RowMapper();
        return map.toDTO(listAisleRow);
    }

    public List<AisleRow> getRowList(){
        return listAisleRow;
    }

    public AisleRow getRealRow(RowDTO row){
        for (AisleRow r: repo.getAllRow()
        ) {
            if(Long.valueOf(r.getRowId().toString()) == row.getRowId()){
                return r;
            }
        }
        return  null;
    }

}
