package entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LiquidLine {
    private Long id;
    private String liquidLine;
}
