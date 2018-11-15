package ca.bcit.assignment1;

public class User {
    private int employeeNumber;
    private String username;
    private String password;
    private String employeeName;
    private boolean admin;

    public User(int empNo, String username, String employeeName,
            String password, boolean admin) {
        this.employeeNumber = empNo;
        this.username = username;
        this.password = password;
        this.employeeName = employeeName;
        this.admin = admin;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
