package com.capgemini.complaintsmanagementsystem.controller;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/complaints")
public class ComplaintController {

    ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }


    @GetMapping
    public ResponseEntity<List<Complaint>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(complaintService.getAllComplaint());

    }

    @GetMapping("/{complaintId}")
     public ResponseEntity<Complaint> getById(@PathVariable Long complaintId){
        return ResponseEntity.status(HttpStatus.OK).body(complaintService.getComplaintById(complaintId));
    }

    @PostMapping
    public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint){
        return ResponseEntity.status(HttpStatus.CREATED).body(complaintService.addComplaint(complaint));
    }

    @PutMapping("/{complaintId}")
    public ResponseEntity<Complaint> uodateComplaint(@RequestBody Complaint complaint, @PathVariable Long complaintId){
        return ResponseEntity.status(HttpStatus.CREATED).body(complaintService.updateComplaint(complaint,complaintId));
    }

    @DeleteMapping("/{complaintId}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable Long complaintId){
        complaintService.deleteComplaint(complaintId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
