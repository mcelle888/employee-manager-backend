package project.employee.Employee;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.employee.Address.AddressRepository;
import project.employee.service.MappingService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

        @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private MappingService mappingService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll().stream()
                .map(employee -> mappingService.convertToDTO(employee))
                .collect(Collectors.toList());
    }

    @PostMapping()
    public String postmethod() {
        return "Testing";
    }


}
