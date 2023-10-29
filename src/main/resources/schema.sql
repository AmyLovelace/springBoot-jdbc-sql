CREATE TABLE IF NOT EXISTS course (
    courseId INTEGER NOT NULL DEFAULT nextval('course_id_seq'),
	title varchar(80) NOT NULL,
	description varchar(500) NOT NULL,
	link varchar(255) NOT NULL,
	CONSTRAINT pk_course_id PRIMARY KEY (courseId)
);