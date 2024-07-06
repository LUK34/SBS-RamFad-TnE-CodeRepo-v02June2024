package kw.kng.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kw.kng.dto.DepartmentDto;

//@FeignClient(url="${mcrsrv-ds-get-dep-url}", value="DEPARTMENT-SERVICE")
@FeignClient( value="DEPARTMENT-SERVICE")
public interface APIClient 
{
	
	@GetMapping("/{code}")
	DepartmentDto getDepartmentByCode(@PathVariable("code") String depCode);

	
}


/*
 we commented the above line because video 107-> we have configured multiple instance of DepartmentService.
 If we include url then this application will depend on that specific instance, we have already configured both EmployeeService and
 DepartmentService as Eureka Client to Eureka Server. For DepartmentService , we have 2 instance. so if one instance is overwhelemed with load. 
 Then the other instance will pick up the load.
 
 */