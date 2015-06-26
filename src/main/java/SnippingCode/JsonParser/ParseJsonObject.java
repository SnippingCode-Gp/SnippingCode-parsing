package SnippingCode.JsonParser;

import SnippingCode.Domain.Code;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseJsonObject {

	public Code parseJsonObject(JSONObject jsonObject) {
		if(jsonObject == null){
			return null;
		}
        Code code = new Code();
        try {
            code.setCode(jsonObject.getString(Code.CODE));
            code.setName(jsonObject.getString(Code.NAME));
            code.setType(jsonObject.getString(Code.TYPE));
            code.setDescription(jsonObject.getString(Code.DESCRIPTION));
            code.setTags(jsonObject.getString(Code.TAGS));
        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        return code;
	}

    public ArrayList<Code> parseJsonArray(JSONArray jsonArray) {
        if(jsonArray == null){
            return null ;
        }
        ArrayList<Code> codes = new ArrayList<Code>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonobj = jsonArray.getJSONObject(i);
                codes.add(parseJsonObject(jsonobj));
            } catch (JSONException e) {
                System.out.println(e.toString());
            }
        }
        return codes;
    }

}
