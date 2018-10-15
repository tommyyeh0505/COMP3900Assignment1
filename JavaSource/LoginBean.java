import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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
    
    public void setUsername(String s) {
        username = s;
    }
    
    public void setPassword(String s) {
        password = s;
    }
}
