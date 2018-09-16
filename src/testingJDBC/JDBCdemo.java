package testingJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCdemo {
	
	public static void main(String[] args){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
			customer cObj = new customer();
			cObj.setName("SMondal");
			cObj.setAdd("Koramangala");
			cObj.setPin(5600094);
			
			Statement stmt = conn.createStatement();
			
			/*String q1 = "CREATE TABLE CUSTOMER " +
						"(name VARCHAR(100),"+
						" address VARCHAR(100),"+
						" pin INTEGER,"+
						" PRIMARY KEY( name ))";*/
			String q2 = "insert into CUSTOMER (name, address, pin) values ('"+cObj.getName()+"','"+cObj.getAdd()+"','"+cObj.getPin()+"')";
			
			/*int tableCreation = stmt.executeUpdate(q1);
			System.out.println("Table Creation Status:"+tableCreation);*/
			
			int flag = stmt.executeUpdate(q2);
			System.out.println(flag);
			
			String q3 = "Select * FROM CUSTOMER";
			ResultSet result = stmt.executeQuery(q3);
			
			while(result.next()){
				String n = result.getString("name");
				String add = result.getString(2);
				int p = result.getInt(3);
				
				System.out.println(" ["+n+"-"+add+"-"+p+"]");
			}
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
