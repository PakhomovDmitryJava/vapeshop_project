package entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Base {
    private Long id;
    private String prVgRatio;
}
