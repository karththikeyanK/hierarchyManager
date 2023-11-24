package com.organization.vehicleManager.entity;

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
        name = "Vender"
)
public class Vendor {
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Vender_Companies",
            joinColumns = @JoinColumn(name = "vender_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id")
    )
    private Set<Company> company;

}
