package ca.bcit.assignment1;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;
import ca.bcit.infosys.employee.Employee;

/**
 * 
 * LoginManager.
 *
 * @author Andy A01027848
 * @version 2018
 */
@Named("login")
@RequestScoped
public class LoginManager implements Serializable {

    @Inject
    private Database database;
    
    private Credentials credentials;

    @PostConstruct
    public void init() {
        Credentials c = (Credentials) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("credentials");

        if (c != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("timesheet_list.xhtml");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        credentials = new Credentials();
    }
    
    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials c) {
        credentials = c;
    }

    public String login() {
        
        List<User> users = database.getUsers();
        
        for (User user : users) {
            if (user.getUsername().equals(credentials.getUserName())
                    && user.getPassword().equals(credentials.getPassword())) {
                FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("credentials", credentials);
                FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("user", user);
                
                return "login";
            }
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Invalid username or password", null));

        return null;
    }
}
