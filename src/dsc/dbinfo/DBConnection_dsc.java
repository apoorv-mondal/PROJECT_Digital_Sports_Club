package dsc.dbinfo;
import java.sql.*;
public class DBConnection_dsc {
	private static Connection con = null;

	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// it is used to create the object
			// factory methods->to create the object of a class
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsc_db", "root", "root");
			// (jdbc:mysql)->subprotocol://is the name of the IP address of the machine
			// where DB is
			// installed
			// (localhost:3306)->3306 is the port number on which MySQL listen the request
			// (sms_db)->database name
			// (con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms_db",
			// "root", "root");)->connection string or connection url
			// first root->userid in MySQL
			// second root->password
			// (?useSSL=false)->is line ki koi zarurat nhi hoti , mere
			// machine pe ek warning aarai thi SSL related isliye ye line likha hai!
		} catch (ClassNotFoundException | SQLException cse) {
			cse.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
		Connection con = openConnection();
		System.out.println(con);
	}
}
