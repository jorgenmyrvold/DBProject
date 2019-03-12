import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBProject {

    void createConnection() {
        try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String url = "jdbc:mysql://mysql.stud.ntnu.no/sigmunom_treningsdagbok";
            String user = "sigmunom_DatDat";
            String pw = "salem";
            Connection con = DriverManager.getConnection(url, user, pw);
            System.out.println("Connected to database!");
        }

        catch (SQLException e) {
            Logger.getLogger(DBProject.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (ClassNotFoundException e) {
            Logger.getLogger(DBProject.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void main(String[] args) {

        DBProject pro = new DBProject();
        pro.createConnection();

    }

}
