package com.civicbridge.entity;

import com.civicbridge.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="complaint_status_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintStatusHistory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ComplaintStatus status;

    @Column(length=500)
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="complaint_id")
    private Complaint complaint;

}