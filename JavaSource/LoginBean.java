import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;

@Named("login")
@RequestScoped
public class LoginBean {

    @Inject
    private EmployeeManager employeeManager;

    private Credentials credentials;
    
    @PostConstruct
    public void init() {
        credentials = new Credentials();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public String validateLogin() {
        if (employeeManager.verifyUser(credentials)) {
            return "login";
        }

        return null;
    }
}
