package project.thi_thu_java_web.dto;


import jakarta.validation.constraints.*;
import lombok.Data;
import project.thi_thu_java_web.entity.MedicationStatus;

@Data
public class MedicationRequestDTO {

    @NotBlank(message = "Tên thuốc không được để trống")
    private String name;

    @NotBlank(message = "Nhà sản xuất không được để trống")
    private String manufacturer;

    @NotNull(message = "Giá bán không được để trống")
    @DecimalMin(value = "0.01", message = "Giá bán phải lớn hơn 0")
    private Double price;

    private MedicationStatus status = MedicationStatus.AVAILABLE;
}