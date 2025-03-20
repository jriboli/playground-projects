package Week09;

public class Week09Research {
//	1.  Research different constructs that are available in MySQL when retrieving data. 
//
//	(a) How is data retrieved from a MySQL database?  
//
//	(b) What clauses exist for restricting the data that is returned to the user?  
//
//	(c) Include the syntax for this type of request, and give an example. 
	
	/*
	 * ANSWER (https://www.w3schools.com/mysql/):
	 * MySQL creates a database to store and manipulate the data. To perform various operations users make requests by 
	 * typing specific statements. One such command is SELECT. When using the SELECT statement you can filter the results 
	 * with several different commands; WHERE, LIMIT, GROUP BY, HAVING. 
	 * 
	 * An example of one would be:
	 * 
	 * SELECT * 
	 * FROM promineoTech.students
	 * WHERE student_is_awesome = True;
	 * 
	 */

//	2. What types of operations can you do in SQL (or specifically MySQL)?  List at least five (5) different statements that exist in MySQL? Give a brief description of what these do.
//
	/*
	 * ANSWER (https://www.w3schools.com/mysql/):
	 * 
	 * Some of the MySQL statements include; SELECT, INSERT INTO, DELETE, UPDATE, JOIN. And here is a bit more detail about each:
	 * 
	 * [SELECT] This is used to pull specific data from the database. User defines the search parameters.
	 * [INSERT INTO] This is used to add new rows/records to a database.
	 * [DELETE] This is used to remove records from a database.
	 * [UPDATE] This is used to update columns for a specific row in a database. Columns must be specified by user. 
	 * [JOIN] This is used to pull information across multiple tables, that share a common value. 
	 * 
	 */
	
//	3. What are wildcards in MySQL? How are they useful?  Give an example of a MySQL statement that could use wildcards.
//
	/*
	 * ANSWER (https://www.w3schools.com/mysql/mysql_wildcards.asp):
	 * In searching there are two wildcards, "_" and "%". The underscore (_) represents a single character, while the percentage (%) represents
	 * 0 to n characters. 
	 * 
	 * An example of each: 
	 * 
	 * WHERE CustomerName LIKE 'a%'
	 * WHERE CustomerName LIKE '_a' 
	 */
	
//	4. Define JDBC.  What is JDBC?  Describe it in your own words.  How is it used? 
//
	/*
	 * ANSWER:
	 * 
	 * JDBC or Java Database Connectivity is an API that connects and executes query commands specified by a user against a database. JDBC 
	 * allows the user to query a database for data, allowing CRUD commands. As the database is a complex structure, this allows us to 
	 * easily interact with it. 
	 */
	
//	5.  List 3-5 interesting facts that you have learned about MySQL from your research?
//
	/*
	 * ANSWER:
	 * MySQL can run on various operating systems such as Windows, Mac, Linux, etc. 
	 * MySQL allows transactions to be rolled back, commit, and crash recovered. 
	 * MySQL consists of a Data Security layer that protects the data from the violator. 
	 */
	
	
}
