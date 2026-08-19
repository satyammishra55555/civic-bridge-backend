package com.civicbridge.service.impl;

import com.civicbridge.entity.Complaint;
import com.civicbridge.repository.ComplaintRepository;
import com.civicbridge.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    // =========================================
    // CREATE COMPLAINT
    // =========================================

    @Override
    public Complaint createComplaint(Complaint complaint) {

        return complaintRepository.save(complaint);
    }

    // =========================================
    // GET ALL COMPLAINTS
    // =========================================

    @Override
    public List<Complaint> getAllComplaints() {

        return complaintRepository.findAll();
    }

    // =========================================
    // GET COMPLAINT BY ID
    // =========================================

    @Override
    public Complaint getComplaintById(Long id) {

        return complaintRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Complaint not found with id: " + id
                        )
                );
    }

    // =========================================
    // UPDATE COMPLAINT
    // =========================================

    @Override
    public Complaint updateComplaint(
            Long id,
            Complaint complaint) {

        Complaint existingComplaint =
                complaintRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Complaint not found with id: " + id
                                )
                        );

        existingComplaint.setTitle(
                complaint.getTitle()
        );

        existingComplaint.setDescription(
                complaint.getDescription()
        );

        existingComplaint.setStatus(
                complaint.getStatus()
        );

        existingComplaint.setPriority(
                complaint.getPriority()
        );

        existingComplaint.setCitizen(
                complaint.getCitizen()
        );

        existingComplaint.setOfficer(
                complaint.getOfficer()
        );

        existingComplaint.setCategory(
                complaint.getCategory()
        );

        return complaintRepository.save(
                existingComplaint
        );
    }

    // =========================================
    // DELETE COMPLAINT
    // =========================================

    @Override
    public void deleteComplaint(Long id) {

        Complaint existingComplaint =
                complaintRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Complaint not found with id: " + id
                                )
                        );

        complaintRepository.delete(
                existingComplaint
        );
    }
}