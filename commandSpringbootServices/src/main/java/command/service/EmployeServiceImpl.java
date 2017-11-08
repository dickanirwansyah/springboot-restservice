package command.service;

import command.model.Employee;
import command.repository.EmployeeRepository;
import command.service.exception.HandlingAlreadyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Validated
@Transactional
public class EmployeServiceImpl implements EmployeeService{

    private static Logger LOGGER =
            LoggerFactory.getLogger(EmployeServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    private EmployeeRepository employeeRepository;

    @Inject
    public EmployeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        /**
         * insert employee
         * */
        LOGGER.debug("proses insert employee : "+employee.getIdemployee());
        Employee jikaDataSama = employeeRepository.findOne(employee.getIdemployee());
        if(jikaDataSama != null){
            throw new HandlingAlreadyException
                    (String.format("data employee sudah ada =%s", employee.getIdemployee()));
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee){
        LOGGER.debug("update karyawan");
        if(!entityManager.contains(employee))
        employee = entityManager.merge(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        LOGGER.debug("hapus data karyawan");
        employeeRepository.delete(employee);
    }

    @Override
    public Employee findOneEmployeeById(String idemployee) {
        LOGGER.debug("menampilkan karyawan berdasarkan kode");
        return employeeRepository.findOne(idemployee);
    }

    @Override
    public List<Employee> findAllEmployee() {
        LOGGER.debug("proses menampilkan data karyawan {}");
        return employeeRepository.findAll();
    }

    @Override
    public boolean ifDataIsExist(Employee employee) {
        return findOneEmployeeById(employee.getIdemployee())!=null;
    }
}
