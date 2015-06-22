package SnippingCode;

import SnippingCode.Domain.Code;
import SnippingCode.Domain.CodeDomainParser;
import SnippingCode.JsonParser.ParseJsonObject;
import SnippingCode.ObjectRequest.CodeReq;
import SnippingCode.Parsing.Parsing;
import SnippingCode.Service.HttpUrlCon;
import SnippingCode.Service.SaveCodeToFile;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;


import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nasser on 17/06/15.
 */
public class Main {
    static Parsing parsing;
    static ParseJsonObject parseJsonObject;
    static SaveCodeToFile saveCodeToFile;
    static ArrayList<CodeDomainParser> codeDomainParsers;

    public static void main(String [ ] args){

        parseJsonObject = new ParseJsonObject();
        saveCodeToFile = new SaveCodeToFile();

        codeDomainParsers = new ArrayList<CodeDomainParser>();

        // parsing xml
        String path="/home/nasser/Desktop/Project/SnippingCode-parsing/src/SnippingCode/test.xml";
        parsing = new Parsing(path);
        List<Code> codes = parsing.parse();

        // get from server
        for(Code item : codes){
            CodeReq codeReq = new CodeReq(item , "ahmed" , "ahmed");
            HttpUrlCon http = new HttpUrlCon();
            try {
                String var = http.excutePost(codeReq);
                JSONObject jsonObject = new JSONObject(var);
                codeDomainParsers.add(parseJsonObject.parseJsonObject(jsonObject)); // return codeDomainParser
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (CodeDomainParser code : codeDomainParsers){
            System.out.println("first code" + code.getName());
            saveCodeToFile.saveCodeToFile(code);
        }

    }
}
