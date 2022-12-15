package entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LiquidTaste {
    private Long id;
    private String liquidTaste;
}
