package com.lezurex.m223.coworkingspace.service;

import java.time.LocalDate;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.lezurex.m223.coworkingspace.JacocoIgnoreGenerated;
import com.lezurex.m223.coworkingspace.model.ApplicationUser;
import com.lezurex.m223.coworkingspace.model.Booking;
import com.lezurex.m223.coworkingspace.model.RoleEnum;
import com.lezurex.m223.coworkingspace.model.StatusEnum;
import com.lezurex.m223.coworkingspace.model.TimeframeEnum;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@JacocoIgnoreGenerated
@IfBuildProfile("dev")
@ApplicationScoped
public class DevDataService {

  @Inject
  EntityManager entityManager;

  @Inject
  HashingService hashingService;

  @Transactional
  public void generateTestData(@Observes StartupEvent event) {
    clearData();

    var userJonathan = new ApplicationUser("jonathan.meier@coworking.ch", "Jonathan", "Meier",
        hashingService.hashPassword("JonathanFTW123"), RoleEnum.ADMIN);
    entityManager.persist(userJonathan);

    var userLisbeth = new ApplicationUser("lisbeth.zbinden@bluewin.ch", "Lisbeth", "z'Binden",
        hashingService.hashPassword("RacletteZumZmorgu123"), RoleEnum.MEMBER);
    entityManager.persist(userLisbeth);
    var bookingA =
        new Booking(LocalDate.now(), TimeframeEnum.MORNING, StatusEnum.CONFIRMED, userLisbeth);
    entityManager.persist(bookingA);

    var bookingB = new Booking(LocalDate.now().plusDays(3), TimeframeEnum.FULL_DAY,
        StatusEnum.PENDING, userLisbeth);
    entityManager.persist(bookingB);
  }

  @Transactional
  public void clearData() {
    entityManager.createNativeQuery("TRUNCATE TABLE booking RESTART IDENTITY CASCADE")
        .executeUpdate();
    entityManager.createNativeQuery("TRUNCATE TABLE applicationuser RESTART IDENTITY CASCADE")
        .executeUpdate();
  }

}
