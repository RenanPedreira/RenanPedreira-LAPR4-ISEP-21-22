package eapli.base.warehousemanagement.dto;

import eapli.base.warehousemanagement.domain.Row.AisleRow;

public class RowDTO {
    private long rowId;

    private String begin;

    private String end;

    private long shelveQuantity;

    private String aisle;

    public RowDTO(AisleRow r) {
        this.rowId = Long.valueOf(r.getRowId().toString());
        this.shelveQuantity = Long.valueOf(r.getShelveQuantity().toString());
        this.aisle = r.getAisleFromRow().toString();
        this.end = r.getEnd().toString();
        this.begin = r.getBegin().toString();
    }

    public Long getRowId() {
        return rowId;
    }

    public Long getShelveQuantity() {
        return shelveQuantity;
    }

    @Override
    public String toString() {
        return "RowDTO{" +
                "rowId=" + rowId +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                ", shelveQuantity=" + shelveQuantity +
                ", aisle='" + aisle + '\'' +
                '}';
    }
}
