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

@Named
public class EmployeeManager implements Serializable {
    @Inject
    private EmployeeDatabase employeeDatabase;

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

    public EmployeeDatabase getEmployeeDatabase() {
        return employeeDatabase;
    }

    public void setEmployeeDatabase(EmployeeDatabase e) {
        employeeDatabase = e;
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

    public void updateUserName(String s) {
        employeeDatabase.getLoginCombos().put(s, "test");
    }

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

    public String createUser() {
        employeeDatabase.addEmployee(new Employee(), null);
        return null;
    }

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
                if (e.getKey().equals(emp.getUserName())) {
                    hasMatch = true;
                }
            }
            if (!hasMatch) {
                it2.remove();
            }
        }

        return "saveUsers";
    }

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
