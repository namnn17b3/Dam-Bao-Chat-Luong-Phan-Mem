<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Teacher Dashboard</title>
        <link rel="shortcut icon" href="https://portal.ptit.edu.vn/wp-content/uploads/2016/04/favicon.png" />
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Font Awesome CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <style>
            /* Set the width of the sidebar */
            .sidebar {
                height: 100%;
                width: 250px;
                position: fixed;
                top: 0;
                left: 0;
                background-color: #343a40;
                padding-top: 50px;
            }
            /* Style sidebar links */
            .sidebar a {
                padding: 10px 15px;
                text-decoration: none;
                font-size: 20px;
                color: #fff;
                display: block;
            }
            /* Change color on hover */
            .sidebar a:hover {
                background-color: #666;
            }
            /* Page content */
            .content {
                margin-left: 250px;
            }
            /* Top bar */
            .top-bar {
                background-color: #f8f9fa;
                border-bottom: 1px solid #ddd;
                padding: 10px 20px;
                width: 100%;
                display: flex;
                justify-content: space-between;
            }
            /* Username display */
            .username {
                margin-right: 20px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <div class="sidebar">
            <a href="/teacher-dashboard" style="background-color: blue;"><i class="fas fa-home"></i> Home</a>
            <a href="/teacher-dashboard/enter-point"><i class="fas fa-cog"></i> Nhập điểm và tính điểm trung bình</a>
            <a href="/teacher-dashboard/statistical-student"><i class="fas fa-chart-bar"></i> Thống kê số sinh viên được thi và không được thi</a>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
        </div>
        <!-- Page content -->
        <div class="content">
            <!-- Top bar -->
            <div class="top-bar">
                <span> </span>
                <span class="username">Xin chào, <c:out value="${teacher.name}"/></span>
            </div>
            <!-- Main content -->
            <h2 style="margin-top: 10px; padding-left: 25px;">Dashboard</h2>
            <p style="padding-left: 25px;">Welcome to teacher dashboard.</p>
        </div>
        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

