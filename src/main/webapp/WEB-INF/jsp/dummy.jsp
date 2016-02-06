<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Dummy page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous" />

		<link rel="shortcut icon" href="/static/favicon.ico" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

		<style type="text/css">
			div.checkbox span label {
		 		display: block 
		 	};
		</style>
	</head>
	<body class="container">
		<div class="row">
		<div class="row">
			<h1>Add note</h1>
			<p class="lead">
				<a href="/dummyList" style="padding-left:20px">go to list</a>
			</p>
		</div>
        	<div class="col-md-4 col-md-offset-4">
        		<form:form method="post" action="/createNote" commandName="note" id="bar">
					<div class="form-group">
						<label for="title">Title</label>
						<form:input path="title" class="form-control" />
					</div>

					<div class="form-group">
						<label for="body">Body</label>
						<form:input path="body" class="form-control" />
					</div>
				
					<div class="form-group">
						<div class="checkbox">
							<label>
			  					<form:checkboxes path="tags" items="${tag_items}" />	
							</label>
						</div>
					</div>
					
					<input type="submit" value="Pure" class="btn btn-primary btn-block" />
					<a role="button" class="btn btn-default btn-block" 
						onclick="$.post('/ajax',$('#bar').serialize(), 
							function(){location.reload();});">Ajax</a>
				</form:form>
        	</div>
		</div>
	</body>
</html>