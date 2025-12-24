package database;

import configs.Const;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.UserEmployer;

public class DBHandlerEmployer extends DatabaseHandler {
    
    public void addEUser(String name, String password, String city, String companyName, String jobDescribe, String girlType, String requirements, boolean advertStatus) throws ClassNotFoundException, SQLException{
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

    public UserEmployer getInfoEmp(String login) {
    String select = "SELECT * FROM " + Const.EUSER_TABLE + " WHERE " + Const.EUSER_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resSet = prSt.executeQuery();

            if (resSet.next()) {
                return new UserEmployer(
                    resSet.getString(Const.EUSER_NAME),
                    resSet.getString(Const.EUSER_PASSWORD),
                    resSet.getString(Const.EUSER_CITY),
                    resSet.getString(Const.EUSER_CNAME),
                    resSet.getString(Const.EUSER_JDESCRIBE),
                    resSet.getString(Const.EUSER_GIRLTYPE),
                    resSet.getString(Const.EUSER_REQUIREMENTS),
                    resSet.getBoolean(Const.EUSER_ASTATUS)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
            return null;
    }

    public void updateEmployerPassword(String field, String edit, String login) {
        if(field.equals("password")) field = Const.EUSER_PASSWORD;
        else if (field.equals("city")) field = Const.EUSER_CITY;
        else if (field.equals("companyName")) field = Const.EUSER_CNAME;
        
        String update = "UPDATE " + Const.EUSER_TABLE + " SET " + field + "=? " +
            "WHERE " + Const.EUSER_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(update);
            prSt.setString(1, edit);
            prSt.setString(2, login);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
