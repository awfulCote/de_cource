CREATE TABLE employeers
(id SERIAL PRIMARY KEY,
 bday DATE,
 startday DATE,
 position CHARACTER VARYING(20),
 level CHARACTER VARYING(30) CHECK ( level IN ('junior', 'middle', 'senior', 'lead')),
 salary INTEGER,
 department INTEGER,
 driverlicense boolean
);

CREATE TABLE departments
(id SERIAL PRIMARY KEY,
 name CHARACTER VARYING(20),
 headname CHARACTER VARYING(20),
 empcount INTEGER
);

CREATE TABLE bonuses
(		idemp INTEGER,
		q1 CHAR CHECK (q1 IN ('A', 'B', 'C', 'D', 'E')),
		q2 CHAR CHECK (q2 IN ('A', 'B', 'C', 'D', 'E')),
		q3 CHAR CHECK (q3 IN ('A', 'B', 'C', 'D', 'E')),
		q4 CHAR CHECK (q4 IN ('A', 'B', 'C', 'D', 'E'))
);

ALTER TABLE employeers ADD COLUMN name CHARACTER VARYING(30);

INSERT INTO employeers VALUES
	(1,  '11.03.1956', '01.09.2002' ,'programmer', 'senior', 300000, 2, true, 'John Abramson'),
	(2,  '09.08.1987', '23.10.2012', 'brand manager', 'middle', 120000, 1, false, 'Garry Botterill'),
	(3, '31.01.1991', '18.11.2018', 'programmer', 'junior', 80000, 2, false, 'Dick Davidson'),
	(4, '21.04.1994', '02.06.2017', 'brand specialist', 'junior', 70000, 1, true, 'Kira Mercer'),
	(5, '11.01.1975', '30.09.2005', 'team lead', 'senior', 310000, 2, true, 'Timmy Turner')
	;

INSERT INTO employeers (id, name, position, level, salary, startday, department)
	VALUES
		(6, 'Autamn Winter', 'head dep', 'senior', 400, '20/11/2022', 3),
		(7, 'Robert White', 'analyst', 'middle', 200, '20/11/2022', 3),
		(8, 'Bobby Pattinson', 'analyst', 'junior', 100, '20/11/2022', 3);

INSERT INTO departments (name, headname, empcount)
VALUES
	'Data Mining', 'Autamn Winter', 3);

SELECT id, name,  '2022-11-22' - startday AS experience_days
FROM employeers;

SELECT id, name,  '2022-11-22' - startday AS experience_days
FROM employeers
LIMIT (3);

SELECT id
FROM employeers
WHERE driverlicense = 'true';

SELECT idemp
FROM bonuses
WHERE q1 IN ('D', 'E') OR q2 IN ('D', 'E') OR q3 IN ('D', 'E') OR q4 IN ('D', 'E');

SELECT MAX(salary)
FROM employeers;

SELECT name
FROM employeers
ORDER BY salary desc
LIMIT (1)

SELECT name
FROM employeers
ORDER BY name

SELECT level, avg('22.11.2022' - startday)
FROM employeers
group by level


SELECT employeers.name, departments.name
FROM employeers JOIN departments
ON employeers.department = departments.id

SELECT employeers.name as fio, departments.name as department, employeers.salary
FROM employeers JOIN departments
ON employeers.department = departments.id
WHERE employeers.salary = (
	SELECT salary
	FROM employeers
	ORDER BY salary desc
	LIMIT (1) )


