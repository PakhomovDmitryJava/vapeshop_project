package service;

import dao.RoleDao;
import dto.RoleDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleService {

    public static final RoleService INSTANCE = new RoleService();

    private final RoleDao roleDao = RoleDao.getInstance();

   public List<RoleDto> findAll() {
       return roleDao.findAll().stream()
               .map(role -> RoleDto.builder()
                       .id(String.valueOf(role.getId()))
                       .role(role.getRole())
                       .build())
               .collect(Collectors.toList());
   }

    public static RoleService getInstance() {
        return INSTANCE;
    }
}
