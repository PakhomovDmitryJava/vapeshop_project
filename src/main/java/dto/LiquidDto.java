package dto;

import entity.Base;
import entity.LiquidLine;
import entity.LiquidTaste;
import entity.Manufacturer;
import entity.NicConcentration;
import entity.NicType;
import entity.OriginCountry;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Data
public class LiquidDto {
    Long id;
    Manufacturer manufacturer; //1
    LiquidLine liquidLine;      //2
    LiquidTaste liquidTaste;    //3
    String description;     //4
    NicType nicType;   //5
    NicConcentration nicConcentration;  //6
    Base base;      //7
    OriginCountry originCountry;    //8
    BigDecimal price;   //9
    Long stock;     //10
}
