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
 * @author nasser
 */

public class Main {
    static ParseJsonObject parseJsonObject;
    static FileOperation fileOperation;
    static ArrayList<Code> codesArray;
    static CodesHttpRequest codesHttpRequest;
    static UserHttpRequest userHttpRequest;

    //  get user code from server and save to test file xml
    public static void getUserCode(){

        try {
            fileOperation.initXmlFile(codesHttpRequest.getAllCode("ahmed", "ahmed", "0"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    public static void checkParsingCode(){
        String pathCode = "/home/nasser/Desktop/Project/SnippingCode-Parsing/ahmed.xml";
        fileOperation.parseCodeJsonFile(pathCode).printAll();
    }

    public static void getCode(){
        String pathTest = "/home/nasser/Desktop/Project/SnippingCode-Parsing/test.xml";
        List<Code> codes = fileOperation.parseXmlFile(pathTest);
        codesArray = new ArrayList<Code>();
        // get from server
        for(Code item : codes){
            CodeReq codeReq = new CodeReq(item , "ahmed" , "ahmed");
            try {
                codesArray.add(codesHttpRequest.getCodeByName(codeReq)); // return codeDomainParser
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (Code code : codesArray){
            fileOperation.saveCodeToFile(code);
        }
    }

    public static void main(String [ ] args){
        parseJsonObject = new ParseJsonObject();
        fileOperation = new FileOperation();
        userHttpRequest = new UserHttpRequest();
        codesArray = new ArrayList<Code>();
        codesHttpRequest = new CodesHttpRequest();

        getUserCode();

        getCode();

        checkParsingCode();

        checkUserSignUp();

        checkLogin();
    }

    private static void checkUserSignUp() {
        User user = new User();
        user.setUsername("ahmednasser");
        user.setEmail("ahmednassersaleh@gmail.com");
        user.setFirstName("ahmed");
        user.setLastName("hamada");
        user.setPassword("ahmednasser");
        try {
            int var = userHttpRequest.signUp(user);
            System.out.println(var);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    private static void checkLogin(){
        User user = new User();
        user.setUsername("ahmednasser");
        user.setEmail("ahmednassersaleh@gmail.com");
        user.setFirstName("ahmed");
        user.setLastName("hamada");
        user.setPassword("ahmednasser");

        try {
            int var = userHttpRequest.login(user);
            System.out.println(var);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }
}
