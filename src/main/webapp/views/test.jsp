<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Test</title>
</head>
<body>
    <h1>Upload File</h1>
    <form action="/test" method="POST" enctype="multipart/form-data">
        <input type="file" name="excelFile" /><br><br>
        <input type="text" name="name" /><br><br>
        <button type="submit">SEND</button>
    </form>
</body>
</html>
