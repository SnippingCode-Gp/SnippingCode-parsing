package SnippingCode.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import SnippingCode.Domain.Code;
import SnippingCode.JsonParser.ParseJsonObject;
import SnippingCode.ObjectRequest.CodeReq;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <h1>Code Request</h1>
 * <p>Get All user code using user name and password</p>
 * @author nasser
 * @version 2
 */
public class CodesHttpRequest {
	private ParseJsonObject jsonParse = null;
	private final String getAllCode = "http://localhost:8080/CodeSnipping/File/viewPlugin";
    private final String getCodeByName = "http://localhost:8080/CodeSnipping/File/getCodeByName";

    public CodesHttpRequest(){
        jsonParse = new ParseJsonObject();
    }

    private HttpURLConnection configConnection(URL url , String urlParm) throws IOException {
		HttpURLConnection con = null;
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
        con.setRequestProperty("Content-Length",
				Integer.toString(urlParm.getBytes().length));
        con.setRequestProperty("Content-Language", "en-US");

        con.setUseCaches(false);
        con.setDoOutput(true);
		return con;
	}

    /**
     * <h1> Get All Code From Server</h1>
     * @param username String
     * @param pass String
     * @return ArrayList<Codes> List of all user codes
     * @throws IOException
     * @throws JSONException
     */
	public ArrayList<Code> getAllCode(String username, String pass) throws IOException,
			JSONException {

		String urlParam = "username=" + username + "&password=" + pass
				+ "&number=0";
		URL url = new URL(getAllCode);

		HttpURLConnection connection = configConnection(url , urlParam);

		// Send request
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParam);
		wr.close();

		// Get Response
		InputStream is = connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');
		}
		rd.close();
		JSONArray json = new JSONArray(response.toString());
		return jsonParse.parseJsonArray(json);
	}

    /**
     * <p> get code using its name and version </p>
     * @param codeReq Class CodeReq
     * @return Code Object
     */
    public Code getCodeByName(CodeReq codeReq) {

        String targetURL = getCodeByName;
        String urlParam = codeReq.getUrlReq();
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(targetURL);

            connection = configConnection(url , urlParam);

            //Send request
            DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
            wr.writeBytes(urlParam);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
            String line;
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            JSONObject json = new JSONObject(response.toString());
            return jsonParse.parseJsonObject(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null)
                connection.disconnect();

        }
    }

}
