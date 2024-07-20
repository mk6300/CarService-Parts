package carservice.carserviceparts.model.dto;

import java.util.UUID;

public class AddPartDTO {
    private String name;
    private double price;
    private UUID supplierId;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(UUID supplierId) {
        this.supplierId = supplierId;
    }
}
