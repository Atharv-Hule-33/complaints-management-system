package com.project;

import com.capgemini.complaintsmanagementsystem.ComplaintsManagementSystemApplication;
import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.service.ComplaintService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ComplaintsManagementSystemApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ComplaintServiceIntegrationTest {
    ComplaintService complaintService;

    @Autowired
    public ComplaintServiceIntegrationTest(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @Test
    @DisplayName("Should save and retrieve an Employee")
    void testSaveAndRetrieveComplaint() {
        Complaint complaint = new Complaint(1L, "cleaning");
        Complaint savedComplaint = complaintService.addComplaint(complaint);

        Optional<Complaint> retrievedComplaint = Optional.ofNullable(complaintService.getComplaintById(savedComplaint.getComplaintId()));
        assertTrue(retrievedComplaint.isPresent());
        assertEquals("cleaning", retrievedComplaint.get().getComplaintDescription());
    }
}
