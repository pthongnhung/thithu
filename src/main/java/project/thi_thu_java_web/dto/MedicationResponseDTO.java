package project.thi_thu_java_web.dto;

import lombok.Data;
import project.thi_thu_java_web.entity.MedicationStatus;

@Data
public class MedicationResponseDTO {
    private Long id;
    private String name;
    private String manufacturer;
    private Double price;
    private MedicationStatus status;
}