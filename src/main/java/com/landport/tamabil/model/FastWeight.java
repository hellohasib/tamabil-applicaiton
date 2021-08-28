package com.landport.tamabil.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Table(name = "fastweight_tbl")
public class FastWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "importid")
    private String importid;

    @Column(name = "serialno")
    private String serialno;

    @Column(name = "descriptionofgood")
    private String descriptionofgood;

    @Column(name = "declaredweight")
    private long declaredweight;

    @Column(name = "importerid")
    private long importerid;

    @Column(name = "numberofpackage")
    private String numberofpackage;

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
    private String createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
}
