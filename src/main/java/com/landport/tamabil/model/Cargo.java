package com.landport.tamabil.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Table(name = "cargo_tbl")
public class Cargo extends AbstractModel<Long>{

    @Column(name = "importid")
    private String importid;

    @Column(name = "serialno")
    private String serialno;

    @Column(name = "lcno")
    private String lcno;

    @Column(name = "lcdate")
    private String lcdate;

    @Column(name = "lcbankbranch")
    private String lcbankbranch;

    @Column(name = "vehicleid")
    private String vehicleid;

    @Column(name = "pilotid")
    private String pilotid;

    @Column(name = "checkoutvalidity")
    private String checkoutvalidity;

    @Column(name = "goodstype")
    private String goodstype;

    @Column(name = "unitofmeasurement")
    private String unitofmeasurement;

    @Column(name = "descriptionofgood")
    private String descriptionofgood;

    @Column(name = "declaredweight")
    private long declaredweight;

    @Column(name = "numberofpackage")
    private String numberofpackage;

    @Column(name = "importerid")
    private String importerid;

    @Column(name = "cnfid")
    private String cnfid;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
}
