package kw.kng.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.dto.EmployeeDto;
import kw.kng.entities.Employee;
import kw.kng.exceptions.EmailAlreadyExistException;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService 
{
	private EmployeeRepo erepo;
	private ModelMapper modelMapper;
	
	public EmployeeServiceImpl(EmployeeRepo erepo, ModelMapper modelMapper) 
	{
		this.erepo = erepo;
		this.modelMapper= modelMapper;
	}

	@Override
	public EmployeeDto createEmployeeSingle(EmployeeDto employeeDto) 
	{
		Optional<Employee> optionalEmployee = erepo.findByEmail(employeeDto.getEmail());
		if(optionalEmployee.isPresent())
		{
			throw new EmailAlreadyExistException("Email: "+optionalEmployee.get().getEmail()+"  already exists in DB.");
		}
		
		//Transfer DTO to ENTITY
		Employee emp = modelMapper.map(employeeDto, Employee.class);
		
		//Save the ENTITY details to PERSISTANCE
		Employee savedEmployee = erepo.save(emp);
		
		//Transfert ENTITY to DTO
		return modelMapper.map(savedEmployee, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> createEmployeeMultiple(List<EmployeeDto> employeeDto) 
	{
		//Transfer List of EMPLOYEE from DTO to ENTITY
		List<Employee> empList = employeeDto.stream().map(dto -> modelMapper.map(dto, Employee.class)).collect(Collectors.toList());
		
			// Check if any of the emails already exist in the database
				 List<String> emails = empList.stream().map(Employee::getEmail).collect(Collectors.toList()); 

					for (String email : emails) 
					{
					Optional<Employee> optionalEmployee = erepo.findByEmail(email);
						if (optionalEmployee.isPresent())
						{
							System.out.println("Email: " + optionalEmployee.get().getEmail());
							throw new EmailAlreadyExistException("Email: " + optionalEmployee.get().getEmail() + " already exists in DB.");
						}
					}
				
		//Save the List in persistance
		List<Employee> savedEmpList = erepo.saveAll(empList);
		
		//Transfer savedEmpList to DTO
		return savedEmpList.stream().map(e -> modelMapper.map(e, EmployeeDto.class)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto getEmployeeById(Long empId) 
	{
		//Search in DB whetehr the data exist for that id. If not found then  throw `ResourceNotFoundException`
		Employee emp = erepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee with id: "+empId+" not found in DB"));
		
		//If found transfer the data from ENTITY to DTO
		return modelMapper.map(emp, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() 
	{
		//Find the List of Employee and save it in ENTITY as LIST
		List<Employee> empList = erepo.findAll();
		
		//Transfer the list from ENTITY to DTO
		return empList.stream().map(e -> modelMapper.map(e, EmployeeDto.class)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployeeById(Long empId, EmployeeDto empDto)
	{
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Employee emp = erepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee with id: "+empId+" not found in DB"));
		
		//Transfer DTO to ENTITY 
		emp.setFirstName(empDto.getFirstName());
		emp.setLastName(empDto.getLastName());
		emp.setEmail(empDto.getEmail());
		
		//Save the ENTITY details to Persistance
		Employee updateEmp = erepo.save(emp);
		
		//Transfer ENTITY to DTO
		return modelMapper.map(updateEmp, EmployeeDto.class);
	}

	@Override
	public void deleteEmployeeById(Long empId) 
	{
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Employee emp = erepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee with id: "+empId+" not found in DB"));
				
		//Delete the user in PERSISTANCE
		erepo.deleteById(empId);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
