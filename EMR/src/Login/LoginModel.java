package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection myConection;

    public LoginModel() {
        Database database = new Database();
        try {
            this.myConection = database.connectToMyDatabase();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (myConection == null)
            System.exit(1);

    }

    public boolean isDatabaseConnected() {
        return myConection != null;
    }

    public boolean checkLogin(String username, String password, String option) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String mySqlData = "SELECT*from admin WHERE username =? AND password=? AND division =?"

                ;
        try {
            preparedStatement = myConection.prepareStatement(mySqlData);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, option);

            resultSet = preparedStatement.executeQuery();
            boolean checkthroughAllDatabase;
            if (resultSet.next())
                return true;
            else
                return false;
        } catch (SQLException ex) {
            return false;
        } finally {
           preparedStatement.close();
            resultSet.close();
        }
    }

}
