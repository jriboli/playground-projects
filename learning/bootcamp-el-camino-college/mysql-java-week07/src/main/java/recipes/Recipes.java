package recipes;

import java.sql.Connection;

import recipes.dao.DbConnection;

public class Recipes {

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
		Connection conn = DbConnection.getConnection();
		
	}

}
