package ca.bcit.assignment1;

import java.io.IOException;
import java.io.Serializable;

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

    /**
     * Employee information database.
     */
    @Inject
    private EmployeeDatabase database;

    /**
     * Credentials for the current login.
     */
    private Credentials credentials;

    /**
     * Bean post construction method.
     */
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

    /**
     * Gets the credentials object.
     * 
     * @return credentials
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Sets the credentials object.
     * 
     * @param c
     *            new credentials object.
     */
    public void setCredentials(Credentials c) {
        credentials = c;
    }

    /**
     * Login validation method, checks the given credentials against the
     * database entries to see if there is a match.
     * 
     * @return string representing navigation outcome for redirection. Null if
     *         validation fails.
     */
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
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Invalid username or password", null));

        return null;
    }
}
