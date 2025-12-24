package database;

import configs.Const;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DBHandlerResponses extends DatabaseHandler{
    
    public void addResponses(int idVacancy, String girlName, String girlType, String telegramm, Boolean responsesStatus) throws ClassNotFoundException, SQLException{
		String insert = "INSERT INTO " + Const.RESPONSES_TABLE + "(" + Const.RESPONSES_ID_VACANCY + "," + Const.RESPONSES_GIRL_NAME + "," + Const.RESPONSES_GIRL_TYPE + "," 
		+ Const.RESPONSES_TELEGRAMM + "," + Const.RESPONSES_STATUS + ")" + "VALUES(?,?,?,?,?)";
	
		PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(insert);
		try {
            
            prSt.setInt(1, idVacancy);
            prSt.setString(2, girlName);
            prSt.setString(3, girlType);
            prSt.setString(4, telegramm);
            prSt.setBoolean(5, responsesStatus);

            prSt.executeUpdate();
            System.out.println("Данные успешно добавлены в базу!");

        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
            e.printStackTrace();
        }
		
	}

    public boolean statusResponses(int idVacancy, String girlName){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.RESPONSES_TABLE  + " WHERE " +
            Const.RESPONSES_ID_VACANCY + "=? AND " + Const.RESPONSES_GIRL_NAME + "=?";
            
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(select);
            prSt.setInt(1, idVacancy);
            prSt.setString(2, girlName);

            resSet = prSt.executeQuery();
            if(resSet.next()){
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Integer> getIdVacancyResponses(String girlName) {
        List<Integer> vacancyIds = new ArrayList<>();
        String select = "SELECT DISTINCT " + Const.RESPONSES_ID_VACANCY + 
                    " FROM " + Const.RESPONSES_TABLE + 
                    " WHERE " + Const.RESPONSES_GIRL_NAME + "=?";
        
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(select);
            prSt.setString(1, girlName);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                vacancyIds.add(resSet.getInt(Const.RESPONSES_ID_VACANCY));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vacancyIds;
    }

    public List<String[]> getIdActiveVacancyResponses(boolean active) {
        List<String[]> activeVacancies = new ArrayList<>();
        String select = "SELECT DISTINCT " + Const.RESPONSES_ID_VACANCY + "," + Const.RESPONSES_GIRL_NAME + "," +
        Const.RESPONSES_GIRL_TYPE + " FROM " + Const.RESPONSES_TABLE + " WHERE " + Const.RESPONSES_STATUS + "=?";
        
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(select);
            prSt.setBoolean(1, active);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()){
                activeVacancies.add(new String[]{
                    String.valueOf(resSet.getInt(Const.RESPONSES_ID_VACANCY)),
                    resSet.getString(Const.RESPONSES_GIRL_NAME),
                    resSet.getString(Const.RESPONSES_GIRL_TYPE)
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return activeVacancies;
    }

    public List<String[]> responsesVacancy(int idVacancy){
        List<String[]> responsesVacancy = new ArrayList<>();
        String select = "SELECT * FROM " + Const.RESPONSES_TABLE + " WHERE " + Const.RESPONSES_ID_VACANCY + "=?";
            
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(select);
            prSt.setInt(1, idVacancy);
            ResultSet resSet = prSt.executeQuery();

            while(resSet.next()){
                responsesVacancy.add(new String[]{
                    String.valueOf(resSet.getInt(Const.RESPONSES_ID)),  
                    resSet.getString(Const.RESPONSES_GIRL_NAME),       
                    resSet.getString(Const.RESPONSES_GIRL_TYPE),        
                    String.valueOf(resSet.getBoolean(Const.RESPONSES_STATUS)),
                    resSet.getString(Const.RESPONSES_TELEGRAMM)
                });
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return responsesVacancy;
    }  

    public void deleteResponses(int idVacancy, String girlName) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Const.RESPONSES_TABLE + " WHERE " + Const.RESPONSES_ID_VACANCY + "=? AND " + Const.RESPONSES_GIRL_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(query);

            prSt.setInt(1, idVacancy);
            prSt.setString(2, girlName);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) { 
            e.printStackTrace(); 
        }
    }
}
