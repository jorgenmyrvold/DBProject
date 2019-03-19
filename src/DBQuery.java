import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DBQuery extends DBConn{

    private PreparedStatement regStatement;

    public DBQuery() {
        connect();
    }

    public void getNSisteTreningsokter(int n) {
        try {
            PreparedStatement getData = conn.prepareStatement("SELECT Tidspunkt, Varighet, Form, Prestasjon, Notat FROM Treningsokt ORDER BY Tidspunkt DESC LIMIT ?");
            getData.setInt(1, n);
            ResultSet rs = getData.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            System.out.println(rsmd.getColumnName(1) + "\t\t\t\t" +
                               rsmd.getColumnName(2) + "\t" +
                               rsmd.getColumnName(3) + "\t" +
                               rsmd.getColumnName(4) + "\t" +
                               rsmd.getColumnName(5));

            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t\t" +
                                   rs.getString(2) + "\t\t\t" +
                                   rs.getString(3) + "\t\t" +
                                   rs.getString(4) + "\t\t\t" +
                                   rs.getString(5));
            }

        } catch (Exception e) {
            System.out.println("db error fetching data = "+e);
        }
    }

    public void getOvelserIGruppe(String gruppenavn) {
        try {
            PreparedStatement getData = conn.prepareStatement("SELECT * FROM OvelseIGruppe WHERE Gruppenavn = '" + gruppenavn + "'");
            ResultSet rs = getData.executeQuery();
            
            System.out.println("Følgende øvelser hører til " + gruppenavn);

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("db error fetching data = "+e);
        }
    }

    public void getPers(String ovelseNavn) {
        try {
            PreparedStatement getData = conn.prepareStatement("SELECT MAX(Vekt) FROM OvelserITreningsokt WHERE OvelseNavn = '" + ovelseNavn + "'");
            ResultSet rs = getData.executeQuery();

            System.out.println("Din personlige rekord for " + ovelseNavn + " er: ");

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println("db error fetching data = "+e);
        }
    }
    
    public boolean ovelseExisits(String ovelse) {
    	try {
            PreparedStatement getData = conn.prepareStatement("SELECT 1 FROM Ovelse WHERE OvelseNavn='" + ovelse + "'");
            ResultSet rs = getData.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("db error fetching data = "+e);
            return false;
        }
    }
    
    public String getApparat(String ovelse) {
    	try {
            PreparedStatement getData = conn.prepareStatement("SELECT ApparatNavn FROM Ovelse WHERE OvelseNavn='" + ovelse + "'");
            ResultSet rs = getData.executeQuery();
            if (rs.next()) {
            	return rs.getString("ApparatNavn");            	
            }
            return null;
        } catch (Exception e) {
            System.out.println("db error fetching data = "+e);
            return null;
        }
    }
    
    public static void main(String[] args) {
//		DBQuery query = new DBQuery();
//		System.out.println(query.getApparat("Benkpress"));
//		System.out.println(String.format("%20s hei", "hei"));
	}
}