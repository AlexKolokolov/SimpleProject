-- это чтобы работал автоинкремент не только через Hibernate
ALTER TABLE employee ALTER employee_id SET DEFAULT nextval('hibernate_sequence'::regclass);

INSERT INTO employee (first_name, last_name, department_id, age, gender, status, salary)
VALUES
('Sharon', 'Osbourne', 1, 40, 1, 0, 2500),
('Bonny', 'Tyler', 2, 45, 1, 0, 3500),
('Maryl', 'Streep', 2, 50, 1, 0, 5500),
('Barak', 'Obama', 1, 55, 0, 0, 8000),
('Bob', 'Marley', 1, 45, 0, 0, 4000);