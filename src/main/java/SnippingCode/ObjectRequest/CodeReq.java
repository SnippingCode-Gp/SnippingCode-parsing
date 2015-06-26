package SnippingCode.ObjectRequest;

import SnippingCode.Domain.Code;

/**
 * Created by nasser on 19/06/15.
 */

/**
 * @author nasser
 * <p> using Code Request when send Request to server</p>
 */
public class CodeReq {
    String name;
    String username;
    String password;
    String version;

    public String getUrlReq(){
        return "name="+name+"&username="+username+"&password="+password+"&version="+version;

    }

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

}
