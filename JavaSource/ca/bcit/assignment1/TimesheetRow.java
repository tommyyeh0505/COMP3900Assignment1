package ca.bcit.assignment1;

import java.sql.PreparedStatement;
import java.util.Arrays;

/**
 * TimesheetRow.
 *
 * @author Tommy Yeh (Jen-Hao) A01025451 
 * @version 2017
 */
public class TimesheetRow {

    
    private int timesheetID;
    private int projectID;
    private String workPackage;
    private double[] hours;
    private String notes;
   
    
    
    /**
     * Returns the {bare_field_name} for this TimesheetRow.
     * @return the timesheetID
     */
    public int getTimesheetID() {
        return timesheetID;
    }
    /**
     * Sets the timesheetID for this TimesheetRow
     * @param timesheetID the timesheetID to set
     */
    public void setTimesheetID(int timesheetID) {
        this.timesheetID = timesheetID;
    }
    /**
     * Constructs an objecct of type TimesheetRow.
     * @param timesheetID
     * @param projectID
     * @param workPackage
     * @param hours
     * @param notes
     */
    public TimesheetRow(int timesheetID, int projectID, String workPackage, double[] hours, String notes) {
        super();
        this.timesheetID = timesheetID;
        this.projectID = projectID;
        this.workPackage = workPackage;
        this.hours = hours;
        this.notes = notes;
    }
    /**
     * Returns the {bare_field_name} for this TimesheetRow.
     * @return the projectID
     */
    public int getProjectID() {
        return projectID;
    }
    /**
     * Sets the projectID for this TimesheetRow
     * @param projectID the projectID to set
     */
    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    /**
     * Returns the {bare_field_name} for this TimesheetRow.
     * @return the workPackage
     */
    public String getWorkPackage() {
        return workPackage;
    }
    /**
     * Sets the workPackage for this TimesheetRow
     * @param workPackage the workPackage to set
     */
    public void setWorkPackage(String workPackage) {
        this.workPackage = workPackage;
    }
    /**
     * Returns the {bare_field_name} for this TimesheetRow.
     * @return the hours
     */
    public double[] getHours() {
        return hours;
    }
    /**
     * Sets the hours for this TimesheetRow
     * @param hours the hours to set
     */
    public void setHours(double[] hours) {
        this.hours = hours;
    }
    /**
     * Returns the {bare_field_name} for this TimesheetRow.
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }
    /**
     * Sets the notes for this TimesheetRow
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    @Override
    public boolean equals(Object o) {

        if (o == this) { 
            return true; 
        } 

        if (!(o instanceof TimesheetRow)) { 
            return false; 
        } 

        TimesheetRow c = (TimesheetRow) o; 

        return c.getProjectID() == projectID 
                && c.getWorkPackage().equals(workPackage);
    } 
    
    @Override
    public String toString() {
        return "Timesheet#" + timesheetID + " ROW projectID: " + projectID 
                + " workPackage: " + workPackage 
                + " HOURS= " + Arrays.asList(hours);
    }
    

    
}
