package ca.bcit.assignment1;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.timesheet.Timesheet;

@Named
public class TimesheetManager implements Serializable {
    @Inject
    private TimesheetDatabase timesheetDatabase;

    private Timesheet currentTimesheet;

    public String editTimesheet(Timesheet t) {
        currentTimesheet = t;
        return "editTimesheet";
    }

    public String createTimesheet() {
        Employee e = (Employee) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("employee");
        currentTimesheet = timesheetDatabase.addTimesheet(e);
        return "createTimesheet";
    }
    
    public List<Timesheet> getTimesheets() {
        Employee e = (Employee) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("employee");
        return timesheetDatabase.getTimesheets(e);
    }

    public String saveTimesheet() {
        return "saveTimesheet";
    }

    public String discardTimesheet() {
        timesheetDatabase.getTimesheets().remove(currentTimesheet);
        currentTimesheet = null;
        return "discardTimesheet";
    }

    public Timesheet getCurrentTimesheet() {
        return currentTimesheet;
    }

    public void setCurrentTimesheet(Timesheet t) {
        currentTimesheet = t;
    }
}
