import EndPoints.PageEndPoint;
import JPA.Document;
import JPA.Page;
import io.quarkus.runtime.StartupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;


@ApplicationScoped
public class StartupService {

    private static final Logger log = LogManager.getLogger(StartupService.class);

    private final EntityManager entityManager;

    @Inject
    public StartupService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void initializeDatabase() {
        // code to initialize the database goes here
        EntityManager em = entityManager;

        Query query = em.createQuery("SELECT COUNT(*) FROM Document");

        long count = (long)query.getSingleResult();

        if(count == 0){

            log.info("Initialization of database");

            Document document1 = new Document();
            document1.setTitle("Template Document");
            document1.setDescription("This is the first document of our application.");

            em.persist(document1);

            Document document2 = new Document();
            document2.setTitle("Second Document");
            document2.setDescription("This is the second document of our application.");

            em.persist(document2);

            Page page1 = new Page();
            page1.setDocument(document1);
            page1.setContent("Here is some lorem ipsum cool content for the page.\nI hope you like it :)");
            page1.setPage_number(document1.getNextPageNumber(em));

            em.persist(page1);

            Page page2 = new Page();
            page2.setDocument(document1);
            page2.setContent("Here we have the second page of our template document.\nPretty cool right? :)");
            page2.setPage_number(document1.getNextPageNumber(em));

            em.persist(page2);


        }



    }

    void onStartup(@Observes StartupEvent startupEvent, StartupService startupService) {
        startupService.initializeDatabase();
    }



}
