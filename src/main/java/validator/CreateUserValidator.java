package validator;

import dto.CreateUserDto;
import util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {

    public static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getDateOfBirth())) {
            validationResult.add(Error.of("invalid.birthday","Birthday is invalid!"));
        }
        return null;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
