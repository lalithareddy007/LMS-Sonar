INSERT INTO public.tbl_lms_user(
	user_id, user_first_name, user_last_name, user_phone_number, user_location, user_time_zone, user_linkedin_url, user_edu_ug, user_edu_pg, user_comments, user_visa_status, creation_time, last_mod_time, user_middle_name)
	VALUES
	('U06', 'John', 'Matthew', 123456789, 'New Jersey', 'EST', 'https://www.linkedin.com/in/John Matthew/', 'Computer Science Engineering', 'MBA', null, 'Not-Specified', '2021-10-04 18:09:38.076245', '2021-10-04 18:09:38.076245', null),
	('U07', 'Steve', 'Job', 879123456, 'Idaho', 'PST', 'https://www.linkedin.com/in/Steve Job/', 'Mechanical Engineering', 'Computer Science Engineering', null, 'H4', '2021-10-04 18:09:38.076245', '2023-11-13 21:01:33.632', null),
	('U08',	'Robert', 'Louis', 9566751211, 'Minneapolis', 'IST', 'https://www.linkedin.com/in/RobertLouis/', 'Electronics and Communication Engineering', null, 'Robotics', 'GC-EAD', '2021-10-04 18:09:38.076245', '2021-10-04 18:09:38.076245', null);

INSERT INTO public.tbl_lms_user_login(
	user_id, user_password, user_login_status, creation_time, last_mod_time, user_login_email)
	VALUES ('U06', '$2a$12$rp4D458edb501.gXGeVyhu0n.cqSIH3UXrGlZRzlUXRZFrtIsPxZO', 'ACTIVE', '2021-10-04 18:11:10.357897', '2021-10-04 18:11:10.357897', 'John.Matthew@gmail.com'),
	('U08',	'$2a$10$StDoLd4y6rNMPxz1iCz75.BGXyW92riUveURDCOZk4J8UpQnxC7wy', 'ACTIVE', '2021-10-04 18:11:10.357897',	'2021-10-04 18:11:10.357897', 'Robert.Louis@gmail.com');

INSERT INTO public.tbl_lms_role(
	role_id, role_name, role_desc, creation_time, last_mod_time)
	VALUES ('R01', 'ROLE_ADMIN', 'LMS_Admin', '2021-10-04 18:06:42.970922', '2021-10-04 18:06:42.970922'),
	('R02',	'ROLE_STAFF', 'LMS_Staff', '2021-10-04 18:06:42.970922', '2021-10-04 18:06:42.970922'),
	('R03',	'ROLE_STUDENT', 'LMS_User', '2021-10-04 18:06:42.970922',	'2021-10-04 18:06:42.970922');


INSERT INTO public.tbl_lms_userrole_map(
	user_role_id, user_id, role_id, user_role_status, creation_time, last_mod_time)
	VALUES (6, 'U06', 'R01', 'Active', '2021-10-04 18:18:40.000121', '2021-10-04 18:18:40.000121'),
	(7,	'U07', 'R02', 'active',	'2021-10-04 18:18:40.000121', '2024-04-11 08:03:40.830571'),
	(8, 'U08',	'R03', 'Active', '2021-10-04 18:18:40.000121', '2021-10-04 18:18:40.000121');

INSERT INTO public.tbl_lms_program(
	program_id, program_name, program_description, program_status, creation_time, last_mod_time)
	VALUES (6, 'SDET', 'Testing batch', 'Active', '2021-10-04 18:14:48.326714', '2021-10-04 18:14:48.326714');

INSERT INTO public.tbl_lms_program(
	program_id, program_name, program_description, program_status, creation_time, last_mod_time)
	VALUES (11, 'JAVA', 'JAVA batch', 'Active', '2021-10-04 18:14:48.326714', '2021-10-04 18:14:48.326714');

INSERT INTO public.tbl_lms_batch(
	batch_id, batch_name, batch_description, batch_status, batch_program_id, batch_no_of_classes, creation_time, last_mod_time)
	VALUES (6, 'SDET', 'SDET BATCH 01', 'Active', 6, 3, '2021-10-04 18:16:02.713333', '2021-10-04 18:16:02.713333');

INSERT INTO public.tbl_lms_batch(
	batch_id, batch_name, batch_description, batch_status, batch_program_id, batch_no_of_classes, creation_time, last_mod_time)
	VALUES (12, 'JAVA', 'JAVA BATCH 01', 'Active', 11, 3, '2021-10-04 18:16:02.713333', '2021-10-04 18:16:02.713333');

INSERT INTO public.tbl_lms_class_sch(
	cs_id, batch_id, class_no, class_date, class_topic, class_staff_id, class_description, class_comments, class_notes, class_recording_path, creation_time, last_mod_time, class_status)
	VALUES (6, 6, 2, '2021-09-21', 'Selenium', 'U07', 'Selenium Class', 'New Class', 'C:ClassNotes', 'C:ClassNotes', '2021-10-04 18:17:16.658917', '2021-10-04 18:17:16.658917', null);

INSERT INTO public.tbl_lms_assignments(
	a_id, a_name, a_description, a_comments, a_due_date, a_path_attach1, a_path_attach2, a_path_attach3, a_path_attach4, a_path_attach5, a_created_by, a_batch_id, a_grader_id, creation_time, last_mod_time, a_cs_id)
	VALUES (6, 'Selenium', 'Selenium assignment', 'New assignment is created', '2024-02-24', 'string',	'string', 'string',	'string', 'string',	'U07',	6,	'U07',	'2024-01-20 19:07:15.238706',	'2024-01-20 19:07:15.238706', 6);

INSERT INTO public.tbl_lms_assignments(
	a_id, a_name, a_description, a_comments, a_due_date, a_path_attach1, a_path_attach2, a_path_attach3, a_path_attach4, a_path_attach5, a_created_by, a_batch_id, a_grader_id, creation_time, last_mod_time, a_cs_id)
	VALUES (7, 'Java', 'Java assignment', 'New Java assignment is created', '2024-6-24', 'string',	'string', 'string',	'string', 'string',	'U07',	12,	'U07',	'2024-01-20 19:07:15.238706',	'2024-01-20 19:07:15.238706', 6);
    
INSERT INTO tbl_lms_user_role_program_batch_map(
    ur_pb_id, user_id, role_id, program_id, batch_id, user_role_program_batch_status, creation_time, last_mod_time)
    VALUES(10, 'U07','R02', 6, 6, 'Active', '2021-10-04 18:16:02.713333', '2021-10-04 18:16:02.713333');

INSERT INTO public.tbl_lms_skill_master(
	skill_id, skill_name, creation_time, last_mod_time)
	VALUES (10, 'Java', '2021-10-04 18:16:02.713333', '2021-10-04 18:16:02.713333');

INSERT INTO tbl_lms_userskill_map(
	user_skill_id, user_id, skill_id, months_of_exp, creation_time, last_mod_time)
	VALUES ('US02', 'U07', 10, 24, '2021-10-04 18:16:02.713333', '2021-10-04 18:16:02.713333');
    
INSERT INTO public.tbl_lms_submissions(
	sub_id, sub_a_id, sub_student_id, sub_description, sub_comments, sub_path_attach1, sub_path_attach2, sub_path_attach3, sub_path_attach4, sub_path_attach5, sub_datetime, graded_by, graded_datetime, grade, creation_time, last_mod_time)
	VALUES (10, 7, 'U08', 'Assignment Subimission', 'Assignment submission comment', 'string', 'string', 'string','string', 'string', '2024-6-24', 'U07', '2024-6-24', 76, '2021-10-04 18:16:02.713333', '2021-10-04 18:16:02.713333');
