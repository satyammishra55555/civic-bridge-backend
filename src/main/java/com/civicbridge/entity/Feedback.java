package com.civicbridge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    @Column(length=1000)
    private String comments;

    @OneToOne
    @JoinColumn(name="complaint_id")
    private Complaint complaint;

}