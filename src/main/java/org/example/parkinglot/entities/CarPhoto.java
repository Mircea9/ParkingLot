package org.example.parkinglot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "car_photo")
public class CarPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    String filename;
    String fileType;
    byte[] fileContent;
    Car car;

    @OneToOne
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}