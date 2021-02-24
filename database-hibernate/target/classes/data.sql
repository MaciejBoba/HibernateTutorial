insert into course_details (id, fullname, created_date, last_updated_date, is_Deleted) values (10001, 'JPA basics', sysdate(), sysdate(), false);
insert into course_details (id, fullname, created_date, last_updated_date, is_Deleted) values (10002, 'JDBC for dummies', sysdate(), sysdate(), false);
insert into course_details (id, fullname, created_date, last_updated_date, is_Deleted) values (10003, 'Java 101', sysdate(), sysdate(), false);
insert into course_details (id, fullname, created_date, last_updated_date, is_Deleted) values (10004, 'Hibernate Advanced', sysdate(), sysdate(), false);
insert into course_details (id, fullname, created_date, last_updated_date, is_Deleted) values (10005, 'JUnit basics', sysdate(), sysdate(), false);
insert into course_details (id, fullname, created_date, last_updated_date, is_Deleted) values (10006, 'C# 102', sysdate(), sysdate(), false);
insert into course_details (id, fullname, created_date, last_updated_date, is_Deleted) values (10007, 'Cpp for dummies', sysdate(), sysdate(), false);

insert into passport (id, number) values (40001, 'A1234256754');
insert into passport (id, number) values (40002, 'B7863575466');
insert into passport (id, number) values (40003, 'C5765677367');
insert into passport (id, number) values (40004, 'D8567563265');
insert into passport (id, number) values (40005, 'E1234665426');
insert into passport (id, number) values (40006, 'F1237643426');

insert into student (id, name, passport_id) values (20001, 'John Doe', 40001);
insert into student (id, name, passport_id) values (20002, 'Mark Smith', 40002);
insert into student (id, name, passport_id) values (20003, 'Sue Black', 40003);
insert into student (id, name, passport_id) values (20004, 'Emma White', 40004);
insert into student (id, name, passport_id) values (20005, 'Robert Grey', 40005);
insert into student (id, name, passport_id) values (20006, 'Amanda Spark', 40006);

insert into review (id, rating, description, course_id) values (60001, '1*','fuck no', 10001);
insert into review (id, rating, description, course_id) values (60002, '2*', 'no', 10001);
insert into review (id, rating, description, course_id) values (60003, '3*','maybe', 10001);
insert into review (id, rating, description, course_id) values (60004, '4*', 'ok', 10004);
insert into review (id, rating, description, course_id) values (60005, '5*', null, 10005);

insert into student_course (Student_id, Course_id) values (20001, 10001);
insert into student_course (Student_id, Course_id) values (20001, 10002);
insert into student_course (Student_id, Course_id) values (20001, 10003);
insert into student_course (Student_id, Course_id) values (20002, 10002);
insert into student_course (Student_id, Course_id) values (20002, 10003);
insert into student_course (Student_id, Course_id) values (20002, 10004);
insert into student_course (Student_id, Course_id) values (20003, 10003);
insert into student_course (Student_id, Course_id) values (20003, 10004);
insert into student_course (Student_id, Course_id) values (20003, 10005);
insert into student_course (Student_id, Course_id) values (20004, 10004);
insert into student_course (Student_id, Course_id) values (20004, 10005);
insert into student_course (Student_id, Course_id) values (20004, 10001);
insert into student_course (Student_id, Course_id) values (20005, 10005);
insert into student_course (Student_id, Course_id) values (20005, 10001);
insert into student_course (Student_id, Course_id) values (20005, 10003);