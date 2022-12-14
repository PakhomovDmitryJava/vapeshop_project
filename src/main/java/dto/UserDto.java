package dto;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Data
public class UserDto {
    Long id;
    String firstName; //1
    String lastName; //2
    LocalDateTime dateOfBirth; //3
    String address; //4
    String email; //5
    String mobilePhone; //6
    String password; //7
    String role; //8
}
