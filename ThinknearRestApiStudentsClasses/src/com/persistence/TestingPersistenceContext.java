package com.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.api.models.Course;
import com.api.models.Student;
import com.api.utils.ApiException;

public class TestingPersistenceContext implements PersistenceContextInterface {

	private static TestingPersistenceContext instance = null;
	
	private Map<Student, ArrayList<Course>> students = new HashMap<Student, ArrayList<Course>>();
	private Map<Course, ArrayList<Student>> courses = new HashMap<Course, ArrayList<Student>>();

	protected TestingPersistenceContext() {
	}
	
	public static PersistenceContextInterface getInstance() {
	      if(instance == null) {
	         instance = new TestingPersistenceContext();
	      }
	      return instance;
	   }
	
	@Override
	public Student addStudent(Student student) throws ApiException {
		if (!students.containsKey(student)) {
			students.put(student, new ArrayList<Course>());
			return student;
		} else {
			throw new ApiException("The student already exists");
		}
	}

	@Override
	public Student updateStudent(Student student) throws ApiException {
		if (students.containsKey(student)) {
			Iterator<Student> iterator = students.keySet().iterator();
			while (iterator.hasNext()) {
				Student key = (Student) iterator.next();

				if (key.equals(student)) {
					key.setFirstName(student.getFirstName());
					key.setLastName(student.getLastName());
					return key;
				}
			}
			return null;
		} else {
			throw new ApiException("The student does not exist");
		}
	}

	@Override
	public void removeStudent(String studentId) throws ApiException {
		Student student = new Student();
		student.setId(studentId);
		
		if (students.containsKey(student)) {
			students.remove(student);

			// The student should be removed from all courses
			for(Entry<Course, ArrayList<Student>> course : courses.entrySet()) {
		       course.getValue().remove(student);
		    }
		} else {
			throw new ApiException("The student does not exist");
		}
	}

	@Override
	public Course addCourse(Course course) throws ApiException {
		if (!courses.containsKey(course)) {
			courses.put(course, new ArrayList<Student>());
			return course;
		} else {
			throw new ApiException("The course already exists");
		}
	}

	@Override
	public Course updateCourse(Course course) throws ApiException {
		if (courses.containsKey(course)) {
			Iterator<Course> iterator = courses.keySet().iterator();
			while (iterator.hasNext()) {
				Course key = (Course) iterator.next();

				if (key.equals(course)) {
					key.setTitle(course.getTitle());
					key.setDescription(course.getDescription());
					return key;
				}
			}
			return null;
		} else {
			throw new ApiException("Course does not exist");
		}
	}

	@Override
	public void removeCourse(String code) throws ApiException {
		Course course = new Course();
		course.setCode(code);
		
		if (courses.containsKey(course)) {
			courses.remove(course); 
			
			// The course should be removed from all students 
			for(Entry<Student, ArrayList<Course>> student : students.entrySet()) {
				student.getValue().remove(course);
		    }
		
		} else {
			throw new ApiException("The course does not exist");
		}
	}

	@Override
	public ArrayList<Student> getAllStudents() {
		return new ArrayList<>(students.keySet());
	}
	
	@Override
	public Student getStudentById(String id) throws ApiException {
		Student student = new Student();
		student.setId(id);
		
		if (students.containsKey(student)) {
			Iterator<Student> iterator = students.keySet().iterator();
			while (iterator.hasNext()) {
				Student key = (Student) iterator.next();

				if (key.equals(student)) {
					return key;
				}
			}
			return null;
		} else {
			throw new ApiException("The student does not exist");
		}
	}
	
	@Override
	public Course getCourseByCode(String code) throws ApiException {
		Course course = new Course();
		course.setCode(code);
		
		if (courses.containsKey(course)) {
			Iterator<Course> iterator = courses.keySet().iterator();
			while (iterator.hasNext()) {
				Course key = (Course) iterator.next();

				if (key.equals(course)) {
					return key;
				}
			}
			return null;
		} else {
			throw new ApiException("The course does not exist");
		}
	}

	@Override
	public ArrayList<Course> getAllCourses() {
		return new ArrayList<>(courses.keySet());
	}

	@Override
	public ArrayList<Student> getAllStudentsAssignedToCourse(String courseCode) throws ApiException {
		Course course = new Course();
		course.setCode(courseCode);
		
		if (courses.containsKey(course)) {
			return courses.get(course);
		} else {
			throw new ApiException("The course does not exist");
		}
	}

	@Override
	public ArrayList<Course> getAllCoursesAssignedToStudent(String studentId) throws ApiException {
		Student student = new Student();
		student.setId(studentId);
		
		if (students.containsKey(student)) {
			return students.get(student);
		} else {
			throw new ApiException("The student does not exist");
		}
	}

	@Override
	public void enrollStudentToCourse(String studentId, String courseCode) throws ApiException {
		Student student = new Student();
		student.setId(studentId);
		
		Course course = new Course();
		course.setCode(courseCode);
		
		if (courses.containsKey(course)) {
			if(students.containsKey(student)) {
				ArrayList<Student> enrolledStudents = courses.get(course);
				if(!enrolledStudents.contains(student)) {
					enrolledStudents.add(getStudentById(studentId)); // We add the student to the course
					students.get(student).add(getCourseByCode(courseCode)); // We add the course to the student
				} else {
					throw new ApiException("The student is already enrolled to course");
				}
			} else {
				throw new ApiException("The student does not exist");
			}
		} else {
			throw new ApiException("The course does not exist");
		}
	}
}
