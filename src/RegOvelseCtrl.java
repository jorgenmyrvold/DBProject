import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegOvelseCtrl extends DBConn {

    private PreparedStatement regStatement;

    public RegOvelseCtrl() {
    }

    public void startReg() {
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelse VALUES ((?), (?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
    }

    public void regOvelse(String navn, String beskrivelse) {
        regOvelse(navn, beskrivelse, "NULL");
    }

    public void regOvelse(String navn, String beskrivelse, String apparatNavn) {
        try {
            regStatement.setString(1, navn);
            regStatement.setString(2, beskrivelse);
            regStatement.setString(3, apparatNavn);
            System.out.println(regStatement.toString());
            regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}