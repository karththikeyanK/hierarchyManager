package com.organization.vehicleManager.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "Company"
)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "register_number", nullable = false)
    private String regNo;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;


}
