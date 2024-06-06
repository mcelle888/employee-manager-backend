package project.employee.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import project.employee.Employee.Employee;
import project.employee.Employee.UpdateEmployeeDTO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(UpdateEmployeeDTO.class, Employee.class)
                .addMappings(mapper -> mapper.skip(Employee::setAddress));

        return modelMapper;
    }
}
