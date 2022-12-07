package com.lezurex.m223.coworkingspace.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.lezurex.m223.coworkingspace.model.Category;

@ApplicationScoped
public class CategoryService {
  @Inject
  EntityManager entityManager;

  @Transactional
  public Category createCategory(Category category) {
    entityManager.persist(category);
    return category;
  }

  @Transactional
  public void deleteCategory(Long id) {
    var category = entityManager.find(Category.class, id);
    entityManager.remove(category);
  }

  @Transactional
  public Category updateCategory(Category newCategory) {
    entityManager.merge(newCategory);
    return newCategory;
  }


  public List<Category> findAll() {
    var query = entityManager.createQuery("FROM Category", Category.class);
    return query.getResultList();
  }
}
