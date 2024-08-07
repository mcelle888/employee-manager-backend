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
    private static final String DEFAULT_IMAGE_URL = "https://t4.ftcdn.net/jpg/04/10/43/77/360_F_410437733_hdq4Q3QOH9uwh0mcqAhRFzOKfrCR24Ta.jpg";

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

        State state = stateRepo.findById(createEmployeeDTO.getAddress().getState().getId())
                .orElseThrow(
                        () -> new NotFoundException(State.class, createEmployeeDTO.getAddress().getState().getId()));

        Address address = mappingService.convertToEntity(createEmployeeDTO.getAddress());
        address.setState(state);
        address = addressRepo.save(address);

        Employee employee = mappingService.convertToEntity(createEmployeeDTO);
        employee.setAddress(address);

        if (employee.getImageLink() == null || employee.getImageLink().isEmpty()) {
            employee.setImageLink(DEFAULT_IMAGE_URL);
        }

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
        Address address = employee.getAddress();
        if (address != null && updateEmployeeDTO.getAddress() != null) {
            State newState = stateRepo.findById(updateEmployeeDTO.getAddress().getState().getId())
                    .orElseThrow(() -> new NotFoundException(State.class,
                            updateEmployeeDTO.getAddress().getState().getId()));
            address.setState(newState);
            address = mappingService.updateAddressFromDTO(updateEmployeeDTO.getAddress(), address);
            addressRepo.save(address);
        }

        mappingService.updateEntityFromDTO(updateEmployeeDTO, employee);

        if (employee.getImageLink() == null || employee.getImageLink().isEmpty()) {
            employee.setImageLink(DEFAULT_IMAGE_URL);
        }

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
