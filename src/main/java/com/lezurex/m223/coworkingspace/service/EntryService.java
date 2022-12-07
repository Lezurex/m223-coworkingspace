package com.lezurex.m223.coworkingspace.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.lezurex.m223.coworkingspace.model.Entry;

@ApplicationScoped
public class EntryService {
  @Inject
  EntityManager entityManager;

  @Transactional
  public Entry createEntry(Entry entry) {
    entityManager.persist(entry);
    return entry;
  }

  @Transactional
  public void deleteEntry(Long id) {
    var entry = entityManager.find(Entry.class, id);
    entityManager.remove(entry);
  }

  @Transactional
  public Entry updateEntry(Entry newEntry) {
    entityManager.merge(newEntry);
    return newEntry;
  }

  public List<Entry> findAll() {
    var query = entityManager.createQuery("FROM Entry", Entry.class);
    return query.getResultList();
  }
}
