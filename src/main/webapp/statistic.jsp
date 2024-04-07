<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link
      rel="stylesheet"
      href="assets/css/index.css"
    />
    
    
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    <link
      rel="stylesheet"
      href="assets/fonts/fontawesome-free-6.4.2-web/css/all.min.css"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;1,500&family=Roboto:wght@100;300;400;500;700;900&family=Schibsted+Grotesk&display=swap"
      rel="stylesheet"
    />
  <title>Thống kê số sinh viên được thi/ Không được thi</title>
</head>
<body>

	<nav class="navbar-classic navbar navbar-expand-lg navbar navbar-expand navbar-light" style="height: 46px;">
	  <div class="d-flex justify-content-between w-100 h-100">
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
			        <li class="nav-item">
			          <a class="nav-link active" aria-current="page" href="home"><i class="fa-solid fa-house"></i> Trang chủ</a>
			        </li>
			        <li class="nav-item">
			          <a class="nav-link active"><i class="fa-solid fa-chart-simple"></i> Thống kê</a>
			        </li>
	        	</ul>
	        	
	     	</div>
		    
	     </div>
	    <div class="navbar-right-wrap ms-2 d-flex nav-top-wrap navbar-nav h-100">
	      <ul class="navbar-right-wrap ms-auto d-flex nav-top-wrap navbar-nav h-100">
	        <!-- Nút trở về -->
	        <li class="stopevent dropdown d-flex align-items-center h-100">
			    <a class="btn btn-light btn-icon rounded-circle indicator indicator-primary text-muted" id="backButton" onclick="goBack()" aria-expanded="false" style="display: none;">
			        <i class="fa-solid fa-arrow-rotate-left"></i>
			    </a>
			</li>

	        
	        
	        <li class="stopevent dropdown d-flex align-items-center h-100">
	          <a class="btn btn-light btn-icon rounded-circle indicator indicator-primary text-muted" id="dropdownNotification" aria-expanded="false" style="background-color: #c1c4c7;">
	            <i class="fa-regular fa-bell"></i>
	          </a>
	        </li>
	        <li class="ms-2 dropdown d-flex align-items-center h-100">
	          <a class="rounded-circle" id="dropdownUser" aria-expanded="false" style="margin: 10px;">
	            <div class="avatar avatar-md avatar-indicators avatar-online d-flex align-items-center justify-content-center" style="height: 100%;">
	              <img alt="avatar" src="assets/img/avatar_2.png" class="rounded-circle" style="max-width: 100%; height: auto; max-height: 46px;">
	            </div>
	          </a>
	        </li>
	      </ul>
	    </div>
	</nav>
	
	<div class="pageContent">
    <c:choose>
        <c:when test="${not empty terms}">
          <div class="row justify-content-center">
		    <div class="mt-6 col-xl-6 col-lg-12 col-md-12 col-12"> 
		        <div class="card">
		            <div class="card-body">
		            <h1 class="text-center mb-4">Thống kê số sinh viên được thi, không được thi</h1>
		                <div class="row">
		               <div class="col-md-6">
			                <div class="mb-3">
			                    <h4 class="mb-2 text-center">Kỳ học</h4>
			                    <select id="termDropdown" onchange="loadSubjectsByTerm()" class="form-select">
			                        <option value="">Chọn kỳ học</option>
			                        <c:forEach var="term" items="${terms}">
			                            <option value="${term.id}">${term.name}</option>
			                        </c:forEach>
			                    </select>
			                </div>
			            </div>
		                 <div class="col-md-6">
			                <div class="mb-3">
			                    <h4 class="mb-2 text-center">Môn học</h4>
			                    <select id="subjectDropdown" onchange="loadStudentScores()" class="form-select">
			                        <option value="">Chọn môn học</option>
			                    </select>
			                </div>
			            </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>

		<!-- Hiển thị biểu đồ -->
		
		<div class="container" id="chartArea" style="max-width: 50%!important;">
		    <canvas id="myChart"></canvas>
		</div>
		
		
		<!-- <button id="backButton" onclick="goBack()" class="btn btn-outline-secondary mt-3" style="display: none;">Trở
		    lại</button> -->
		
		
		    </c:when>
		    <c:otherwise>
		        <p class="mt-5 mb-0 text-center"><span class="text-dark me-2">Không có thông tin về kỳ học hiện tại.</span></p>
		    </c:otherwise>
		    </c:choose>
		</div>
	  
	<script>

	//Gọi API để lấy danh sách môn học và thêm vào dropdown menu
		function loadSubjectsByTerm() {
		    var termDropdown = document.getElementById("termDropdown");
		    var selectedTermId = termDropdown.value;
		    if (selectedTermId !== "") {
		        fetch("api/statistic/subjects?termId=" + selectedTermId)
		            .then(response => response.json())
		            .then(data => {
		                var subjectDropdown = document.getElementById("subjectDropdown");
		                subjectDropdown.innerHTML = "<option value=''>Chọn môn học</option>";
		
		                data.forEach(subject => {
		                    var option = document.createElement("option");
		                    option.text = subject.name; 
		                    option.value = subject.id; 
		                    subjectDropdown.add(option);
		                });
		            })
		            .catch(error => console.error("Error loading subject", error));
		    }
		}



		function loadStudentScores() {
			var subjectDropdown = document.getElementById("subjectDropdown");
		    var selectedSubjectId = subjectDropdown.value;
		    var termDropdown = document.getElementById("termDropdown");
		    var selectedTermId = termDropdown.value;

		    if (selectedSubjectId !== "" && selectedTermId !== "") {
		        fetch("api/statistic/points?subjectId=" + selectedSubjectId + "&termId=" + selectedTermId)
	            .then(response => response.json())
	            .then(data => {
	            	
	                console.log(data);
	                var numNotQualified = 0;
	                var numQualified = 0;
	                removeChart();

	                // Tạo canvas mới
	                var canvas = document.createElement("canvas");
	                canvas.id = "myChart";

	                // Thêm canvas mới vào container
	                var container = document.getElementById("chartArea");
	                container.appendChild(canvas);
	                
	                // Đếm số lượng sinh viên đủ và không đủ điều kiện
	                data.forEach(point => {
	                    if (point.cc === 0 || point.btl === 0 || point.ktgk === 0 || point.ktck === 0 || point.th === 0) {
	                        numNotQualified++;
	                    } else {
	                        numQualified++;
	                    }
	                });
				
	                drawChart(numNotQualified, numQualified);
	             // Hiển thị nút "Trở lại"
	                var backButton = document.getElementById("backButton");
	                backButton.style.display = "block";
	            })
	            .catch(error => {
	                showErrorDialog("Đã có lỗi xảy ra vui lòng thử lại sau");
	            });
		    }
		}

		function removeChart() {
		    var oldCanvas = document.getElementById("myChart");
		    // Xóa canvas cũ nếu tồn tại
		    if (oldCanvas) {
		        oldCanvas.remove();
		    }
		}
		
		function drawChart(numNotQualified, numQualified) {
			console.log("draw");
			  // Lấy context của canvas
			  var ctx = document.getElementById('myChart').getContext('2d');

			  var totalStudents = numNotQualified + numQualified;

			  // Tạo biểu đồ
			  var myChart = new Chart(ctx, {
			    type: 'bar',
			    data: {
			      labels: ['Không đủ điều kiện', 'Đủ điều kiện'],
			      datasets: [
			        {
			          label: 'Số lượng sinh viên',
			          data: [numNotQualified, numQualified],
			          backgroundColor: [
			            'rgba(0, 123, 255, 1)',
			            'rgba(0, 123, 255, 1)'
			          ],
			          borderColor: [
			            'rgba(0, 123, 255, 1)',
			            'rgba(0, 123, 255, 1)'
			          ],
			          borderWidth: 1
			        }
			      ]
			    },
			    options: {
			      scales: {
			        x: {
			          barPercentage: 0.6,// Độ rộng của các cột so với khoảng cách giữa các cột
			          categoryPercentage: 0.8 // Độ rộng của các nhóm cột so với khoảng cách giữa các nhóm
			        },
			        y: {
			          beginAtZero: true,
			          ticks: {
			            precision: 0 // Hiển thị số liệu là số nguyên
			          }
			        }
			      },
			      plugins: {
			        title: {
			          display: true,
			          text: 'Tổng số sinh viên: ' + totalStudents
			        },
			        tooltip: {
			          callbacks: {
			            label: function(context) {
			              var label = context.dataset.label || '';
			              if (label) {
			                label += ': ';
			              }
			              label += context.parsed.y;
			              return label;
			            }
			          }
			        },
			        datalabels: {
			          display: true,
			          color: 'white',
			          anchor: 'end',
			          align: 'end',
			          formatter: function(value, context) {
			            return value;
			          }
			        }
			      }
			    },
			    plugins: [
			      {
			        id: 'datalabels',
			        font: {
			          size: 16,
			          weight: 'bold'
			        }
			      }
			    ]
			  });
			}
		
		function goBack() {
		    window.history.back();
		}
		function showErrorDialog(errorMessage) {
	        alert(errorMessage);
	        goBack();
	    }
	</script>
</body>

</html>