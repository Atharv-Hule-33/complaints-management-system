package com.project;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.complaintsmanagementsystem.ComplaintsManagementSystemApplication;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintType;
import com.capgemini.complaintsmanagementsystem.service.ComplaintTypeService;

@SpringBootTest(classes = ComplaintsManagementSystemApplication.class)
class ComplaintTypeServiceIntegrationTest {

	@Autowired
	private ComplaintTypeService complaintTypeService;

	@Test
	@DisplayName("save and retrieve all complaint types")
	void testGetAllComplaintTypes() {
		ComplaintType type1 = new ComplaintType("Doctor Not Available", ComplaintSeverity.HIGH);
		ComplaintType type2 = new ComplaintType("Lack of Cleanliness", ComplaintSeverity.MEDIUM);
		complaintTypeService.createComplaintType(type1);
		complaintTypeService.createComplaintType(type2);

		List<ComplaintType> types = complaintTypeService.getAllComplaintTypes();

		assertEquals(2, types.size());
	}

	@Test
	@DisplayName("get complaint type by ID")
	void testGetComplaintTypeById() {
		ComplaintType type1 = complaintTypeService
				.createComplaintType(new ComplaintType("Doctor Not Available", ComplaintSeverity.HIGH));

		ComplaintType foundType = complaintTypeService.getComplaintTypeById(type1.getComplaintTypeId());

		assertEquals(type1.getComplaintTypeId(), foundType.getComplaintTypeId());
		assertEquals("Doctor Not Available", foundType.getComplaintType());
		assertEquals(ComplaintSeverity.HIGH, foundType.getComplaintSeverity());
	}

	@Test
	@DisplayName("create a new complaint type")
	void testCreateComplaintType() {
		ComplaintType newType = new ComplaintType("Lack of Cleanliness", ComplaintSeverity.MEDIUM);

		ComplaintType savedType = complaintTypeService.createComplaintType(newType);

		assertNotNull(savedType.getComplaintTypeId());
		assertEquals("Lack of Cleanliness", savedType.getComplaintType());
		assertEquals(ComplaintSeverity.MEDIUM, savedType.getComplaintSeverity());
	}

	@Test
	@DisplayName("update an existing complaint type")
	void testUpdateComplaintType() {
		ComplaintType original = complaintTypeService
				.createComplaintType(new ComplaintType("Old Issue", ComplaintSeverity.LOW));

		ComplaintType updatedDetails = new ComplaintType("Updated Issue", ComplaintSeverity.HIGH);
		ComplaintType updatedType = complaintTypeService.updateComplaintType(original.getComplaintTypeId(),
				updatedDetails);

		assertEquals(original.getComplaintTypeId(), updatedType.getComplaintTypeId());
		assertEquals("Updated Issue", updatedType.getComplaintType());
		assertEquals(ComplaintSeverity.HIGH, updatedType.getComplaintSeverity());
	}
	
	@Test
    @DisplayName("Should delete a complaint type")
    void testDeleteComplaintType() {

        complaintTypeService.deleteComplaintType(2L);

        assertThrows(Exception.class, () -> {
            complaintTypeService.getComplaintTypeById(2L);
        });
    }
	
	@Test
    @DisplayName("Should filter complaint types by severity")
    void testGetComplaintTypesBySeverity() {
        complaintTypeService.createComplaintType(new ComplaintType("Critical", ComplaintSeverity.HIGH));
        complaintTypeService.createComplaintType(new ComplaintType("Moderate", ComplaintSeverity.MEDIUM));
        complaintTypeService.createComplaintType(new ComplaintType("Minor", ComplaintSeverity.LOW));
        complaintTypeService.createComplaintType(new ComplaintType("Another Critical", ComplaintSeverity.HIGH));

        List<ComplaintType> highSeverityTypes = complaintTypeService.getComplaintTypesBySeverity(ComplaintSeverity.HIGH);

        assertEquals(2, highSeverityTypes.size());
        assertTrue(highSeverityTypes.stream().allMatch(t -> t.getComplaintSeverity() == ComplaintSeverity.HIGH));
    }
}
