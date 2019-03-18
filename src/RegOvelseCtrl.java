import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegOvelseCtrl extends DBConn {

    private PreparedStatement regStatement;

    public RegOvelseCtrl() {
    }

    // Registrering hvis vi må ha en overgangstabell med apparat og øvelse
    public void startRegFriOvelse() {
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelse VALUES ((?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
    }

    public void regFriOvelse(String navn, String beskrivelse) {
        connect();
        startRegFriOvelse();

        try {
            regStatement.setString(1, navn);
            regStatement.setString(2, beskrivelse);
            System.out.println(regStatement.toString());
            regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void startRegApparatOvelse() {
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelse VALUES ((?), (?)); " +
                                                      "INSERT INTO ApparatTilOvelse VALUES ((?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
    }

    public void regApparatOvelse(String ovelseNavn, String beskrivelse, String apparatNavn) {
        connect();
        startRegApparatOvelse();

        try {
            regStatement.setString(1, ovelseNavn);
            regStatement.setString(2, beskrivelse);
            regStatement.setString(3, ovelseNavn);
            regStatement.setString(4, apparatNavn);
            System.out.println(regStatement.toString());
            regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // For registrering av øvelser hvis vi får til FK som kan være NULL
    public void startRegOvelse() {
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelse VALUES ((?), (?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
    }

    public void regOvelse(String ovelseNavn, String beskrivelse){
        regOvelse(ovelseNavn, beskrivelse, "NULL");
    }

    public void regOvelse(String ovelseNavn, String beskrivelse, String apparatNavn) {
        connect();
        startRegApparatOvelse();

        try {
            regStatement.setString(1, ovelseNavn);
            regStatement.setString(2, beskrivelse);
            regStatement.setString(3, apparatNavn);
            System.out.println(regStatement.toString());
            regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}