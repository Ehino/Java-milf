package database;

import configs.Configs;
import configs.Const;
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

    public String getUserRole(String login, String password) {
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
        } catch(SQLException e){
            System.out.print("Ошибка SQL: ");
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            System.out.print("Ошибка драйвера: ");
            e.printStackTrace();
        }

        return "NotFound";
    }

    public boolean isNameUser(String login) {
    ResultSet resSet = null;

        String selectAlt = "SELECT * FROM " + Const.ALT_TABLE + " WHERE " +
                Const.ALT_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnectionDirty().prepareStatement(selectAlt);
            prSt.setString(1, login);
            resSet = prSt.executeQuery();

            if (resSet.next()){
                return true;
            } 

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectMilf = "SELECT * FROM " + Const.MILF_TABLE + " WHERE " +
                Const.MILF_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnectionDirty().prepareStatement(selectMilf);
            prSt.setString(1, login);
            resSet = prSt.executeQuery();

            if (resSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String selectEmp = "SELECT * FROM " + Const.EUSER_TABLE + " WHERE " +
                Const.EUSER_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(selectEmp);
            prSt.setString(1, login);
            resSet = prSt.executeQuery();

            if (resSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void updateDirtyCirls(String field, String edit, String login, String userRole) {
        if(userRole.equals("Milfa")){
            userRole = Const.MILF_TABLE;
        } else {
            userRole = Const.ALT_TABLE;
        }
        
        String update = "UPDATE " + userRole + " SET " + field + 
        "=? WHERE " + Const.MILF_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnectionDirty().prepareStatement(update);
            if(!field.equals(Const.MILF_COOKING)) {
                if(field.equals(Const.MILF_AGE) || field.equals(Const.MILF_CHILDREN) || field.equals(Const.MILF_HUSBAND) || field.equals(Const.ALT_CBOYFRIEND)){
                    try {
                        prSt.setInt(1, Integer.parseInt(edit));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    prSt.setString(1, edit);
                }
            } else{
                if(edit.equals("Умеет")) prSt.setBoolean(1, true);
                else prSt.setBoolean(1, false);
            } 
            prSt.setString(2, login);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
} 