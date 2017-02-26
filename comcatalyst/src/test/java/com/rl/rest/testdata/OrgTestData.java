package com.rl.rest.testdata;

import com.rl.rest.model.OrgModel;

public class OrgTestData {

    public static final OrgModel orgData = generateModelForOrg("Relevancelab", "RLOrganization", "600", "true" );

    //public static final OrgModel orgData1 = generateModelForOrg("orgname12346", "RL Organization", "1800", "true" );

    public static OrgModel generateModelForOrg(String orgname, String domainNmae, String plannedCost, String active) {

        OrgModel model = new OrgModel();

        model.setOrgName(orgname);
        model.setDomainName(domainNmae);
        model.setPlannedCost(plannedCost);
        model.setActive(active);

       return model;
    }
}
