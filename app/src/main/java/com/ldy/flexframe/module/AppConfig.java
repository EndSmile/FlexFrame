package com.ldy.flexframe.module;

import java.io.Serializable;

/**
 * Created by hhz on 2015/5/20.
 */
public class AppConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 会话ID
     */
    private String accessKey;
    /**
     * 警员ID
     */
    private String personId;

    /**
     * 警号
     */
    private String policeNo;

    /**
     * 警员姓名
     */
    private String policeName;

    private String identifier;
    private String deptNo;
    private String deptName;
    private String mobile;
    private String depId;

    /**
     * 权限
     */
    private String powers;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * 获取 {@link #policeNo}
     * @return
     */
    public String getPoliceNo() {
        return policeNo;
    }

    /**
     * 设置 {@link #policeNo}
     * @return
     */
    public void setPoliceNo(String policeNo) {
        this.policeNo = policeNo;
    }

    /**
     * 获取 {@link #policeName}
     * @return
     */
    public String getPoliceName() {
        return policeName;
    }

    /**
     * 设置 {@link #policeName}
     * @return
     */
    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }
    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "accessKey='" + accessKey + '\'' +
                ", personId='" + personId + '\'' +
                ", policeNo='" + policeNo + '\'' +
                ", policeName='" + policeName + '\'' +
                ", identifier='" + identifier + '\'' +
                ", deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", depId='" + depId + '\'' +
                ", powers='" + powers + '\'' +
                '}';
    }
}
