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

    private ArrayList<TimesheetRow> rows;
    private int empNumber;
    private int weekNumber;
    private Date weekEnding;
    /**
     * Constructs an objecct of type Timesheet.
     * @param rows
     * @param empNumber
     * @param weekNumber
     * @param weekEnding
     */
    public Timesheet(ArrayList<TimesheetRow> rows, int empNumber, int weekNumber, Date weekEnding) {
        super();
        this.rows = rows;
        this.empNumber = empNumber;
        this.weekNumber = weekNumber;
        this.weekEnding = weekEnding;
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
    
    
    
}
