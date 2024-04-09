use dbclpm;

create table teacher(
	id int primary key auto_increment,
	name varchar(255) not null,
	email varchar(255) unique not null
)

create table subject(
	id int primary key auto_increment,
	name varchar(255) not null,
	number_of_credits int not null,
	percent_cc int not null default 0,
	percent_btl int not null default 0,
	percent_ktgk int not null default 0,
	percent_ktck int not null default 0
)

create table term(
	id int primary key auto_increment,
	name varchar(255) not null
)

create table student(
	id int primary key auto_increment,
	name varchar(255) not null,
	email varchar(255) not null,
	phone varchar(20) not null,
	address varchar(255) not null
)

create table class(
	id int primary key auto_increment,
	name varchar(255) not null,
	room_name varchar(255) not null,
	teacher_id int not null,
	foreign key (teacher_id) references teacher(id),
	
	subject_id int not null,
	foreign key (subject_id) references subject(id),
	
	term_id int not null,
	foreign key (term_id) references term(id)
)

create table `point`(
	id int primary key auto_increment,
	cc float not null default 0,
	btl float not null default 0,
	ktgk float not null default 0,
	ktck float not null default 0,
	
	class_id int not null,
	foreign key (class_id) references class(id),
	
	student_id int not null,
	foreign key (student_id) references student(id)
)

select distinct term.*
from class, term  
where class.teacher_id = 1 and class.term_id = term.id
and term.end_date < DATE(NOW())
-- and DATE(NOW()) between term.start_date and term.end_date


select distinct subject.*
from class, subject
where class.term_id = 2 and class.teacher_id = 1;


select class.*
from class
where class.teacher_id = 1 and
class.term_id = 2 and class.subject_id = 4;


select student.*,
`subquery`.id as point_id, `subquery`.cc, `subquery`.btl, `subquery`.th, `subquery`.ktgk, `subquery`.ktck, `subquery`.student_id, `subquery`.class_id
from student inner join (
	select `point`.*
	from `point`, class
	where `point`.class_id = class.id
	and class.id = 2
) as `subquery`
on student.id = `subquery`.student_id
order by student.name asc, student.id asc
limit 0, 10


select count(*) as quantity
from student inner join (
	select `point`.*
	from `point`, class
	where `point`.class_id = class.id
	and class.id = 2
) as `subquery`
on student.id = `subquery`.student_id




select * from class
where class.id = 2



truncate table point



select subject.*
from subject, class
where class.id = 107 and class.subject_id = subject.id


select subject.*
from `point`, class, subject
where `point`.id = 4697
and `point`.class_id = class.id and class.subject_id = subject.id 

SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));




select term.*
from `point`, class, term
where `point`.id = 4697 and `point`.class_id = class.id
and class.term_id = term.id





select subquery1.accept, subquery2.*, subquery3.class_name, subquery3.subject_id
from
(
	select count(`point`.id) as accept, class.id as class_id
	from `point`, class
	where class.term_id = 1
	and `point`.class_id = class.id
	and 
	(`point`.cc is null or `point`.cc != 0) and
	(`point`.btl is null or `point`.btl != 0) and
	(`point`.th is null or `point`.th != 0) and
	(`point`.ktgk is null or `point`.ktgk != 0)
	group by class.id
) as subquery1
inner join
(
	select count(`point`.id) as reject, class.id as class_id
	from `point`, class
	where class.term_id = 1
	and `point`.class_id = class.id
	and (
		`point`.cc = 0 or
		`point`.btl = 0 or
		`point`.th = 0 or
		`point`.ktgk = 0
	)
	group by class.id
) as subquery2
on subquery1.class_id = subquery2.class_id
inner join
(
	select id as class_id, name as class_name, class.subject_id
	from class where class.term_id = 1
) as subquery3
on subquery2.class_id = subquery3.class_id
where subquery3.subject_id = 1






select class.*
from `point`, class
where `point`.id = 4697 and `point`.class_id = class.id


select distinct class.*
from `point`, class
where `point`.class_id = class.id and class.term_id = 2



select count(`point`.id) as accept, class.id as class_id
from class, `point`
where `point`.class_id = class.id and class.term_id = 1
and 
(`point`.cc is null or `point`.cc != 0) and
(`point`.btl is null or `point`.btl != 0) and
(`point`.th is null or `point`.th != 0) and
(`point`.ktgk is null or `point`.ktgk != 0)
group by class.id





select distinct percent_cc, percent_btl, percent_th, percent_ktgk, percent_ktck
from class, subject
where class.id = 1 and class.subject_id = subject.id


select count(*) from point


select id from `point` where class_id = 7


select term.*
from class, term
where class.id = 107 and class.term_id = term.id


select teacher.*
from class, teacher
where class.id = 107 and class.teacher_id = teacher.id





select `point`.*
from `point`
where `point`.id in (4697, 4718, 4782, 4801, 4847, 4922, 5135, 3688, 5584, 5626)


update `point`
set cc = null, btl = null,
th = null, ktgk = null, ktck = null
where id in (4697, 4718, 4782, 4801, 4847, 4922, 5135, 3688, 5584, 5626)






