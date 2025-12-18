package org.example.parkinglot.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.parkinglot.common.UserDto;
import org.example.parkinglot.entities.users.User;
import org.example.parkinglot.entities.users.UserGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @Inject
    PasswordBean passwordBean;

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers");
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    Collections.emptyList(),
                    Collections.emptyList()
            );
            dtos.add(userDto);
        }
        return dtos;
    }

    public void createUser(String username, String email, String password, Collection<String> groups) {
        LOG.info("createUser");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));
        entityManager.persist(newUser);
        assignGroupsToUser(username, groups);
    }

    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }

    public Collection<String> findUsernamesByUserIds(Collection<Long> userIds) {
        return entityManager.createQuery("SELECT u.username FROM User u WHERE u.id IN :userIds", String.class)
                .setParameter("userIds", userIds)
                .getResultList();
    }

    public UserDto findById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            return null;
        }

        List<String> userGroups = entityManager.createQuery("SELECT ug.userGroup FROM UserGroup ug WHERE ug.username = :username", String.class)
                .setParameter("username", user.getUsername())
                .getResultList();

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getCars(),
                userGroups
        );
    }

    public void updateUser(Long userId, String email, String password, Collection<String> groups) {
        LOG.info("updateUser");
        User user = entityManager.find(User.class, userId);

        if (user != null) {
            user.setEmail(email);
            if (password != null && !password.isEmpty()) {
                user.setPassword(passwordBean.convertToSha256(password));
            }

            entityManager.createQuery("DELETE FROM UserGroup ug WHERE ug.username = :username")
                    .setParameter("username", user.getUsername())
                    .executeUpdate();

            assignGroupsToUser(user.getUsername(), groups);
        }
    }
}