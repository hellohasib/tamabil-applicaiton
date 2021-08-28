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
@Table(name = "weight_tbl")
public class Weight extends AbstractModel<Long>{

    @Column(name = "importid")
    private String importid;

    @Column(name = "serialno")
    private String serialno;

    @Column(name = "importerid")
    private long importerid;

    @Column(name = "loadweight")
    private long loadweight;

    @Column(name = "unloadweight")
    private long unloadweight;

    @Column(name = "netweight")
    private long netweight;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
}
