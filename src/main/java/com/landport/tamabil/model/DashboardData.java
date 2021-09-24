package com.landport.tamabil.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DashboardData {
    private Long totalCargo;
    private Long totalVehicle;
    private Long totalImporter;
    private Long totalPilot;

}
