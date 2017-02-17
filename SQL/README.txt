1. Run project on server with hibernate.hbm2ddl.auto=create parameter in aplicationContext.xml
	Hibernate will create all the necessary tables.
2. Add Departments to DB. Query set from file insert_departments.sql
3. Add Actions to DB. Query set from file insert_actions.sql
4. Register custom data types. Query set from file register_types.sql
5. Compile packages from files constants.pck, dep_manage.pck and emp_manage.pck
6. Next time run project on server with hibernate.hbm2ddl.auto=update parameter in aplicationContext.xml.