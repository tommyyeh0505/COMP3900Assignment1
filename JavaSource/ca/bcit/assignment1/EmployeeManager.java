package ca.bcit.assignment1;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;
import ca.bcit.infosys.employee.Employee;

@Named
public class EmployeeManager implements Serializable {
    @Inject
    EmployeeDatabase employeeDatabase;

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    private Credentials credentials;
    private Employee currentEmployee;
    private boolean isAdmin;

    @PostConstruct
    public void init() {
        credentials = (Credentials) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("credentials");

        currentEmployee = (Employee) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("employee");

        isAdmin = employeeDatabase.getAdministrator().equals(currentEmployee);
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Employee> getEmployees() {
        return employeeDatabase.getEmployees();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials c) {
        credentials = c;
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee e) {
        currentEmployee = e;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

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

    public String deleteUser(Employee e) {
        employeeDatabase.getLoginCombos().remove(e.getUserName());
        employeeDatabase.getEmployees().remove(e);
        return null;
    }
}
