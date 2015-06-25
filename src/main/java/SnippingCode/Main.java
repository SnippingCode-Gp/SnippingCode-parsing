package SnippingCode;

import SnippingCode.Domain.Code;
import SnippingCode.Domain.CodeDomainParser;
import SnippingCode.JsonParser.ParseJsonObject;
import SnippingCode.ObjectRequest.CodeReq;
import SnippingCode.Parsing.CodeParsing;
import SnippingCode.Parsing.CodeRetreiveParsing;
import SnippingCode.Service.HttpUrlCon;
import SnippingCode.Service.SaveCodeToFile;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by nasser on 17/06/15.
 */
public class Main {
    static CodeRetreiveParsing codeRetreiveParsing;
    static ParseJsonObject parseJsonObject;
    static SaveCodeToFile saveCodeToFile;
    static ArrayList<CodeDomainParser> codeDomainParsers;
    static CodeParsing codeParsing;

    public static void main(String [ ] args){

        parseJsonObject = new ParseJsonObject();
        saveCodeToFile = new SaveCodeToFile();

        codeDomainParsers = new ArrayList<CodeDomainParser>();

        // codeRetreiveParsing xml
        String pathTest = "/home/nasser/Desktop/Project/SnippingCode-Parsing/test.xml";
        String pathCode = "/home/nasser/Desktop/Project/SnippingCode-Parsing/ahmed.xml";

        codeRetreiveParsing = new CodeRetreiveParsing(pathTest);
        List<Code> codes = codeRetreiveParsing.parse();
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
            saveCodeToFile.saveCodeToFile(code);
        }

        codeParsing = new CodeParsing(pathCode);
        codeParsing.parse().printAll();
    }
}
