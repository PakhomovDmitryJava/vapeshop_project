package dto;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class OriginCountryDto {
    Long id;
    String country;
}
