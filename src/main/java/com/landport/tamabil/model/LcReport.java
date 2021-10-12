package com.landport.tamabil.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class LcReport extends AbstractModel<Long>{
    private String fromdate;
    private String todate;
    private String lcno;
}
