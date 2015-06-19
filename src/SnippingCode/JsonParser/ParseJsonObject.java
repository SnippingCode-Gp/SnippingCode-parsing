package SnippingCode.JsonParser;

import SnippingCode.Domain.CodeDomainParser;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJsonObject {
	public String parseToStringType(JSONObject json) {
		try {
			String string = json.getString("string");
			return string;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	public CodeDomainParser parseJsonObject(JSONObject jsonObject) {
		if(jsonObject == null){
			return null;
		}
        CodeDomainParser codeDomainParser = new CodeDomainParser();
        try {
            codeDomainParser.setCode(jsonObject.getString("code"));
            codeDomainParser.setName(jsonObject.getString("name"));
            codeDomainParser.setType(jsonObject.getString("type"));
            codeDomainParser.setDescription(jsonObject.getString("description"));
        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        return codeDomainParser;
	}
}
