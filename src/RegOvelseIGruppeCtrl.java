import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegOvelseIGruppeCtrl {
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
        try {
            regStatement.setString(1, ovelseNavn);
            regStatement.setString(2, gruppeNavn);
            System.out.println(regStatement.toString());
            regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
