<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee panel</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

	<div class="container">

		<a href='<c:url value='/employeeDetails/add'></c:url>'>Add
			Employee</a>
			
			<c:if test="${empList != null}">
			<div class="table-responsive-sm">
		<table id="users_table" class="table table-bordered mt-5">

			<thead>
				<tr align="center">
					<th>Employee Id</th>
					<th>Name</th>
					<th>Position</th>
					<th>Salary</th>
					<th>Manager Name</th>
					<th>Department</th>
					<th colspan="2">Action</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${empList }" var="data">
					<tr align="center">
						<td><c:out value="${data.empno }" /></td>
						<td><c:out value="${data.name }" /></td>
						<td><c:out value="${data.position }" /></td>
						<td><c:out value="${data.salary }" /></td>
						<td><c:out value="${data.managername }" /></td>
						<td><c:out value="${data.dept }" /></td>
						<td><a id="update"
							href='<c:url value="/employeeDetails/edit?empno=${data.empno }" />'
							class="btn btn-primary">Update</a></td>
						<td><a id="delete"
							href='<c:url value="/employeeDetails/delete?empno=${data.empno }" />'
							class="btn btn-outline-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		</div>
		</c:if>
	</div>

</body>
</html>