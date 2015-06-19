package SnippingCode.ObjectRequest;

import SnippingCode.Domain.Code;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nasser on 19/06/15.
 */
public class CodeReq {
    String name;
    String username;
    String password;
    String version;

    public CodeReq(Code item, String username, String password) {
        this.name = item.getName();
        version = item.getVersion();
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<NameValuePair> createParam(){
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("version", version));
        return params;
    }
}
