package ca.bcit.assignment1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
        
        //Creating Test Data
        Employee testEmployee = new Employee("Tommy", 5, "Tommy");
        
        //For Old EndWeek
        Calendar b = new GregorianCalendar();
        b.setTime(new Date(107,9,7));
        int currentDay = b.get(Calendar.DAY_OF_WEEK);
        int leftDays = Calendar.FRIDAY - currentDay;
        b.add(Calendar.DATE, leftDays); 
        Date time = b.getTime();
        
        //Add timesheetRow
        ArrayList<TimesheetRow> rows = new ArrayList<TimesheetRow>();
        BigDecimal[] hours = new BigDecimal[7];
        for(int i = 0; i < 7; i++) {
            hours[i] = new BigDecimal(i);
        }
        rows.add(new TimesheetRow(55,"abc", hours, "nothing"));
        
        //Adds Timesheet to database
        Timesheet oldTimesheet = new Timesheet(testEmployee, time, rows );
        timesheets.add(oldTimesheet);
    }

    @Override
    public List<Timesheet> getTimesheets() {
        return timesheets;
    }

    @Override
    public List<Timesheet> getTimesheets(Employee e) {
        List<Timesheet> employeeTimesheets = new ArrayList<Timesheet>();

        for (Timesheet t : timesheets) {
            if (t.getEmployee().getUserName().equals(e.getUserName())) {
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
