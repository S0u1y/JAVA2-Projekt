package EndPoints;

import JPA.Course;
import JPA.Student;
import JPA.Subject;
import JPA.Teacher;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class SubjectEndPoint {

    @Inject
    EntityManager em;

    @GET
    @Path("/subjects")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Subject> getSubjects(){
        return em.createQuery("SELECT subject FROM Subject AS subject", Subject.class).getResultList();
    }

    @Transactional
    @POST
    @Path("/subject")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Long createSubject(Subject subject) {
        em.persist(subject);
        return subject.getId();
    }

    @Transactional
    @PUT
    @Path("/subject")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSubject(Subject subject) {
        Subject result = em.find(Subject.class, subject.getId());
        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN)
                    .entity("JPA.Course with id = " + subject.getId() + " not found").build();
        }
        result.setName(subject.getName());
        result.setCode(subject.getCode());
        result.setTeacher(subject.getTeacher());
        result.setStudents(subject.getStudents());
        return Response.ok().build();
    }

    @Transactional
    @PUT
    @Path("/subject/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(
            @PathParam("subjectToUpdate") Long id,
            Student student){

        Subject subject = em.find(Subject.class, id);
        subject.getStudents().add(student);
        return Response.ok().build();
    }

    @Transactional
    @PUT
    @Path("/subject/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setTeacher(
            @PathParam("subjectToUpdate") Long id,
            Teacher teacher){

        Subject subject = em.find(Subject.class, id);
        subject.setTeacher(teacher);
        return Response.ok().build();
    }

}
