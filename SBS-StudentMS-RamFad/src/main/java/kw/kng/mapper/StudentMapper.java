package kw.kng.mapper;

import kw.kng.dto.StudentDto;
import kw.kng.entities.Student;

public class StudentMapper 
{

	//Map Entity Class(Student) to Binding Class(StudentDto)
	/*
	public static StudentDto mapToStudentDto(Student student)
	{
		StudentDto studentDto = new  StudentDto(
						student.getId(),
						student.getFirstName(),
						student.getLastName(),
						student.getEmail()
				);
		return studentDto;
	}
	
	
	//Map Binding Class(StudentDto) to Entity Class(Student)
	public static Student mapToStudent(StudentDto studentDto)
	{
		Student student= new Student(
						studentDto.getId(),
						studentDto.getFirstName(),
						studentDto.getLastName(),
						studentDto.getEmail()
				);
		return student;
	}
	*/
	
}
