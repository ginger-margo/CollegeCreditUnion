package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Category;
import org.example.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Category createCategoryIfNotExists(String category) {
        Optional<Category> categoryOpt = categoryRepository.findByName(category);
        if (categoryOpt.isPresent()) {
            return categoryOpt.get();
        } else {
            return categoryRepository.save(new Category(UUID.randomUUID(), category, ""));
        }

    }
}
