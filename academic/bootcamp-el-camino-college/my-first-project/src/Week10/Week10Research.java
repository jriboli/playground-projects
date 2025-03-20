package Week10;

public class Week10Research {
	
//	1. What is JDBC?  What does it stand for?  How does it differ from or relate to MySQL?
	
	/*
	 * ANSWER (https://www.geeksforgeeks.org/jdbc-tutorial/?ref=gcse) : 
	 * 
	 * 	JDBC - stands for Java Database Connectivity. This is an API, interface, for connecting and executing queries against a 
	 * database. While JDBC is not only for connecting to MySQL, it supports communicating with MySQL database. Just need to make sure
	 * we are using the right driver - mysql-connector-java.
	 */
//
//	2. Give an example of the primary JDBC Classes?  What Classes do we use to do the following, and give an example of syntax to accomplish the following:
//
//	(a) Attach or Connect to a database
//
//	(b) Create a statement  (to perform one of the CRUD operations on a database)
//
//	(c) Execute a query
//
//	(d) Process the data retrieved, 
//
//	(e) Close the connection to the database
	
	/*
	 * ANSWER (https://www.tutorialspoint.com/what-are-the-main-classes-and-interfaces-of-jdbc) : 
	 * 
	 * 	Here are the important classes/interfaces of JDBC; DriverManager, Driver, Statement, PreparedStatement, CallableStatement, 
	 * Connection, ResultSet, ResultSetMetaData. 
	 * 
	 * 	A. Which classes do we use to attach or connect to a database: DriverManager, Driver
	 *  B. Which classes create a statement: Statement, PreparedStatement, CallableStatement
	 *  C. Which classes execute a query: Statement, PreparedStatement, CallableStatement
	 *  D. Which classes process the data retrieved: ResultSet, ResultSetMetaData
	 *  E. Which classes close the connection to the database: Connection
	 */
//
//
//	3. What are the two method calls to execute a query in JDBC, and when do you use each?
	
	/*
	 * ANSWER:
	 * 
	 * 	There are several methods available, depending on the class you use. For PreparedStatement we have executeQuery(), executeUpdate(), 
	 * and execute(). ExecuteQuery is used when the query is a SELECT as it returns a ResultSet instances. ExecuteUpdate is used for create, 
	 * drop, insert, update, delete, etc. It only returns an Int instance. 
	 */

}
