var divTerm = document.querySelector('.list-term');

var divSubject = document.querySelector('.list-subject');

var divClass = document.querySelector('.list-class');

var divStudent = document.querySelector('.list-student');

var divNhapDiem = document.querySelector('.nhap-diem');

var listDiv = [];

var idx = 0;

var page = 0;

//alert('ok');

listDiv.push(divTerm);
listDiv.push(divSubject);
listDiv.push(divClass);
listDiv.push(divStudent);
listDiv.push(divNhapDiem);

function setDisplay(x){
	for(let i=0;i<5;i++){
		listDiv[i].style.display = 'none';
	}
	listDiv[x].style.display = 'block';
	
	page = x;
	switch(x){
		case 0:
			updateTerm();
			break;
		case 1:
			updateSubject();
			break;
		case 2:
			updateClass();
			break;
		case 3:
			updateStudent();
			break;
		case 4:
			nhap_diem_view();
			break;
		default:
			break;
	}
	return;
}

function back(){
	setDisplay(page-1);
}

if(term_id==-1){
	setDisplay(0);
}

function updateTerm(){
	var tableTerm = document.querySelector('.list-term table');
	
	tableTerm.innerHTML = `
		<tr>
			<td>id</td>
			<td>name</td>
			<td></td>
		</tr>
	`;
	
	var listData;
	fetch('/DBCLPM/api/nhap-diem?action=getterm')
	.then(response => {
		return response.json();
	})
	.then(data => {
		listData = data;
		
		listData.forEach( (i, index) => {
			tableTerm.innerHTML += `
				<tr>
					<td>${i.id}</td>
					<td>${i.name}</td>
					<td><button id = "bt${idx}">chon</button></td>
				</tr>
			`;
			idx+=1;
		});
		if(listData.length>0) setActionBt(listData,1);
	})
	.catch(error => {
		alert(error);
	});
}

var pc_cc = -1;
var pc_btl = -1;
var pc_th = -1;
var pc_ktgk = -1;
var pc_ktck = -1;

function setActionBt(listData,x){
	let s = listData.length;
	listData.forEach((i, index) => {
		document.getElementById(`bt${idx-s+index}`).onclick = function(){
			switch(x){
				case 1:
					term_id = i.id;
					break;
				case 2:
					subject_id = i.id;
					pc_cc = i.percent_cc;
					pc_btl = i.percent_btl;
					pc_ktck = i.percent_ktck;
					pc_ktgk = i.percent_ktgk;
					pc_th = i.percent_th;
					break;
				case 3:
					class_id = i.id;
					break;
				case 4:
					student = i;
					break;
				default:
					break;
			}
			setDisplay(x);
	};
	})
}

function updateSubject(){
	var tableTerm = document.querySelector('.list-subject table');
	
	tableTerm.innerHTML = `
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
	`;
	
	var listData;
	fetch(`/DBCLPM/api/nhap-diem?action=getsubject&term_id=${term_id}`)
	.then(response => {
		return response.json();
	})
	.then(data => {
		listData = data;
		
		listData.forEach( (i, index) => {
			tableTerm.innerHTML += `
				<tr>
					<td>${i.id}</td>
					<td>${i.name}</td>
					<td>${i.percent_cc}%</td>
					<td>${i.percent_btl}%</td>
					<td>${i.percent_th}%</td>
					<td>${i.percent_ktgk}%</td>
					<td>${i.percent_ktck}%</td>
					<td><button id="bt${idx}">chon</button></td>
			</tr>
			`;
			idx+=1;
		});
		if(listData.length>0) setActionBt(listData,2);
	})
	.catch(error => {
		alert(error);
	});
}

function updateClass(){
	var table = document.querySelector('.list-class table');
	
	table.innerHTML = `
	<tr>
		<td>id</td>
		<td>name</td>
		<td>room</td>
		<td>teacher name</td>
		<td></td>
	</tr>
	`;
	
	var listData;
	fetch(`/DBCLPM/api/nhap-diem?action=getclass&term_id=${term_id}&subject_id=${subject_id}`)
	.then(response => {
		return response.json();
	})
	.then(data => {
		listData = data;
		
		listData.forEach( (i, index) => {
			table.innerHTML += `
				<tr>
					<td>${i.id}</td>
					<td>${i.name}</td>
					<td>${i.room_name}</td>
					<td>${i.teacher_name}</td>
					<td><button id="bt${idx}">chon</button></td>
				</tr>
			`;
			idx+=1;
		});
		if(listData.length>0) setTimeout(function(){
			setActionBt(listData,3);
		},500);
	})
	.catch(error => {
		alert(error);
	});
}

function updateStudent(){
	var table = document.querySelector('.list-student table');
	
	table.innerHTML = `
	<tr>
		<td>code</td>
		<td>name</td>
		<td>phone</td>
		<td>address</td>
		<td>date of birth</td>
		<td>class</td>
		<td>CC(${pc_cc}%)</td>
		<td>DBT(${pc_btl}%)</td>
		<td>DKTTH(${pc_th}%)</td>
		<td>DGK(${pc_ktgk}%)</td>
		<td>DTKTHP(${pc_ktck}%)</td>
		<td>DTK</td>
		<td></td>
	</tr>`;
	
	var listData;
	fetch(`/DBCLPM/api/nhap-diem?action=getstudent&term_id=${term_id}&subject_id=${subject_id}&class_id=${class_id}`)
	.then(response => {
		return response.json();
	})
	.then(data => {
		listData = data;
		
		listData.forEach( (i, index) => {
			let cc = i.cc ? i.cc : 'empty';
			let btl = i.btl ? i.btl : 'empty';
			let th = i.th ? i.th : 'empty';
			let ktgk = i.ktgk ? i.ktgk : 'empty';
			let ktck = i.ktck ? i.ktck : 'empty';
			let tk = (cc*pc_cc + btl*pc_btl + th*pc_th + ktgk*pc_ktgk + ktck*pc_ktck)/100;
			table.innerHTML += `
				<tr>
					<td>SV${i.id}</td>
					<td>${i.name}</td>
					<td>${i.phone}</td>
					<td>${i.address}</td>
					<td>${i.date_of_birth}</td>
					<td>${i.class_name}</td>
					<td>${cc}</td>
					<td>${btl}</td>
					<td>${th}</td>
					<td>${ktgk}</td>
					<td>${ktck}</td>
					<td>${tk}</td>
					<td><button id="bt${idx}">chon</button></td>
				</tr>
			`;
			idx+=1;
		});
		if(listData.length>0) setActionBt(listData,4);
	})
	.catch(error => {
		alert(error);
	});
}

function nhap_diem_view(){
	document.querySelector('.student-name').innerText = student.name;
	document.querySelector('.student-code').innerText = "SV" + student.id;
	document.querySelector('#cc').value = student.cc ?? null;
	document.querySelector('#btl').value = student.btl ?? null;
	document.querySelector('#th').value = student.th ?? null;
	document.querySelector('#ktgk').value = student.ktgk ?? null;
	document.querySelector('#ktck').value = student.ktck ?? null;
}

function submitScore(){
	alert('send');
	setDisplay(page-1);
}