<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login</title>
	<link rel="shortcut icon" href="https://portal.ptit.edu.vn/wp-content/uploads/2016/04/favicon.png" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css"/>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="/css/common.css"/>
</head>
<style>
	@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,700);
	
	body {
	  background: #ed8e8e;
	}
	
	.login {
	  width: 400px;
	  margin: auto;
	  font-size: 16px;
	}
	
	/* Reset top and bottom margins from certain elements */
	.login-header,
	.login p {
	  margin-top: 0;
	  margin-bottom: 0;
	}
	
	/* The triangle form is achieved by a CSS hack */
	.login-triangle {
	  width: 0;
	  margin-right: auto;
	  margin-left: auto;
	  border: 12px solid transparent;
	  border-bottom-color: #ed4646;
	}
	
	.login-header {
	  background: #ed4646;
	  padding: 20px;
	  font-size: 1.4em;
	  font-weight: normal;
	  text-align: center;
	  text-transform: uppercase;
	  color: #fff;
	}
	
	.login-container {
	  background: #ebebeb;
	  padding: 12px;
	}
	
	/* Every row inside .login-container is defined with p tags */
	.login p {
	  padding: 12px;
	}
	
	.login input {
	  box-sizing: border-box;
	  display: block;
	  width: 100%;
	  border-width: 1px;
	  border-style: solid;
	  padding: 16px;
	  outline: 0;
	  font-family: inherit;
	  font-size: 0.95em;
	}
	
	.login input[type="email"],
	.login input[type="password"] {
	  background: #fff;
	  border-color: #bbb;
	  color: #555;
	}
	
	/* Text fields' focus effect */
	.login input[type="email"]:focus,
	.login input[type="password"]:focus {
	  border-color: #888;
	}
	
	.login input[type="submit"] {
	  background: #ed4646;
	  border-color: transparent;
	  color: #fff;
	  cursor: pointer;
	}
	
	.login input[type="submit"]:hover {
	  background: #f16969;
	}
	
	/* Buttons' focus effect */
	.login input[type="submit"]:focus {
	  border-color: #05a;
	}
</style>
<body>
    <div class="login">
        <div class="login-triangle"></div>
        <h2 class="login-header">Log in</h2>
		<div class="login-container">
		    <p><input type="email" placeholder="Email"></p>
		    <p><input type="password" placeholder="Password"></p>
		    <p><input type="submit" value="Log in"></p>
		</div>
    </div>
    <script src="/js/login.js"></script>
</body>
</html>
