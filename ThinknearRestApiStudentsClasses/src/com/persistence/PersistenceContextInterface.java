package com.persistence;

import java.util.List;

import com.api.models.Course;
import com.api.models.Student;
import com.api.utils.ApiException;

public interface PersistenceContextInterface {
	
	Student addStudent(Student student) throws ApiException;
	
	Student updateStudent(Student student) throws ApiException;
	
	void removeStudent(String studentId) throws ApiException;
	
	Course addCourse(Course course) throws ApiException;
	
	Course updateCourse(Course course) throws ApiException;
	
	void removeCourse(String code) throws ApiException;
	
	List<Student> getAllStudents();
	
	List<Course> getAllCourses();
	
	List<Student> getAllStudentsAssignedToCourse(String courseCode) throws ApiException;
	
	List<Course> getAllCoursesAssignedToStudent(String studentId) throws ApiException;
	
	void enrollStudentToCourse(String studentId, String courseCode) throws ApiException;

	Student getStudentById(String id) throws ApiException;
	
	Course getCourseByCode(String code) throws ApiException;
}
