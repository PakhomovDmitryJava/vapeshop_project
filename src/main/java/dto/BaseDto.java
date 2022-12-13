package dto;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class BaseDto {
    Long id;
    String prVgRatio;
}
