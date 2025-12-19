import java.sql.Connection;
import java .sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL ="jdbc:mysql://bdqwmavsq4l6l9bkzlxe-mysql.services.clever-cloud.com:3306/bdqwmavsq4l6l9bkzlxe";
    private static final String USER= "unx56aig0swnn2rp";
    private static final String PASS= "KrBieSHSffiu3Z4SivxR";

    public static Connection getConexion(){
        try{
            return DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException e) {
            System.out.println("Error conexión BD");
            return null;
        }
    }

}
