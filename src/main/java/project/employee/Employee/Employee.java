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
    private Date dob;

    @Column(nullable = false)
    private Boolean fullTime;

    @Column(nullable = false)
    private Boolean permanent;

    @Column(nullable = false)
    private Date dateStarted;

    @Column(nullable = true)
    private Date dateEnded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Boolean getFullTime() {
        return fullTime;
    }

    public void setFullTime(Boolean fullTime) {
        this.fullTime = fullTime;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Override
    public String toString() {
        return "Employee [id=" + id + ", f_name=" + f_name + ", l_name=" + l_name + ", email=" + email + ", phone="
                + phone + ", dob=" + dob + ", fullTime=" + fullTime + ", permanent=" + permanent + ", dateStarted="
                + dateStarted + ", dateEnded=" + dateEnded + ", address=" + address + "]";
    }

   

}
