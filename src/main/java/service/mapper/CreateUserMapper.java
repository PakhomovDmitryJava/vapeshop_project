package service.mapper;

import dto.CreateUserDto;
import entity.User;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    public static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .firstName(object.getAddress())
                .lastName(object.getAddress())
                .dateOfBirth(object.getAddress())
                .address(object.getAddress())
                .email(object.getAddress())
                .mobilePhone(object.getAddress())
                .password(object.getAddress())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
