package com.ldy.flexframe.module;

/**
 * Created by frog on 2016/1/19.
 */
public class LoginParams extends BaseParams {
    private String validateName;
    private String validatePass;
    private String imsi;
    private String imei;
    private String iccid;

    public String getValidateName() {
        return validateName;
    }

    public void setValidateName(String validateName) {
        this.validateName = validateName;
    }

    public String getValidatePass() {
        return validatePass;
    }

    public void setValidatePass(String validatePass) {
        this.validatePass = validatePass;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
}
