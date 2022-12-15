package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.LocalDateFormatter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    Long id;
    String role;
}
