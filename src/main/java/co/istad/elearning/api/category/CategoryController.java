package co.istad.elearning.api.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDto> listAllGategory() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.createCategory(categoryRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryService.updateCategory(id, categoryRequestDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}/disable")
    public void disableCategory(@PathVariable Long id) {
        categoryService.disableCategory(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
