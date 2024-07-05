package kw.kng.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import kw.kng.dto.APIResponseDto;
import kw.kng.dto.DepartmentDto;
import kw.kng.dto.EmployeeDto;
import kw.kng.entities.Employee;
import kw.kng.exceptions.EmailAlreadyExistException;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService 
{
	private EmployeeRepo erepo;
	private ModelMapper modelMapper;  //Spring bean
	private RestTemplate restTemplate ;    //Spring bean
	private WebClient webClient;  //Spring bean
	
	
	public EmployeeServiceImpl(EmployeeRepo erepo, ModelMapper modelMapper,RestTemplate restTemplate, WebClient webClient) 
	{
		this.erepo = erepo;
		this.modelMapper= modelMapper;
		this.restTemplate = restTemplate;
		this.webClient=webClient;
	}

	//---------------------------------------------------------------------------------------------------------------------------------------
														//Properties
	
	@Value("${mcrsrv-ds-get-dep-url}")
	private String mcrsrvDSGetCodeUrl;
	
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public EmployeeDto createEmployeeSingle(EmployeeDto employeeDto) 
	{
		// Check if email already exist in db or not -> EMAIL VALIDATION
		Optional<Employee> optionalEmployee = erepo.findByEmail(employeeDto.getEmail());
		if(optionalEmployee.isPresent())
		{
			throw new EmailAlreadyExistException("Email: "+optionalEmployee.get().getEmail()+"  already exists in DB.");
		}
		
		//----------------------------------------------------------------------------------------
		//Check if the department code of department microservice exist or not :
		
		//1. MicroService Communication -> REST Template Method:  OLD soon to be deprecated
		/*
			ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(mcrsrvDSGetCodeUrl + employeeDto.getDepartmentCode() , DepartmentDto.class);
			String checkDepCodeExistOrNot = responseEntity.getBody().getDepartmentCode();		
		*/
		
		//2. MicroService Communication -> WebClient Method: 
		DepartmentDto departmentDto = webClient.get()
																				  .uri(mcrsrvDSGetCodeUrl + employeeDto.getDepartmentCode().trim())
																				  .retrieve()
																				  .bodyToMono(DepartmentDto.class)
																				  .block();
		
		employeeDto.setDepartmentCode(departmentDto.getDepartmentCode().trim());
		//----------------------------------------------------------------------------------------

		//Transfer DTO to ENTITY
		Employee emp = modelMapper.map(employeeDto, Employee.class);
		
		//Save the ENTITY details to PERSISTANCE
		Employee savedEmployee = erepo.save(emp);
		
		//Transfert ENTITY to DTO
		return modelMapper.map(savedEmployee, EmployeeDto.class);
	}


	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
			
			//Check if the department code of `DepartmentService` microservice exist or not  -Validation will be handled by  `DepartmentService` microservice
			for(Employee e :empList)
			{
				String departmentCode = e.getDepartmentCode().trim();
				
				//----------------------------------------------------------------------------------------
				//Check if the department code of department microservice exist or not :
	
				//1. MicroService Communication -> REST Template Method -> REST Template Method:  OLD soon to be deprecated
				/*
					ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(mcrsrvDSGetCodeUrl + departmentCode , DepartmentDto.class);
					String checkDepCodeExistOrNot = responseEntity.getBody().getDepartmentCode();		
					e.setDepartmentCode(checkDepCodeExistOrNot);
				*/
				
				//2. MicroService Communication -> WebClientMethod Method 
				DepartmentDto departmentDto = webClient.get()
																						 .uri(mcrsrvDSGetCodeUrl + departmentCode)
																						 .retrieve()
																						 .bodyToMono(DepartmentDto.class)
																						 .block();

				e.setDepartmentCode(departmentDto.getDepartmentCode().trim());
				//----------------------------------------------------------------------------------------
				
			}		
									
		//Save the List in persistance
		List<Employee> savedEmpList = erepo.saveAll(empList);
		
		//Transfer savedEmpList to DTO
		return savedEmpList.stream().map(e -> modelMapper.map(e, EmployeeDto.class)).collect(Collectors.toList());
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public APIResponseDto getEmployeeById(Long empId) 
	{
		//Search in DB whether the data exist for that id. If not found then  throw `ResourceNotFoundException`
		Employee emp = erepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee with id: "+empId+" not found in DB"));
		
		//----------------------------------------------------------------------------------------
		//1. MicroService Communication -> REST Template Method -> REST Template Method:  OLD soon to be deprecated
		/*
		ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(mcrsrvDSGetCodeUrl + emp.getDepartmentCode() , DepartmentDto.class);
		DepartmentDto departmentDto = responseEntity.getBody();
		*/
		
		//2. MicroService Communication -> WebClientMethod Method 
		DepartmentDto departmentDto = webClient.get()
																				 .uri(mcrsrvDSGetCodeUrl + emp.getDepartmentCode().trim())
																				 .retrieve()
																				 .bodyToMono(DepartmentDto.class)
																				 .block();
		//----------------------------------------------------------------------------------------
		
		//If found transfer the data from ENTITY to DTO
		EmployeeDto employeeDto = modelMapper.map(emp , EmployeeDto.class);
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartment(departmentDto);
		apiResponseDto.setEmployee(employeeDto);
		
		
		//return modelMapper.map(emp, EmployeeDto.class);
		return apiResponseDto;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	@Override
	public List<APIResponseDto> getAllEmployee() 
	{
		//Find the List of Employee and save it in ENTITY as LIST
		List<Employee> empList = erepo.findAll();
		
		// Create a list to hold the combined API responses
	    List<APIResponseDto> apiResponseList = new ArrayList<>();

	    // Loop through each employee and fetch the department details
	    for (Employee emp : empList) 
	    {
	    	
	    	//----------------------------------------------------------------------------------------
	        // 1. MicroService Communication -> REST Template Method -> REST Template Method:  OLD soon to be deprecated
	        /*
	    	ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(mcrsrvDSGetCodeUrl + emp.getDepartmentCode(), DepartmentDto.class);
	        DepartmentDto departmentDto = responseEntity.getBody();
	        */
	    	
			//2. MicroService Communication -> WebClientMethod Method 
			DepartmentDto departmentDto = webClient.get()
																					 .uri(mcrsrvDSGetCodeUrl + emp.getDepartmentCode().trim())
																					 .retrieve()
																					 .bodyToMono(DepartmentDto.class)
																					 .block();
	        //----------------------------------------------------------------------------------------
	        
	        // Transfer the data from ENTITY to DTO
	        EmployeeDto employeeDto = modelMapper.map(emp, EmployeeDto.class);

	        // Create an APIResponseDto object and set the employee and department details
	        APIResponseDto apiResponseDto = new APIResponseDto();
	        apiResponseDto.setDepartment(departmentDto);
	        apiResponseDto.setEmployee(employeeDto);

	        // Add the APIResponseDto object to the list
	        apiResponseList.add(apiResponseDto);
	    }

	    // Return the list of APIResponseDto objects
	    return apiResponseList;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	@Override
	public EmployeeDto updateEmployeeById(Long empId, EmployeeDto empDto)
	{
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Employee emp = erepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee with id: "+empId+" not found in DB"));
		//----------------------------------------------------------------------------------------
		//Check if the department code of department microservice exist or not
		
		//1. MicroService Communication -> REST Template Method: 
		/*
		ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(mcrsrvDSGetCodeUrl + empDto.getDepartmentCode() , DepartmentDto.class);
		String checkDepCodeExistOrNot = responseEntity.getBody().getDepartmentCode();		
		*/
		
		//2. MicroService Communication -> WebClientMethod Method 
		DepartmentDto departmentDto = webClient.get()
																				 .uri(mcrsrvDSGetCodeUrl + empDto.getDepartmentCode().trim())
																				 .retrieve()
																				 .bodyToMono(DepartmentDto.class)
																				 .block();
		
		//----------------------------------------------------------------------------------------
		//Transfer DTO to ENTITY 
		emp.setFirstName(empDto.getFirstName());
		emp.setLastName(empDto.getLastName());
		emp.setEmail(empDto.getEmail());
		emp.setDepartmentCode(empDto.getDepartmentCode().trim());
		
		//Save the ENTITY details to Persistance
		Employee updateEmp = erepo.save(emp);
		
		//Transfer ENTITY to DTO
		return modelMapper.map(updateEmp, EmployeeDto.class);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void deleteEmployeeById(Long empId) 
	{
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Employee emp = erepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee with id: "+empId+" not found in DB"));
				
		//Delete the user in PERSISTANCE
		erepo.deleteById(empId);
		
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
