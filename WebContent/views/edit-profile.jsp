<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Edit Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center mb-4">
			<div class="card" style="width: 25rem;">
				<div class="card-header text-center h4 bg-primary text-white">Edit
					Profile</div>
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
							<button type="submit" class="btn btn-primary px-4" formaction="">Update</button>
						</div>
					</form>
				</div>
				
				<i class="text-center fw-light text-danger">${message }</i>
			</div>
		</div>
	</div>
</body>
</html>