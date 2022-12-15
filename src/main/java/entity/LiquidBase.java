package entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LiquidBase {
    private Long id;
    private String base;
}
