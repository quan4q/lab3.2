package accounts;

import java.sql.*;

public class UsersDB {

    private final String checkQuery = "select * from users where username = ?";

    public int setNewUser(UserProfile userProfile){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "12345"
            );

            String insertQuery = "INSERT INTO users (username, password, email) Values (?, ?, ?)";

            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);

            checkStatement.setString(1, userProfile.getLogin());

            ResultSet result = checkStatement.executeQuery();

            if(result.next()){
                return -1;
            }
            else {
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

                insertStatement.setString(1, userProfile.getLogin());
                insertStatement.setString(2, userProfile.getPassword());
                insertStatement.setString(3, userProfile.getEmail());

                return insertStatement.executeUpdate();
            }

        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            return -1;
        }
    }

    public String getPassword(String login){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "12345"
            );

            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);

            checkStatement.setString(1, login);

            ResultSet checkResult = checkStatement.executeQuery();

            if(checkResult.next()){
                String selectQuery = "SELECT * FROM users WHERE username = ?";

                PreparedStatement selectStmnt = connection.prepareStatement(selectQuery);

                selectStmnt.setString(1, login);

                ResultSet result = selectStmnt.executeQuery();
                result.next();

                return result.getString("password");
            }
            else{
                return null;
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}
