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

@Named
@ApplicationScoped
public class EmployeeDatabase implements Serializable, EmployeeList {

    private Employee administrator;
    private List<Employee> employees;
    private Map<String, String> loginCombos;

    public EmployeeDatabase() {
        administrator = new Employee("Andy", 1, "Andy");
        employees = new ArrayList<Employee>();
        loginCombos = new HashMap<String, String>();

        addEmployee(new Employee("Joe", 2, "Joe"), "pass");
        addEmployee(new Employee("Tommy", 3, "Tommy"), "pass");
        addEmployee(new Employee("YAY", 4, "YAY"), "pass");
        addEmployee(administrator, "pass");
    }
    
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
    }

    @Override
    public void addEmployee(Employee newEmployee, String password) {
        employees.add(newEmployee);
        loginCombos.put(newEmployee.getUserName(), password);
    }

}
