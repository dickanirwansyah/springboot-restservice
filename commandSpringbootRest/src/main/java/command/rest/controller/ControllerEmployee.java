package command.rest.controller;

import command.model.Employee;
import command.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ControllerEmployee {

    private EmployeeService employeeService;

    private static Logger LOGGER = LoggerFactory.getLogger(ControllerEmployee.class);

    @Inject
    public ControllerEmployee(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> findAllEmployees(){
        List<Employee> listemployee = employeeService.findAllEmployee();
        LOGGER.debug("menampilkan data karyawan");
        if(listemployee.isEmpty()){
            LOGGER.debug("menampilkan data karyawan");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employee>>(listemployee, HttpStatus.OK);
    }

    @PostMapping(value = "/insertEmployee")
    public ResponseEntity<Employee> insertEmployee(@RequestBody Employee employee, UriComponentsBuilder builder){
        LOGGER.debug("insert data karyawan");
        if(employeeService.ifDataIsExist(employee)){
            LOGGER.debug("maaf nama karyawan sudah ada : "+employee.getIdemployee());
            return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
        }
        employeeService.insertEmployee(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertEmployee/{idemployee}")
        .buildAndExpand(employee.getIdemployee()).toUri());
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateEmployee/{idemployee}")
    public ResponseEntity<Employee>updateEmployee(@PathVariable String idemployee,
                                                  @RequestBody Employee employee){
        LOGGER.debug("update karyawan : "+employee.getIdemployee());
        Employee emp = employeeService.findOneEmployeeById(idemployee);
        if(emp == null){
            LOGGER.debug("data karyawan tidak ada");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        emp.setName(employee.getName());
        emp.setPosition(employee.getPosition());
        employeeService.updateEmployee(emp);
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteEmployee/{idemployee}")
    public ResponseEntity<Employee>deleteEmployee(@PathVariable String idemployee){
        LOGGER.debug("hapus data karyawan");
        Employee employee = employeeService.findOneEmployeeById(idemployee);
        if(employee == null){
            LOGGER.debug("data tidak ada yang ingin dihapus");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        employeeService.deleteEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
}
