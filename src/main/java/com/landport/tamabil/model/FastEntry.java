package com.landport.tamabil.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "fast_entry_tbl")
public class FastEntry extends AbstractModel<Long> {
    @Column(name = "truck_serial_no")
    Long truckserialno;

    @Column(name = "driver_name")
    String drivername;

    @Column(name = "vehiclereg_no")
    String vehicleregno;

    @Column(name = "exporter_name")
    String exportername;

    @Column(name = "importer_name")
    String importername;

    @Column(name = "importer_contact")
    String importercontact;

    @Column(name = "goods_name")
    String goodsname;

    @Column(name = "gross_weight")
    Long grossweight;

    @Column(name = "unload_weight")
    Long unloadweight;

    @Column(name = "net_weight")
    Long netweight;

    @Column(name = "number_of_package")
    String numberofpackage;

    @Column(name = "lc_no")
    String lcno;

    @Column(name = "cnf_agent")
    String cnfagent;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
