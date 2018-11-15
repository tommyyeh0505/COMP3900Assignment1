package ca.bcit.assignment1;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Timesheet.
 *
 * @author Tommy Yeh (Jen-Hao) A01025451 
 * @version 2017
 */
public class Timesheet {

    private int timesheetID;
    private ArrayList<TimesheetRow> rows;
    private int empNumber;
    private int weekNumber;
    private Date weekEnding;
   
    
    
    
    /**
     * Constructs an objecct of type Timesheet.
     * @param timesheetID
     * @param rows
     * @param empNumber
     * @param weekNumber
     * @param weekEnding
     */
    public Timesheet(int timesheetID, ArrayList<TimesheetRow> rows, int empNumber, int weekNumber, Date weekEnding) {
        super();
        this.timesheetID = timesheetID;
        this.rows = rows;
        this.empNumber = empNumber;
        this.weekNumber = weekNumber;
        this.weekEnding = weekEnding;
    }
    /**
     * Returns the {bare_field_name} for this Timesheet.
     * @return the timesheetID
     */
    public int getTimesheetID() {
        return timesheetID;
    }
    /**
     * Sets the timesheetID for this Timesheet
     * @param timesheetID the timesheetID to set
     */
    public void setTimesheetID(int timesheetID) {
        this.timesheetID = timesheetID;
    }
    /**
     * Returns the {bare_field_name} for this Timesheet.
     * @return the rows
     */
    public ArrayList<TimesheetRow> getRows() {
        return rows;
    }
    /**
     * Sets the rows for this Timesheet
     * @param rows the rows to set
     */
    public void setRows(ArrayList<TimesheetRow> rows) {
        this.rows = rows;
    }
    /**
     * Returns the {bare_field_name} for this Timesheet.
     * @return the empNumber
     */
    public int getEmpNumber() {
        return empNumber;
    }
    /**
     * Sets the empNumber for this Timesheet
     * @param empNumber the empNumber to set
     */
    public void setEmpNumber(int empNumber) {
        this.empNumber = empNumber;
    }
    /**
     * Returns the {bare_field_name} for this Timesheet.
     * @return the weekNumber
     */
    public int getWeekNumber() {
        return weekNumber;
    }
    /**
     * Sets the weekNumber for this Timesheet
     * @param weekNumber the weekNumber to set
     */
    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }
    /**
     * Returns the {bare_field_name} for this Timesheet.
     * @return the weekEnding
     */
    public Date getWeekEnding() {
        return weekEnding;
    }

    /**
     * Sets the weekEnding for this Timesheet
     * @param weekEnding the weekEnding to set
     */
    public void setWeekEnding(Date weekEnding) {
        this.weekEnding = weekEnding;
    }

    public void addNewRow(int projectID, String workPackage, String notes) {
        final int daysWeek = 7;
        double[] emptyHours = new double[daysWeek];
        rows.add(new TimesheetRow(timesheetID, projectID, workPackage, emptyHours, notes));

    }

    public void addRow(TimesheetRow row) {
        rows.add(row);
    }

    public void deleteLastRow() {
        if (rows.size() > 0) {
            rows.remove(rows.size() - 1);
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) { 
            return true; 
        } 

        if (!(o instanceof Timesheet)) { 
            return false; 
        } 

        Timesheet c = (Timesheet) o; 

        return c.getEmpNumber() == empNumber && c.getWeekEnding() == weekEnding;
    } 
    
    @Override
    public String toString() {
        return "Timesheet#" + timesheetID + " Week: " + weekEnding + " EmpNumber: " + empNumber + " Size: " + rows.size(); 
    }


}


