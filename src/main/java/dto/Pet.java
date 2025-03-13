package dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Pet {
    private Integer id;
    private Integer petId;
    private Integer quantity;
    private Date orderDate;
    private String status;
    private Boolean complete;
}