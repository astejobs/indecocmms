package com.aste.lsme.domain;


import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class ScheduledEquipment {
    private Workspace workspace;
    private Long EquipmentTypeid;
    private Long EquipmentSubtypeid;
    private Long Equipmentid;
    private Long Buildingid;
    private Long Locationid;
    private String TaskToBePerformed;
    private Integer FrequencyOfService;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date From;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date To;
    private String[] status;

    public Workspace getWorkspace() {
        return this.workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public Long getEquipmentTypeid() {
        return this.EquipmentTypeid;
    }

    public void setEquipmentTypeid(Long equipmentTypeid) {
        this.EquipmentTypeid = equipmentTypeid;
    }

    public Long getEquipmentSubtypeid() {
        return this.EquipmentSubtypeid;
    }

    public void setEquipmentSubtypeid(Long equipmentSubtypeid) {
        this.EquipmentSubtypeid = equipmentSubtypeid;
    }

    public Long getEquipmentid() {
        return this.Equipmentid;
    }

    public void setEquipmentid(Long equipmentid) {
        this.Equipmentid = equipmentid;
    }

    public Long getBuildingid() {
        return this.Buildingid;
    }

    public void setBuildingid(Long buildingid) {
        this.Buildingid = buildingid;
    }

    public Long getLocationid() {
        return this.Locationid;
    }

    public void setLocationid(Long locationid) {
        this.Locationid = locationid;
    }

    public String getTaskToBePerformed() {
        return this.TaskToBePerformed;
    }

    public void setTaskToBePerformed(String taskToBePerformed) {
        this.TaskToBePerformed = taskToBePerformed;
    }

    public Integer getFrequencyOfService() {
        return this.FrequencyOfService;
    }

    public void setFrequencyOfService(Integer frequencyOfService) {
        this.FrequencyOfService = frequencyOfService;
    }

    public Date getFrom() {
        return this.From;
    }

    public void setFrom(Date from) {
        this.From = from;
    }

    public Date getTo() {
        return this.To;
    }

    public void setTo(Date to) {
        this.To = to;
    }

    public String[] getStatus() {
        return this.status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }
}