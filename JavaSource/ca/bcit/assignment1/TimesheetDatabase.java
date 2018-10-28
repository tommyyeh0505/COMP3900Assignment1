package ca.bcit.assignment1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.timesheet.Timesheet;
import ca.bcit.infosys.timesheet.TimesheetCollection;
import ca.bcit.infosys.timesheet.TimesheetRow;

/**
 * 
 * TimesheetDatabase.
 *
 * @author Tommy Yeh (Jen-Hao) A01025451 
 * @version 2017
 */
@Named
@ApplicationScoped
public class TimesheetDatabase implements Serializable, TimesheetCollection {
    /** list of timesheets. */
    private List<Timesheet> timesheets;

    /**
     * 
     * Constructs an objecct of type TimesheetDatabase.
     */
    public TimesheetDatabase() {
        timesheets = new ArrayList<Timesheet>();
    }

    @Override
    public List<Timesheet> getTimesheets() {
        return timesheets;
    }

    @Override
    public List<Timesheet> getTimesheets(Employee e) {
        List<Timesheet> employeeTimesheets = new ArrayList<Timesheet>();

        for (Timesheet t : timesheets) {
            if (t.getEmployee().equals(e)) {
                employeeTimesheets.add(t);
            }
        }

        return employeeTimesheets;
    }

    @Override
    public Timesheet getCurrentTimesheet(Employee e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Timesheet addTimesheet(Employee e) {
        Timesheet t = new Timesheet();
        ArrayList<TimesheetRow> rows = (ArrayList<TimesheetRow>) t.getDetails();
        for (int i = 0; i < 2 + 2 + 1; i++) {
            rows.add(new TimesheetRow());
        }
        System.out.println(e);
        t.setEmployee(e);
        timesheets.add(t);

        return t;
    }

}
