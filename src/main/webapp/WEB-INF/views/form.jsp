<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Employee form</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h3 id="form_header" align="center">Employee
			Form</h3>
		<div></div>

		<!-- User input form to add a new user or update the existing user-->
		<c:url var="saveUrl" value="/EmployeeDetails/save" />
		<form:form id="user_form" modelAttribute="empAttr"
			 method="POST" action="save" class="mt-5">

			<form:hidden path="empno" />
			<div class="form-group">
				<form:input path="name" cssClass="form-control"
					placeholder="Enter Employee Name" />
					<form:errors path="name" cssClass="error" />
			</div>

			<div class="form-group">
				<form:input path="position" cssClass="form-control"
					placeholder="Enter Employee Position" />
					<form:errors path="position" cssClass="error" />
			</div>

			<div class="form-group">
				<form:input path="salary" type="number" cssClass="form-control"
					placeholder="Enter Employee Salary" />
					<form:errors path="salary" cssClass="error" />
			</div>

			<div class="form-group">
				<form:input path="managername" cssClass="form-control"
					placeholder="Enter Employee's Manager Name" />
					<form:errors path="managername" cssClass="error" />
			</div>

			<div class="form-group">
				<form:input path="dept" cssClass="form-control"
					placeholder="Enter Employee Department" />
					<form:errors path="dept" cssClass="error" />
			</div>

			<div class="container">
				<button id="saveBtn" type="submit" class="btn btn-outline-success float-right">ADD EMPLOYEE</button>
			</div>

		</form:form>
	</div>
</body>
</html>