package com.ramonsilva.desafioanotaai.services;

import com.ramonsilva.desafioanotaai.domain.category.Category;
import com.ramonsilva.desafioanotaai.domain.category.CategoryDTO;
import com.ramonsilva.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.ramonsilva.desafioanotaai.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category insert(CategoryDTO categoryDTO) {
        Category newCategory = new Category(categoryDTO);
        this.repository.save(newCategory);
        return newCategory;

    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Optional<Category> getById(String id){
        return this.repository.findById(id);
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if(!categoryDTO.title().isEmpty())
            category.setTitle(categoryDTO.title());

        if(!categoryDTO.description().isEmpty())
            category.setDescription(categoryDTO.description());

        repository.save(category);

        return category;
    }

    public void delete(String id) {
        Category category = repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        repository.delete(category);
    }
}
