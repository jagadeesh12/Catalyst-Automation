package com.rl.rest.model;

/**
 * Created by jagadeesh on 15/2/17.
 */
public class BUModel {


    private String businessGroupName;
    private String orgName;
    private String OrgRowID;
    private String plannedCost;


    public String getBusinessGroupName() {
        return businessGroupName;
    }

    public void setBusinessGroupName(String businessGroupName) {
        this.businessGroupName = businessGroupName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgRowID() {
        return OrgRowID;
    }

    public void setOrgRowID(String orgRowID) {
        OrgRowID = orgRowID;
    }

    public String getPlannedCost() {
        return plannedCost;
    }

    public void setPlannedCost(String plannedCost) {
        this.plannedCost = plannedCost;
    }
}
