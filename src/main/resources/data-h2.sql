insert into Membership (grade, name, device_control_count) values (1, 'free', 3);
insert into Membership (grade, name, device_control_count) values (2, 'gold', 10);

insert into Member ( email, id, password, MEMBERSHIP_GRADE, created_date, modified_date  ) values ('test@a.com','testid','testpassword', 1, now(), now());
