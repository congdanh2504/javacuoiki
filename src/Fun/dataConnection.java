package Fun;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class dataConnection {
	public static Connection ConnectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlvayvonsv","root","");
			return conn;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi");
			return null;
		}
	}
}
