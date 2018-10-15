import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.timesheet.Timesheet;
import ca.bcit.infosys.timesheet.TimesheetCollection;


/**
 * TimesheetManager.
 *
 * @author Tommy Yeh (Jen-Hao) A01025451 
 * @version 2017
 */
@Named("user")
@ApplicationScoped
public class TimesheetManager implements Serializable, TimesheetCollection {

    private Employee testEmployee;
    
    private ArrayList<Employee> employees;
    private ArrayList<Timesheet> timesheets;
        private Timesheet currentTimesheet;
        
        /**
         * Returns the {bare_field_name} for this TimesheetManager.
         * @return the currentTimesheet
         */
        public Timesheet getCurrentTimesheet() {
            return currentTimesheet;
        }

        /**
         * Sets the currentTimesheet for this TimesheetManager
         * @param currentTimesheet the currentTimesheet to set
         */
        public void setCurrentTimesheet(Timesheet currentTimesheet) {
            this.currentTimesheet = currentTimesheet;
        }

        /**
         * Sets the timesheets for this TimesheetManager
         * @param timesheets the timesheets to set
         */
        public void setTimesheets(ArrayList<Timesheet> timesheets) {
            this.timesheets = timesheets;
        }

        public TimesheetManager () {
            timesheets = new ArrayList<Timesheet>();
           testEmployee = new Employee();
           testEmployee.setName("Tommy");
        }

        /**
         * @see ca.bcit.infosys.timesheet.TimesheetCollection#getTimesheets()
         * @return
         */
        @Override
        public List<Timesheet> getTimesheets() {
            return timesheets;
        }

        /**
         * @see ca.bcit.infosys.timesheet.TimesheetCollection#getTimesheets(ca.bcit.infosys.employee.Employee)
         * @param e
         * @return
         */
        @Override
        public List<Timesheet> getTimesheets(Employee e) {
            ArrayList<Timesheet> temp = new ArrayList<Timesheet>();
            for(Timesheet t: timesheets) {
                if(t.getEmployee().equals(e)) {
                    temp.add(t);
                }
            }
            return temp;
        }

        /**
         * @see ca.bcit.infosys.timesheet.TimesheetCollection#getCurrentTimesheet(ca.bcit.infosys.employee.Employee)
         * @param e
         * @return
         */
        @Override
        public Timesheet getCurrentTimesheet(Employee e) {
            Timesheet temp = null;

            Calendar c = new GregorianCalendar();
            int currentDay = c.get(Calendar.DAY_OF_WEEK);
            int leftDays = Calendar.FRIDAY - currentDay;
            c.add(Calendar.DATE, leftDays);
            Date endWeek = c.getTime();
            
            for(Timesheet t: timesheets) {
                if(t.getEndWeek().equals(endWeek)) {
                    temp = t;
                }
            }
            
            return temp;
        }

        /**
         * @see ca.bcit.infosys.timesheet.TimesheetCollection#addTimesheet()
         * @return
         */
        @Override
        public String addTimesheet() {
            Timesheet t1 = new Timesheet();
            t1.setEmployee(testEmployee);
            currentTimesheet = t1;
            timesheets.add(t1);
            
            return "createTimesheet";
        }


}
