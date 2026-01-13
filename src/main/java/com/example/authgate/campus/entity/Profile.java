package com.example.authgate.campus.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String nim;

    @Column(length = 100)
    private String jurusan;

    private Integer angkatan;

    @Column(length = 255)
    private String alamat;

    @Column(length = 255)
    private String photoUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
