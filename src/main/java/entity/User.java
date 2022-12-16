package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName; //1
    private String lastName; //2
    private LocalDate dateOfBirth; //3
    private String address; //4
    private String email; //5
    private String mobilePhone; //6
    private String password; //7
    private Role role; //8;
}
