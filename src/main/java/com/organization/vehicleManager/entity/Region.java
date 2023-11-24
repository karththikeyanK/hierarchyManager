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
        name = "Region"
)
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "region_name", nullable = false)
    private String regionName;

    @Column(name = "region_code", nullable = false)
    private String regionCode;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

}
