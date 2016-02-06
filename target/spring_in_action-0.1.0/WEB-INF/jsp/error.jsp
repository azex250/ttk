<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Dummy page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous" />
		<link rel="shortcut icon" href="/static/favicon.ico" />
	</head>
	<body class="container">
		<div class="row">
			<h1>Error 500</h1>
			<p class="lead">
				<c:out value="${message}"/>
			</p>
		</div>
	</body>
</html>