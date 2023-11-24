package com.organization.vehicleManager.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "Customer"
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_address", nullable = false)
    private String address;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Customer_Company",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private Set<Company> company;



}
