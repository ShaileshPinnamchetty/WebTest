package Test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraBase {
	String session_key="";
	String issue_id="";
	String comment_id="";
	Properties pr=new Properties();
	FileInputStream fis; 
	String testClassName="";
	String testName="";
	
	public JiraBase(String classname, String testname){
		this.testClassName=classname;
		this.testName=testname;
	}
	
	public void sessionKey() throws FileNotFoundException{
		RestAssured.baseURI="http://localhost:8080";
		Response res=given().header("Content-Type","application/json").
		body("{ \"username\": \"shaileshshetty0\", \"password\": \"#HTyGByPXE4i2m.\" }").when().
		post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().response();
		JsonPath jp=getJson(res);
		session_key=jp.get("session.value").toString();
		createAnIssue();
	}
	
	public JsonPath getJson(Response r){
		String str_res=r.asString();
		//System.out.println(str_res);
		JsonPath jp=new JsonPath(str_res);
		return jp;
	}
	
	
	public void createAnIssue(){
		System.out.println("----------Create issue started");
		RestAssured.baseURI="http://localhost:8080";
		Response res=given().header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+session_key).
		body("{"+
  "\"fields\": {"+
    "\"summary\": \"Automated test "+testName+" failed.\","+
      "\"issuetype\": {"+
      "\"name\": \"Bug\""+
	"},"+    
    "\"project\": {"+
      "\"key\": \"RES\"},"+
    "\"description\": \"There seems to be an issue with "+testName+" functionality. Refer the attached screenshot.\"}}").when().
		post("/rest/api/2/issue").then().assertThat().statusCode(201).extract().response();
		JsonPath jp=getJson(res);
		issue_id=jp.get("id");
		System.out.println("Issue id is: "+issue_id);
		System.out.println("----------Create issue ended");
		addComment();
	}
	
	public void addComment() {
		System.out.println("----------Add comment started");
		try {
			fis=new FileInputStream("C:\\Users\\Dinesh\\workspace\\WebTest\\src\\test\\resources\\prop.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			pr.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Running command: "+pr.getProperty("res_issue")+"/"+issue_id+"/comment");
		RestAssured.baseURI=pr.getProperty("BaseURL");
		Response res=given().header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+session_key).
		body("{"+
			  "\"visibility\": {"+
		    "\"type\": \"role\","+
		    "\"value\": \"Administrators\""+
		  "},"+
		  "\"body\": \"This issue was created using Java REST Assured library.\","+
		            "\"type\": \"text\""+
		"}").when().post(pr.getProperty("res_issue")+"/"+issue_id+"/comment").then().
		assertThat().statusCode(201).and().contentType(ContentType.JSON).extract().response();
		JsonPath jp=getJson(res);
		comment_id=jp.get("id");
		System.out.println("----------Add comment ended");
		uploadAttachment();
	}
	
	public void uploadAttachment(){
		System.out.println("----------Upload attachment started");
		System.out.println("Using"+pr.getProperty("res_issue")+"/"+issue_id+"/attachments");
		System.out.println("Using screenshot: "+pr.getProperty("attachment")+"\\"+testClassName+"\\"+testName+".jpg");
		File f=new File(pr.getProperty("attachment")+"\\"+testClassName+"\\"+testName+".jpg");
		RestAssured.baseURI=pr.getProperty("BaseURL");
		Response res=given().multiPart(f).header("X-Atlassian-Token","nocheck").header("Content-Type","multipart/form-data").
		header("Cookie","JSESSIONID="+session_key).when().
		post(pr.getProperty("res_issue")+"/"+issue_id+"/attachments").then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();
		JsonPath jp=getJson(res);
		System.out.println("Attachment id is: "+jp.get("id[0]")+" and size is: "+jp.getInt("[0].size")+" KB");
		System.out.println("----------Upload attachment ended");
	}
	
	public static void main(String[] args){
		JiraBase jb=new JiraBase("Test.Login","loginTest");
		try {
			jb.sessionKey();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
