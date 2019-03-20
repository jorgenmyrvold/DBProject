import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegOvelseIGruppeCtrl extends DBConn{
    private PreparedStatement regStatement;

    public RegOvelseIGruppeCtrl() {
    }

    public void startReg() {
        try {
            regStatement = conn.prepareStatement("INSERT INTO OvelseIGruppe VALUES ((?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into OvelseIGruppe");
        }
    }

    public void regOvelse(String ovelseNavn, String gruppeNavn) {
        connect();
        startReg();

        try {
            regStatement.setString(1, ovelseNavn);
            regStatement.setString(2, gruppeNavn);
//            System.out.println(regStatement.toString());
            regStatement.execute();
            System.out.println("Øvelsegruppe " + gruppeNavn + " er registrert for " + ovelseNavn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
