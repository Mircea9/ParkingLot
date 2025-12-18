package org.example.parkinglot.entities.cars;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "car_photo")
public class CarPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "filename", nullable = false, length = 255)
    private String filename;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "file_type", nullable = false, length = 50)
    private String fileType;

    @NotNull
    @Column(name = "file_content", nullable = false)
    private byte[] fileContent;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public byte[] getFileContent() { return fileContent; }
    public void setFileContent(byte[] fileContent) { this.fileContent = fileContent; }
    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }
}