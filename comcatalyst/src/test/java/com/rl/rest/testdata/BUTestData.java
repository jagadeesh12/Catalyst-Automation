package com.rl.rest.testdata;

import com.rl.rest.model.BUModel;
import com.rl.rest.model.OrgModel;


/**
 * Created by jagadeesh on 15/2/17.
 */
public class BUTestData {
    public static final BUModel buData = generateModelForBU("E-Learning","Relevancelab","Org Row ID","1000");

    public static BUModel generateModelForBU(String businessGroupName, String orgName, String OrgRowID , String plannedCost ){
        BUModel buModel =new BUModel();

        buModel.setBusinessGroupName(businessGroupName);
        buModel.setPlannedCost(plannedCost);
        buModel.setOrgRowID("");
        buModel.setOrgName(orgName);

        return buModel;
    }
}
