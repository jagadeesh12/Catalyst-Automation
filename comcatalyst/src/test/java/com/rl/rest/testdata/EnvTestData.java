package com.rl.rest.testdata;


import com.rl.rest.model.EnvModel;


public class EnvTestData {

    public static final EnvModel envData = generateModelForEnv("Pre-Prod", "undefined");
    public static EnvModel generateModelForEnv(String environmentname, String environmentname_rowid){
        EnvModel envModel = new EnvModel();
        envModel.setEnvironmentname(environmentname);
        envModel.setEnvironmentname_rowid(environmentname_rowid);

        return envModel;
    }


}


