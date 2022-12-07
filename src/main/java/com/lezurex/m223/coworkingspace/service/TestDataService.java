package com.lezurex.m223.coworkingspace.service;

import java.time.LocalDate;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.lezurex.m223.coworkingspace.model.ApplicationUser;
import com.lezurex.m223.coworkingspace.model.Booking;
import com.lezurex.m223.coworkingspace.model.RoleEnum;
import com.lezurex.m223.coworkingspace.model.StatusEnum;
import com.lezurex.m223.coworkingspace.model.TimeframeEnum;
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

    var userJonathan = new ApplicationUser("jonathan.meier@coworking.ch", "Jonathan", "Meier",
        "JonathanFTW123", RoleEnum.ADMIN);
    entityManager.persist(userJonathan);

    var userLisbeth = new ApplicationUser("lisbeth.zbinden@bluewin.ch", "Lisbeth", "z'Binden",
        "RacletteZumZmorgu123", RoleEnum.MEMBER);
    entityManager.persist(userLisbeth);

    var bookingA =
        new Booking(LocalDate.now(), TimeframeEnum.MORNING, StatusEnum.CONFIRMED, userLisbeth);
    entityManager.persist(bookingA);

    var bookingB = new Booking(LocalDate.now().plusDays(3), TimeframeEnum.FULL_DAY,
        StatusEnum.PENDING, userLisbeth);
    entityManager.persist(bookingB);
  }

  private void clearData() {
    entityManager.createQuery("DELETE FROM Booking").executeUpdate();
    entityManager.createQuery("DELETE FROM ApplicationUser").executeUpdate();
  }

}
