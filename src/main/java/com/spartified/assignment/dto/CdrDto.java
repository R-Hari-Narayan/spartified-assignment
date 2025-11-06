package com.spartified.assignment.dto;

import java.time.Instant;

public class CdrDto {
    private String msisdn;
    private long dataVolumeBytes;
    private String startDatetime; // expect ISO-8601 string in UTC or epoch ms
    private String endDatetime;
    private String mcc;
    private String mnc;

    // getters/setters (or use Lombok @Data)

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
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

    public long getDataVolumeBytes() {
        return dataVolumeBytes;
    }

    public void setDataVolumeBytes(long dataVolumeBytes) {
        this.dataVolumeBytes = dataVolumeBytes;
    }
}