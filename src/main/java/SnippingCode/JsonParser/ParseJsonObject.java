package SnippingCode.JsonParser;

import SnippingCode.Domain.Code;
import SnippingCode.Domain.CodeDomainParser;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJsonObject {

	public CodeDomainParser parseJsonObject(JSONObject jsonObject) {
		if(jsonObject == null){
			return null;
		}
        CodeDomainParser codeDomainParser = new CodeDomainParser();
        try {
            codeDomainParser.setCode(jsonObject.getString(Code.CODE));
            codeDomainParser.setName(jsonObject.getString(Code.NAME));
            codeDomainParser.setType(jsonObject.getString(Code.TYPE));
            codeDomainParser.setDescription(jsonObject.getString(Code.DESCRIPTION));
            codeDomainParser.setTags(jsonObject.getString(Code.TAGS));
        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        return codeDomainParser;
	}
}
