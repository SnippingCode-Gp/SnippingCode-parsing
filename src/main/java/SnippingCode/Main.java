package SnippingCode;

import SnippingCode.Domain.Code;
import SnippingCode.JsonParser.ParseJsonObject;
import SnippingCode.ObjectRequest.CodeReq;
import SnippingCode.Service.CodesHttpRequest;
import SnippingCode.Service.FileOperation;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nasser on 17/06/15.
 */
public class Main {
    static ParseJsonObject parseJsonObject;
    static FileOperation fileOperation;
    static ArrayList<Code> codes;
    static CodesHttpRequest codesHttpRequest;

    public static void main(String [ ] args){
        parseJsonObject = new ParseJsonObject();
        fileOperation = new FileOperation();
        codes = new ArrayList<Code>();

//        get user code from server and save to test file xml
        codesHttpRequest = new CodesHttpRequest();
        try {
            fileOperation.initXmlFile(codesHttpRequest.getAllCode("ahmed", "ahmed"));
        } catch (IOException e) {
            e.printStackTrace();
        }



        // codeRetreiveParsing xml
        String pathTest = "/home/nasser/Desktop/Project/SnippingCode-Parsing/test.xml";
        String pathCode = "/home/nasser/Desktop/Project/SnippingCode-Parsing/ahmed.xml";

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

        fileOperation.parseCodeJsonFile(pathCode).printAll();
    }
}
