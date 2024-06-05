package kw.kng.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import kw.kng.dto.StudentDto;
import kw.kng.service.StudentService;

@Controller
public class StudentController 
{
	private StudentService ss;

	public StudentController(StudentService ss) 
	{
		this.ss = ss;
	}
	
	@GetMapping("/students")
	public String listStudents(Model model)
	{
		List<StudentDto> students= ss.getAllStudents();
		
		model.addAttribute("students", students);
		return "students";
	}
	
	//Handle method to handle new student request
	@GetMapping("/students/new")
	public String newStudent(Model model)
	{
		StudentDto studDto = new StudentDto();
		model.addAttribute("student", studDto);
		return "create_student";
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Handler method to handle save student form submit request
	@PostMapping("/students")
	public String saveStudent(@Valid @ModelAttribute("student") StudentDto student,
												BindingResult result,
												RedirectAttributes attrs,
												Model model)
	{
		if(result.hasErrors()) //Server side Validation logic
		{
			model.addAttribute("student",student);
			return "create_student";
		}
		
		String msg=ss.createStudent(student);
		//add the result message as Flash Attribute
		attrs.addFlashAttribute("resultMsg", msg);
		
		return "redirect:/students";
	}
	
	
	@PostMapping("/students/{studentId}")
	public String updateStudent(@PathVariable("studentId") Long studentId,
													@Valid @ModelAttribute("student") StudentDto studentDto,
													BindingResult result,
													RedirectAttributes attrs,
													Model model)
	{
		if(result.hasErrors()) //Server side Validation logic
		{
			model.addAttribute("student", studentDto);
			studentDto.setId(studentId);
			return "edit_student";
		}
		studentDto.setId(studentId); //as part of update procedure
		
		String msg = ss.updateStudent(studentDto);
		//add the result message as Flash Attribute
		attrs.addFlashAttribute("resultMsg",msg);
		
		return "redirect:/students";
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/students/{studentId}/edit")
	public String editStudent(@PathVariable("studentId") Long studentId,
												Model model)
	{
		StudentDto studentDto=ss.getStudentByid(studentId);
		model.addAttribute("student",studentDto);
		return "edit_student";
	}
	

	@GetMapping("/students/{studentId}/delete")
	public String deleteStudent(RedirectAttributes attrs,
													@PathVariable("studentId") Long studentId)
	{
		String msg= ss.deleteStudent(studentId);
		attrs.addFlashAttribute("resultMsg",msg);
		
		return "redirect:/students";
	}
	
	@GetMapping("/students/{studentId}/view")
	public String viewStudent(@PathVariable("studentId") Long studentId,
												Model model)
	{
		StudentDto studentDto=ss.getStudentByid(studentId);
		model.addAttribute("student",studentDto);
		return "view_student";
	}
	
	
	

}
