package mapper;

import dao.RoleDao;
import dto.CreateUserDto;
import entity.Role;
import entity.User;
import util.LocalDateFormatter;

import java.util.Optional;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    public static final CreateUserMapper INSTANCE = new CreateUserMapper();

    public static final RoleDao roleDao = RoleDao.getInstance();


    @Override
    public User mapFrom(CreateUserDto object) {
        String roleName = null;
        Optional<Role> optionalRole = roleDao.findById(Long.valueOf(object.getRoleId()));
        if (optionalRole.isPresent()) {
            roleName = optionalRole.get().getRoleName();
        }
        return User.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .dateOfBirth(LocalDateFormatter.format(object.getDateOfBirth()))
                .address(object.getAddress())
                .email(object.getEmail())
                .mobilePhone(object.getMobilePhone())
                .password(object.getPassword())
                .role(Role.builder()
                        .id(Long.valueOf(object.getRoleId()))
                        .roleName(roleName)
                        .build())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}