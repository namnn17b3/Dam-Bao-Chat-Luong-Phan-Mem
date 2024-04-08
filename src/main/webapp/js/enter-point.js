const chooseTerm = document.querySelector('#term');
const chooseSubject = document.querySelector('#subject');
const chooseClass = document.querySelector('#class');
const querySearch = new URLSearchParams(window.location.search);
const teacherId = +document.querySelector('#teacher-id').innerText;
const tableWapper = document.querySelector('.table-wapper');
const paginationWapper = document.querySelector('.pagination-wapper');
const nodes = 3;
let currentSubject = null;

let termId = +querySearch.get('termId') ? +querySearch.get('termId') : null;
let subjectId = +querySearch.get('subjectId') ? +querySearch.get('subjectId') : null;
let classId = +querySearch.get('classId') ? +querySearch.get('classId') : null;
let _page = +querySearch.get('page') ? +querySearch.get('page') : 1;

const btnModalSave = document.querySelector('.btn-save');
const btnModalCancel = document.querySelector('.btn-cancle');
const wapperBtnImport = document.querySelector('.wapper-btn-import');

function sweetalert(message, icon) {
    Swal.fire({
        title: 'Notify',
        text: message,
        icon: icon
    });
}

function savePoint(pointId, modalInp, pointInfo) {
    const [ccInp, btlInp, thInp, ktgkInp, ktckInp] = modalInp;
    const urlencoded = new URLSearchParams();
    urlencoded.append("pointId", `${pointId}`);
    urlencoded.append("cc", ccInp.value);
    urlencoded.append("btl", btlInp.value);
    urlencoded.append("th", thInp.value);
    urlencoded.append("ktgk", ktgkInp.value);
    urlencoded.append("ktck", ktckInp.value);
    
    console.log(ccInp.value, btlInp.value, thInp.value, ktgkInp.value, ktckInp.value);

    const requestOptions = {
        method: "PUT",
        body: urlencoded,
        redirect: "follow"
    };
    
    fetch("http://localhost:8080/api/teacher/save-point", requestOptions)
        .then((response) => response.json())
        .then((result) => {
            console.log('response:', result);
            const { message, status, data } = result;
            
            if (status != 200) {
                alert(message);
                return;
            }
            ['cc', 'btl', 'th', 'ktgk', 'ktck', 'scoreByNumber', 'scoreByWord', 'scorePerFourRank', 'note'].forEach((key, index) => {
                pointInfo[index].innerText = data[key] != undefined ? data[key] : '--';
            });
            btnModalCancel.click();
            sweetalert(message, 'success');
        })
        .catch((error) => console.error(error));
}

function change(value) {
    const date = new Date(value);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    
    return (day < 10 ? '0' : '') + day + '/' + (month < 10 ? '0' : '') + month + '/' + year;
}

function renderPagination(data) {
    const quantity = data.quantity;
    const itemInPage = data.itemInPage;
    const page = data.page;
    const allPage = quantity % itemInPage == 0
        ? quantity / itemInPage | 0
        : (quantity / itemInPage) | 0 + 1;
    const startNode = page % nodes == 0 ? page - nodes + 1 : page - page % nodes + 1;
    const endNode = startNode + nodes - 1 <= allPage
        ? startNode + nodes - 1
        : allPage;
    const prev = page > 1 ? page - 1 : null;
    const next = page + 1 <= allPage ? page + 1 : null;
    let html = `
        <nav aria-label="...">
            <ul class="pagination">
    `;
    html += `
        <li class="page-item${prev ? '' : ' disabled'}">
            <a class="page-link" href="?page=${prev}&termId=${termId}&subjectId=${subjectId}&classId=${classId}">Previous</a>
        </li>
    `;
    for (let i = startNode; i <= endNode; i++) {
        html += `
            <li class="page-item${page == i ? ' active' : ''}" aria-current="page">
                <a class="page-link" href="?page=${i}&termId=${termId}&subjectId=${subjectId}&classId=${classId}">${i}</a>
            </li>
        `;
    }
    html += `
        <li class="page-item${next ? '' : ' disabled'}">
            <a class="page-link" href="?page=${next}&termId=${termId}&subjectId=${subjectId}&classId=${classId}">Next</a>
        </li>
    `;
    html += `
            </ul>
        </nav>
    `;
    paginationWapper.innerHTML = html;
}

