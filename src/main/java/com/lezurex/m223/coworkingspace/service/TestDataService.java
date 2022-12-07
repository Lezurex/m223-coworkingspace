package com.lezurex.m223.coworkingspace.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.lezurex.m223.coworkingspace.model.ApplicationUser;
import io.quarkus.arc.profile.UnlessBuildProfile;
import io.quarkus.runtime.StartupEvent;

@UnlessBuildProfile("prod")
@ApplicationScoped
public class TestDataService {

  @Inject
  EntityManager entityManager;

  @Transactional
  public void generateTestData(@Observes StartupEvent event) {
    clearData();

    var userHans = new ApplicationUser("hans@example.com", "HansFTW123");
    entityManager.persist(userHans);

    var userJoerg = new ApplicationUser("joerg@example.com", "JoergFTW123");
    entityManager.persist(userJoerg);
  }

  private void clearData() {
    entityManager.createQuery("DELETE FROM ApplicationUser").executeUpdate();
  }

}
