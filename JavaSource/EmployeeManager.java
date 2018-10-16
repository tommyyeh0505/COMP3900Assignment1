import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;
import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.employee.EmployeeList;

@Named("employee")
@ApplicationScoped
public class EmployeeManager implements EmployeeList {

    private Employee currentEmployee;
    private Employee administrator;
    private List<Employee> employees = new ArrayList<Employee>();
    private Map<String, String> loginCombos = new HashMap<String, String>() {{
        put("Andy", "password");
    }};

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

    @Override
    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    @Override
    public Employee getAdministrator() {
        return administrator;
    }

    @Override
    public boolean verifyUser(Credentials credential) {
        String username = currentEmployee.getUserName();
        String password = loginCombos.get(username);
        
        

        if (credential.getUserName().toLowerCase().equals(username)
                && credential.getPassword().toLowerCase().equals(password)) {
            return true;
        }

        return false;
    }

    @Override
    public String logout(Employee employee) {
        return "logout";
    }

    @Override
    public void deleteEmployee(Employee userToDelete) {
        employees.remove(userToDelete);
    }

    @Override
    public void addEmployee(Employee newEmployee) {
        employees.add(newEmployee);
    }

}
