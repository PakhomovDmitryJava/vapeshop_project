package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
public class Order {
    private Long id;
    private User user; //1
    private LocalDateTime orderDate; //2
    private boolean isPaid; //3
    private LocalDateTime paymentDate; //4
}
