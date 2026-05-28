package project.thi_thu_java_web.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.thi_thu_java_web.dto.MedicationRequestDTO;
import project.thi_thu_java_web.dto.MedicationResponseDTO;
import project.thi_thu_java_web.entity.Medication;
import project.thi_thu_java_web.exception.ResourceNotFoundException;
import project.thi_thu_java_web.repository.MedicationRepository;
import project.thi_thu_java_web.service.MedicationService;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    @Override
    @Transactional
    public MedicationResponseDTO create(MedicationRequestDTO requestDTO) {
        Medication medication = Medication.builder()
                .name(requestDTO.getName())
                .manufacturer(requestDTO.getManufacturer())
                .price(requestDTO.getPrice())
                .status(requestDTO.getStatus())
                .isDeleted(false)
                .build();

        Medication saved = medicationRepository.save(medication);
        return mapToResponse(saved);
    }

    @Override
    public Page<MedicationResponseDTO> getAll(String keyword, Pageable pageable) {
        return medicationRepository.findByKeyword(keyword, pageable).map(this::mapToResponse);
    }

    @Override
    @Transactional
    public MedicationResponseDTO updatePut(Long id, MedicationRequestDTO requestDTO) {
        Medication medication = medicationRepository.findActiveById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuốc với ID: " + id));


        medication.setName(requestDTO.getName());
        medication.setManufacturer(requestDTO.getManufacturer());
        medication.setPrice(requestDTO.getPrice());
        medication.setStatus(requestDTO.getStatus());

        return mapToResponse(medicationRepository.save(medication));
    }

    @Override
    @Transactional
    public MedicationResponseDTO updatePatch(Long id, MedicationRequestDTO requestDTO) {
        Medication medication = medicationRepository.findActiveById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuốc với ID: " + id));


        if (requestDTO.getName() != null && !requestDTO.getName().isBlank()) {
            medication.setName(requestDTO.getName());
        }
        if (requestDTO.getManufacturer() != null && !requestDTO.getManufacturer().isBlank()) {
            medication.setManufacturer(requestDTO.getManufacturer());
        }
        if (requestDTO.getPrice() != null) {
            if (requestDTO.getPrice() <= 0) throw new IllegalArgumentException("Giá bán phải lớn hơn 0");
            medication.setPrice(requestDTO.getPrice());
        }
        if (requestDTO.getStatus() != null) {
            medication.setStatus(requestDTO.getStatus());
        }

        return mapToResponse(medicationRepository.save(medication));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Medication medication = medicationRepository.findActiveById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuốc với ID: " + id));

        // Cơ chế Soft Delete (Xóa mềm)
        medication.setIsDeleted(true);
        medicationRepository.save(medication);
    }

    // Helper method map Entity -> DTO
    private MedicationResponseDTO mapToResponse(Medication medication) {
        MedicationResponseDTO response = new MedicationResponseDTO();
        response.setId(medication.getId());
        response.setName(medication.getName());
        response.setManufacturer(medication.getManufacturer());
        response.setPrice(medication.getPrice());
        response.setStatus(medication.getStatus());
        return response;
    }
}