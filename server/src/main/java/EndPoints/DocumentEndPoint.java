package EndPoints;

import JPA.Course;
import JPA.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class DocumentEndPoint {
    @Inject
    EntityManager em;

    @GET
    @Path("/documents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document> getDocuments(){
        return em.createQuery("SELECT document FROM Document AS document", Document.class).getResultList();
    }

    @Transactional
    @POST
    @Path("/document")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Long createDocument(Document document){
        em.persist(document);
        return document.getId();
    }

}
