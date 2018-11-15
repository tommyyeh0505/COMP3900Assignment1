package ca.bcit.assignment1;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 * ApplicationManager.
 *
 * @author Andy A01027848
 * @version 2018
 */
@Named("app")
@SessionScoped
public class ApplicationManager implements Serializable {

    /**
     * TimesheetManager session object.
     */
    @Inject
    private TimesheetManager timesheetManager;

    /**
     * EmployeeManager session object.
     */
    @Inject
    private EmployeeManager employeeManager;
    
    @PostConstruct
    public void init() {
        
    }

    /**
     * Gets the employeeManager object.
     * 
     * @return employeeManager
     */
    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    /**
     * Sets the employeeManager object.
     * 
     * @param employeeManager
     *            new EmployeeManager object
     */
    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    /**
     * Gets the timesheetManager object.
     * 
     * @return timesheetManager
     */
    public TimesheetManager getTimesheetManager() {
        return timesheetManager;
    }

    /**
     * Sets the timesheetManager object.
     * 
     * @param timesheetManager
     *            new TimesheetManager object
     */
    public void setTimesheetManager(TimesheetManager timesheetManager) {
        this.timesheetManager = timesheetManager;
    }

    /**
     * Logout the user, destroys the current session. Wildfly seems to keep
     * saying that the session cannot be destroy (Cannot figure out why).
     * 
     * @return string for navigation
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        return "logout";
    }

    /**
     * Bean predestruction method. Destroys the session, same as the logout()
     * method, cannot figure out why session does not get destroyed, but
     * still works fine.
     */
    @PreDestroy
    public void destroy() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
    }
}
