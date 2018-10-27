package ca.bcit.assignment1;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("app")
@SessionScoped
public class ApplicationManager implements Serializable {
    
    @Inject
    private TimesheetManager timesheetManager;

    @Inject
    private EmployeeManager employeeManager;
    
    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public TimesheetManager getTimesheetManager() {
        return timesheetManager;
    }

    public void setTimesheetManager(TimesheetManager timesheetManager) {
        this.timesheetManager = timesheetManager;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        return "logout";
    }

    @PreDestroy
    public void destroy() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
    }
}
