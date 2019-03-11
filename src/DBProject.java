import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBProject {

    public static void main(String[] args) {

        DBProject pro = new DBProject();
        pro.createConnection();

    }

    void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnction("jdbc.mysql://localhost:3306/DatDatProsjekt", "root", "root");
            System.out.println("Connected to database!");
        }

        catch (SQLException e) {
            Logger.getLogger(DatDatProsjekt.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (ClassNotFoundException e) {
            Logger.getLogger(DatDatProsjekt.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
