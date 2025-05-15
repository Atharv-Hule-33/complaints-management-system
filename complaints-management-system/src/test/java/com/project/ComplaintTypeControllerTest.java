package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capgemini.complaintsmanagementsystem.controller.ComplaintTypeController;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintType;
import com.capgemini.complaintsmanagementsystem.service.ComplaintTypeService;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ComplaintTypeControllerTest {

	@Mock
	private ComplaintTypeService complaintTypeService;

	@InjectMocks
	private ComplaintTypeController complaintTypeController;

	private ComplaintType complaintType1;
	private ComplaintType complaintType2;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		complaintType1 = new ComplaintType();
		complaintType1.setComplaintTypeId(1L);
		complaintType1.setComplaintType("Doctor Not Available");
		complaintType1.setComplaintSeverity(ComplaintSeverity.HIGH);

		complaintType2 = new ComplaintType();
		complaintType2.setComplaintTypeId(2L);
		complaintType2.setComplaintType("Lack of Cleanliness");
		complaintType2.setComplaintSeverity(ComplaintSeverity.MEDIUM);
	}

	@Test
	public void getAllComplaintTypesTest() {
		List<ComplaintType> expectedTypes = Arrays.asList(complaintType1, complaintType2);
		when(complaintTypeService.getAllComplaintTypes()).thenReturn(expectedTypes);

		ResponseEntity<List<ComplaintType>> response = complaintTypeController.getAllComplaintTypes();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, response.getBody().size());
		verify(complaintTypeService, times(1)).getAllComplaintTypes();
	}

	@Test
	public void getComplaintTypeByIdTest() {
		when(complaintTypeService.getComplaintTypeById(1L)).thenReturn(complaintType1);

		ResponseEntity<ComplaintType> response = complaintTypeController.getComplaintTypeById(2L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Doctor Not Available", response.getBody().getComplaintType());
		verify(complaintTypeService, times(1)).getComplaintTypeById(1L);
	}

	@Test
	public void createComplaintTypeTest() {
		when(complaintTypeService.createComplaintType(complaintType1)).thenReturn(complaintType1);

		ResponseEntity<ComplaintType> response = complaintTypeController.createComplaintType(complaintType1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1L, response.getBody().getComplaintTypeId());
		verify(complaintTypeService, times(1)).createComplaintType(complaintType1);
	}

	@Test
	public void updateComplaintTypeTest() {
		ComplaintType updatedDetails = new ComplaintType();
		updatedDetails.setComplaintType("Updated Doctor Issue");
		updatedDetails.setComplaintSeverity(ComplaintSeverity.MEDIUM);
		ComplaintType expectedType = new ComplaintType();
		expectedType.setComplaintTypeId(1L);
		expectedType.setComplaintType("Updated Doctor Issue");
		expectedType.setComplaintSeverity(ComplaintSeverity.MEDIUM);

		when(complaintTypeService.updateComplaintType(1L, updatedDetails)).thenReturn(expectedType);

		ResponseEntity<ComplaintType> response = complaintTypeController.updateComplaintType(1L, updatedDetails);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Updated Doctor Issue", response.getBody().getComplaintType());
		assertEquals(ComplaintSeverity.MEDIUM, response.getBody().getComplaintSeverity());
		verify(complaintTypeService, times(1)).updateComplaintType(1L, updatedDetails);
	}

	@Test
	public void deleteComplaintTypeTest() {
		doNothing().when(complaintTypeService).deleteComplaintType(1L);
		verify(complaintTypeService, times(1)).deleteComplaintType(1L);
	}

	@Test
	public void getComplaintTypesBySeverityTest() {
		List<ComplaintType> expectedTypes = Arrays.asList(complaintType1);
		when(complaintTypeService.getComplaintTypesBySeverity(ComplaintSeverity.HIGH)).thenReturn(expectedTypes);

		ResponseEntity<List<ComplaintType>> response = complaintTypeController
				.getComplaintTypesBySeverity(ComplaintSeverity.HIGH);

		assertEquals(1, response.getBody().size());
		assertEquals("Doctor Not Available", response.getBody().get(0).getComplaintType());
		verify(complaintTypeService, times(1)).getComplaintTypesBySeverity(ComplaintSeverity.HIGH);
	}
}
