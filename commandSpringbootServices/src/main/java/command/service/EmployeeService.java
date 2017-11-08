package command.service;

import command.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee insertEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee findOneEmployeeById(String idemployee);

    List<Employee> findAllEmployee();

    boolean ifDataIsExist(Employee employee);

}
