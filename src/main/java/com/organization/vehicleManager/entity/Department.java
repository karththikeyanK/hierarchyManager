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
        name = "Department"
)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dept_name", nullable = false)
    private String name;

    @Column(name = "dept_code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;


}
