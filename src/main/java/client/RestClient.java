package client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RestClient {

    // 1.create one Get Method
    public CloseableHttpResponse get(String url) throws IOException {
    CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url); // create a request ,get a request
      CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);// hit the Get URL

        return closeableHttpResponse;

    }
}
