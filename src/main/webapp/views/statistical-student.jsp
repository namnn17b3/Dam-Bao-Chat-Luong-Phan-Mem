<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Teacher Dashboard | Statistical Student</title>
        <link rel="shortcut icon" href="https://portal.ptit.edu.vn/wp-content/uploads/2016/04/favicon.png" />
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Font Awesome CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css"/>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            body {
                padding-bottom: 50px;
            }
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
                font-size: 20px;
            }
            
            .main-content {
                display: flex;
                flex-direction: column;
                width: 100%;
                height: 100%;
                h1 {
                    margin-top: 25px;
                    margin-left: auto;
                    margin-right: auto;
                    text-align: center;
                }
            }
            
            .selector-condition {
                display: flex;
                flex-wrap: wrap;
                .condition {
                    flex: 1;
                }
                .condition + .condition {
                    margin-left: 50px;
                }
            }
            
            th, td {
                text-align: center;
                vertical-align: middle !important;
            }
            
            .table-wapper {
                width: 90%;
                overflow-x: auto;
                margin-left: auto; 
                margin-right: auto;
             }
             
             .wapper-btn-import {             
                 display: none;
                 flex-direction: row-reverse;
                 margin-top: 20px;
             }
             
             .pagination-wapper {
                display: flex;
                margin: 25px auto auto auto;
                flex-direction: row-reverse;
                width: 90%;
             }
             #myChart {
                width: 100% !important;
                height: 100% !important;
             }
             
             .btn-back {
                width: auto !important;
                height: 100% !important;
             }
        </style>
    </head>
    <body>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="margin: 0 auto;">
            <div class="modal-dialog modal-dialog-centered modal-lg" style="max-width: 52vw;">
                <div class="modal-content" style="width: 100%;">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel" style="font-size: 25px;">Biểu đồ thống kê</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <canvas id="myChart"></canvas>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success btn-cancle btn-back" data-dismiss="modal">BACK</button>
                    </div>
                </div>
            </div>
        </div>
        <button style="display: none;" type="button" class="btn btn-info btn-statistical-student" data-toggle="modal" data-target="#exampleModal">
        </button>
        <div id="teacher-id" style="display: none;">${teacher.id}</div>
        <!-- Sidebar -->
        <div class="sidebar">
            <a href="/teacher-dashboard"><i class="fas fa-home"></i> Home</a>
            <a href="/teacher-dashboard/enter-point"><i class="fas fa-cog"></i> Nhập điểm và tính điểm trung bình</a>
            <a href="/teacher-dashboard/statistical-student" style="background-color: blue;"><i class="fas fa-chart-bar"></i> Thống kê số sinh viên được thi và không được thi</a>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
        </div>
        <!-- Page content -->
        <div class="content">
            <!-- Top bar -->
            <div class="top-bar">
                <span> </span>
                <span class="username">Xin chào, <span><c:out value="${teacher.name}"/></span></span>
            </div>
            <!-- Main content -->
            <div class="container-xl main-content">
                <h1>Thống kê số sinh viên được thi và không được thi</h1>
                <div class="selector-condition mt-5">
                    <select class="form-control condition" id="term">
                        <option>-- Chọn học kì --</option>
                    </select>
                    <select class="form-control condition" id="subject">
                        <option>-- Chọn môn học --</option>
                    </select>
                </div>
            </div>
        </div>
        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.js"></script>
        <script src="/js/statistical-student.js"></script>
    </body>
</html>
    