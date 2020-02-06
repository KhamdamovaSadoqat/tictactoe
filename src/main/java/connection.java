import java.sql.*;

public class connection {
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    private String base;

    public connection(String BaseName) throws ClassNotFoundException{

        try {
            base = "`"+BaseName+"`";
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+BaseName+
                    "?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            statement = connection.createStatement();
            System.out.println("connected");
        }  catch (SQLException ex) {
            System.out.println("ERROR 1 : " + ex);
        }

    }



    public ResultSet Buyruq(String query) {
        try {
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
