package entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Liquid {
    private Long id;
    private Manufacturer manufacturer;
    private LiquidLine liquidLine;
    private LiquidTaste liquidTaste;
    private String description;
    private NicType nicType;
    private NicConc nicConc;
    private LiquidBase liquidBase;
    private OriginCountry originCountry;
    private BigDecimal price;
    private Long stock;

}
