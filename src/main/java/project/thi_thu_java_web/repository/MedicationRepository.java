package project.thi_thu_java_web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.thi_thu_java_web.entity.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    // Lấy danh sách thuốc chưa xóa + Tìm kiếm theo tên hoặc nhà sản xuất + Phân trang
    @Query("SELECT m FROM Medication m WHERE m.isDeleted = false AND " +
            "(:keyword IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.manufacturer) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Medication> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // Tìm kiếm một thuốc cụ thể chưa bị xóa bằng ID
    @Query("SELECT m FROM Medication m WHERE m.id = :id AND m.isDeleted = false")
    java.util.Optional<Medication> findActiveById(@Param("id") Long id);
}
