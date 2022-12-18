package mapper;

import dto.CreateLiquidDto;
import entity.Liquid;

public class CreateLiquidMapper implements Mapper<CreateLiquidDto, Liquid> {

    public static final CreateLiquidMapper INSTANCE = new CreateLiquidMapper();

    @Override
    public Liquid mapFrom(CreateLiquidDto object) {
        return Liquid.builder()
                .manufacturer()
                .liquidLine()
                .liquidTaste()
                .description()
                .nicType()
                .nicConc()
                .liquidBase()
                .originCountry()
                .price()
                .stock()
                .build();
    }

    public static CreateLiquidMapper    getInstance() {
        return INSTANCE;
    }
}
