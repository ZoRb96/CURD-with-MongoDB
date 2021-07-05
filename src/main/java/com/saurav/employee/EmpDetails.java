package com.saurav.employee;

import org.springframework.data.annotation.Id;


public class EmpDetails {

	@Id
	private String id;
	
	private String empno;
	private String name;
	private String position;
	private String salary;
	private String managername;
	private String dept;

	public EmpDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpDetails(String id, String empno, String name, String position, String salary, String managername,
			String dept) {
		super();
		this.id = id;
		this.empno = empno;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.managername = managername;
		this.dept = dept;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}
