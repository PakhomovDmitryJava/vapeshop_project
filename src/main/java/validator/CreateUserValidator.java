package validator;

import dto.CreateUserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.LocalDateFormatter;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {

    public static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto userDto) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(userDto.getDateOfBirth())) {
            validationResult.add(Error.of("invalid.birthday","Birthday is invalid!"));
        }
        return null;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
