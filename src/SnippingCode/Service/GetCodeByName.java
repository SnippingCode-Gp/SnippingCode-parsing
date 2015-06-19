package SnippingCode.Service;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import SnippingCode.Domain.CodeDomainParser;
import SnippingCode.JsonParser.ParseJsonObject;
import SnippingCode.ObjectRequest.CodeReq;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by nasser on 19/06/15.
 */

public class GetCodeByName {
    private String url = "http://localhost:8080/CodeSnipping/File/getCodeByName";
    private ParseJsonObject jsonParse;

    public void getCode(CodeReq codeReq) throws URISyntaxException,
            ClientProtocolException, IOException {

        jsonParse = new ParseJsonObject();
        HttpClient client = getThreadSafeClient();
        HttpResponse response;
        URI urls = new URI(url);
        HttpPost post = new HttpPost(urls);
        List<NameValuePair> params = codeReq.createParam();
        post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        response = client.execute(post);
        System.out.println(response.toString());
        response = client.execute(post);
        System.out.println(response.toString());
        JSONObject returnObject = parseInputStream(response);
        CodeDomainParser code = jsonParse.parseJsonObject(returnObject);
        code.printAll();

    }

    private JSONObject parseInputStream(HttpResponse response) {

        try {
            String http = EntityUtils.toString(response.getEntity());
            System.out.println(http);
            JSONObject JsonObject = new JSONObject(http);
            return JsonObject;
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
        return null;
    }

    public static DefaultHttpClient getThreadSafeClient()  {

        DefaultHttpClient client = new DefaultHttpClient();
        ClientConnectionManager mgr = client.getConnectionManager();
        HttpParams params = client.getParams();
        client = new DefaultHttpClient(new ThreadSafeClientConnManager(params,

                mgr.getSchemeRegistry()), params);
        return client;
    }
}
