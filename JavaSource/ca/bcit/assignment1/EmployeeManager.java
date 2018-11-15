package ca.bcit.assignment1;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;
import ca.bcit.infosys.employee.Employee;
import deprecated.EmployeeDatabase;

/**
 * 
 * EmployeeManager.
 *
 * @author Andy A01027848
 * @version 2018
 */
@Named
public class EmployeeManager implements Serializable {

    /**
     * Employee database.
     */
    @Inject
    private EmployeeDatabase employeeDatabase;

    /**
     * Current password field variable.
     */
    private String currentPassword;

    /**
     * New password field variable.
     */
    private String newPassword;

    /**
     * Confirm password field variable.
     */
    private String confirmPassword;
    
    /**
     * Object storing the credentials for the current user.
     */
    private Credentials credentials;
    
    /**
     * The current user.
     */
    private Employee currentEmployee;
    
    /**
     * Represents whether the user is the administrator or not.
     */
    private boolean isAdmin;

    /**
     * Bean post construct method.
     */
    @PostConstruct
    public void init() {
        credentials = (Credentials) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("credentials");

        currentEmployee = (Employee) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("employee");

        isAdmin = employeeDatabase.getAdministrator().equals(currentEmployee);
    }

    /**
     * Returns whether the current user is the admin.
     * @return isAdmin
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets whether use isAdmin.
     * @param isAdmin isAdmin variable
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Gets all the employees in the employee database.
     * @return List object containing all employees
     */
    public List<Employee> getEmployees() {
        return employeeDatabase.getEmployees();
    }

    /**
     * Gets the current user's credentials.
     * @return credentials
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Sets the current user's session credentials object.
     * @param c new credentials
     */
    public void setCredentials(Credentials c) {
        credentials = c;
    }

    /**
     * Gets the current employee.
     * @return current employee
     */
    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    /**
     * Sets the current employee.
     * @param e new employee
     */
    public void setCurrentEmployee(Employee e) {
        currentEmployee = e;
    }

    /**
     * Gets the current password for input field.
     * @return currentPassword
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    /**
     * Sets the current password for input field.
     * @param currentPassword new current password
     */
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    /**
     * Gets the new password for input field.
     * @return newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets new password for input field.
     * @param newPassword new password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Gets the confirmation password for input field.
     * @return confirmationPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirmation password for input field.
     * @param confirmPassword confirmation password
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Gets the employeeDatabase object.
     * @return employeeDatabase object.
     */
    public EmployeeDatabase getEmployeeDatabase() {
        return employeeDatabase;
    }

    /**
     * Sets the employeeDatabase object.
     * @param e new EmployeeDatabase object
     */
    public void setEmployeeDatabase(EmployeeDatabase e) {
        employeeDatabase = e;
    }

    /**
     * Saves the current employee's password.
     * @return string for navigation
     */
    public String savePassword() {
        if (currentPassword.equals(credentials.getPassword())
                && newPassword.equals(confirmPassword)) {
            credentials.setPassword(newPassword);
            employeeDatabase.setLoginCombos(credentials.getUserName(),
                    newPassword);
            return "savePassword";
        }

        return null;
    }

    /**
     * Updates current employee's username.
     * @param s new username
     */
    public void updateUserName(String s) {
        employeeDatabase.getLoginCombos().put(s, "test");
    }

    /**
     * Deletes a user.
     * @param e employee to delete
     * @return string for navigation
     */
    public String deleteUser(Employee e) {
        if (e.equals(employeeDatabase.getAdministrator())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Cannot delete the administrator.", null));
            return null;
        }
        employeeDatabase.getLoginCombos().remove(e.getUserName());
        employeeDatabase.getEmployees().remove(e);
        return null;
    }

    /**
     * Creates a new user.
     * @return string 'null' to navigate to same page (refresh)
     */
    public String createUser() {
        employeeDatabase.addEmployee(new Employee(), null);
        return null;
    }

    /**
     * Saves the users when a admin modifies the users list.
     * @return string for navigation
     */
    public String saveUsers() {
        Iterator<Employee> it = employeeDatabase.getEmployees().iterator();

        while (it.hasNext()) {
            Employee e = it.next();
            String password = employeeDatabase.getLoginCombos()
                    .get(e.getUserName());
            if (e.getEmpNumber() == 0 || stringNullOrEmpty(e.getName())
                    || stringNullOrEmpty(e.getUserName())
                    || stringNullOrEmpty(password)) {
                employeeDatabase.getLoginCombos().remove(e.getUserName());
                it.remove();
            }
        }

        Iterator<Entry<String, String>> it2 = employeeDatabase.getLoginCombos()
                .entrySet().iterator();
        while (it2.hasNext()) {
            Entry<String, String> e = it2.next();
            boolean hasMatch = false;
            for (Employee emp : employeeDatabase.getEmployees()) {
                if (e.getKey() != null
                        && e.getKey().equals(emp.getUserName())) {
                    hasMatch = true;
                }
            }
            if (!hasMatch) {
                it2.remove();
            }
        }

        return "saveUsers";
    }

    /**
     * Checks to see if a string is null or empty.
     * @param s string to check
     * @return boolean representing whether string is null or empty
     */
    private boolean stringNullOrEmpty(String s) {
        if (s == null) {
            return true;
        }

        if (s.trim().length() == 0) {
            return true;
        }

        return false;
    }

}
