package dto;

import entity.LiquidBase;
import entity.LiquidLine;
import entity.LiquidTaste;
import entity.Manufacturer;
import entity.NicConc;
import entity.NicType;
import entity.OriginCountry;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class LiquidDto {
    Long id;
    Manufacturer manufacturer; //1
    LiquidLine liquidLine;      //2
    LiquidTaste liquidTaste;    //3
    String description;     //4
    NicType nicType;   //5
    NicConc nicConc;  //6
    LiquidBase liquidBase;      //7
    OriginCountry originCountry;    //8
    BigDecimal price;   //9
    Long stock;     //10
}
