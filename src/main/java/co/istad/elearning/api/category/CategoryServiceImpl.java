package co.istad.elearning.api.category;


import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryMapper.toListCategoryResDto(categoryRepository.findAll());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id);
        if (Objects.isNull(category)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Category id not found");
        }
        return categoryMapper.toCategoryResDto(category);
    }

    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) {
        try {
            Category category = categoryMapper.fromCategoryReqDto(categoryRequestDto);
            categoryRepository.save(category);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Something when wrong");
        }
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        if ( this.isEmptyString(categoryRequestDto.name()) || this.isEmptyBoolean(categoryRequestDto.isDeleted())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Please input require field");
        }
        Category category = categoryRepository.findById(id);
        if (Objects.isNull(category)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Category id not found");
        }
        category = categoryMapper.fromIdAndCategoryReqDto(id, categoryRequestDto);
        return categoryMapper.toCategoryResDto(categoryRepository.save(category));
    }

    @Transactional
    @Override
    public void disableCategory(Long id) {
        if (Objects.isNull(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "require id");
        }
        categoryRepository.disableCategoryById(id);
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        if (Objects.isNull(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "require id");
        }
        categoryRepository.deleteById(id);
    }

    public Boolean isEmptyString(String val) {
        if (Objects.isNull(val)) {
            return true;
        }
        return false;
    }

    public Boolean isEmptyBoolean(Boolean val) {
        if (Objects.isNull(val)) {
            return true;
        }
        return false;
    }


}
