package com.promineotech.person.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.promineotech.person.model.Person;

public class PersonDao {
	
	private Connection conn;

	public PersonDao() {
		try {
			conn = DriverManager.getConnection("jdbc:h2:mem:test");
		} catch (SQLException e) {
			throw new MemDbException("Error obtaining connection", e);
		}
	}
	
	public void createPersonTable() {
		// TODO Auto-generated method stub
		String sql = ""
				+ "CREATE TABLE person ("
				+ "person_pk INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
				+ "person_name VARCHAR(200)"
				+ ")";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemDbException(e);
		}
		
	}

	public void dropPersonTable() {
		// TODO Auto-generated method stub
		String sql = ""
				+ "DROP TABLE person";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemDbException(e);
		}
	}

	public void addPerson(String name) {
		String sql = ""
				+ "INSERT INTO person "
				+ "(person_name) "
				+ "VALUES (?)";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemDbException(e);
		}
	}

	public Optional<Person> findPerson(String nameToFind) {
		Person person = null;
		String sql = ""
				+ "SELECT * FROM person "
				+ "WHERE person_name = ?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, nameToFind);
			
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					int pk = rs.getInt("person_pk");
					String name = rs.getString("person_name");
					
					person = new Person(pk, name);
				}
			}
			
			return Optional.ofNullable(person);
			
		} catch (SQLException e) {
			throw new MemDbException(e);
		}
		
		
	}

}
