package dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class ManufacturerDto {
    Long id;
    String manufacturer;
}
