package com.api.controllers;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.api.models.Course;
import com.api.models.Student;
import com.api.utils.ApiException;
import com.persistence.PersistenceContextFactory;
import com.persistence.PersistenceContextInterface;

@Path("/students")
public class StudentController {
	private PersistenceContextInterface context = PersistenceContextFactory.getPersistenceContext(null);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllStudents()
	{
		List<Student> list = context.getAllStudents();
		GenericEntity<List<Student>> entity = new GenericEntity<List<Student>>(list) {};
		return Response.ok().entity(entity).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentById(@PathParam("id") String id) {
		try {
			return Response.ok().entity(context.getStudentById(id)).build();
		} catch (ApiException e) {
			return Response.status(400).entity(e.getMessage()).build();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return Response.serverError().entity("An error has occurred").build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudent(Student student) {
		try {
			return Response.ok().entity(context.addStudent(student)).build();
		} catch (ApiException e) {
			return Response.status(400).entity(e.getMessage()).build();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return Response.serverError().entity("An error has occurred").build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStudent(Student student) {
		try {
			return Response.ok().entity(context.updateStudent(student)).build();
		} catch (ApiException e) {
			return Response.status(400).entity(e.getMessage()).build();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return Response.serverError().entity("An error has occurred").build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStudent(@PathParam("id") String id) {
		try {
			context.removeStudent(id);
			return Response.noContent().build();
		} catch (ApiException e) {
			return Response.status(400).entity(e.getMessage()).build();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return Response.serverError().entity("An error has occurred").build();
		}
	}

	@GET
	@Path("/{id}/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEnrolledCourses(@PathParam("id") String id) {
		try {
			List<Course> list = context.getAllCoursesAssignedToStudent(id);
			GenericEntity<List<Course>> entity = new GenericEntity<List<Course>>(list) {};
			return Response.ok().entity(entity).build();
		} catch (ApiException e) {
			return Response.status(400).entity(e.getMessage()).build();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return Response.serverError().entity("An error has occurred").build();
		}
	}
}
