import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ca.bcit.infosys.employee.EmployeeList;

@Named("login")
@RequestScoped
public class LoginBean {
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    
}
