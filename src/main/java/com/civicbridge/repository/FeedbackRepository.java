package com.civicbridge.repository;

import com.civicbridge.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long>{

}