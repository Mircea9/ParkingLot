package org.example.parkinglot.common;

import org.example.parkinglot.entities.cars.Car;
import java.util.List;

public class UserDto {
    Long id;
    String username;
    String password;
    String email;
    List<Car> cars;
    List<String> groups;

    public UserDto(Long id, String username, String email, String password, List<Car> cars, List<String> groups) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cars = cars;
        this.groups = groups;
    }

    public List<String> getGroups() {
        return groups;
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}