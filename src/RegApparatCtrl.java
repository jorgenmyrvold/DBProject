import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegApparatCtrl extends DBConn {

    private PreparedStatement regStatement;

    public RegApparatCtrl() {
    }

    public void startReg() {
        try {
            regStatement = conn.prepareStatement("INSERT INTO Apparat VALUES ((?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Apparat");
        }
    }
    public void regApparat (String navn, String beskrivelse) {
        try {
            regStatement.setString(1, navn);
            regStatement.setString(2, beskrivelse);
            System.out.println(regStatement.toString());
            regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}