package org.example.parkinglot.servlets.users;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parkinglot.common.UserDto;
import org.example.parkinglot.ejb.UsersBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"}))
@WebServlet(name = "EditUser", value = "/EditUser")
public class EditUser extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> userGroups = Arrays.asList("READ_CARS", "WRITE_CARS", "READ_USERS", "WRITE_USERS", "INVOICE");
        request.setAttribute("userGroups", userGroups);

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            UserDto user = usersBean.findById(Long.parseLong(idStr));
            request.setAttribute("user", user);
        }

        request.getRequestDispatcher("/WEB-INF/pages/users/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("user_id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String[] userGroups = request.getParameterValues("user_groups");

        if (userGroups == null) {
            userGroups = new String[0];
        }

        if (userIdStr != null && !userIdStr.isEmpty()) {
            Long userId = Long.parseLong(userIdStr);
            usersBean.updateUser(userId, email, password, Arrays.asList(userGroups));
        }

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}