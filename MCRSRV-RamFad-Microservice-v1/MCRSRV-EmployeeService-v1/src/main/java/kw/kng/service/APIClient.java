package kw.kng.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kw.kng.dto.DepartmentDto;

@FeignClient(url="${mcrsrv-ds-get-dep-url}", value="DEPARTMENT-SERVICE")
public interface APIClient 
{
	
	@GetMapping("/{code}")
	DepartmentDto getDepartmentByCode(@PathVariable("code") String depCode);

	
}
