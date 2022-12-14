package entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NicConc {
    private Long id;
    private String nicConcentration;
}
