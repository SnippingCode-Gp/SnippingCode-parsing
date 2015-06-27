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

/**
 * Created by nasser on 17/06/15.
 * @author nasser
 */
public class Main {
    static ParseJsonObject parseJsonObject;
    static FileOperation fileOperation;
    static ArrayList<Code> codes;
    static CodesHttpRequest codesHttpRequest;
    static UserHttpRequest userHttpRequest;

//        get user code from server and save to test file xml
    public static void getUserCode(){
        codes = new ArrayList<Code>();
        codesHttpRequest = new CodesHttpRequest();
        try {
            fileOperation.initXmlFile(codesHttpRequest.getAllCode("ahmed", "ahmed"));
        } catch (IOException e) {
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

        // get from server
        for(Code item : codes){
            CodeReq codeReq = new CodeReq(item , "ahmed" , "ahmed");
            try {
                Main.codes.add(codesHttpRequest.getCodeByName(codeReq)); // return codeDomainParser
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (Code code : Main.codes){
            fileOperation.saveCodeToFile(code);
        }
    }

    public static void main(String [ ] args){
        parseJsonObject = new ParseJsonObject();
        fileOperation = new FileOperation();
        userHttpRequest = new UserHttpRequest();
//        getUserCode();
//
//        getCode();
//
//        checkParsingCode();

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
            int var = userHttpRequest.signUpRequest(user);
            System.out.println(var);
        } catch (IOException e) {
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
            int var = userHttpRequest.loginHttpRequest(user);
            System.out.println(var);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
