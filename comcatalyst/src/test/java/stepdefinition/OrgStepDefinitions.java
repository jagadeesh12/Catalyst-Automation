package stepdefinition;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
//import com.rl.rest.catalyst.common.commonUtility;
import com.rl.rest.model.*;
import com.rl.rest.testdata.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OrgStepDefinitions {
    private static Response orgListResponse;
    private static Response buListResponse;
    private static Response projectListResponse;
    private static Response chefServerListResponse;
    private static Response EnvListResponse;
    private ValidatableResponse json;
    static String token;
    static String fieldvalue;
    static String bufieldvalue;
    static String projfieldvalue;
    static String chefserverfieldvalue;
    static String envfieldvalue;
    public static String jsonAsStringForOrg;
    public static String jsonAsStringForBU;
    public static String jsonAsStringForProj;
    public static String jsonAsStringForChefServer;
    public static String jsonAsStringForEnv;
    public static
    Map<String,String> createOrg;
    OrgModel model;
    BUModel buModel;
    ProjectModel projectModel;
    ChefServerModel chefModel;
    EnvModel envModel;
    Map<String,String> createBg;
    Map<String,String> createPro;
    Map<String,String> createChef;
    Map<String,String> createEnv;


    @Given("^User logs in as Superadmin$")
    public void user_logs_in_as_Superadmin() throws Throwable {
        // here we setup the default URL and API base path to use throughout the tests
        RestAssured.baseURI = "http://d4d.rlcatalyst.com";
        RestAssured.basePath = "/d4dMasters/";
        RestAssured.defaultParser = Parser.JSON;

        Map<String,String> login = new HashMap<String,String>();
        login.put("username", "superadmin");
        login.put("pass", "superadmin@123");
        login.put("authType", "token");

        token = given()
                .contentType("application/json")
                .body(login)
                .when().post(RestAssured.baseURI+"/auth/signin")
                .then().statusCode(200)
                .extract().path("token");
        System.out.println(" Token Number after sigin: " + token);
        System.out.println(" URL is " + RestAssured.baseURI);

    }

    @And("^User create an Org$")
    public void user_create_an_Org() throws Throwable {
        System.out.println("Token Number while creating org: " + token);
        model = OrgTestData.orgData;
        createOrg = new HashMap<String,String>();
        createOrg.put("orgname", model.getOrgName());
        createOrg.put("domainname", model.getDomainName());
        createOrg.put("plannedCost", model.getPlannedCost());
        createOrg.put("active", model.getActive());

        /*commonUtility util = new commonUtility();
        System.out.println(util.getSettingDetails(RestAssured.baseURI,token,model.getOrgName(),"1"));*/

        System.out.println("createOrg" +createOrg);

        given()
                .contentType("application/json")
                .header("x-catalyst-auth",token)
                .body(createOrg)
                .when().post(RestAssured.baseURI+RestAssured.basePath+"savemasterjsonrownew/1/null/"+createOrg.get("orgname")).then()
                .statusCode(200);

        model = OrgTestData.orgData;
        RestAssured.defaultParser = Parser.JSON;
        orgListResponse = given()
                .contentType("application/json")
                .header("x-catalyst-auth",token)
                .when().get(RestAssured.baseURI+RestAssured.basePath+"/readmasterjsonneworglist/1")
                .then()
                .extract().response();
        jsonAsStringForOrg = orgListResponse.asString();
        System.out.println(jsonAsStringForOrg);
        List rowids = from(jsonAsStringForOrg).get("rowid");
        List orgNames =  from(jsonAsStringForOrg).get("orgname");
        System.out.println(" RowIDs are " + rowids);
        System.out.println("Orgname is " + orgNames);

        Iterator itr = orgNames.iterator();
        Iterator it = rowids.iterator();
        int rows = rowids.size();

        for (int j=0; j< rows; j++) {
            String org = itr.next().toString();
            String rowId = it.next().toString();

            if(org.equals(model.getOrgName()) && rowId!= null ){
                System.out.println("Row id for org"+model.getOrgName()+" :"+rowId);
                fieldvalue = rowId;
                //BUTestData.buData.setOrgRowID(fieldvalue);
                break;
            }
        }


    }
/*
    @And("^User delets the Org$")
    public void User_delets_the_Org() throws Throwable {
        model = OrgTestData.orgData;
        RestAssured.defaultParser = Parser.JSON;
        response = given()
                .contentType("application/json")
                .header("x-catalyst-auth",token)
                .when().get(RestAssured.baseURI+RestAssured.basePath+"/readmasterjsonneworglist/1")
                .then()
                .extract().response();
        jsonAsString = response.asString();
        System.out.println(jsonAsString);
        List rowids = from(jsonAsString).get("rowid");
        List orgNames =  from(jsonAsString).get("orgname");
        System.out.println(" RowIDs are " + rowids);
        System.out.println("Orgname is " + orgNames);

        Iterator itr = orgNames.iterator();
        Iterator it = rowids.iterator();
        int rows = rowids.size();

        for (int j=0; j< rows; j++) {
            String org = itr.next().toString();
            String rowId = it.next().toString();

            if(org.equals(model.getOrgName()) && rowId!= null ){
                System.out.println("Row id for org"+model.getOrgName()+" :"+rowId);
                fieldvalue = rowId;
                BUTestData.buData.setOrgRowID(fieldvalue);
                break;
            }
        }
        Map<String,String> deleteOrg = new HashMap<String,String>();
        deleteOrg.put("id", "1");
        deleteOrg.put("fieldname", "rowid");
        deleteOrg.put("fieldvalue", fieldvalue);
        given()
                .contentType("application/json")
                .header("x-catalyst-auth",token)
                .body(deleteOrg)
                .when().get(RestAssured.baseURI+RestAssured.basePath+"/removeitem/1/rowid/"+deleteOrg.get("fieldvalue")).then()
                .statusCode(200);
    }*/

    @Given("^User creates Business group$")
    public void user_creates_Business_group() throws Throwable {

        System.out.println("Creating BU and token is " + token);


        buModel = BUTestData.buData;
        createBg = new HashMap<String, String>();
        createBg.put("productgroupname", buModel.getBusinessGroupName());
        createBg.put("orgname", model.getOrgName());
        createBg.put("orgname_rowid", fieldvalue);
        createBg.put("plannedCost", buModel.getPlannedCost());

            System.out.println(createBg);

            given()
                    .contentType("application/json")
                    .header("x-catalyst-auth", token)
                    .body(createBg)
                    .when().post(RestAssured.baseURI + RestAssured.basePath + "savemasterjsonrownew/2/null/" + createOrg.get("orgname")).then()
                    .statusCode(200);

        buModel = BUTestData.buData;
        RestAssured.defaultParser = Parser.JSON;
        buListResponse = given()
                .contentType("application/json")
                .header("x-catalyst-auth",token)
                .when().get(RestAssured.baseURI+RestAssured.basePath+"/readmasterjsonneworglist/2")
                .then()
                .extract().response();
        jsonAsStringForBU = buListResponse.asString();
        System.out.println("Project JSON" +jsonAsStringForBU);
        List buRowids = from(jsonAsStringForBU).get("rowid");
        List buNames =  from(jsonAsStringForBU).get("productgroupname");
        System.out.println(" BU RowIDs are " + buRowids);
        System.out.println("BU names are " + buNames);

        Iterator buNamesItr = buNames.iterator();
        Iterator buRowIDIt = buRowids.iterator();
        int buRows = buRowids.size();

        for (int k=0; k< buRows; k++) {
            String buName = buNamesItr.next().toString();
            String buRowId = buRowIDIt.next().toString();

            if(buName.equals(buModel.getBusinessGroupName()) && buRowId!=null){
                System.out.println("Row id for Business unit" +buModel.getBusinessGroupName()+ " :"+buRowId);
                bufieldvalue = buRowId;
                BUTestData.buData.setOrgRowID(bufieldvalue);
                break;

            }

        }

    }

    @Given("^User creates Project$")
    public void user_creates_Project() throws Throwable {
        System.out.println("Token while creating Project " +token);

        projectModel=ProjectTestData.projectData;
        createPro=new HashMap<String, String>();
        createPro.put("projectname",projectModel.getProjectname());
        createPro.put("description",projectModel.getDescription());
        createPro.put("orgname",model.getOrgName());
        createPro.put("orgname_rowid",fieldvalue);
        createPro.put("productgroupname",buModel.getBusinessGroupName());
        createPro.put("productgroupname_rowid",bufieldvalue);

        System.out.println(createPro);

        given()
                .contentType("application/json")
                .header("x-catalyst-auth", token)
                .body(createPro)
                .when().post(RestAssured.baseURI + RestAssured.basePath + "savemasterjsonrownew/4/null/" + createOrg.get("orgname")).then()
                .statusCode(200);

        //projectModel=ProjectTestData.projectData;
        RestAssured.defaultParser=Parser.JSON;
        projectListResponse =given()
                .contentType("application/json")
                .header("x-catalyst-auth",token)
                .when().get(RestAssured.baseURI+RestAssured.basePath+"/readmasterjsonneworglist/4")
                .then()
                .extract().response();
        jsonAsStringForProj=projectListResponse.asString();
        System.out.println("Project List JSON" + jsonAsStringForProj);
        List projRowids=from(jsonAsStringForProj).get("rowid");
        List projNames=from(jsonAsStringForProj).get("projectname");

        System.out.println(" BU RowIDs are " + projRowids);
        System.out.println("BU names are " + projNames);

        Iterator projNamesItr=projNames.iterator();
        Iterator projRowIDIt=projRowids.iterator();
        int projRows=projRowids.size();

        for(int l=0;l<projRows;l++){
            String projName=projNamesItr.next().toString();
            String projROwID=projRowIDIt.next().toString();

            if(projName.equals(projectModel.getProjectname())&& projROwID!=null){
                System.out.println("Row ID for Project" +projectModel.getProjectname()+ ":" +projROwID);
                projfieldvalue=projROwID;
                break;
            }

        }


    }


    @Given("^User creates Chef Server$")
    public void user_creates_Chef_Server() throws Throwable {
        System.out.println("Token while creating Chef Server " +token);

        chefModel= ChefServerTestData.chefServerData;

        createChef=new HashMap<String, String>();
        createChef.put("configname",chefModel.getConfigname());
        createChef.put("loginname",chefModel.getLoginname());
        createChef.put("url",chefModel.getUrl());
        createChef.put("orgname",model.getOrgName());
        createChef.put("orgname_rowid",fieldvalue);
        createChef.put("userpemfile_filename",chefModel.getUserpemfile_filename());
        createChef.put("kniferbfile_filename",chefModel.getKniferbfile_filename());
        createChef.put("folderpath",chefModel.getFolderpath());

        System.out.println(createChef);

        given()
                .contentType("multipart/form-data")
                .header("x-catalyst-auth", token)
                .multiPart("userpemfile", new File("//src//Filesupload//jagadeesh12.pem"))
                .multiPart("kniferbfile", new File("//src//Filesupload//knife.rb"))
                .formParam("configname",chefModel.getConfigname())
                .formParam("loginname",chefModel.getLoginname())
                .formParam("url",chefModel.getUrl())
                .formParam("orgname",model.getOrgName())
                .formParam("orgname_rowid",fieldvalue)
                .formParam("userpemfile_filename",chefModel.getUserpemfile_filename())
                .formParam("kniferbfile_filename",chefModel.getKniferbfile_filename())
                .formParam("folderpath",chefModel.getFolderpath())
                .when().post(RestAssured.baseURI + RestAssured.basePath + "savemasterjsonrownew/10/userpemfile,kniferbfile/" +  createOrg.get("orgname")).then()
                .statusCode(200);

        RestAssured.defaultParser = Parser.JSON;
        chefServerListResponse=given()
                .contentType("application/json")
                .header("x-catalyst-auth",token)
                .when().get(RestAssured.baseURI+RestAssured.basePath+"/readmasterjsonneworglist/10")
                .then()
                .extract().response();

        jsonAsStringForChefServer=chefServerListResponse.asString();
        System.out.println("Chef Server JSON" +jsonAsStringForChefServer);

        List chefServerRowIds=from(jsonAsStringForChefServer).get("rowid");
        List chefServerNames=from(jsonAsStringForChefServer).get("configname");

        System.out.println("Chef Server Row Ids are" +chefServerRowIds);
        System.out.println("Chef Server Names are" +chefServerNames);

        Iterator chefServerNamesItr = chefServerNames.iterator();
        Iterator chefServerRowIDIt = chefServerRowIds.iterator();
        int chefServerRows = chefServerRowIds.size();

        for (int m=0;m<chefServerRows;m++){
            String chefServerName=chefServerNamesItr.next().toString();
            String chefServerRowID=chefServerRowIDIt.next().toString();

            if(chefServerName.equals(chefModel.getConfigname())&& chefServerRowID!=null){
                System.out.println("Row ID for Chef Server" +chefModel.getConfigname()+ ":" +chefServerRowID);
                chefserverfieldvalue=chefServerRowID;
                break;

            }

        }

    }

    @Given("^User creates Environment$")
    public void user_creates_Environment() throws Throwable {
        System.out.println("Token while creating Environment " +token);

        Thread.sleep(10000);

        envModel= EnvTestData.envData;
        createEnv=new HashMap<String, String>();
        createEnv.put("orgname",model.getOrgName());
        createEnv.put("orgname_rowid",fieldvalue);
        createEnv.put("configname",chefModel.getConfigname());
        createEnv.put("configname_rowid",chefserverfieldvalue);
        createEnv.put("environmentname",envModel.getEnvironmentname());
        createEnv.put("environmentname_rowid",envModel.getEnvironmentname_rowid());
        createEnv.put("projectname",projectModel.getProjectname());
        createEnv.put("projectname_rowid",projfieldvalue);

        System.out.println(createEnv);

        given().contentType("application/json")
                .header("x-catalyst-auth", token)
                .body(createEnv)
                .when().post(RestAssured.baseURI+RestAssured.basePath +"savemasterjsonrownew/3/null/" + createOrg.get("orgname")).then()
                .statusCode(200);
    }
}
