<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Teacher Dashboard | Enter Point</title>
        <link rel="shortcut icon" href="https://portal.ptit.edu.vn/wp-content/uploads/2016/04/favicon.png" />
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Font Awesome CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css"/>
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
        </style>
    </head>
    <body>
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="exampleModalLabel">Title</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                <span aria-hidden="true">&times;</span>
		                </button>
		            </div>
		            <div class="modal-body">
		                <div>
		                    <div class="form-group">
		                        <label for="recipient-name" class="col-form-label lb-cc">CC:</label>
		                        <input type="text" class="form-control" id="cc" name="cc">
		                    </div>
		                    <div class="form-group">
                                <label for="recipient-name" class="col-form-label lb-btl">BTL:</label>
                                <input type="text" class="form-control" id="btl" name="btl">
                            </div>
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label lb-th">TH:</label>
                                <input type="text" class="form-control" id="th" name="th">
                            </div>
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label lb-ktgk">KTGK:</label>
                                <input type="text" class="form-control" id="ktgk" name="ktgk">
                            </div>
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label lb-ktck">KTCK:</label>
                                <input type="text" class="form-control" id="ktck" name="ktck">
                            </div>
		                </div>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-secondary btn-cancle" data-dismiss="modal">Hủy</button>
		                <button type="button" class="btn btn-primary btn-save">Lưu</button>
		            </div>
		        </div>
		    </div>
		</div>
        <div id="teacher-id" style="display: none;">${teacher.id}</div>
        <!-- Sidebar -->
        <div class="sidebar">
            <a href="/teacher-dashboard"><i class="fas fa-home"></i> Home</a>
            <a href="/teacher-dashboard/enter-point" style="background-color: blue;"><i class="fas fa-cog"></i> Nhập điểm và tính điểm trung bình</a>
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
            <div class="container-xl main-content">
                <h1>Nhập điểm và tính điểm trung bình</h1>
                <div class="selector-condition mt-5">
		            <select class="form-control condition" id="term">
					    <option>-- Chọn học kì --</option>
		            </select>
		            <select class="form-control condition" id="subject">
                        <option>-- Chọn môn học --</option>
                    </select>
                    <select class="form-control condition" id="class">
                        <option>-- Chọn lớp --</option>
                    </select>
                </div>
                
                <div class="wapper-btn-import">
	                <label for="excelFile" class="btn btn-success">
	                    <i class="fa-solid fa-upload"></i>
	                    <span>Import Excel</span>
	                </label>
	                <input type="file" id="excelFile" name="excelFile" style="display: none;"/>
                </div>
            </div>
            <div class="table-wapper"></div>
            
            <div class="pagination-wapper"></div>
        </div>
        <!-- Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.js"></script>
        <script src="/js/enter-point.js"></script>
    </body>
</html>
