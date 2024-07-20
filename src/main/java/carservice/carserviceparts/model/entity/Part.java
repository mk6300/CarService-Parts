package carservice.carserviceparts.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@Entity
@Table(name = "parts")
public class Part {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotEmpty
        private String name;

        @NotEmpty
        private double price;

        @NotEmpty
        private UUID supplierId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(UUID supplierId) {
        this.supplierId = supplierId;
    }
}
