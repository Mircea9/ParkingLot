package org.example.parkinglot.common;

import org.example.parkinglot.entities.Car;

import java.util.List;

public class UserDto {
    Long id;
    String username;
    String password;
    String email;
    List<Car> cars;

    public UserDto(Long id, String username, String email, String password, List<Car> cars) {
        this.cars = cars;
        this.email = email;
        this.id = id;
        this.password = password;
        this.username = username;
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
