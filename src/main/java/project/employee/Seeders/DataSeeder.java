package project.employee.Seeders;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import project.employee.Address.Address;
import project.employee.Address.AddressRepository;
import project.employee.Employee.Employee;
import project.employee.Employee.EmployeeRepository;
import project.employee.State.State;
import project.employee.State.StateRepository;

@Component
@Profile("seed")
public class DataSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);
    private static final String DEFAULT_IMAGE_URL = "https://t4.ftcdn.net/jpg/04/10/43/77/360_F_410437733_hdq4Q3QOH9uwh0mcqAhRFzOKfrCR24Ta.jpg";

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private StateRepository stateRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        seedData();
    }

    public void seedData() throws Exception {
        logger.info("Seeding states...");

        // Seed States
        State state1 = new State();
        state1.setState("VIC");
        stateRepo.save(state1);

        State state2 = new State();
        state2.setState("NSW");
        stateRepo.save(state2);

        State state3 = new State();
        state3.setState("QLD");
        stateRepo.save(state3);

        State state4 = new State();
        state4.setState("SA");
        stateRepo.save(state4);

        State state5 = new State();
        state5.setState("WA");
        stateRepo.save(state5);

        State state6 = new State();
        state6.setState("TAS");
        stateRepo.save(state6);

        State state7 = new State();
        state7.setState("NT");
        stateRepo.save(state7);

        State state8 = new State();
        state8.setState("ACT");
        stateRepo.save(state8);

        // Seed Addresses
        logger.info("Seeding Addresses...");
        Address address1 = new Address();
        address1.setNumber(10L);
        address1.setAddress("Apple St");
        address1.setPostcode(3000L);
        address1.setState(state1);
        addressRepo.save(address1);

        Address address2 = new Address();
        address2.setNumber(22L);
        address2.setAddress("Banana St");
        address2.setPostcode(2000L);
        address2.setState(state2);
        addressRepo.save(address2);

        Address address3 = new Address();
        address3.setNumber(43L);
        address3.setAddress("Orange St");
        address3.setPostcode(4000L);
        address3.setState(state3);
        addressRepo.save(address3);

        Address address4 = new Address();
        address4.setNumber(56L);
        address4.setAddress("Peach St");
        address4.setPostcode(5000L);
        address4.setState(state4);
        addressRepo.save(address4);

        Address address5 = new Address();
        address5.setNumber(67L);
        address5.setAddress("Pear St");
        address5.setPostcode(6000L);
        address5.setState(state5);
        addressRepo.save(address5);

        Address address6 = new Address();
        address6.setNumber(78L);
        address6.setAddress("Plum St");
        address6.setPostcode(7000L);
        address6.setState(state6);
        addressRepo.save(address6);

        Address address7 = new Address();
        address7.setNumber(89L);
        address7.setAddress("Cherry St");
        address7.setPostcode(8000L);
        address7.setState(state7);
        addressRepo.save(address7);

        Address address8 = new Address();
        address8.setNumber(90L);
        address8.setAddress("Grape St");
        address8.setPostcode(9000L);
        address8.setState(state8);
        addressRepo.save(address8);

        Address address9 = new Address();
        address9.setNumber(91L);
        address9.setAddress("Mango St");
        address9.setPostcode(9100L);
        address9.setState(state1);
        addressRepo.save(address9);

        logger.info("Seeding employees...");

        // Seed Employees
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Employee employee1 = new Employee();
        employee1.setF_name("Jake");
        employee1.setL_name("Peralta");
        employee1.setEmail("jp@example.com");
        employee1.setPhone(401222123L);
        employee1.setDob(dateFormat.parse("01-06-1981"));
        employee1.setFullTime(true);
        employee1.setPermanent(true);
        employee1.setDateStarted(new Date());
        employee1.setAddress(address1);
        employee1.setImageLink(DEFAULT_IMAGE_URL);
        employeeRepo.save(employee1);

        Employee employee2 = new Employee();
        employee2.setF_name("Amy");
        employee2.setL_name("Santiago");
        employee2.setEmail("as@example.com");
        employee2.setPhone(401333123L);
        employee2.setDob(dateFormat.parse("15-09-1983"));
        employee2.setFullTime(true);
        employee2.setPermanent(true);
        employee2.setDateStarted(new Date());
        employee2.setAddress(address2);
        employee2.setImageLink(DEFAULT_IMAGE_URL);
        employeeRepo.save(employee2);

        Employee employee3 = new Employee();
        employee3.setF_name("Rosa");
        employee3.setL_name("Diaz");
        employee3.setEmail("rd@example.com");
        employee3.setPhone(401444123L);
        employee3.setDob(dateFormat.parse("20-04-1982"));
        employee3.setFullTime(true);
        employee3.setPermanent(true);
        employee3.setDateStarted(new Date());
        employee3.setAddress(address3);
        employee3.setImageLink(DEFAULT_IMAGE_URL);
        employeeRepo.save(employee3);

        Employee employee4 = new Employee();
        employee4.setF_name("Terry");
        employee4.setL_name("Jeffords");
        employee4.setEmail("tj@example.com");
        employee4.setPhone(401555123L);
        employee4.setDob(dateFormat.parse("30-06-1973"));
        employee4.setFullTime(true);
        employee4.setPermanent(true);
        employee4.setDateStarted(new Date());
        employee4.setAddress(address4);
        employee4.setImageLink(DEFAULT_IMAGE_URL);
        employeeRepo.save(employee4);

        Employee employee5 = new Employee();
        employee5.setF_name("Raymond");
        employee5.setL_name("Holt");
        employee5.setEmail("rh@example.com");
        employee5.setPhone(401666123L);
        employee5.setDob(dateFormat.parse("20-02-1962"));
        employee5.setFullTime(true);
        employee5.setPermanent(true);
        employee5.setDateStarted(new Date());
        employee5.setAddress(address5);
        employee5.setImageLink(DEFAULT_IMAGE_URL);
        employeeRepo.save(employee5);

        Employee employee6 = new Employee();
        employee6.setF_name("Gina");
        employee6.setL_name("Linetti");
        employee6.setEmail("gl@example.com");
        employee6.setPhone(401777123L);
        employee6.setDob(dateFormat.parse("15-10-1985"));
        employee6.setFullTime(true);
        employee6.setPermanent(true);
        employee6.setDateStarted(new Date());
        employee6.setAddress(address6);
        employee6.setImageLink(DEFAULT_IMAGE_URL);
        employeeRepo.save(employee6);

        Employee employee7 = new Employee();
        employee7.setF_name("Charles");
        employee7.setL_name("Boyle");
        employee7.setEmail("cb@example.com");
        employee7.setPhone(401888123L);
        employee7.setDob(dateFormat.parse("11-11-1978"));
        employee7.setFullTime(true);
        employee7.setPermanent(true);
        employee7.setDateStarted(new Date());
        employee7.setAddress(address7);
        employee7.setImageLink(DEFAULT_IMAGE_URL);
        employeeRepo.save(employee7);

        Employee employee8 = new Employee();
        employee8.setF_name("Hitchcock");
        employee8.setL_name("Scully");
        employee8.setEmail("hs@example.com");
        employee8.setPhone(401999123L);
        employee8.setDob(dateFormat.parse("09-09-1960"));
        employee8.setFullTime(true);
        employee8.setPermanent(true);
        employee8.setDateStarted(new Date());
        employee8.setAddress(address8);
        employee8.setImageLink(DEFAULT_IMAGE_URL);
        employeeRepo.save(employee8);

        Employee employee9 = new Employee();
        employee9.setF_name("Kevin");
        employee9.setL_name("Cozner");
        employee9.setEmail("kc@example.com");
        employee9.setPhone(401101123L);
        employee9.setDob(dateFormat.parse("05-05-1964"));
        employee9.setFullTime(true);
        employee9.setPermanent(true);
        employee9.setDateStarted(new Date());
        employee9.setAddress(address9);
        employee9.setImageLink(DEFAULT_IMAGE_URL);
        employeeRepo.save(employee9);

        logger.info("Seeding completed.");
    }
}
