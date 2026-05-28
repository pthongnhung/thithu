package project.thi_thu_java_web.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.thi_thu_java_web.dto.MedicationRequestDTO;
import project.thi_thu_java_web.dto.MedicationResponseDTO;

public interface MedicationService {
    MedicationResponseDTO create(MedicationRequestDTO requestDTO);

    Page<MedicationResponseDTO> getAll(String keyword, Pageable pageable);

    MedicationResponseDTO updatePut(Long id, MedicationRequestDTO requestDTO);

    MedicationResponseDTO updatePatch(Long id, MedicationRequestDTO requestDTO);

    void delete(Long id);
}