function renderTable(data) {
    const studentPointTables = data.studentPointTables;
    let stt = (data.page - 1) * data.itemInPage + 1;
    let html = `
        <table class="table table-striped mt-3" style="width: 2000px">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">MSV</th>
                    <th scope="col">Họ và tên</th>
                    <th scope="col">Ngày sinh</th>
                    <th scope="col">Lớp</th>
                    <th scope="col">CC (${currentSubject.percentCC}%)</th>
                    <th scope="col">BTL (${currentSubject.percentBTL}%)</th>
                    <th scope="col">TH (${currentSubject.percentTH}%)</th>
                    <th scope="col">KTGK (${currentSubject.percentKTGK}%)</th>
                    <th scope="col">KTCK (${currentSubject.percentKTCK}%)</th>
                    <th scope="col">ĐTK (Bằng số)</th>
                    <th scope="col">ĐTK (Bằng chữ)</th>
                    <th scope="col">ĐTK (Hệ 4)</th>
                    <th scope="col">Ghi chú</th>
                    <th scope="col"> </th>
                </tr>
            </thead>
            <tbody>
    `;
    
    studentPointTables.forEach(item => {
        const student = item.student;
        const point = item.point;
        const pointExtend = item.pointExtent;
        html += `
            <tr>
                <td class="pointId" style="display: none;">${point.id}</td>
                <td scope="row">${stt}</td>
        `;
        Object.keys(student).forEach(key => {
            html += `
                <td ${key == 'id' ? 'class="msv"' : ''}>${key == 'dateOfBirth' ? change(student[key]) : (key == 'id' ? 'SV' + student[key] : student[key])}</td>
            `;
        });
        Object.keys({
            cc: point.cc,
            btl: point.btl,
            th: point.th,
            ktgk: point.ktgk,
            ktck: point.ktck,
        }).forEach(key => {
            html += `<td class="${key}">${point[key] != undefined ? point[key]: '--'}</td>`;
        });
        ['scoreByNumber', 'scoreByWord', 'scorePerFourRank', 'note'].forEach(key => {
            html += `<td class="${key}">${pointExtend[key] != undefined ? pointExtend[key] : '--'}</td>`;
        });
        html += `
                <td>
                    <button type="button" class="btn btn-info btn-edit-point" data-toggle="modal" data-target="#exampleModal">
                        <i class="fa-solid fa-pen-to-square"></i>
                        <span>Nhập điểm</span>
                    </button>
                </td>
            </tr>
        `;
        stt++;
    });
    tableWapper.innerHTML = html;
    const btnEditPoints = document.querySelectorAll('.btn-edit-point');
    btnEditPoints.forEach(item => {        
        item.addEventListener('click', () => {
            const row = item.parentElement.parentElement;
            const msv = row.querySelector('.msv').innerText.trim();
            document.querySelector('.modal-title').innerText = `Nhập điểm cho ${msv}`;

            document.querySelector('.lb-cc').innerText = `CC (${currentSubject.percentCC}%):`;
            document.querySelector('.lb-btl').innerText = `BTL (${currentSubject.percentBTL}%):`;
            document.querySelector('.lb-th').innerText = `TH (${currentSubject.percentTH}%):`;
            document.querySelector('.lb-ktgk').innerText = `KTGK (${currentSubject.percentKTGK}%):`;
            document.querySelector('.lb-ktck').innerText = `KTCK (${currentSubject.percentKTCK}%):`;

            const ccInp = document.querySelector('#cc');
            const btlInp = document.querySelector('#btl');
            const thInp = document.querySelector('#th');
            const ktgkInp = document.querySelector('#ktgk');
            const ktckInp = document.querySelector('#ktck');

            const cc = row.querySelector('.cc').innerText.trim();
            const btl = row.querySelector('.btl').innerText.trim();
            const th = row.querySelector('.th').innerText.trim();
            const ktgk = row.querySelector('.ktgk').innerText.trim();
            const ktck = row.querySelector('.ktck').innerText.trim();
            
            const modalText = [cc, btl, th, ktgk, ktck];
            const modalInp = [ccInp, btlInp, thInp, ktgkInp, ktckInp];
            const scoreByNumber = row.querySelector('.scoreByNumber');
            const scoreByWord = row.querySelector('.scoreByWord');
            const scorePerFourRank = row.querySelector('.scorePerFourRank');
            const note = row.querySelector('.note');
            
            modalInp.forEach((item, index) => {
                item.value = modalText[index] == '--' ? '' : modalText[index];
            });
            
            const pointId = row.querySelector('.pointId').innerText.trim();
            const pointInfo = [
                row.querySelector('.cc'),
                row.querySelector('.btl'),
                row.querySelector('.th'),
                row.querySelector('.ktgk'),
                row.querySelector('.ktck'),
                scoreByNumber, scoreByWord, scorePerFourRank, note,
            ];
            btnModalSave.onclick = () => {
                savePoint(pointId, modalInp, pointInfo);
            }
        });
    });
}

