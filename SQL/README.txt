1. Run project on server with hibernate.hbm2ddl.auto=create parameter in aplicationContext.xml
	Hibernate will create all the necessary tables.
2. Add Departments to DB. Query set from file insert_departments.sql
3. Add Actions to DB. Query set from file insert_actions.sql
4. Add Users to DB. Query set from file insert_users.sql
5. Next time run project on server with hibernate.hbm2ddl.auto=update parameter in aplicationContext.xml.