package com.saurav.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.saurav.employee.EmpDetails;
import com.saurav.employee.EmployeeDAO;

@Controller
@RequestMapping("/employeeDetails")
public class ControllerClass {

	private static Logger log = Logger.getLogger(ControllerClass.class);

	@Autowired
	@Qualifier("employeeValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@Resource(name="empdetails")
	private EmployeeDAO employeeDAO;

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getEmployee(Model model) {
		log.debug("Request to fetch all users from the mongo database");
		List<EmpDetails> empList = employeeDAO.getAll();
		log.debug(empList);
		model.addAttribute("empList", empList);
		return "welcome";
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		log.debug("Request to open the new user form page");
		model.addAttribute("empAttr", new EmpDetails());
		return "form";
	}

	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam(value = "empno", required = true) String id, Model model) {
		log.debug("Request to open the edit user form page");
		model.addAttribute("empAttr", employeeDAO.findUserId(id));
		return "form";
	}

	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "empno", required = true) String id, Model model) {
		employeeDAO.delete(id);
		return "redirect:list";
	}

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("empAttr") @Validated EmpDetails empDetails, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "form";
		}
		else if (empDetails.getEmpno() != null && !empDetails.getEmpno().trim().equals("")) {
			employeeDAO.edit(empDetails);
		} else {
			employeeDAO.add(empDetails);
		}
		return "redirect:list";
	}

}
