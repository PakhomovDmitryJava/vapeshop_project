package mapper;

import dto.CreateUserDto;
import entity.User;
import mapper.Mapper;
import util.LocalDateFormatter;

import java.time.LocalDateTime;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    public static final CreateUserMapper INSTANCE = new CreateUserMapper();


    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .dateOfBirth(LocalDateFormatter.format(object.getDateOfBirth()))
                .address(object.getAddress())
                .email(object.getEmail())
                .mobilePhone(object.getMobilePhone())
                .password(object.getPassword())
                .role()
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}