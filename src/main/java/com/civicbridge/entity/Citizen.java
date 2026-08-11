package com.civicbridge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "citizens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Citizen extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(length = 255)
    private String address;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String state;

    @Column(length = 10)
    private String pincode;

    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Complaint> complaints = new ArrayList<>();
}