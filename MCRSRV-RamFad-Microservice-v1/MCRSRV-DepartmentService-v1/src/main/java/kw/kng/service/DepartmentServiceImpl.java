package kw.kng.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.dto.DepartmentDto;
import kw.kng.entities.Department;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repo.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService 
{

	private DepartmentRepo drepo;
	private ModelMapper modelMapper;

	public DepartmentServiceImpl(DepartmentRepo drepo, ModelMapper modelMapper) 
	{
		this.drepo = drepo;
		this.modelMapper=modelMapper;
	}

	@Override
	public DepartmentDto saveDepartmentSingle(DepartmentDto departmentDto) 
	{
		//Transfer DTO to ENTITY
		Department dep =modelMapper.map(departmentDto, Department.class);
		
		//Save the ENTITY details into PERSISTANCE Layer
		Department savedDepartment= drepo.save(dep);
		
		//After saving transfer ENTITY to DTO
		return modelMapper.map(savedDepartment, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> saveDepartmentMultiple(List<DepartmentDto> departmentDto)
	{
		// Transfer the LIST of DEPARTMENTS from DTO to ENTITY
		List<Department> departmentList = departmentDto.stream().map(dto -> modelMapper.map(dto, Department.class)).collect(Collectors.toList());
		
		//Save the List of DEPARTMENTS in ENTITY using PERSISTENCE
		List<Department> savedDepartmentList = drepo.saveAll(departmentList);
		
		//Transfer the `savedDepartmentList` intp DTO
		return savedDepartmentList.stream().map(d -> modelMapper.map(d, DepartmentDto.class)).collect(Collectors.toList());
	}

	@Override
	public DepartmentDto getDepartmentById(Long departmentId)
	{
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Department dep = drepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department with id: "+departmentId+" not found in DB."));
		
		//Transfer the ENTITY details to DTO
		return modelMapper.map(dep, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> getAllDepartment() 
	{
		//Find the list of department and save the details in ENTITY as List
		List<Department> depList = drepo.findAll();
		
		//Transfer ENTITY to DTO
		return depList.stream().map((u) -> modelMapper.map(u, DepartmentDto.class)).collect(Collectors.toList());	
	}

	@Override
	public DepartmentDto updateDepartment(Long depid, DepartmentDto departmentDto) 
	{
		
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Department dep = drepo.findById(depid).orElseThrow(() -> new ResourceNotFoundException("Department with id: "+depid+" not found in DB."));
		
		//Transfer the DTO details to ENTITY
		dep.setDepartmentName(departmentDto.getDepartmentName());
		dep.setDepartmentDescription(departmentDto.getDepartmentDescription());
		dep.setDepartmentCode(departmentDto.getDepartmentCode());
		
		//Save the ENTITY details to PERSISTENCE
		Department saveDep = drepo.save(dep);
		
		return modelMapper.map(saveDep, DepartmentDto.class);
	}

	@Override
	public void deleteDepartmentById(Long depid) 
	{
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Department dep = drepo.findById(depid).orElseThrow(() -> new ResourceNotFoundException("Department with id: "+depid+" not found in DB."));
				
		drepo.deleteById(depid);
		
	}

	@Override
	 public DepartmentDto getDepartmentByCode(String code) 
	{
        // Search in DB if the data exists or not. If not, throw `ResourceNotFoundException`
        Department dep = drepo.findByDepartmentCode(code)
                                       .orElseThrow(() -> new ResourceNotFoundException("Department with code: " + code + " not found in DB."));
        
        // Transfer the ENTITY details to DTO
        return modelMapper.map(dep, DepartmentDto.class);
    }
	
	
	
	
	
	
	
	
}
