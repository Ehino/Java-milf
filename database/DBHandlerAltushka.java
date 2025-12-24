package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import configs.Const;
import models.Altushka;

public class DBHandlerAltushka extends DatabaseHandler{

    public void addAlt(String name, String password, String city, int age, boolean cooking, int countBoyfriend) throws ClassNotFoundException, SQLException{
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

    public Altushka getInfoAlt(String login) {
    String select = "SELECT * FROM " + Const.ALT_TABLE + " WHERE " + Const.ALT_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnectionDirty().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resSet = prSt.executeQuery();

            if (resSet.next()) {
                return new Altushka(
                    resSet.getString(Const.ALT_NAME),
                    resSet.getString(Const.ALT_PASSWORD),
                    resSet.getString(Const.ALT_CITY),
                    resSet.getInt(Const.ALT_AGE),
                    resSet.getBoolean(Const.ALT_COOKING),
                    resSet.getInt(Const.ALT_CBOYFRIEND)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
            return null;
    }
    
}
