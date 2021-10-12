package com.landport.tamabil.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Table(name = "driver_tbl")
public class Driver extends AbstractModel<Long> {

    @Column(name = "drivername")
    private String drivername;

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
