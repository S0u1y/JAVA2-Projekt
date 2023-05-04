package EndPoints;

import JPA.Subject;
import JPA.Teacher;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TeacherEndPoint {
    @Inject
    EntityManager em;

    @GET
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeachers(){
        return Response.ok(
                em.createQuery(
                        "SELECT teacher FROM Teacher AS teacher", Teacher.class
                ).getResultList()).build() ;
    }

    @Transactional
    @POST
    @Path("/teacher")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Long createTeacher(Teacher teacher) {
        em.persist(teacher);
        return teacher.getId();
    }

    @Transactional
    @PUT
    @Path("/teacher")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTeacher(Teacher teacher) {
        Teacher result = em.find(Teacher.class, teacher.getId());
        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN)
                    .entity("JPA.Course with id = " + teacher.getId() + " not found").build();
        }
        result.setFirstName(teacher.getFirstName());
        result.setLastName(teacher.getLastName());
        result.setSubjects(teacher.getSubjects());
        result.setAge(teacher.getAge());
        return Response.ok().build();
    }

    @Transactional
    @PUT
    @Path("/teacher/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSubject(
            @PathParam("teacherToUpdate") Long id,
            Subject subject){

        Teacher teacher = em.find(Teacher.class, id);
        teacher.getSubjects().add(subject);
        return Response.ok().build();
    }

}
