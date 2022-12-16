package servlet;

import dto.CreateUserDto;
import dto.RoleDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoleService;
import service.UserService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final RoleService roleService = RoleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.findAll().stream().map(RoleDto::getRole).collect(Collectors.toList()));

        req.getRequestDispatcher(JspHelper.getPath("/registration"))
                .forward(req, resp);
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

//        try {
//            userService.create(createUserDto);
//            resp.sendRedirect("/login");
//
//        } catch (ValidationException exception) {
//            req.setAttribute("errors", exception.getErrors());
//            doGet(req, resp);
//        }
    }
}