package ca.bcit.assignment1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;
import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.employee.EmployeeList;

/**
 * 
 * EmployeeDatabase.
 *
 * @author Andy A01027848
 * @version 2018
 */
@Named
@ApplicationScoped
public class EmployeeDatabase implements Serializable, EmployeeList {

    /**
     * EmployeeDatabase administrator.
     */
    private Employee administrator;
    
    /**
     * List of employees in the database.
     */
    private List<Employee> employees;
    
    /**
     * Map of username password pairs.
     */
    private Map<String, String> loginCombos;

    /**
     * Constructs an object of type EmployeeDatabase.
     * Adds dummy data.
     */
    public EmployeeDatabase() {
        int i = 0;
        final int[] id = {1, 2, 3, 4};
        administrator = new Employee("Andy", id[i++], "Andy");
        employees = new ArrayList<Employee>();
        loginCombos = new HashMap<String, String>();

        addEmployee(new Employee("Joe", id[i++], "Joe"), "pass");
        addEmployee(new Employee("Tommy", id[i++], "Tommy"), "pass");
        addEmployee(new Employee("YAY", id[i++], "YAY"), "pass");
        addEmployee(administrator, "pass");
    }
    
    /**
     * Gets password from an employee object.
     * @param e employee object
     * @return password related to the employee object
     */
    public String getPassword(Employee e) {
        return loginCombos.get(e.getUserName());
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployee(String name) {
        for (Employee e : employees) {
            if (e.getName().equals(name)) {
                return e;
            }
        }

        return null;
    }

    @Override
    public Map<String, String> getLoginCombos() {
        return loginCombos;
    }

    /**
     * Puts a new login combination.
     * @param username username
     * @param password password
     */
    public void setLoginCombos(String username, String password) {
        loginCombos.put(username, password);
    }

    @Override
    public Employee getCurrentEmployee() {
        return null;
    }

    @Override
    public Employee getAdministrator() {
        return administrator;
    }

    @Override
    public boolean verifyUser(Credentials c) {
        String password = loginCombos.get(c.getUserName());

        if (password == null) {
            return false;
        }

        if (password.equals(c.getPassword())) {
            return true;
        }

        return false;
    }

    @Override
    public String logout(Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployee(Employee userToDelete) {
        employees.remove(userToDelete);
        loginCombos.remove(userToDelete.getUserName());
    }

    @Override
    public void addEmployee(Employee newEmployee, String password) {
        employees.add(newEmployee);
        loginCombos.put(newEmployee.getUserName(), password);
    }

}
