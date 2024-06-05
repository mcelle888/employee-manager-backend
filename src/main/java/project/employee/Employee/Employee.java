package project.employee.Employee;

import java.util.Date;

import jakarta.persistence.*;
import project.employee.Address.Address;

@Entity
@Table(name = "employee_list")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String f_name;

    @Column(nullable = false)
    private String l_name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long phone;

    @Column(nullable = false)
    private Boolean fullTime;

    @Column(nullable = false)
    private Boolean permanent;

    @Column(nullable = false)
    private Date dateStarted;

    @Column(nullable = true)
    private Date dateEnded;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
