package com.saurav.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.saurav.employee.EmpDetails;

public class EmployeeFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return EmpDetails.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salary", "salary.required");

		EmpDetails emp = (EmpDetails) target;
		if (emp.getSalary()!="" && Integer.parseInt(emp.getSalary()) <= 20000) {
			errors.rejectValue("salary", "salaryValid", new Object[] { "'Salary'" }, "salary cannot be less than 20k");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "position.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "managername", "managername.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dept", "dept.required");
		
	}

}
