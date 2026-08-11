package com.civicbridge.entity;

import com.civicbridge.enums.ComplaintStatus;
import com.civicbridge.enums.Priority;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 200)
    private String title;

    @Column(nullable = false,length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ComplaintStatus status = ComplaintStatus.NEW;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Priority priority = Priority.MEDIUM;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officer_id")
    private Officer officer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder.Default
    @OneToMany(mappedBy = "complaint",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ComplaintImage> images = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "complaint",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ComplaintStatusHistory> statusHistory = new ArrayList<>();

    @OneToOne(mappedBy = "complaint",
            cascade = CascadeType.ALL)
    private Feedback feedback;
}