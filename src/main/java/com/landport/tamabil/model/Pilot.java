package com.landport.tamabil.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Table(name = "pilot_tbl")
public class Pilot extends AbstractModel<Long> {

    @Column(name = "pilotname")
    private String pilotname;

    @Column(name = "fathername")
    private String fathername;

    @Column(name = "address")
    private String address;

    @Column(name = "drivinglicense")
    private String drivinglicense;

    @Column(name = "dob")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private String dob;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "country")
    private String country;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date updatedAt;
}
