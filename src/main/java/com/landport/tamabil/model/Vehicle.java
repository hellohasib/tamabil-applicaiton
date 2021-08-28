package com.landport.tamabil.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Table(name = "vehicle_tbl")
public class Vehicle extends AbstractModel<Long> {

    @Column(name = "vehicleregno")
    private String vehicleregno;

    @Column(name = "chassisno")
    private String chassisno;

    @Column(name = "engineno")
    private String engineno;

    @Column(name = "vehicleregcertificateno")
    private String vehicleregcertificateno;

    @Column(name = "ownerofvehicle")
    private String ownerofvehicle;

    @Column(name = "owneraddress")
    private String owneraddress;

    @Column(name = "ownercellphone")
    private String ownercellphone;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
}
