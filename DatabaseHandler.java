import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseHandler extends Configs{
	Connection dbConnection;

	public Connection getDbConnection() throws ClassNotFoundException, SQLException {
		String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

		Class.forName("com.mysql.jdbc.Driver");

		dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

		return dbConnection;
	}

	public void singUpAlt(String name, String password, String city, int age, boolean cooking, int countBoyfriend) throws ClassNotFoundException, SQLException{
		String insert = "INSERT INTO " + Const.ALT_TABLE + "(" + Const.ALT_NAME + "," + Const.ALT_PASSWORD + "," + Const.ALT_CITY + "," 
		+ Const.ALT_AGE + "," + Const.ALT_COOKING + "," 
		+ Const.ALT_CBOYFRIEND + "," + Const.ALT_GIRLTYPE + ")" + "VALUES(?,?,?,?,?,?,?)";
	
		PreparedStatement prSt = getDbConnection().prepareStatement(insert);
		try {
            
            prSt.setString(1, name);
            prSt.setString(2, password);
            prSt.setString(3, city);
            prSt.setInt(4, age);
            prSt.setBoolean(5, cooking);
            prSt.setInt(6, countBoyfriend);
            prSt.setString(7, "Альтушка");

            prSt.executeUpdate();
            System.out.println("Данные успешно добавлены в базу!");

        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
            e.printStackTrace();
        }
		
	}
}