import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegGjennomfortOvelseCtrl extends DBConn {
	
	private PreparedStatement regStatement;

    public RegGjennomfortOvelseCtrl() {
    	connect();
        startRegOvelse();
    }

    public void startRegOvelse() {
        try {
            regStatement = conn.prepareStatement("INSERT INTO OvelserITreningsokt VALUES (NULL, (?), (?), (?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
    }

    // Kan brukes både med 2 og 3 argumenter.
    public void regFriOvelse(String navn, String beskrivelse) {
    	try {
	        regStatement.setString(1, navn);
	        regStatement.setNull(2, java.sql.Types.INTEGER);;
	        regStatement.setNull(3, java.sql.Types.INTEGER);
	        regStatement.setString(4, beskrivelse);
	        System.out.println(regStatement.toString());
	        regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void regApparatOvelse(String navn, int vekt, int antallSett) {
    	try {
	        regStatement.setString(1, navn);
	        regStatement.setInt(2, vekt);;
	        regStatement.setInt(3, antallSett);
	        regStatement.setNull(4, java.sql.Types.VARCHAR);
	        System.out.println(regStatement.toString());
	        regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
		RegGjennomfortOvelseCtrl ctrl = new RegGjennomfortOvelseCtrl();
//		ctrl.regApparatOvelse("Benkpress", 50, 3);
		ctrl.regFriOvelse("Situps", "Tungt idag!");
	}
}
