package entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Liquid {
    private Long id;
    private Manufacturer manufacturer; //1
    private LiquidLine liquidLine;      //2
    private FlavourType flavourType;    //3
    private String description;     //4
    private NicType nicType;   //5
    private NicConc nicConc;  //6
    private Base base;      //7
    private OriginCountry originCountry;    //8
    private BigDecimal price;   //9
    private Long stock;     //10

}
