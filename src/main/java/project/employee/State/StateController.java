package project.employee.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.employee.service.MappingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private MappingService mappingService;

    @GetMapping
    public List<StateDTO> getAllStates() {
        List<State> states = stateRepo.findAll();
        return states.stream()
                .map(mappingService::convertToDTO)
                .collect(Collectors.toList());
    }
}
