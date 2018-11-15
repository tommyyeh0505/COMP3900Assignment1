package ca.bcit.assignment1;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@Named
@ApplicationScoped
public class Database implements Serializable {

    @Resource(mappedName = "java:jboss/datasources/assignment2")
    private DataSource dataSource;
    
    public void addUser(User user) {
        try {
            Connection connection = dataSource.getConnection();
            
            String sql = "INSERT INTO Users(username, empName, password, administrator) VALUES(?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmployeeName());
            pstmt.setString(3, user.getPassword());
            pstmt.setBoolean(4, user.isAdmin());
            
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<User> getUsers() {
        System.out.println("run");
        List<User> users = new ArrayList<User>();
        
        try {
            Connection connection = dataSource.getConnection();
            ResultSet result = connection.createStatement().executeQuery(
                    "SELECT * FROM users");
            
            while (result.next()) {
                int id = result.getInt(1);
                String username = result.getString(2);
                String empName = result.getString(3);
                String password = result.getString(4);
                boolean admin = result.getBoolean(5);
                User user = new User(id, username, empName, password, admin);
                users.add(user);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public void updateUser(User user) {
        try {
            Connection connection = dataSource.getConnection();
            
            String sql = "UPDATE Users"
                    + " SET username = ?,"
                    + " empName = ?,"
                    + " password = ?,"
                    + " administrator = ?"
                    + " WHERE empNumber = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmployeeName());
            pstmt.setString(3, user.getPassword());
            pstmt.setBoolean(4, user.isAdmin());
            pstmt.setInt(5, user.getEmployeeNumber());
            
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteUser(User user) {
        try {
            Connection connection = dataSource.getConnection();
            
            String sql = "DELETE FROM Users WHERE empNumber = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, user.getEmployeeNumber());
            
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addTimesheet(Timesheet timesheet) {
        try {
            Connection connection = dataSource.getConnection();
            
            String sql = "INSERT INTO Timesheet(empNumber, weekNumber, weekEnding) VALUES(?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, timesheet.getEmpNumber());
            pstmt.setInt(2, timesheet.getWeekNumber());
            pstmt.setString(3, timesheet.getWeekEnding().toString());
            
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Timesheet> getTimesheets() {
        List<Timesheet> timesheets = new ArrayList<Timesheet>();
        
        
        return timesheets;

    
    }
    
    public void updateTimesheet(Timesheet timesheet) {
        
    }
    
    public void deleteTimesheet(Timesheet timesheet) {
        try {
            Connection connection = dataSource.getConnection();
            
            String sql = "DELETE FROM Timesheet WHERE timesheetid = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, 0); // timesheet id
            
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addTimesheetRow(TimesheetRow row) {
        try {
            Connection connection = dataSource.getConnection();
            
            String sql = "INSERT INTO timesheetrow VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, row.getTimesheetID());
            pstmt.setInt(2, row.getProjectID());
            pstmt.setString(3, row.getWorkPackage());
            pstmt.setDouble(4, row.getSat());
            pstmt.setDouble(5, row.getSun());
            pstmt.setDouble(6, row.getMon());
            pstmt.setDouble(7, row.getTue());
            pstmt.setDouble(8, row.getWed());
            pstmt.setDouble(9, row.getThu());
            pstmt.setDouble(10, row.getFri());
            pstmt.setString(11, row.getNotes());
            
            pstmt.executeUpdate();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<TimesheetRow> getTimesheetRows() {
        return null;
    }
    
    public void updateTimesheetRow(TimesheetRow row) {
        
    }
    
    public void deleteTimesheetRow(Timesheet row) {
        
    }
}
