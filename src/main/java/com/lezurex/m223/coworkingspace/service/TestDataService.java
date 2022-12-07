package com.lezurex.m223.coworkingspace.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.lezurex.m223.coworkingspace.model.ApplicationUser;
import com.lezurex.m223.coworkingspace.model.Category;
import com.lezurex.m223.coworkingspace.model.Entry;
import com.lezurex.m223.coworkingspace.model.Tag;
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

    var projectACategory = new Category("Project A");
    entityManager.persist(projectACategory);

    var projectBCategory = new Category("Project B");
    entityManager.persist(projectBCategory);

    var projectCCategory = new Category("Project C");
    entityManager.persist(projectCCategory);

    var programmingTag = new Tag("Programming");
    entityManager.persist(programmingTag);

    var debuggingTag = new Tag("Debugging");
    entityManager.persist(debuggingTag);

    var meetingTag = new Tag("Meeting");
    entityManager.persist(meetingTag);

    var firstEntry =
        new Entry(LocalDateTime.now().minusHours(3), LocalDateTime.now().minusHours(2));
    firstEntry.setTags(new HashSet<>(Arrays.asList(programmingTag, debuggingTag)));
    firstEntry.setCategory(projectACategory);
    firstEntry.setApplicationUser(userHans);
    entityManager.persist(firstEntry);

    var secondEntry =
        new Entry(LocalDateTime.now().minusHours(6), LocalDateTime.now().minusHours(3));
    secondEntry.setTags(new HashSet<>(Arrays.asList(meetingTag)));
    secondEntry.setCategory(projectBCategory);
    secondEntry.setApplicationUser(userJoerg);
    entityManager.persist(secondEntry);

    var thirdEntry = new Entry(LocalDateTime.now().minusHours(2), LocalDateTime.now());
    thirdEntry.setTags(new HashSet<>(Arrays.asList(debuggingTag)));
    thirdEntry.setCategory(projectCCategory);
    thirdEntry.setApplicationUser(userJoerg);
    entityManager.persist(thirdEntry);
  }

  private void clearData() {
    entityManager.createQuery("DELETE FROM Entry").executeUpdate();
    entityManager.createQuery("DELETE FROM Tag").executeUpdate();
    entityManager.createQuery("DELETE FROM Category").executeUpdate();
    entityManager.createQuery("DELETE FROM ApplicationUser").executeUpdate();
  }

}
