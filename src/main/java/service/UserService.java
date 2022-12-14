package service;

import DAO.UserDao;
import dto.CreateUserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    public static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();

    public Long create(CreateUserDto createUserDto) {
        return null;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
