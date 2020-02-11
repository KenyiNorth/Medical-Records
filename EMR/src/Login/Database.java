package Login;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class Database {
    private static String username = "root";
    private static String password = "xuKWk4jXtg(6";
    private String databaseName ="jdbc:mysql://localhost:3306/electronicManagemetnSystem";
    String url = "jdbc:mysql://localhost:3306/db?serverTimezone=" + TimeZone.getDefault().getID();



    public Connection connectToMyDatabase()throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection(databaseName,username,password);

        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();

        }
        return  null;

    }
}
