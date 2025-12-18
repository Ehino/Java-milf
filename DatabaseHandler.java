import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseHandler extends Configs{
	Connection dbConnectionDirty;
    Connection dbConnectionEmployer;

	public Connection getDbConnectionDirty() throws ClassNotFoundException, SQLException {
		String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbNameDirty;

		Class.forName("com.mysql.cj.jdbc.Driver");

		dbConnectionDirty = DriverManager.getConnection(connectionString, dbUser, dbPass);

		return dbConnectionDirty;
	}

    public Connection getDbConnectionEmployer() throws ClassNotFoundException, SQLException {
		String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbNameEmployer;

		Class.forName("com.mysql.cj.jdbc.Driver");

		dbConnectionEmployer = DriverManager.getConnection(connectionString, dbUser, dbPass);

		return dbConnectionEmployer;
	}

	public void singUpAlt(String name, String password, String city, int age, boolean cooking, int countBoyfriend) throws ClassNotFoundException, SQLException{
		String insert = "INSERT INTO " + Const.ALT_TABLE + "(" + Const.ALT_NAME + "," + Const.ALT_PASSWORD + "," + Const.ALT_CITY + "," 
		+ Const.ALT_AGE + "," + Const.ALT_COOKING + "," 
		+ Const.ALT_CBOYFRIEND + "," + Const.ALT_GIRLTYPE + ")" + "VALUES(?,?,?,?,?,?,?)";
	
		PreparedStatement prSt = getDbConnectionDirty().prepareStatement(insert);
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

	public void singUpMilf(String name, String password, String city, int age, boolean cooking,int children, int husband) throws ClassNotFoundException, SQLException{
		String insert = "INSERT INTO " + Const.MILF_TABLE + "(" + Const.MILF_NAME + "," + Const.MILF_PASSWORD + "," + Const.MILF_CITY + "," 
		+ Const.MILF_AGE + "," + Const.MILF_COOKING + "," 
		+ Const.MILF_CHILDREN + "," + Const.MILF_HUSBAND + "," + Const.MILF_GIRLTYPE + ")" + "VALUES(?,?,?,?,?,?,?,?)";
	
		PreparedStatement prSt = getDbConnectionDirty().prepareStatement(insert);
		try {
            
            prSt.setString(1, name);
            prSt.setString(2, password);
            prSt.setString(3, city);
            prSt.setInt(4, age);
            prSt.setBoolean(5, cooking);
            prSt.setInt(6, children);
			prSt.setInt(7, husband);
            prSt.setString(8, "Милфа");

            prSt.executeUpdate();
            System.out.println("Данные успешно добавлены в базу!");

        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
            e.printStackTrace();
        }
		
	}

    public void singUpEUser(String name, String password, String city, String companyName, String jobDescribe, String girlType, String requirements, boolean advertStatus) throws ClassNotFoundException, SQLException{
		String insert = "INSERT INTO " + Const.EUSER_TABLE + "(" + Const.EUSER_NAME + "," + Const.EUSER_PASSWORD + "," + Const.EUSER_CITY + "," 
		+ Const.EUSER_CNAME + "," + Const.EUSER_JDESCRIBE + "," 
		+ Const.EUSER_GIRLTYPE + "," + Const.EUSER_REQUIREMENTS + "," + Const.EUSER_ASTATUS + ")" + "VALUES(?,?,?,?,?,?,?,?)";
	
		PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(insert);
		try {
            
            prSt.setString(1, name);
            prSt.setString(2, password);
            prSt.setString(3, city);
            prSt.setString(4, companyName);
            prSt.setString(5, jobDescribe);
            prSt.setString(6, girlType);
			prSt.setString(7, requirements);
            prSt.setBoolean(8, advertStatus);

            prSt.executeUpdate();
            System.out.println("Данные успешно добавлены в базу!");

        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
            e.printStackTrace();
        }
		
	}

    public String ResultUser(String login, String password) {
    ResultSet resSet = null;

        String selectAlt = "SELECT * FROM " + Const.ALT_TABLE + " WHERE " +
                Const.ALT_NAME + "=? AND " + Const.ALT_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnectionDirty().prepareStatement(selectAlt);
            prSt.setString(1, login);
            prSt.setString(2, password);
            resSet = prSt.executeQuery();

            if (resSet.next()) {
                return "Altushka";
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectMilf = "SELECT * FROM " + Const.MILF_TABLE + " WHERE " +
                Const.MILF_NAME + "=? AND " + Const.MILF_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnectionDirty().prepareStatement(selectMilf);
            prSt.setString(1, login);
            prSt.setString(2, password);
            resSet = prSt.executeQuery();

            if (resSet.next()) {
                return "Milfa";
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectEmp = "SELECT * FROM " + Const.EUSER_TABLE + " WHERE " +
                Const.EUSER_NAME + "=? AND " + Const.EUSER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(selectEmp);
            prSt.setString(1, login);
            prSt.setString(2, password);
            resSet = prSt.executeQuery();

            if (resSet.next()) {
                return "Employer";
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    
        return "NotFound";
    }
    
}