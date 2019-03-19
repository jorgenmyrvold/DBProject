import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


public class RegOvelseCtrl extends DBConn {

    private PreparedStatement regStatement;

    public RegOvelseCtrl() {
    	connect();
        startRegOvelse();
    }

    public void startRegOvelse() {
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelse VALUES ((?), (?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
    }

    // Kan brukes både med 2 og 3 argumenter.
    public void regOvelse(String navn, String beskrivelse, String... apparatOpt) {
        String apparat = apparatOpt.length == 1 ? apparatOpt[0] : null;

        try {
            regStatement.setString(1, navn);
            regStatement.setString(2, beskrivelse);
            regStatement.setString(3, apparat);
            System.out.println(regStatement.toString());
            regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
		RegOvelseCtrl ctrl = new RegOvelseCtrl();
		ctrl.regOvelse("Benkpress", "Husk å ha en som spotter!", "Benk");
	}
}