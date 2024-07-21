package carservice.carserviceparts.model.dto;

import java.util.UUID;

public class AddPartDTO {
    private String name;
    private Double price;

    private UUID supplierId;

    public String getName() {
        return name;
    }

    public AddPartDTO() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
