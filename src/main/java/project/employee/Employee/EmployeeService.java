package project.employee.Employee;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import project.employee.Address.Address;
import project.employee.Address.AddressRepository;
import project.employee.State.State;
import project.employee.State.StateRepository;
import project.employee.exceptions.NotFoundException;
import project.employee.service.MappingService;

@Service
@Transactional
public class EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private MappingService mappingService;

    public EmployeeDTO createEmployee(CreateEmployeeDTO createEmployeeDTO) throws NotFoundException {
        logger.info("Creating employee '{} {}'", createEmployeeDTO.getF_name(), createEmployeeDTO.getL_name());

        State state = stateRepo.findById(createEmployeeDTO.getAddress().getStateId())
                .orElseThrow(() -> new NotFoundException(State.class, createEmployeeDTO.getAddress().getStateId()));

        Address address = mappingService.convertToEntity(createEmployeeDTO.getAddress());
        address.setState(state);
        address = addressRepo.save(address);

        Employee employee = mappingService.convertToEntity(createEmployeeDTO);
        employee.setAddress(address);
        employee = employeeRepo.save(employee);

        return mappingService.convertToDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Fetching all employees");
        return employeeRepo.findAll().stream()
                .map(mappingService::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) throws NotFoundException {
        logger.info("Fetching employee by ID: {}", id);
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isEmpty()) {
            logger.error("Employee {} not found", id);
            throw new NotFoundException(Employee.class, id);
        }
        return mappingService.convertToDTO(employee.get());
    }

    public EmployeeDTO updateEmployee(Long id, UpdateEmployeeDTO updateEmployeeDTO) throws NotFoundException {
        logger.info("Updating employee ID: {}", id);
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isEmpty()) {
            logger.error("Employee {} not found", id);
            throw new NotFoundException(Employee.class, id);
        }
        Employee employee = optionalEmployee.get();
        mappingService.updateEntityFromDTO(updateEmployeeDTO, employee);

        employee = employeeRepo.save(employee);
        return mappingService.convertToDTO(employee);
    }

    public void deleteEmployee(Long id) throws NotFoundException {
        logger.info("Deleting employee ID: {}", id);
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isEmpty()) {
            logger.error("Employee {} not found", id);
            throw new NotFoundException(Employee.class, id);
        }
        employeeRepo.delete(employee.get());
        logger.info("Employee {} deleted", id);
    }
    
}
