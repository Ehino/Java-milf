package database;

import configs.Const;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHandlerVacancy extends DatabaseHandler{
    
    public void addVacancy(String loginEmp, String jobDescription, String girlType, String requirements) {
        String insert = "INSERT INTO " + Const.VACANCY_TABLE + "(" + Const.VACANCY_LOGIN + "," + Const.VACANCY_JDESCRIBE + "," + Const.VACANCY_GIRLTYPE + "," 
        + Const.VACANCY_REQUIREMENTS + "," + Const.VACANCY_ASTATUS + ")" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(insert);
            prSt.setString(1, loginEmp);
            prSt.setString(2, jobDescription);
            prSt.setString(3, girlType);
            prSt.setString(4, requirements);
            prSt.setBoolean(5, true);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String[]> getEmployerVacancies(String vacancyLogin) {
        List<String[]> vacancies = new ArrayList<>();
        String select = "SELECT * FROM " + Const.VACANCY_TABLE + " WHERE " + Const.VACANCY_LOGIN + "=?";

        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(select);
            prSt.setString(1, vacancyLogin);
            ResultSet resSet = prSt.executeQuery();

            while (resSet.next()) {
                int idVacancy = resSet.getInt(Const.VACANCY_ID);
                boolean isActive = resSet.getBoolean(Const.VACANCY_ASTATUS);

                vacancies.add(new String[]{
                    String.valueOf(resSet.getInt(Const.VACANCY_ID)),
                    resSet.getString(Const.VACANCY_GIRLTYPE),
                    resSet.getString(Const.VACANCY_JDESCRIBE),
                    resSet.getString(Const.VACANCY_REQUIREMENTS),
                    isActive ? "Активна" : "Закрыта"
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    public List<String[]> getFilterVacancies(String vacancyGirltype, boolean vacancyAStatus) {
        List<String[]> vacancies = new ArrayList<>();
        String select = "SELECT * FROM " + Const.VACANCY_TABLE + " WHERE (" + Const.VACANCY_GIRLTYPE + "=? OR " + Const.VACANCY_GIRLTYPE + "='Любой') AND " + Const.VACANCY_ASTATUS + "=?";

        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(select);
            prSt.setString(1, vacancyGirltype);
            prSt.setBoolean(2, vacancyAStatus);
            ResultSet resSet = prSt.executeQuery();

            while (resSet.next()) {
                boolean isActive = resSet.getBoolean(Const.VACANCY_ASTATUS);

                vacancies.add(new String[]{
                    String.valueOf(resSet.getInt(Const.VACANCY_ID)),
                    resSet.getString(Const.VACANCY_GIRLTYPE),
                    resSet.getString(Const.VACANCY_JDESCRIBE),
                    resSet.getString(Const.VACANCY_REQUIREMENTS),
                    isActive ? "Активна" : "Закрыта"
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    public void updateVacancyStatus(int idVacancy, boolean isActive) {
        String update = "UPDATE " + Const.VACANCY_TABLE + " SET " + Const.VACANCY_ASTATUS + "=? " +
            "WHERE " + Const.VACANCY_ID + "=?";
        
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(update);
            prSt.setBoolean(1, isActive);
            prSt.setInt(2, idVacancy);
            
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteVacancy(int idVacancy, String login){
        String query = "DELETE FROM " + Const.VACANCY_TABLE + " WHERE " + Const.VACANCY_ID + "=? AND " + Const.VACANCY_LOGIN + "=?";
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(query);

            prSt.setInt(1, idVacancy);
            prSt.setString(2, login);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) { 
            e.printStackTrace(); 
        }
    }
}
