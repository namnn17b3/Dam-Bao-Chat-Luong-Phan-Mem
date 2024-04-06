<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Trang Chủ</title>
</head>
<body>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <h1 class="text-center mb-4">Trang Chủ</h1>
        <div class="d-grid gap-2">
          <!-- Nút "Nhập Điểm" -->
          <button class="btn btn-primary" type="button" onclick="goToNhapDiem()">Nhập Điểm</button>
         <!-- Nút "Thống Kê" -->
          <a href="statistic" class="btn btn-success">Thống Kê</a>
      </div>
    </div>
  </div>


  <!-- Script JavaScript để chuyển hướng khi nhấn nút -->
  <script>
    function goToNhapDiem() {
      window.location.href = "nhapdiem";
    }

    
  </script>
</body>
</html>