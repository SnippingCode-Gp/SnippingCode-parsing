package SnippingCode.Parsing;

import SnippingCode.Domain.Code;
import SnippingCode.Domain.CodeDomainParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
* Created by nasser on 24/06/15.
*/
public class CodeParsing {
    private String path;
    private CodeDomainParser codeDomainParser;

    public CodeParsing(String path){
        this.path = path;
    }

    private String readFile(){
        BufferedReader rd = null;
        StringBuilder response = new StringBuilder();
        String line;
        try {
            rd = new BufferedReader(new FileReader(path));
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    public CodeDomainParser parse() {
        codeDomainParser = new CodeDomainParser();

        String name = null, description = null, codeBody = null, type = null;
        JSONArray jsonArray = null;

        try {
            JSONObject object = new JSONObject(readFile());
            name = object.getString("name");
            description = object.getString("description");
            codeBody = object.getString("codeBody");
            type = object.getString("type");
            jsonArray = object.getJSONArray("tags");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(jsonArray.length());

        for(int i = 0 ; i < jsonArray.length() ; i++)
            try {
                codeDomainParser.addStringToSet(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        codeDomainParser.setName(name);
        codeDomainParser.setType(type);
        codeDomainParser.setDescription(description);
        codeDomainParser.setCode(codeBody);

        return codeDomainParser;
    }

}
