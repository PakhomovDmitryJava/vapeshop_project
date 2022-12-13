package dto;

import entity.User;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Data
public class OrderDto {
    Long id;
    User user;
    LocalDateTime orderDate;
    boolean isPaid;
    LocalDateTime paymentDate;
}
