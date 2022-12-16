package dto;

import entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UserDto {
    Long id;
    String firstName; //1
    String lastName; //2
    LocalDateTime dateOfBirth; //3
    String address; //4
    String email; //5
    String mobilePhone; //6
    String password; //7
    Role role; //8
}
