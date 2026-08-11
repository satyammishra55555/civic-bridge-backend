package com.civicbridge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="complaint_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintImage extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String imageUrl;

    @Column(length=300)
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="complaint_id")
    private Complaint complaint;

}