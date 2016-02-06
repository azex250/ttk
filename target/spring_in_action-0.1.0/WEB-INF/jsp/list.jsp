<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Dummy page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous" />

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
		<link rel="shortcut icon" href="/static/favicon.ico" />
	</head>
	<body class="container">
		<div class="row">
			<h1>Notes List</h1>
			<p class="lead">
				<a href="/" style="padding-left:20px">go to add form</a>
			</p>
		</div>
		<div class="row">
			<form method="get" action="/dummyList" class="form-inline">
				<div class="form-group">
					<input placeholder="Search by title" name="title" class="form-control" type="text" />
					<button class="btn btn-default" type="submit">Find</button>
				</div>
			</form> 
		</div>
		<div class="row">
			<table class="table table-striped table-bordered table-hover table-sm">
				<thead class="thead-inverse">
					<tr>
						<th>title</th>
						<th>body</th>
						<th>tags</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${notes}" var="note">   
						<tr>
							<td><c:out value="${note.title}"/></td>
							<td><c:out value="${note.body}"/></td>
							<td>
								<c:forEach items="${note.tags}" var="tag">
									<a href="dummyList?tag=${tag}"><c:out value="${tag}"/></a>
								</c:forEach>
							</td>
						</tr>  
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>