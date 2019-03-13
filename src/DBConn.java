import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DBConn {
	
	protected Connection conn;
	
	public DBConn() {
	}

    public void connect() {
        try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
            String url = "jdbc:mysql://mysql.stud.ntnu.no/sigmunom_treningsdagbok";
            String user = "sigmunom_DatDat";
            String pw = "salem";
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to database!");
        }

        catch (SQLException e) {
            Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (ClassNotFoundException e) {
            Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
