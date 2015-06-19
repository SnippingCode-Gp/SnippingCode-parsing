package SnippingCode;

import SnippingCode.Domain.Code;
import SnippingCode.JsonParser.ParseJsonObject;
import SnippingCode.ObjectRequest.CodeReq;
import SnippingCode.Parsing.Parsing;
import SnippingCode.Service.HttpUrlCon;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by nasser on 17/06/15.
 */
public class Main {

    static Parsing parsing;
    static ParseJsonObject parseJsonObject;

    public static void main(String [ ] args){
        parseJsonObject = new ParseJsonObject();

        // parsing xml
        String path="/home/nasser/Desktop/Project/SnippingCode-parsing/src/SnippingCode/test.xml";
        parsing = new Parsing(path);
        List<Code> codes = parsing.parse();

        // get from server
        for(Code item : codes){

            CodeReq codeReq = new CodeReq(item , "ahmed" , "ahmed");
            HttpUrlCon http = new HttpUrlCon();
            System.out.println("\nTesting 2 - Send Http POST request");
            try {
                String var = http.excutePost(codeReq);
                JSONObject jsonObject = new JSONObject(var);
                parseJsonObject.parseJsonObject(jsonObject).printAll(); // return codeDomainParser
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Accepted");

        }

    }
}
