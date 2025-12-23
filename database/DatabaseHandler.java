package database;

import configs.Configs;
import configs.Const;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Altushka;
import models.Milfa;
import models.UserEmployer;


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
        String select = "SELECT * FROM " + Const.VACANCY_TABLE + " WHERE " + Const.VACANCY_GIRLTYPE + "=? AND " + Const.VACANCY_ASTATUS + "=?";

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

    public void updateVacancyStatus(int vacancyId, boolean isActive) {
        String update = "UPDATE " + Const.VACANCY_TABLE + " SET " + 
                    Const.VACANCY_ASTATUS + " = ? " +
                    "WHERE " + Const.VACANCY_ID + " = ?";
        
        try {
            PreparedStatement prSt = getDbConnectionEmployer().prepareStatement(update);
            prSt.setBoolean(1, isActive);
            prSt.setInt(2, vacancyId);
            
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
} 