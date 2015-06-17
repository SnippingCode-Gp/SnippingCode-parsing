package SnippingCode;

import SnippingCode.Domain.Code;
import SnippingCode.Parsing.Parsing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nasser on 17/06/15.
 */
public class Main {
    static Parsing parsing;

    public static void main(String [ ] args){
        String path="/home/nasser/Desktop/Project/SnippingCode-parsing/src/SnippingCode/test.xml";
        parsing = new Parsing(path);
        List<Code> codes = parsing.parse();
        for(Code item : codes){
            System.out.println("name : " + item.getName());
            System.out.println("version : " + item.getVersion());
        }
    }
}
