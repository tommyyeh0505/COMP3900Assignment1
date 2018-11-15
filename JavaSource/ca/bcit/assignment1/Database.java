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

        try {
            //GET ALL TIMESHEETS
            Connection connection = dataSource.getConnection();
            ResultSet result = connection.createStatement().executeQuery(
                    "SELECT * FROM Timesheet");
            int index = 0;
            while (result.next()) {
                index = 0;
                double[] hours = new double[7];
                ArrayList<TimesheetRow> timesheetRows = new ArrayList<TimesheetRow>();
                int timesheetID = result.getInt(++index);
                int empNumber = result.getInt(++index);
                int weekNumber = result.getInt(++index);
                Date weekEnding = result.getDate(++index);
                Timesheet t = new Timesheet(timesheetID, timesheetRows, empNumber, weekNumber, weekEnding);
                timesheets.add(t);
            }

            List<TimesheetRow> rows = getTimesheetRows(); //gets all TimesheetRows
            //Adds TimesheetRows to respective Timesheets.
            for(Timesheet t : timesheets) {
                t.populateTimesheet(rows);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timesheets;
    }

    public void updateTimesheet(Timesheet timesheet) {
        try {
            Connection connection = dataSource.getConnection();

            String sql = "UPDATE Timesheet"
                    + " SET empNumber = ?,"
                    + " weekNumber = ?,"
                    + " weekEnding = ?"
                    + " WHERE timesheetId = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, timesheet.getEmpNumber());
            pstmt.setInt(2, timesheet.getWeekNumber());
            pstmt.setDate(3, timesheet.getWeekEnding());
            pstmt.setInt(4, timesheet.getTimesheetID());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTimesheet(Timesheet timesheet) {
        try {
            Connection connection = dataSource.getConnection();

            String sql = "DELETE FROM Timesheet WHERE timesheetid = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, timesheet.getTimesheetID());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTimesheetRow(TimesheetRow row) {
        try {
            Connection connection = dataSource.getConnection();

            String sql = "INSERT INTO TimesheetRow VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        ArrayList<TimesheetRow> rows = new ArrayList<TimesheetRow>();
        //GET ALL TIMESHEETROWS
        try {
            Connection connection = dataSource.getConnection();
            ResultSet result = connection.createStatement().executeQuery(
                    "SELECT * FROM TimesheetRow");
            int index = 0;
            while (result.next()) {
                index = 0;
                double[] hours = new double[7];

                int timesheetRowID = result.getInt(++index);
                int timesheetID = result.getInt(++index);
                int projectID = result.getInt(++index);
                String workPackage = result.getString(++index);
                hours[0] = result.getDouble(++index);
                hours[1] = result.getDouble(++index);
                hours[2] = result.getDouble(++index);
                hours[3] = result.getDouble(++index);
                hours[4] = result.getDouble(++index);
                hours[5] = result.getDouble(++index);
                hours[6] = result.getDouble(++index);
                String notes = result.getString(++index);

                TimesheetRow row = new TimesheetRow(timesheetRowID, timesheetID, projectID, workPackage, hours, notes);

                rows.add(row);
            }
            //Adds TimesheetRows to respective Timesheets.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public void updateTimesheetRow(TimesheetRow row) {
        try {
            Connection connection = dataSource.getConnection();

            String sql = "UPDATE TimesheetRow"
                    + " SET projectID = ?,"
                    + " workPackage = ?,"
                    + " sat = ?,"
                    + " sun = ?,"
                    + " mon = ?,"
                    + " tue = ?,"
                    + " wed = ?,"
                    + " thu = ?,"
                    + " fri = ?,"
                    + " notes = ?"
                    + " WHERE timesheetRowID = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, row.getProjectID());
            pstmt.setString(2, row.getWorkPackage());
            double[] hours = row.getHours();
            pstmt.setDouble(3, hours[0]);
            pstmt.setDouble(4, hours[1]);
            pstmt.setDouble(5, hours[2]);
            pstmt.setDouble(6, hours[3]);
            pstmt.setDouble(7, hours[4]);
            pstmt.setDouble(8, hours[5]);
            pstmt.setDouble(9, hours[6]);
            pstmt.setString(10, row.getNotes());
            pstmt.setInt(11, row.getTimesheetRowID());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTimesheetRow(TimesheetRow row) {
        try {
            Connection connection = dataSource.getConnection();

            String sql = "DELETE FROM TimesheetRow WHERE timesheetRowID = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, row.getTimesheetRowID());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
