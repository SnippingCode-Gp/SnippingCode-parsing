package SnippingCode.Service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import SnippingCode.Domain.User;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nasser on 27/06/15.
 * @author Abdelgawad
 */
public class UserHttpRequest {

    private User user = null;
    private final String signupRequest = "http://localhost:8080/CodeSnipping/SignUp";

    public UserHttpRequest(){

    }

    public UserHttpRequest(User user){
         this.user = user;
    }



    private HttpURLConnection configHttpConnection (URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setUseCaches(false);
        return connection;
    }

    /**
     * <p> signup but User Class is send in constructor of UserHttpRequest </p>
     * @return status code
     * @throws IOException
     */
    public int signUpRequest() throws IOException {
        return signUp(user);
    }

    /**
     *
     * @param usr User from Class User
     * @return status Code
     * @throws JSONException
     * @throws IOException
     */
    public int signUp(User usr) throws JSONException, IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", usr.getFirstName());
        jsonObject.put("username", usr.getUsername());
        jsonObject.put("password", usr.getPassword());
        jsonObject.put("email", usr.getEmail());
        String urlParameters = jsonObject.toString();

        URL url = new URL(signupRequest);
        HttpURLConnection connection = configHttpConnection(url);

        DataOutputStream outStream = new DataOutputStream(connection.getOutputStream());
        outStream.write(urlParameters.getBytes());
        outStream.flush();
        outStream.close();

        return connection.getResponseCode();
    }

    /**
     * <p> login but User Class is send in constructor of UserHttpRequest</p>
     * @return status code
     * @throws IOException
     */
    public int login() throws IOException {
        return login(user);
    }

    /**
     *
     * @param usr User from Class User
     * @return status code
     * @throws IOException
     * @throws JSONException
     */
    public int login(User usr) throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", usr.getUsername());
        jsonObject.put("password", usr.getPassword());
        String urlParameters = jsonObject.toString();

        String requestURL = "http://localhost:8080/CodeSnipping/registration/login";
        URL url = new URL(requestURL);
        HttpURLConnection connection = configHttpConnection(url);

        DataOutputStream outStream = new DataOutputStream(connection.getOutputStream());
        outStream.write(urlParameters.getBytes());
        outStream.flush();
        outStream.close();

        return connection.getResponseCode();
    }
}
