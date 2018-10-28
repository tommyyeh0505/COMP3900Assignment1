package ca.bcit.assignment1;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.timesheet.Timesheet;
/**
 * 
 * TimesheetManager.
 *
 * @author Tommy Yeh (Jen-Hao) A01025451 
 * @version 2017
 */
@Named
public class TimesheetManager implements Serializable {
    
    /**
     * Injecting timesheetDatabase.
     */
    @Inject
    private TimesheetDatabase timesheetDatabase;

    /** stores current timesheet as a variable to access. */
    private Timesheet currentTimesheet;

    /**
     * forwards to edit timesheet page.
     * @param t timesheet
     * @return pagename
     */
    public String editTimesheet(Timesheet t) {
        currentTimesheet = t;
        return "editTimesheet";
    }

    /**
     * Creates new Timesheet and forwards to create timesheet page.
     * @return pagename
     */
    public String createTimesheet() {
        Employee e = (Employee) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("employee");
        currentTimesheet = timesheetDatabase.addTimesheet(e);
        return "createTimesheet";
    }

    /**
     * Returns list of timesheets.
     * @return timesheets
     */
    public List<Timesheet> getTimesheets() {
        Employee e = (Employee) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("employee");
        return timesheetDatabase.getTimesheets(e);
    }

    /**
     * Goes back to timesheetListPage.
     * @return string
     */
    public String saveTimesheet() {
        return "saveTimesheet";
    }

    /**
     * Destroys timesheet and deletes it from timesheetList.
     * @return string
     */
    public String discardTimesheet() {
        timesheetDatabase.getTimesheets().remove(currentTimesheet);
        currentTimesheet = null;
        return "discardTimesheet";
    }

    /**
     * Returns current Timesheet.
     * @return timesheet
     */
    public Timesheet getCurrentTimesheet() {
        return currentTimesheet;
    }

    /**
     * Sets current Timesheet.
     * @param t timesheet
     */
    public void setCurrentTimesheet(Timesheet t) {
        currentTimesheet = t;
    }

  


}
