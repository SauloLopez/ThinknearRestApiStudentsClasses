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

@Path("/courses")
public class CourseController {
	private PersistenceContextInterface context = PersistenceContextFactory.getPersistenceContext(null);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCourses() {
		List<Course> list = context.getAllCourses();
		GenericEntity<List<Course>> entity = new GenericEntity<List<Course>>(list) {};
		return Response.ok().entity(entity).build();
	}
	
	@GET
	@Path("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCourseByCode(@PathParam("code") String code) {
		try {
			return Response.ok().entity(context.getCourseByCode(code)).build();
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
	public Response addCourse(Course course) {
		try {
			return Response.ok().entity(context.addCourse(course)).build();
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
	public Response updateCourse(Course course) {
		try {
			return Response.ok().entity(context.updateCourse(course)).build();
		} catch (ApiException e) {
			return Response.status(400).entity(e.getMessage()).build();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return Response.serverError().entity("An error has occurred").build();
		}
	}

	@DELETE
	@Path("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(@PathParam("code") String code) {
		try {
			context.removeCourse(code);
			return Response.noContent().build();
		} catch (ApiException e) {
			return Response.status(400).entity(e.getMessage()).build();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return Response.serverError().entity("An error has occurred").build();
		}
	}
	
	@GET
	@Path("/{code}/students")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEnrolledStudents(@PathParam("code") String code) {
		try {
			List<Student> list = context.getAllStudentsAssignedToCourse(code);
			GenericEntity<List<Student>> entity = new GenericEntity<List<Student>>(list) {};
			return Response.ok().entity(entity).build();			
		} catch (ApiException e) {
			return Response.status(400).entity(e.getMessage()).build();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return Response.serverError().entity("An error has occurred").build();
		}
	}
	
	@PUT
	@Path("/{code}/student/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response enrollStudent(@PathParam("code") String code, @PathParam("id") String id) {
		try {
			context.enrollStudentToCourse(id, code);
			return Response.ok().build();
		} catch (ApiException e) {
			return Response.status(400).entity(e.getMessage()).build();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return Response.serverError().entity("An error has occurred").build();
		}
	}
}
