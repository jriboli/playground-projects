package Week08;

public class Week08Research {
//	1. Describe each of the ten (10) MySQL data types.  
//
//	(a) How is each data type used?
//
//	(b) What makes each unique?
	
	/*
	 * ANSWER (https://www.javatpoint.com/mysql-data-types):
	 * 
	 * 	This question is a bit funny, cause there are more than 10 data types in MySql. I will
	 * take one type from each group (Numeric, Date and Time, String, BLOB, Spatial, and JSON)
	 * 
	 * [Numeric - Int]
	 * This can be used to storage a 'normal' number both positive and negative (signed). Example
	 * would be quantity of items in a shipment. 
	 * 
	 * [Date and Time - DATE]
	 * This is the date variable. It holds day, month, and year. It can be used to hold the DateCreate value,
	 * for when an order is placed. 
	 * 
	 * [String - VARCHAR(size)]
	 * This is used to hold a block of string/words. I can be used to hold user details, such as first_name,
	 * last_name, and email. The size helps define the allowed length of the string. Helps manage store/space used.
	 * 
	 * [BLOB - TINYBLOB]
	 * This data type is used to hold a large amount of information/data. Tiny holds up to 255 bytes of data. It 
	 * can be used to store comments posted by users in a chat app - potentially. 
	 * 
	 * [Spatial - POINT]
	 * These are math types, specifically point is used to store X, Y coordinates. Beyond using this for math or
	 * graphing, maybe it can be used to board games. Keeping track of where each piece is on the board. 
	 * 
	 * [JSON] 
	 * This type allows to store JSON formatted data. Could be used to store API request/responses.
	 */
//
//
//	2. Research primary & foreign keys in RDBMSs?  What are they?  Why are they useful?
	
	/*
	 * ANSWER:
	 * 
	 * 	RDBMS is a relational DB, it maintains those links using Primary/Foreign keys. Primary key is used on a table
	 * to specific the key for that table. Example items table has a item_id Primary key. Then Foreign key links to the
	 * primary key table. Say we have a table called orders, where we link items in each order to the item_id in the items
	 * table. This helps make sure that the two tables are linked. We can not add a random number in the order table, the 
	 * foreign key forces any value in that column to exist in the items table. So you could not add a random number. 
	 * 
	 */
//
//	3. Define DDL and DML.   What do the acronyms mean? Describe each one. Give examples.
	
	/*
	 * ANSWER (https://www.geeksforgeeks.org/sql-ddl-dql-dml-dcl-tcl-commands/):
	 * 
	 * 	DDL is Data Definition Language: This is a list of SQL commands that can be used to define the database schema.
	 * Example of a few commands : Create, Drop, Alter, Truncate 
	 * 
	 * DML is Data Manipulation Language: This is a list of SQL command that are used to manipulate data present in 
	 * a database. Examples of a few commands: Insert, Update, Delete, Lock
	 */
//
//	4. What are INDEXes in MySQL?  Why are they important?  What benefit do they provide?
	
	/*
	 * ANSWER (https://www.geeksforgeeks.org/sql-indexes/):
	 * 
	 * 	Indexes are a schema object, they are used to help speed up actions done on the data in a database. As a 
	 * database grows larger, retrieving specific rows starts to take longer. As the search as the check all rows. 
	 * Indexes help speed this up. 
	 */
//
//	5. What is a VIEW in an RDBMS.  What is a view?  Why is it useful?
	
	/*
	 * ANSWER (https://www.geeksforgeeks.org/sql-views/):
	 * 
	 * 	Views are like a virtual table, it can be specific columns from a single table. Or it can span multiple tables. 
	 * This allows us to only the data we want to for processing. We can even apply filtering similar to a SELECT 
	 * statement.
	 */
//
//	6. What is a JOIN when talking about databases?
	
	/*
	 * ANSWER:
	 * 
	 *	Join is a SQL command that will pull pull information/data across multiple tables using a common value. Typically
	 * that data we want may live in multiple tables. JOIN allows us to link rows in each using a ID or something unique.
	 * 
	 */
}
