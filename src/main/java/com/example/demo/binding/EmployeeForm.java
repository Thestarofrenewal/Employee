package com.example.demo.binding;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class EmployeeForm {
	

	@NotBlank(message = "{app.name}")
    private String name;

	@NotBlank(message = "{app.designation}")
    private String designation;
	
}
