
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportGen {
 
	public static void main(String[] args) {
		 
		File reportOutputDirectory = new File("target/reports/json/cucumber-html-reports");
		List<String> jsonReportFiles = new ArrayList<String>();

		  jsonReportFiles.add("target/reports/json/cucumber-html-reports/cucumber.json");
		
		String buildNumber = "1" ;
		String buildProjectName = "LitPro Sanity Fx";
		/*Boolean skippedFails = false;
		Boolean undefinedFails = false;
		Boolean flashCharts = true;
		Boolean runWithJenkins = true;*/
		ReportBuilder reportBuilder;
		//ReportBuilder(List<String> jsonReports, File reportDirectory, String pluginUrlPath, String buildNumber, String buildProject, boolean skippedFails, boolean undefinedFails, boolean flashCharts, boolean runWithJenkins, boolean artifactsEnabled, String artifactConfig, boolean highCharts) 
		try {
			reportBuilder = new ReportBuilder(jsonReportFiles,reportOutputDirectory,"", buildNumber,buildProjectName, false,false,true,true,false,"",false);
			reportBuilder.generateReports();
		} catch (Exception e) {
			System.out.println("EXCEPTION" + e);
		}
	}
}
