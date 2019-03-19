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
            PreparedStatement getData = conn.prepareStatement("SELECT * FROM Ovelse");
//            getData.setInt(1, n);
            ResultSet rs = getData.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= 3; i++) {
                    System.out.print(rsmd.getColumnName(i) + "\t");
                }
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + "\t");
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("db error fetching data = "+e);
        }
    }
}