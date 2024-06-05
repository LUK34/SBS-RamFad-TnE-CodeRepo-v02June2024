package kw.kng.service;

import java.util.List;

import jakarta.validation.Valid;
import kw.kng.dto.StudentDto;

public interface StudentService
{
	List<StudentDto> getAllStudents();
	String createStudent(StudentDto student);
	StudentDto getStudentByid(Long studentId);
	String updateStudent( StudentDto studentDto);
	String deleteStudent(Long studentid);
}
