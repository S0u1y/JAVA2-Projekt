package EndPoints;

import JPA.Document;
import JPA.Page;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class PageEndPoint {

    @Inject
    EntityManager em;

    @GET
    @Path("/page/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPage(@PathParam("id") Long id){
        Page result = em.find(Page.class, id);
        if (result == null){
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN)
                    .entity("JPA.Page with id = " + id + " not found").build();
        }
        return Response.ok(result).build();
    }
    @GET
    @Path("/page/{document_id}/{page_num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPage(@PathParam("document_id") Long id, @PathParam("page_num") int page_num){
        Query query = em.createQuery("SELECT page FROM Page AS page WHERE page.document = :document_id AND page.page_number = :page_num", Page.class);
        query.setParameter("document_id", em.find(Document.class, id));
        query.setParameter("page_num", page_num);

        List results = query.getResultList();

        if(results == null){
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN)
                    .entity("JPA.Page from document " + id + " and page_number " + page_num + " not found").build();
        }

        return Response.ok(results.get(0)).build();
    }

    @Transactional
    @POST
    @Path("/page")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Long createPage(Page page){
        page.setPage_number(getNextPageNumber(page.getDocument().getId()));
        em.persist(page);

        return page.getId();
    }

    public int getNextPageNumber(Long document_id){
        Query max = em.createQuery("SELECT max(page.page_number) FROM Page as page WHERE page.document = :document_id");
        max.setParameter("document_id", em.find(Document.class, document_id));

        return (int)(max.getResultList().get(0)) + 1;
    }

    @GET
    @Path("/pagenum/{document_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getNumberOfPages(@PathParam("document_id") Long id){
        return getNextPageNumber(id)-1;
    }

}
