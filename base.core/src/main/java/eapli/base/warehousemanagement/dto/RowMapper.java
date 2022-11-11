package eapli.base.warehousemanagement.dto;

import eapli.base.warehousemanagement.domain.Row.AisleRow;


import java.util.ArrayList;
import java.util.List;

public class RowMapper {

    public List<RowDTO> toDTO(List<AisleRow> aisleRowList){
        List<RowDTO> rowDTOList = new ArrayList<>();

        for(AisleRow r: aisleRowList) {
            RowDTO row = new RowDTO(r);
           rowDTOList.add(row);
        }
        return rowDTOList;
    }
}
