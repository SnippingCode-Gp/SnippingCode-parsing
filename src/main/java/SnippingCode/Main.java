package SnippingCode;

import SnippingCode.Domain.Code;
import SnippingCode.Domain.User;
import SnippingCode.JsonParser.ParseJsonObject;
import SnippingCode.ObjectRequest.CodeReq;
import SnippingCode.Service.CodesHttpRequest;
import SnippingCode.Service.FileOperation;
import SnippingCode.Service.UserHttpRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

/**
 * Created by nasser on 17/06/15.
 * 
 * @author nasser
 */

public class Main {
  static ParseJsonObject parseJsonObject;
  static FileOperation fileOperation;
  static ArrayList<Code> codesArray;
  static CodesHttpRequest codesHttpRequest;
  static UserHttpRequest userHttpRequest;

  private static final String username = "abdelgawad";
  private static final String password = "123456";

  // get user code from server and save to test file xml
  public static void getUserCode() {

    try {
      ArrayList<Code> codes = codesHttpRequest.getAllCode(username, password,
          "0");
      fileOperation.initXmlFile(codes);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void checkParsingCode(String name) {
    String pathCode = "/home/" + username + "/.SC/codes/" + name;
    fileOperation.parseCodeJsonFile(pathCode).printAll();
  }

  public static void getCode() {
    String pathTest = "/home/" + username + "/.SC/Codes.xml";
    List<Code> codes = fileOperation.parseXmlFile(pathTest);
    codesArray = new ArrayList<Code>();

    // get from server
    for (Code item : codes) {
      CodeReq codeReq = new CodeReq(item, username, password);
      try {
        codesArray.add(codesHttpRequest.getCodeByName(codeReq)); // return
                                                                 // codeDomainParser
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    for (Code code : codesArray) {
      fileOperation.saveCodeToFile(code);
    }
  }

  private static void checkUserSignUp() {
    User user = new User();
    user.setUsername(username);
    user.setEmail("ahmednasser1993@gmail.com");
    user.setFirstName(username);
    user.setLastName(username);
    user.setPassword(password);
    try {
      int var = userHttpRequest.signUp(user);
      System.out.println(var);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void checkLogin() {
    User user = new User();
    user.setUsername(username);
    user.setEmail("ahmednassersaleh@gmail.com");
    user.setFirstName(username);
    user.setLastName("hamada");
    user.setPassword(password);

    try {
      int var = userHttpRequest.login(user);
      System.out.println(var);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void uploadCode() throws JSONException {
    Code code = new Code();
    code.setName("test5");
    code.setCode("ahmed nasser saleh");
    code.setDescription("ay 7aga feh ay betngan");
    code.setType("cpp & java");
    code.setTags("BFS,DFS,MMM,DMN,");

    codesHttpRequest.uploadCode(code, username, password);
  }

  public static void main(String[] args) {
    parseJsonObject = new ParseJsonObject();
    fileOperation = new FileOperation();
    userHttpRequest = new UserHttpRequest();
    codesArray = new ArrayList<Code>();
    codesHttpRequest = new CodesHttpRequest();

    checkUserSignUp();
    
    try {
      uploadCode();
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    getUserCode();

    // getCode();
    //
    // checkParsingCode("test5");
    //
    
    // checkLogin();

    
  }

}
