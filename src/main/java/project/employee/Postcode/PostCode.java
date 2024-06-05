package project.employee.Postcode;

import jakarta.persistence.*;
import java.util.List;
import project.employee.Address.Address;

@Entity
@Table(name = "postcodes")
public class PostCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String state;

    @OneToMany(mappedBy = "postcode")
    private List<Address> addresses;

}
