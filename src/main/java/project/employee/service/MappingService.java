package project.employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.employee.Address.Address;
import project.employee.Address.AddressDTO;
import project.employee.Employee.CreateEmployeeDTO;
import project.employee.Employee.Employee;
import project.employee.Employee.EmployeeDTO;
import project.employee.Employee.UpdateEmployeeDTO;
import project.employee.State.State;
import project.employee.State.StateDTO;
import project.employee.State.StateRepository;
import project.employee.exceptions.NotFoundException;

@Service
public class MappingService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StateRepository stateRepo;

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        if (employee.getAddress() != null) {
            AddressDTO addressDTO = modelMapper.map(employee.getAddress(), AddressDTO.class);
            addressDTO.setStateId(employee.getAddress().getState().getId());  
            employeeDTO.setAddress(addressDTO);
        }
        return employeeDTO;
    }

    public Employee convertToEntity(CreateEmployeeDTO createEmployeeDTO) throws NotFoundException {
        Employee employee = modelMapper.map(createEmployeeDTO, Employee.class);
        if (createEmployeeDTO.getAddress() != null) {
            Address address = convertToEntity(createEmployeeDTO.getAddress());
            employee.setAddress(address);
        }
        return employee;
    }

    public Address convertToEntity(AddressDTO addressDTO) throws NotFoundException {
        Address address = modelMapper.map(addressDTO, Address.class);
        if (addressDTO.getStateId() != null) {
            State state = stateRepo.findById(addressDTO.getStateId())
                    .orElseThrow(() -> new NotFoundException(State.class, addressDTO.getStateId()));
            address.setState(state);
        }
        return address;
    }

    public State convertToEntity(StateDTO stateDTO) {
        return modelMapper.map(stateDTO, State.class);
    }

    public void updateEntityFromDTO(UpdateEmployeeDTO updateEmployeeDTO, Employee employee) throws NotFoundException {
        modelMapper.map(updateEmployeeDTO, employee);
        if (updateEmployeeDTO.getAddress() != null) {
            Address address = employee.getAddress();
            if (address == null) {
                address = new Address();
                employee.setAddress(address);
            }
            updateAddressFromDTO(updateEmployeeDTO.getAddress(), address);
        }
    }

    private void updateAddressFromDTO(AddressDTO addressDTO, Address address) throws NotFoundException {
        modelMapper.map(addressDTO, address);
        if (addressDTO.getStateId() != null) {
            State state = stateRepo.findById(addressDTO.getStateId())
                    .orElseThrow(() -> new NotFoundException(State.class, addressDTO.getStateId()));
            address.setState(state);
        }
    }
}
