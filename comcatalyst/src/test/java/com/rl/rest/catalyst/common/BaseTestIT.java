package com.rl.rest.catalyst.common;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTestIT {
    //Environments URL
    private static final String ciEnv="http://fs-iam-services-ci.scholastic-labs.io";

    private static final String qaEnv="http://fs-iam-services-ci.scholastic-labs.io";

    static Properties prop=null;
    public static FileInputStream fis=null;
    public static boolean authenticationHeaderFlag=false;

    private static String CIPropfilePath=System.getProperty("user.dir")+"/src/test/resources/CI-PropIT.properties";
    private static String QAPropfilePath=System.getProperty("user.dir")+"/src/test/resources/QA-PropIT.properties";
    private static final String PROPERTY_PREFIX = "RestAssured";

    @BeforeClass
    public static void beforeClass() {
        Properties props = System.getProperties();
        System.out.println("Initializing System properties: " + props);
        RestAssured.baseURI = getProperty("baseURI", "http://fs-iam-services-ci.scholastic-labs.io");
        RestAssured.port = getIntProperty("port", 8080);
        if(RestAssured.baseURI.contains("api")) {
            RestAssured.basePath = getProperty("basePath", "/app/iam-svcs/1.0");
            authenticationHeaderFlag=true;
        }else
            RestAssured.basePath = getProperty("basePath", "/idm-services");
        initEnvProperty();
    }

    @Before
    public void initialization() {
        System.out.println("\n\n\n\n*******Initialized RestAssured*******");
        System.out.println("RestAssured.baseURI: " + RestAssured.baseURI);
        System.out.println("RestAssured.port: " + RestAssured.port);
        System.out.println("RestAssured.basePath: " + RestAssured.basePath);
        System.out.println("******************************\n\n\n\n");

    }

    private static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key, null);
        if( value != null ) {
            return Integer.parseInt(value);
        }
        else {
            return defaultValue;
        }
    }

    private static String getSystemProperty(String prop) {
        String val = System.getProperty(prop);
        System.out.println("System property " + prop + ": " + val);
        if( val == null ) {
            return null;
        }
        val = val.trim();
        return val.isEmpty() ? null : val;
    }

    private static String getProperty(String key, String defaultValue) {
        String fullPropertyName = PROPERTY_PREFIX + "." + key;
        String value = getSystemProperty(fullPropertyName);
        if( value != null ) {
            return value;
        }
        value = getSystemProperty(key);
        return value != null ? value : defaultValue;
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("Test :"+ description.getMethodName()  +" succeeded.");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("Test :"+ description.getMethodName() + " failed with " + e.getMessage() + ".");
        }

        @Override
        protected void starting(Description description) {
            System.out.println("Beginning Test :" + description.getMethodName());
        }
    };

    public static void initEnvProperty() {
        prop=new Properties();
        try
        {
            if(RestAssured.baseURI.equals(ciEnv) ||RestAssured.baseURI.equals(ciEnv)) {
                fis=new FileInputStream(CIPropfilePath);
                prop.load(fis);
            }else if(RestAssured.baseURI.equalsIgnoreCase(qaEnv) || RestAssured.baseURI.equals(qaEnv)) {
                fis=new FileInputStream(QAPropfilePath);
                prop.load(fis);
            }
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public String readProperty(String key) {
        return prop.getProperty(key);
    }
}
