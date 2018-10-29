package ca.bcit.assignment1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
     * Checks the week.
     */
    private Boolean checkWeek;

    /**
     * forwards to edit timesheet page.
     * 
     * @param t
     *            timesheet
     * @return pagename
     */
    public String editTimesheet(Timesheet t) {
        currentTimesheet = t;
        return "editTimesheet";
    }

    /**
     * Creates new Timesheet and forwards to create timesheet page.
     * 
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
     * 
     * @return timesheets
     */
    public List<Timesheet> getTimesheets() {
        Employee e = (Employee) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("employee");
        return timesheetDatabase.getTimesheets(e);
    }

    /**
     * Goes back to timesheetListPage.
     * 
     * @return string
     */
    public String saveTimesheet() {
        return "saveTimesheet";
    }

    /**
     * Destroys timesheet and deletes it from timesheetList.
     * 
     * @return string
     */
    public String discardTimesheet() {
        timesheetDatabase.getTimesheets().remove(currentTimesheet);
        currentTimesheet = null;
        return "discardTimesheet";
    }

    /**
     * Returns current Timesheet.
     * 
     * @return timesheet
     */
    public Timesheet getCurrentTimesheet() {
        return currentTimesheet;
    }

    /**
     * Sets current Timesheet.
     * 
     * @param t
     *            timesheet
     */
    public void setCurrentTimesheet(Timesheet t) {
        currentTimesheet = t;
    }

    /**
     * CheckWeek Getter.
     * 
     * @return true if timesheet week is same as current week
     */
    public Boolean getCheckWeek() {
        Calendar c = new GregorianCalendar();
        c.setTime(currentTimesheet.getEndWeek());
        c.setFirstDayOfWeek(Calendar.SATURDAY);
        int a = c.get(Calendar.WEEK_OF_YEAR);

        Calendar b = new GregorianCalendar();

        int currentDay = b.get(Calendar.DAY_OF_WEEK);
        int leftDays = Calendar.FRIDAY - currentDay;
        b.add(Calendar.DATE, leftDays);
        int d = b.get(Calendar.WEEK_OF_YEAR);
        System.out
                .println("Timesheet Week= " + a + " : " + "Current Week=" + d);
        checkWeek = (a == d);
        return checkWeek;

    }

    /**
     * Sets the checkWeek for this TimesheetManager.
     * 
     * @param checkWeek
     *            the checkWeek to set
     */
    public void setCheckWeek(Boolean checkWeek) {
        this.checkWeek = checkWeek;
    }

}
