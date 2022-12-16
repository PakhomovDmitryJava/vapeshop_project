package dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class NicConcDto {
    Long id;
    String nicConcentration;
}
