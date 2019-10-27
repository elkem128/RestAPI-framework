package testPackage;

import base.TestBase;
import client.RestClient;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.TestUtil;

import java.io.IOException;
import java.util.HashMap;

public class GetApiTest extends TestBase {
    TestBase testBase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    @BeforeMethod
    public void setUp() throws IOException {
        testBase = new TestBase();
        serviceUrl =  prop.getProperty("URL");
      apiUrl =prop.getProperty("serviceURL");

       url = serviceUrl + apiUrl;
    }
     @Test
    public void getAPITest() throws IOException {
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);

         int statusCode =  closeableHttpResponse.getStatusLine().getStatusCode();
         System.out.println("statusCode----->" + statusCode);
         Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Status code is not 200");
         // b.JSON String
         String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
         JSONObject responseJsonObject= new JSONObject(responseString);
         System.out.println("Response JSON from api " + responseJsonObject);

        String perPageValue=  TestUtil.getValueByJPath(responseJsonObject,"/per_page");
         System.out.println("value of per page is ---->" + perPageValue);
         Assert.assertEquals(perPageValue, 3);
         //c. All Headers
         Header[] headersArray= closeableHttpResponse.getAllHeaders();
         HashMap<String,String> allHeaders = new HashMap<String,String>();
         for(Header header: headersArray){
             allHeaders.put(header.getName(),header.getValue());
         }
         System.out.println("Headers Array --->" + allHeaders);
     }


}
