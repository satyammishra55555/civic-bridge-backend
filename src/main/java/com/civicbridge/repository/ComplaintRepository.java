package com.civicbridge.repository;

import com.civicbridge.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint,Long>{

}