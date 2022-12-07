package com.lezurex.m223.coworkingspace.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.lezurex.m223.coworkingspace.model.Tag;

@ApplicationScoped
public class TagService {
  @Inject
  EntityManager entityManager;

  @Transactional
  public Tag createTag(Tag tag) {
    entityManager.persist(tag);
    return tag;
  }

  @Transactional
  public void deleteTag(Long id) {
    var tag = entityManager.find(Tag.class, id);
    entityManager.remove(tag);
  }

  @Transactional
  public Tag updateTag(Tag newTag) {
    entityManager.merge(newTag);
    return newTag;
  }


  public List<Tag> findAll() {
    var query = entityManager.createQuery("FROM Tag", Tag.class);
    return query.getResultList();
  }
}
