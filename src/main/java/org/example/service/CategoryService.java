package org.example.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.model.Category;
import org.example.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service // spring service for dependency injection
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;


    @Transactional //database operations completed fully or rolled back if error
    public Category createCategoryIfNotExists(String categoryName) {
        Optional<Category> categoryOpt = categoryRepository.findByNameForUpdate(categoryName);
        if (categoryOpt.isPresent()) {
            return entityManager.merge(categoryOpt.get());
        }

        Category newCategory = new Category(null, categoryName, "");
        return categoryRepository.save(newCategory);
    }
}
