package com.capgemini.complaintsmanagementsystem.service;

import com.capgemini.complaintsmanagementsystem.Dto.UserDashboardDto;
import com.capgemini.complaintsmanagementsystem.Dto.RecentComplaintDto;
import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.entity.User;
import com.capgemini.complaintsmanagementsystem.exception.UserNotFoundException;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintRepository;
import com.capgemini.complaintsmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDashboardServiceImpl implements UserDashboardService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserDashboardServiceImpl(ComplaintRepository complaintRepository, UserRepository userRepository) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDashboardDto getUserDashboardData(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserDashboardDto dto = new UserDashboardDto();
        dto.setTotalComplaints(complaintRepository.countByUser(user));
        dto.setResolvedComplaints(complaintRepository.countByUserAndComplaintStatus(user, "RESOLVED"));
        dto.setInProgressComplaints(complaintRepository.countByUserAndComplaintStatus(user, "IN_PROGRESS"));
        dto.setFiledComplaints(complaintRepository.countByUserAndComplaintStatus(user, "FILED"));

        List<Complaint> recentComplaints = complaintRepository.findTop5ByUserOrderByComplaintFiledDateDesc(user);
        List<RecentComplaintDto> recentDtos = recentComplaints.stream().map(c -> {
            RecentComplaintDto rc = new RecentComplaintDto();
            rc.setId(c.getComplaintId());
            rc.setDepartment(c.getDepartment().getDepartmentName());
            rc.setType(c.getComplaintType().getComplaintType());
            rc.setFiledOn(c.getComplaintFiledDate());
            rc.setStatus(c.getComplaintStatus());
            return rc;
        }).collect(Collectors.toList());
        dto.setRecentComplaints(recentDtos);

        return dto;
    }
}
