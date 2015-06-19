package SnippingCode;

import SnippingCode.Domain.Code;
import SnippingCode.ObjectRequest.CodeReq;
import SnippingCode.Parsing.Parsing;
import SnippingCode.Service.GetCodeByName;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nasser on 17/06/15.
 */
public class Main {

    static Parsing parsing;
    static GetCodeByName getCode;

    public static void main(String [ ] args){
        getCode = new GetCodeByName();
        System.out.println("Main work");
        String path="/home/nasser/Desktop/Project/SnippingCode-parsing/src/SnippingCode/test.xml";
        parsing = new Parsing(path);
        List<Code> codes = parsing.parse();
        for(Code item : codes){
            try {
                CodeReq codeReq = new CodeReq(item , "ahmed" , "ahmed");
                getCode.getCode(codeReq);
                System.out.println("Accepted");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
