package dto;

import entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class OrderDto {
    Long id;
    User user;
    LocalDateTime orderDate;
    boolean isPaid;
    LocalDateTime paymentDate;
}
