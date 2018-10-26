package ca.bcit.assignment1;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;
import ca.bcit.infosys.employee.Employee;

@Named("login")
@RequestScoped
public class LoginManager implements Serializable {

    @Inject
    private EmployeeDatabase database;

    private Credentials credentials;

    @PostConstruct
    public void init() {
        Credentials c = (Credentials) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .put("credentials", credentials);
        
        if (c != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("timesheet_list.xhtml");
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
        if (database.verifyUser(credentials)) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("credentials", credentials);
            
            for (Employee e : database.getEmployees()) {
                if (e.getUserName().equals(credentials.getUserName())) {
                    FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("employee", e);
                    break;
                }
            }
            
            return "login";
        }

        return null;
    }
}
