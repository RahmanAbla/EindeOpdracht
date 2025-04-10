package com.example.eindeopdrachtvanrahman.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private  String nameOfImage;
    @Column
    private String typeOfImage;
    @Lob
    private byte[]imageData;
    @OneToOne(mappedBy ="imageData",cascade = CascadeType.ALL)
    private CarMechanic carMechanic;

}
