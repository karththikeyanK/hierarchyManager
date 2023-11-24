package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionResponse {
    private Integer id;
    private String regionName;
    private String regionCode;
    private Integer companyId;
}
