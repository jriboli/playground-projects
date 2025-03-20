package Week15;

public class Week15_Research {
	/*
	 * 1. Research Spring Boot vs. Spring.  Describe each.  How do they relate?  Why do we use Spring Boot?
	 * 
	 * ANSWER:
	 * Spring Boot is an extension of the Spring framework designed to streamline development and simplify the deployment of production-ready applications. 
	 * It builds on the capabilities of the Spring framework, providing conventions and defaults for rapid development and easy deployment, 
	 * making it a popular choice for modern Java development, especially in the context of microservices.
	 * 
	 * 2. What are the annotations used for CRUD operations in Spring Boot? 
	 * 
	 * ANSWER:
	 * In Spring Boot, CRUD (Create, Read, Update, Delete) operations are commonly performed on entities using the Spring Data JPA module. 
	 * The primary annotations for CRUD operations in Spring Boot are provided by Spring Data JPA. 
	 * 
	 * Here are the key annotations:
	 * 	@Entity:
	 * Marks a Java class as a JPA entity, representing a table in a relational database.
	 * 
	 * @Repository:
	 * Annotates a class as a Spring Data repository, indicating that it should be responsible for database operations on a specific entity.
	 * 
	 * @CrudRepository or @JpaRepository:
	 * Extends the Repository interface and provides CRUD operations for the specified entity.
	 * 
	 * @Table:
	 * Specifies the details of the database table associated with an entity.
	 * 
	 * @Id:
	 * Marks a field in an entity class as the primary key.
	 * 
	 * @GeneratedValue:
	 * Specifies the generation strategy for the primary key values.
	 * 
	 * @Column:
	 * Configures the details of a column in the database table.
	 * 
	 * @Query:
	 * Defines a custom query method in a repository interface.
	 * 
	 * 3. Describe the difference between Hibernate & Spring Boot JPA
	 * 
	 * ANSWER:
	 * Hibernate is a robust standalone Object-Relational Mapping (ORM) framework for Java, offering extensive features and customization options, 
	 * and it can be used independently in any Java application. In contrast, Spring Boot JPA is a part of the Spring Data project that simplifies 
	 * data access in Spring applications, leveraging Hibernate as its default implementation. Spring Boot JPA streamlines development by providing 
	 * abstractions and conventions, reducing the need for explicit configuration and boilerplate code. It is tightly integrated with the Spring Boot 
	 * framework, making it an ideal choice for projects that value simplicity, convention over configuration, and rapid development within the Spring ecosystem.
	 * 
	 * 4. What is Tomcat? How does it relate to Spring Boot?
	 * 
	 * ANSWER:
	 * Tomcat serves as the default embedded server in Spring Boot, providing a convenient and lightweight container for running Spring-based 
	 * web applications. The integration between Spring Boot and Tomcat enhances developer productivity by offering sensible defaults, easy deployment, 
	 * and the flexibility to choose alternative embedded servers if needed.
	 * 
	 */

}
