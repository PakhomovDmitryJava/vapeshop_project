package validator;

import dto.CreateUserDto;

public class CreateUserValidator implements Validator<CreateUserDto> {

    public static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        return null;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
