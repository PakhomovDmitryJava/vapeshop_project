package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class CreateUserDto {
    String firstName;
    String lastName;
    String dateOfBirth;
    String address;
    String email;
    String mobilePhone;
    String password;
    String role;
}
