insert into user_details(id,dob,name)
values(10001, current_date(), 'Aman');

insert into user_details(id,dob,name)
values(10002, current_date(), 'Prakash');

insert into user_details(id,dob,name)
values(10003, current_date(), 'Sid');

insert into post(id,descr,user_id)
values(20001,'I am using facebook', 10001);

insert into post(id,descr,user_id)
values(20002,'I am using Insta', 10001);

insert into post(id,descr,user_id)
values(20003,'I am using yt', 10002);

insert into post(id,descr,user_id)
values(20004,'I am using whatsapp', 10002);
