package dto;


import entity.LiquidBase;
import entity.LiquidLine;
import entity.LiquidTaste;
import entity.Manufacturer;
import entity.NicConc;
import entity.NicType;
import entity.OriginCountry;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CreateLiquidDto {
    String manufacturerId; //1
    String liquidLineId;      //2
    String liquidTasteId;    //3
    String description;     //4
    String nicTypeId;   //5
    String nicConcId;  //6
    String liquidBaseId;      //7
    String originCountryId;    //8
    String price;   //9
    String stock;     //10
}
