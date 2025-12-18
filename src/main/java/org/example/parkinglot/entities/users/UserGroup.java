package org.example.parkinglot.entities.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usergroups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "usergroup", nullable = false, length = 20)
    private String userGroup;

    public UserGroup() {
    }

    public UserGroup(String username, String userGroup) {
        this.username = username;
        this.userGroup = userGroup;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getUserGroup() { return userGroup; }
    public void setUserGroup(String userGroup) { this.userGroup = userGroup; }
}