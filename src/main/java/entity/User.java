package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName; //1
    private String lastName; //2
    private LocalDateTime dateOfBirth; //3
    private String address; //4
    private String email; //5
    private String mobilePhone; //6
    private String password; //7
    private String role; //8;
}
