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

@Service
public class MappingService {

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        if (employee.getAddress() != null) {
            AddressDTO addressDTO = modelMapper.map(employee.getAddress(), AddressDTO.class);
            addressDTO.setState(modelMapper.map(employee.getAddress().getState(), StateDTO.class)); // Map state
            employeeDTO.setAddress(addressDTO);
        }
        return employeeDTO;
    }

    public Employee convertToEntity(CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = modelMapper.map(createEmployeeDTO, Employee.class);
        if (createEmployeeDTO.getAddress() != null) {
            Address address = modelMapper.map(createEmployeeDTO.getAddress(), Address.class);
            State state = new State();
            state.setId(createEmployeeDTO.getAddress().getStateId());
            address.setState(state);
            employee.setAddress(address);
        }
        return employee;
    }

    public Address convertToEntity(AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        if (addressDTO.getStateId() != null) {
            State state = new State();
            state.setId(addressDTO.getStateId());
            address.setState(state);
        }
        return address;
    }

    public State convertToEntity(StateDTO stateDTO) {
        return modelMapper.map(stateDTO, State.class);
    }

    public void updateEntityFromDTO(UpdateEmployeeDTO updateEmployeeDTO, Employee employee) {
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

    private void updateAddressFromDTO(AddressDTO addressDTO, Address address) {
        modelMapper.map(addressDTO, address);
        if (addressDTO.getStateId() != null) {
            State state = address.getState();
            if (state == null) {
                state = new State();
                address.setState(state);
            }
            state.setId(addressDTO.getStateId());
        }
    }
    
    public StateDTO convertToDTO(State state) {
        return modelMapper.map(state, StateDTO.class);
    }
}

