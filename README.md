# ThinknearRestApiStudentsClasses
Author: Saulo Lopez

The problem was coded using jersey framework

The code has a Controller layer to manage services, a persistence context, and a model layer
It uses maven as build automation tool
	
The endpoints are the next ones:
	Create Student: 
	POST http://localhost:8080/RestApi/students
	Body: {"firstName":"firstName","id":"123","lastName":"lastName"}
	Content-Type: application/json

	Get Student by id:
	GET: http://localhost:8080/RestApi/students/123
	Content-Type: application/json

	Edit Student:
	PUT http://localhost:8080/RestApi/students/123
	Body: {"firstName":"firstName2", lastName":"lastName2"}
	Content-Type: application/json

	Delete Student:
	DELETE http://localhost:8080/RestApi/students/123
	Content-Type: application/json

	Browse list of all Students:
	GET http://localhost:8080/RestApi/student
	Content-Type: application/json

	View all Classes assigned to a Student:
	GET http://localhost:8080/RestApi/students/123/courses
	Content-Type: application/json

	Create Class: 
	POST http://localhost:8080/RestApi/course
	Body: {"title":"title","code":"1", "description":"description"}
	Content-Type: application/json

	Get class by code:
	GET: http://localhost:8080/RestApi/courses/1
	Content-Type: application/json

	Edit Class:
	PUT http://localhost:8080/RestApi/courses/1
	Body: {"title":"title2",description":"description2"}
	Content-Type: application/json

	Delete Class:
	DELETE http://localhost:8080/RestApi/courses/1
	Content-Type: application/json

	Browse list of all Classes:
	GET http://localhost:8080/RestApi/course
	Content-Type: application/json

	View all Students assigned to a Class:
	GET http://localhost:8080/RestApi/courses/1/students
	Content-Type: application/json

	Enroll a student in a Class:
	PUT http://localhost:8080/RestApi/courses/123/student/1
	Content-Type: application/json
