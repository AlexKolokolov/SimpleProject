1. Run project on server with hibernate.hbm2ddl.auto=create parameter in acplicationContext.xml
	Hibernate will create all the neccessary tables.
2. Add Departments to DB. Query set from file insert_departments.sql
3. Add Actions to DB. Query set from file insert_actions.sql
4. Compile packages from files dep_manage.pck and emp_manage.pck
5. Next time run project on server with hibernate.hbm2ddl.auto=update parameter in acplicationContext.xml.