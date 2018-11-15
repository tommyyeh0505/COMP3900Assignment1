package ca.bcit.assignment1;

/**
 * User.
 *
 * @author Tommy Yeh (Jen-Hao) A01025451 
 * @version 2017
 */
public class User {

    private String userName;
    private int empNumber;
    private String name;
    private String password;
    private Boolean admin;
    
    /**
     * Constructs an objecct of type User.
     * @param userName
     * @param empNumber
     * @param name
     * @param password
     * @param admin
     */
    public User(String userName, int empNumber, String name, String password, Boolean admin) {
        super();
        this.userName = userName;
        this.empNumber = empNumber;
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    /**
     * Returns the {bare_field_name} for this User.
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the userName for this User
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the {bare_field_name} for this User.
     * @return the empNumber
     */
    public int getEmpNumber() {
        return empNumber;
    }

    /**
     * Sets the empNumber for this User
     * @param empNumber the empNumber to set
     */
    public void setEmpNumber(int empNumber) {
        this.empNumber = empNumber;
    }

    /**
     * Returns the {bare_field_name} for this User.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for this User
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the {bare_field_name} for this User.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for this User
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the {bare_field_name} for this User.
     * @return the admin
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * Sets the admin for this User
     * @param admin the admin to set
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    
}
