package EndPoints;

import JPA.Course;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class CourseEndPoint {

	@Inject
	EntityManager em;

	@GET
	@Path("/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourses() {
		return em.createQuery("SELECT course FROM Course AS course", Course.class).getResultList();
	}

	@GET
	@Path("/course/{id}")
	public Response getCourse(@PathParam("id") Long id) {
		Course result = em.find(Course.class, id);
		if (result == null) {
			return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN)
					.entity("JPA.Course with id = " + id + " not found").build();
		}
		return Response.ok(result, MediaType.APPLICATION_JSON).build();
	}

	@Transactional
	@POST
	@Path("/course")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Long createCourse(Course course) {
		em.persist(course);
		System.out.println("TEST");
		return course.getId();
	}

	@Transactional
	@PUT
	@Path("/course")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCourse(Course course) {
		Course result = em.find(Course.class, course.getId());
		if (result == null) {
			return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN)
					.entity("JPA.Course with id = " + course.getId() + " not found").build();
		}
		result.setName(course.getName());
		result.setDescription(course.getDescription());
		result.setCredits(course.getCredits());
		result.setSemester(course.getSemester());
		return Response.ok().build();
	}

	@Transactional
	@DELETE
	@Path("/course/{id}")
	public Response deleteCourse(@PathParam("id") Long id) {
		Course result = em.find(Course.class, id);
		if (result == null) {
			return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN)
					.entity("JPA.Course with id = " + id + " not found").build();
		}
		em.remove(result);
		return Response.ok().build();
	}
}
