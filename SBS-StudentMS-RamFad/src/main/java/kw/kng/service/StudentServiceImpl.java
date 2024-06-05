package kw.kng.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.dto.StudentDto;
import kw.kng.entities.Student;
import kw.kng.repo.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService 
{
	  private  StudentRepo srepo;
	    private ModelMapper modelMapper;

	    public StudentServiceImpl(StudentRepo srepo, ModelMapper modelMapper)
	    {
	        this.srepo = srepo;
	        this.modelMapper = modelMapper;
	    }

	@Override
	public List<StudentDto> getAllStudents() 
	{
		List<Student> studs= srepo.findAll();
		return studs.stream().map((s) -> modelMapper.map(s, StudentDto.class)).collect(Collectors.toList()); //Map Entity class ->Dto class and store the result set in List.
															
	}

	@Override
	public String createStudent(StudentDto studentDto) 
	{		
		//Map DTO to Entity
		Student student = modelMapper.map(studentDto, Student.class);
		//Ensure the status is 'Y' when creating a new student
		student.setStatus("Y");
		//Save
		Student saveStudent= srepo.save(student);
		
		return "Student Details SAVED into DB with id value: " + saveStudent.getId();
	}

	@Override
	public StudentDto getStudentByid(Long studentId) 
	{
		Student student= srepo.findById(studentId).get();
		StudentDto studentDto = modelMapper.map(student, StudentDto.class); //Map Entity class ->Dto class
		
		return studentDto;
	}

	@Override
	public String updateStudent(StudentDto studentDto) 
	{
		//Map Dto class ->Entity class
		 Student student = modelMapper.map(studentDto, Student.class);
		//Ensure the status is 'Y' when updating a new student
		 student.setStatus("Y");
		 //save
	      Student updatedStudent = srepo.save(student);
	      
	      return "Student Details UPDATED into DB with id value: " +	updatedStudent.getId(); 
	}

	
	//Soft deletion
	@Override
	public String deleteStudent(Long studentid)
	{
		srepo.deleteById(studentid);	
		return "Student with id: "+studentid+" is DELETED  from DB.";
	}

	
	

	
}
