package entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FlavourType {
    private Long id;
    private String flavourType;
}
