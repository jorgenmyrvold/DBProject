import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegTreningCtrl extends DBConn {
	
	private PreparedStatement regStatement;
	
	public RegTreningCtrl() {
	}
	
	public void startReg() {
        try {
            regStatement = conn.prepareStatement("INSERT INTO Treningsokt VALUES (NULL, (?), (?), (?), (?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Treningsokt");
        }
    }
    public void regTrening (String tidspunkt, int varighet, int form, int prestasjon, String notat) {
    	// tidspunkt format: YYYY-MM-DD HH:MM(:SS)
        try {
            regStatement.setString(1, tidspunkt);
            regStatement.setInt(2, varighet);
            regStatement.setInt(3, form);
            regStatement.setInt(4, prestasjon);
            regStatement.setString(5, notat);
            System.out.println(regStatement.toString());
            regStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	public static void main(String[] args) {
		
		RegTreningCtrl ctrl = new RegTreningCtrl();
		ctrl.connect();
		ctrl.startReg();
		ctrl.regTrening("2019-3-18 8:1", 60, 10,10,"hei");
	}
	
}

//public void startReg() {
//	String sql = String.format("INSERT INTO Treningsokt (Tidspunkt, Varighet, Form, Prestasjon, Notat) "
//			+ "VALUES ('%s', %d, %d, %d, '%s')", tidspunkt.toString().substring(0, 19), varighet, form, prestasjon, notat);
//	System.out.println(sql);
//	try {
//		regstatement = conn.prepareStatement(sql);
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	try {
//		regstatement.execute();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	System.out.println("funka!");
//}
