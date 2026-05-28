package project.thi_thu_java_web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.thi_thu_java_web.dto.MedicationRequestDTO;
import project.thi_thu_java_web.dto.MedicationResponseDTO;
import project.thi_thu_java_web.service.MedicationService;

@RestController
@RequestMapping("/api/v1/medications")
@RequiredArgsConstructor

public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping
    public ResponseEntity<MedicationResponseDTO> create(@Valid @RequestBody MedicationRequestDTO requestDTO) {
        return new ResponseEntity<>(medicationService.create(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MedicationResponseDTO>> getAll(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(medicationService.getAll(keyword, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationResponseDTO> updatePut(
            @PathVariable Long id,
            @Valid @RequestBody MedicationRequestDTO requestDTO) {
        return ResponseEntity.ok(medicationService.updatePut(id, requestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MedicationResponseDTO> updatePatch(
            @PathVariable Long id,
            @RequestBody MedicationRequestDTO requestDTO) {
        // Không dùng @Valid ép buộc để cho phép truyền thiếu trường
        return ResponseEntity.ok(medicationService.updatePatch(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        medicationService.delete(id);
        return ResponseEntity.ok("Xóa thông tin thuốc thành công (Soft Delete).");
    }

}
