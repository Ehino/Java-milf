package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import configs.Const;
import models.Milfa;

public class DBHandlerMilfa extends DatabaseHandler{
    
    public void addMilf(String name, String password, String city, int age, boolean cooking,int children, int husband) throws ClassNotFoundException, SQLException{
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

    public Milfa getInfoMilf(String login) {
    String select = "SELECT * FROM " + Const.MILF_TABLE + " WHERE " + Const.MILF_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnectionDirty().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resSet = prSt.executeQuery();

            if (resSet.next()) {
                return new Milfa(
                    resSet.getString(Const.MILF_NAME),
                    resSet.getString(Const.MILF_PASSWORD),
                    resSet.getString(Const.MILF_CITY),
                    resSet.getInt(Const.MILF_AGE),
                    resSet.getBoolean(Const.MILF_COOKING),
                    resSet.getInt(Const.MILF_CHILDREN),
                    resSet.getInt(Const.MILF_HUSBAND)
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
            return null;
    }
}
