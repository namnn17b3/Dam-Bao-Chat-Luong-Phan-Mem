<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<div class="header"></div>
	<div class="content" style="display: flex">
		<div class="list-tab">
			<a href="/DBCLPM/nhap-diem"></a>
		</div>
		<div class="content-tab">
			<link rel="stylesheet" href="css/nhap-diem.css">
			<div class="list-term">
				<h2>danh sach ky hoc dang dien ra</h2>
				<table>
					<tr>
						<td>id</td>
						<td>name</td>
						<td></td>
					</tr>
				</table>
			</div>
			
			<div class="list-subject" style="display: none">
				<h2>danh sach mon hoc</h2>
				<button onclick="back()">quay lai</button>
				<table>
					<tr>
						<td>id</td>
						<td>name</td>
						<td>CC</td>
						<td>BTL</td>
						<td>TH</td>
						<td>GK</td>
						<td>KTHP</td>
						<td></td>
					</tr>
				</table>
			</div>
			
			<div class="list-class" style="display: none">
				<h2>danh sach lop hoc</h2>
				<button onclick="back()">quay lai</button>
				<table>
					<tr>
						<td>id</td>
						<td>name</td>
						<td>room</td>
						<td>teacher name</td>
						<td></td>
					</tr>
				</table>
			</div>
			<div class="list-student" style="display: none">
				<h2>danh sach sinh vien</h2>
				<button onclick="back()">quay lai</button>
				<button style="float: right">import excel</button>
				<table>
					<tr>
						<td >code</td>
						<td>name</td>
						<td>phone</td>
						<td>address</td>
						<td>date of birth</td>
						<td>class</td>
						<td class="cc">CC</td>
						<td class="dbt">DBT</td>
						<td class="dktth">DKTTH</td>
						<td class="dgk">DGK</td>
						<td class="dtkthp">DTKTHP</td>
						<td>DTK</td>
						<td></td>
					</tr>
				</table>
			</div>
			
			<div class="nhap-diem" style="display: none">
				<h2>nhap diem cho sinh vien: <span class="student-name"></span></h2>
				<h2>ma SV: <span class="student-code"></span></h2>
				<button onclick="back()">quay lai</button>
				
				<table>
					<tr>
						<td>chuyen can</td>
						<td>bai tap</td>
						<td>thuc hanh</td>
						<td>kiem tra giua ky</td>
						<td>kiem tra cuoi ky</td>
					</tr>
					<tr>
						<td><input id="cc" type="number" min="0" max="10" step="0.1"></td>
						<td><input id="btl" type="number" min="0" max="10" step="0.1"></td>
						<td><input id="th" type="number" min="0" max="10" step="0.1"></td>
						<td><input id="ktgk" type="number" min="0" max="10" step="0.1"></td>
						<td><input id="ktck" type="number" min="0" max="10" step="0.1"></td>
					</tr>
				</table>
				<button class="submit" onclick="submitScore()">luu</button>
			</div>
			<script>
				var term_id = -1;
				var teacher_id = -1;
				var subject_id = -1;
				var class_id = -1;
				var student = null;
				var cc = null;
				var dbt = null;
				var dktth = null;
				var dgk = null;
				var dtkthp =  null;
			</script>
			<script src="js/nhap-diem.js"></script>
		</div>
	</div>
	<div class="footer"></div>
</body>
</html>