package com.civicbridge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "officers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Officer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(nullable = false, length = 100)
    private String designation;

    @Builder.Default
    @OneToMany(mappedBy = "officer")
    private List<Complaint> complaints = new ArrayList<>();
}