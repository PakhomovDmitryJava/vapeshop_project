package servlet;

import DAO.RoleDao;
import dto.CreateUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final RoleDao roleDao = RoleDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles",roleDao.findAll());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var createUserDto = CreateUserDto.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .dateOfBirth(req.getParameter("dateOfBirth"))
                .address(req.getParameter("address"))
                .email(req.getParameter("email"))
                .mobilePhone(req.getParameter("mobilePhone"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .build();

        userService.create(createUserDto);
        resp.sendRedirect("/login");
    }
}
