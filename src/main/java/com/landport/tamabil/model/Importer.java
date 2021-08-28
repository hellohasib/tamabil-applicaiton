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
@Table(name = "importer_tbl")
public class Importer extends AbstractModel<Long> {

    @Column(name = "importername")
    private String importername;

    @Column(name = "address")
    private String address;

    @Column(name = "proprietorname")
    private String proprietorname;

    @Column(name = "tradelicenseno")
    private String tradelicenseno;

    @Column(name = "cellphone")
    private long cellphone;

    @Column(name = "email")
    private String email;

    @Column(name = "nidno")
    private String nidno;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
}
