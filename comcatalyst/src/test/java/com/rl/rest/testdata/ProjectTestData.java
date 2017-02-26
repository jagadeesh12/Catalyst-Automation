package com.rl.rest.testdata;

import com.rl.rest.model.ProjectModel;


/**
 * Created by jagadeesh on 21/2/17.
 */
public class ProjectTestData {

    public static final ProjectModel projectData = generatemodalForProject("Schoalstic" , "This is SCLT Project");

    public static ProjectModel generatemodalForProject(String projectname, String description){
        ProjectModel projectModel=new ProjectModel();
        projectModel.setProjectname(projectname);
        projectModel.setDescription(description);

        return projectModel;

    }
}

