package eapli.base.warehousemanagement.domain.Warehouse;

import eapli.framework.domain.model.ValueObject;
import javax.persistence.Embeddable;

@Embeddable
public class WarehousePlant implements ValueObject {

    private String description;
    private long length;
    private long width;
    private long square;
    private String unit;

    public WarehousePlant() {
    }

    public WarehousePlant(String description, long length, long width, long square, String unit) {
        this.description = description;
        this.length = length;
        this.width = width;
        this.square = square;
        this.unit = unit;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getSquare() {
        return square;
    }

    public void setSquare(long square) {
        this.square = square;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "description: " + description +
                ", length: " + length +
                ", width: " + width +
                ", square: " + square +
                ", unit: " + unit;
    }
}
