package dto;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class LiquidBaseDto {
    Long id;
    String base;
}
