<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin người dùng</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body>
	<div class="container mt-3">
		<div class="d-flex justify-content-center mb-4">
			<div class="card" style="width: 25rem;">
				<div class="card-header text-center h4 bg-primary text-white">USER</div>
				<div class="card-body">
					<form action="./index" method="post">
						<div class="mb-3">
							<label class="form-label">Username</label> <input type="text"
								class="form-control" name="id" value="${user.id }">
						</div>
						<div class="mb-3">
							<label class="form-label">Password</label> <input type="password"
								class="form-control" name="password" value="${user.password }">
						</div>
						<div class="mb-3">
							<label class="form-label">Fullname</label> <input type="text"
								class="form-control" name="fullname" value="${user.fullname }">
						</div>
						<div class="mb-3">
							<label class="form-label">Email address</label> <input
								type="email" class="form-control" name="email"
								value="${user.email }">
						</div>
						<div class="mb-3">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="admin"
									id="admin" value="true" checked> <label
									class="form-check-label">Admin</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="admin"
									id="user" value="false"> <label
									class="form-check-label">User</label>
							</div>
						</div>

						<div class="mb-3 text-center">
							<button type="submit" class="btn btn-primary"
								formaction="./create">Create</button>
							<button type="submit" class="btn btn-primary"
								formaction="./update">Update</button>
							<button type="submit" class="btn btn-primary"
								formaction="./delete?id=${user.id }">Delete</button>
							<button type="submit" class="btn btn-primary">Reset</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Password</th>
						<th scope="col">Fullname</th>
						<th scope="col">Email</th>
						<th scope="col">Admin</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users }">
						<tr>
							<th scope="row">${user.id }</th>
							<td>${user.password }</td>
							<td>${user.fullname }</td>
							<td>${user.email }</td>
							<td>${user.admin }</td>
							<td><a href="./edit?id=${user.id}"
								class="btn btn-warning text-white" role="button">Edit </a> <a
								href="./delete?id=${user.id}" class="btn btn-danger"
								role="button">Remove </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>