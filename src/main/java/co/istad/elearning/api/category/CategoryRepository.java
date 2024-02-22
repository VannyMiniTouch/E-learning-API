package co.istad.elearning.api.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository
        extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoriesById(Long id);
    Category findById(Long id);
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Category c SET c.isDeleted = true WHERE c.id = :id")
    void disableCategoryById(Long id);

    // Using JPQL
    //@Query(value = "SELECT * FROM categories", nativeQuery = true)
    @Query("SELECT cate FROM Category AS cate")
    List<Category> selectAllCategories();

    @Query("SELECT cate.name FROM Category AS cate")
    List<String> selectCategoryNames();

    @Query("SELECT cate FROM Category AS cate WHERE cate.name = ?1 AND cate.isDeleted = ?2")
    List<Category> selectCategoryByNameAndIsDeleted(String name,
                                                    Boolean isDeleted);

    @Query("SELECT cate FROM Category AS cate WHERE cate.name = :name")
    List<Category> selectCategoryByName(String name);

    @Transactional // Should use in service layer
    @Modifying
    @Query("""
        UPDATE Category AS cate
        SET cate.name = :newName
        WHERE cate.id = :id
    """)
    void updateCategoryName(Integer id, String newName);
    @Modifying
    @Query("""
        DELETE
        FROM Category AS cate
        WHERE cate.id = :id
    """)
    void removeById(Integer id);

}
