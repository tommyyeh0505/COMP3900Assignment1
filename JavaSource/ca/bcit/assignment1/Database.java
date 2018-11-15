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

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        
        try {
            Connection connection = dataSource.getConnection();
            ResultSet result = connection.createStatement().executeQuery(
                    "SELECT * FROM users");
            
            int index = 0;
            while (result.next()) {
                index = 0;
                
                int id = result.getInt(++index);
                String username = result.getString(++index);
                String empName = result.getString(++index);
                String password = result.getString(++index);
                boolean admin = result.getBoolean(++index);
                User user = new User(id, username, empName, password, admin);
                users.add(user);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public void addUser(User user) {
        try {
            Connection connection = dataSource.getConnection();
            
            String sql = "INSERT INTO Users VALUES(?, ?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, user.getEmployeeNumber());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmployeeName());
            pstmt.setString(4, user.getPassword());
            pstmt.setBoolean(5, user.isAdmin());
            
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
