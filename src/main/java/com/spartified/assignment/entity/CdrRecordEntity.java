package com.spartified.assignment.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "cdr_records")
public class CdrRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "msisdn", nullable = false)
    private String msisdn;

    // store MB for convenience
    @Column(name = "data_volume_mb", nullable = false)
    private Double dataVolumeMb;

    @Column(name = "start_utc", nullable = false)
    private Instant startUtc;

    @Column(name = "end_utc", nullable = false)
    private Instant endUtc;

    @Column(name = "mcc", nullable = false, length = 10)
    private String mcc;

    @Column(name = "mnc", nullable = false, length = 10)
    private String mnc;

    // constructors, getters, setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Double getDataVolumeMb() {
        return dataVolumeMb;
    }

    public void setDataVolumeMb(Double dataVolumeMb) {
        this.dataVolumeMb = dataVolumeMb;
    }

    public Instant getStartUtc() {
        return startUtc;
    }

    public void setStartUtc(Instant startUtc) {
        this.startUtc = startUtc;
    }

    public Instant getEndUtc() {
        return endUtc;
    }

    public void setEndUtc(Instant endUtc) {
        this.endUtc = endUtc;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }
}