function renderTableAndPagination() {
    if (!classId) {
        //tableWapper.innerHTML = '';
        //wapperBtnImport.style.display = 'none';
        //paginationWapper.innerHTML = '';
        return;
    }
    
    wapperBtnImport.style.display = 'flex';
    
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
    
    fetch(`${window.location.protocol}//${window.location.host}/api/teacher/student-point-table?classId=${classId}&page=${_page}`, requestOptions)
        .then((response) => response.json())
        .then((result) => {
            const status = result.status;
            const message = result.message;
            const data = result.data;
            if (status == 200) {
                if (!data) {
                    sweetalert('No data', 'info');
                    return;
                }
                renderTable(data);
                renderPagination(data);
            }
            else {
                sweetalert(message, 'error');
            }
        })
        .catch((error) => console.error(error));
}

function renderSelectionClass() {
    if (!termId || !subjectId) {
        chooseClass.innerHTML = '<option>-- Chọn lớp --</option>';
        classId = null;
        //tableWapper.innerHTML = '';
        //wapperBtnImport.style.display = 'none';
        //paginationWapper.innerHTML = '';
        return;
    }
    
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };

    fetch(`${window.location.protocol}//${window.location.host}/api/teacher/class?termId=${termId}&subjectId=${subjectId}`, requestOptions)
        .then((response) => response.json())
        .then((result) => {
            const data = result.data;
            let html = '<option>-- Chọn lớp --</option>';
            for (let i = 0; i < data.length; i++) {
                html += `                
                    <option value="${data[i].id}" ${classId == data[i].id ? 'selected' : ''}>${data[i].name}</option>
                `;
            }
            chooseClass.innerHTML = html;
            chooseClass.onchange = () => {
                classId = +chooseClass.value;
                _page = 1;
                if (!classId) {
                    window.location.href = `${window.location.protocol}//${window.location.host}/${window.location.pathname}?termId=${termId}&subjectId=${subjectId}`;
                    return;
                }
                if (_page && termId && subjectId && classId) {                    
                    window.location.href = `?page=${_page}&termId=${termId}&subjectId=${subjectId}&classId=${classId}`;
                }
                //renderTableAndPagination();
                if (!data.length) {
                    classId = null;
                    sweetalert('No data', 'error');
                }
            }
        })
        .then(renderTableAndPagination)
        .catch((error) => console.error(error));
}

function makeCurrentObject(data, id) {
    let res = data.filter(item => {
        return item.id == id;
    });
    if (res.length) res = res[0];
    return res;
}

function renderSelectionSubject() {
    if (!termId) {
        chooseSubject.innerHTML = '<option>-- Chọn môn học --</option>';
        chooseClass.innerHTML = '<option>-- Chọn lớp --</option>';
        currentSubject = null;
        subjectId = null;
        classId = null;
        //tableWapper.innerHTML = '';
        //wapperBtnImport.style.display = 'none';
        //paginationWapper.innerHTML = '';
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
            currentSubject = makeCurrentObject(data, subjectId);
            chooseSubject.onchange = () => {
                subjectId = +chooseSubject.value;
                currentSubject = makeCurrentObject(data, subjectId);
                classId = null;
                if (!subjectId) {
                    window.location.href = `${window.location.protocol}//${window.location.host}/${window.location.pathname}?termId=${termId}`;
                    return;
                }
                renderSelectionClass();
            }
            if (!data.length) {
                classId = null;
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

    fetch(`${window.location.protocol}//${window.location.host}/api/teacher/term`, requestOptions)
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
            chooseTerm.onchange = () => {
                termId = +chooseTerm.value;
                subjectId = null;
                classId = null;
                if (!termId) {
                    window.location.href = `${window.location.protocol}//${window.location.host}/${window.location.pathname}`;
                    return;
                }
                renderSelectionSubject();
            }
            if (!data.length) {
                classId = null;
                sweetalert('No data', 'error');
            }
        })
        .catch((error) => console.error(error));
}

renderSelectionTerm();
renderSelectionSubject();
renderSelectionClass();
//renderTableAndPagination();
