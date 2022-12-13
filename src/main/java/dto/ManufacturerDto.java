package dto;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class ManufacturerDto {
    Long id;
    String manufacturer;
}
