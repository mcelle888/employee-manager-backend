package project.employee.Address;

import jakarta.persistence.*;
import project.employee.Employee.Employee;
import project.employee.Postcode.PostCode;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postcode_id", nullable = false)
    private PostCode postcode;

    @OneToOne(mappedBy = "address")
    private Employee employee;

}
