package com.rl.rest.testdata;

import com.rl.rest.model.ChefServerModel;

import java.io.File;


public class ChefServerTestData {

    public static final ChefServerModel chefServerData = generatemodalForChefServer("RLChefServer","jagadeesh12","https://api.opscode.com/organizations/jm012",new File("/home/jagadeesh/CatalystAPIAutomation/comcatalyst/src/Filesupload/jagadeesh12.pem"),"jagadeesh12.pem",new File("/home/jagadeesh/CatalystAPIAutomation/comcatalyst/src/Filesupload/knife.rb"),"knife.rb","/jagadeesh12/.chef/");

    public static ChefServerModel generatemodalForChefServer(String configname, String loginname, String url, File userpemfile, String userpemfile_filename, File kniferbfile, String kniferbfile_filename, String folderpath){
        ChefServerModel chefModel = new ChefServerModel();

        chefModel.setConfigname(configname);
        chefModel.setLoginname(loginname);
        chefModel.setUrl(url);
        chefModel.setUserpemfile(userpemfile);
        chefModel.setUserpemfile_filename(userpemfile_filename);
        chefModel.setKniferbfile(kniferbfile);
        chefModel.setKniferbfile_filename(kniferbfile_filename);
        chefModel.setFolderpath(folderpath);

        return chefModel;
    }

}
