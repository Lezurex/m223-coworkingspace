package com.lezurex.m223.coworkingspace.service;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.lezurex.m223.coworkingspace.model.ApplicationUser;

@ApplicationScoped
public class ApplicationUserService {

  @Inject
  EntityManager entityManager;

  @Transactional
  public ApplicationUser createApplicationUser(ApplicationUser applicationUser) {
    entityManager.persist(applicationUser);
    return applicationUser;
  }

  @Transactional
  public void deleteApplicationUser(Long id) {
    var applicationUser = entityManager.find(ApplicationUser.class, id);
    applicationUser.getBookings().forEach(b -> entityManager.remove(b));
    entityManager.remove(applicationUser);
  }

  @Transactional
  public ApplicationUser updateApplicationUser(ApplicationUser newApplicationUser) {
    entityManager.merge(newApplicationUser);
    return newApplicationUser;
  }

  public List<ApplicationUser> findAll() {
    var query = entityManager.createQuery("FROM ApplicationUser", ApplicationUser.class);
    return query.getResultList();
  }

  public Optional<ApplicationUser> findByEmail(String email) {
    System.out.println(email);
    var user = entityManager.createNamedQuery("ApplicationUser.findByEmail", ApplicationUser.class)
        .setParameter("email", email).getResultStream().findFirst();
    return user;
  }

}
