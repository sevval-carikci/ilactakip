package com.ilactakip.services;

import com.ilactakip.dao.Dao;
import com.ilactakip.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Transactional
public class CategoryService {

    private final Dao dao;

    public CategoryService(Dao dao) {
        this.dao = dao;
    }

    /* ================== GET ALL ================== */
    public List<Category> getAllCategories() {
        return dao.findAllCategories();
    }

    /* ================== GET BY ID ================== */
    public Category getCategoryById(Long id) {
        Category category = dao.findCategoryById(id);
        if (category == null) {
            throw new RuntimeException("Kategori bulunamadı. ID: " + id);
        }
        return category;
    }

    /* ================== CREATE ================== */
    public Category createCategory(Category category) {
        return dao.saveCategory(category);
    }

    /* ================== UPDATE ================== */
    public Category updateCategory(Long id, Category category) {
        Category existing = getCategoryById(id);
        existing.setName(category.getName());
        return dao.updateCategory(existing);
    }

    /* ================== PARTIAL UPDATE ================== */
    public Category partialUpdateCategory(Long id, Category category) {
        Category existing = getCategoryById(id);
        if (category.getName() != null) existing.setName(category.getName());
        return dao.updateCategory(existing);
    }

    /* ================== DELETE ================== */
    public void deleteCategory(Long id) {
        Category existing = getCategoryById(id);
        dao.deleteCategory(existing);
    }
}
