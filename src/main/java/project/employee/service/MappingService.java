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

@Service
public class MappingService {

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        AddressDTO addressDTO = modelMapper.map(employee.getAddress(), AddressDTO.class);
        employeeDTO.setAddress(addressDTO);
        return employeeDTO;
    }

    public Employee convertToEntity(CreateEmployeeDTO createEmployeeDTO) {
        return modelMapper.map(createEmployeeDTO, Employee.class);
    }

    public Employee convertToEntity(UpdateEmployeeDTO updateEmployeeDTO) {
        return modelMapper.map(updateEmployeeDTO, Employee.class);
    }

    public Address convertToEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }
}
