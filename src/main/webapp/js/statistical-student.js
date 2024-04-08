const chooseTerm = document.querySelector('#term');
const chooseSubject = document.querySelector('#subject');
const teacherId = +document.querySelector('#teacher-id').innerText;
let termId = null;
let subjectId = null;

const btnModalSave = document.querySelector('.btn-save');
const btnModalCancel = document.querySelector('.btn-cancle');
const btnStatisticalStudent = document.querySelector('.btn-statistical-student');

let myChart = null;
const username = document.querySelector('.username span').innerText;

function sweetalert(message, icon) {
    Swal.fire({
        title: 'Notify',
        text: message,
        icon: icon
    });
}

function change(value) {
    const date = new Date(value);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    
    return (day < 10 ? '0' : '') + day + '/' + (month < 10 ? '0' : '') + month + '/' + year;
}

function drawChart(dataset) {
    if (myChart) {
        myChart.destroy(); // Hủy bỏ biểu đồ cũ trước khi vẽ biểu đồ mới
    }
    // Dữ liệu
    const labels = dataset.className;
    const studentsPassed = dataset.accept;
    const studentsFailed = dataset.reject;
    
    const termName = chooseTerm.options[chooseTerm.selectedIndex].text;
    const subjectName = chooseSubject.options[chooseSubject.selectedIndex].text;
    
    // Tạo biểu đồ cột đứng
    const ctx = document.getElementById('myChart').getContext('2d');
    myChart = new Chart(ctx, {
        type: 'bar', // Chuyển sang loại biểu đồ cột đứng
        data: {
            labels: labels,
            datasets: [{
                label: 'Được thi',
                data: studentsPassed,
                backgroundColor: 'rgba(54, 162, 235, 0.5)', // Màu nền của cột được thi
                borderColor: 'rgba(54, 162, 235, 1)', // Màu viền của cột được thi
                borderWidth: 1
            }, {
                label: 'Không được thi',
                data: studentsFailed,
                backgroundColor: 'rgba(255, 99, 132, 0.5)', // Màu nền của cột không được thi
                borderColor: 'rgba(255, 99, 132, 1)', // Màu viền của cột không được thi
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            //indexAxis: 'y', // Đặt trục hoành là trục y
            scales: {
                x: {
                    beginAtZero: true // Bắt đầu từ 0 trên trục x
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: `Số sinh viên được thi và không được thi các lớp học phần môn ${subjectName} - Học kì ${termName} - Giảng viên ${username}` // Đặt tiêu đề cho biểu đồ
                }
            }
        }
    });
    btnStatisticalStudent.click();
}

function fetchAnddrawChart(termId, subjectId) {
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
    
    fetch(`http://localhost:8080/api/teacher/statistical?termId=${termId}&subjectId=${subjectId}`, requestOptions)
        .then((response) => response.json())
        .then((result) => {
            const { message, status, data } = result;
            if (status != 200) {
                sweetalert(message, 'info');
                return;
            }
            if (!data || !data.length) {
                sweetalert('No data', 'info');
                return;
            }
            const dataset = {
                accept: null,
                reject: null,
                className: null,
            }
            Object.keys(dataset).forEach(key => {
                dataset[key] = data.map(item => {
                    return item[key];
                });
            });
            drawChart(dataset);
        })
        .catch((error) => console.error(error));
}

function renderSelectionSubject() {
    if (!termId) {
        chooseSubject.innerHTML = '<option>-- Chọn môn học --</option>';
        subjectId = null;
        return;
    }
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };

    fetch(`${window.location.protocol}//${window.location.host}/api/teacher/subject?teacherId=${teacherId}&termId=${termId}`, requestOptions)
        .then((response) => response.json())
        .then((result) => {
            const data = result.data;
            let html = '<option>-- Chọn môn học --</option>';
            for (let i = 0; i < data.length; i++) {
                html += `                
                    <option value="${data[i].id}" ${subjectId == data[i].id ? 'selected' : ''}>${data[i].name}</option>
                `;
            }
            chooseSubject.innerHTML = html;
            chooseSubject.onclick = () => {
                // draw chart
                subjectId = +chooseSubject.value;
                if (!subjectId) return;
                fetchAnddrawChart(termId, subjectId);
            }
            if (!data.length) {
                sweetalert('No data', 'error');
            }
        })
        .catch((error) => console.error(error));
}

function renderSelectionTerm() {
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };

    fetch(`${window.location.protocol}//${window.location.host}/api/teacher/term?lt=1`, requestOptions)
        .then((response) => response.json())
        .then((result) => {
            const data = result.data;
            let html = '<option>-- Chọn học kì --</option>';
            for (let i = 0; i < data.length; i++) {
                html += `           
                    <option value="${data[i].id}" ${termId == data[i].id ? 'selected' : ''} title="${data[i].name} ${change(data[i].startDate)} - ${change(data[i].endDate)}">
                        ${data[i].name} &nbsp;&nbsp;${change(data[i].startDate)} - ${change(data[i].endDate)}
                    </option>
                `;
            }
            chooseTerm.innerHTML = html;
            chooseTerm.onclick = () => {
                termId = +chooseTerm.value;
                renderSelectionSubject();
            }
            if (!data.length) {
                sweetalert('No data', 'error');
            }
        })
        .catch((error) => console.error(error));
}

renderSelectionTerm();
//renderSelectionSubject();
