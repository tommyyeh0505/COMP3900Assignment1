import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;

@Named("login")
@RequestScoped
public class LoginBean {
    
    @Inject private EmployeeManager employeeManager;
    
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setUsername(String s) {
        username = s;
    }
    
    public void setPassword(String s) {
        password = s;
    }
    
    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }
    
    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public String validateLogin() {
        Credentials c = new Credentials();
        c.setUserName(username);
        c.setPassword(password);
        boolean validationStatus = employeeManager.verifyUser(c);
        
        if (validationStatus) {
            return "login";
        }
        
        return null;
    }
}
