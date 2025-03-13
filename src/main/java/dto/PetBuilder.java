package dto;

import java.time.LocalDateTime;

public class PetBuilder {
    private Integer id = 1;
    private Integer petId = 1001;
    private Integer quantity = 1;
    private LocalDateTime orderDate = LocalDateTime.now();
    private String status = "available";
    private Boolean complete = false;

    public PetBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public PetBuilder withPetId(Integer petId) {
        this.petId = petId;
        return this;
    }

    public PetBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public PetBuilder withOrderDate(LocalDateTime  orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public PetBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public PetBuilder withComplete(Boolean complete) {
        this.complete = complete;
        return this;
    }

    public Pet build() {
        return Pet.builder()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .orderDate(orderDate)
                .status(status)
                .complete(complete)
                .build();
    }
}