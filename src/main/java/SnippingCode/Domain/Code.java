package SnippingCode.Domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nasser
 * <p> Code Domain </p>
 */
public class Code implements Serializable {

	public Code() {

	}
    public static final String CODE = "code";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";
    public static final String TAGS = "tags";
    public static final String TAG = "tag";
    public static final String CODE_BODY = "codeBody";

	private String name;
	private String type;
	private String code;
    private String description;
    private String tags;
    private Set<String> tagSet;

    public Set<String> getTagSet() {
        if(tags.equals("") || tags.equals(",")) return null;
        else if (tagSet == null) parseStringTagsToSet();
        return tagSet;
    }

    public void setTags(String tags) {
        this.tags = tags;
        parseStringTagsToSet();
    }

    public void setTagSet(Set<String> tagSet) {
        this.tagSet = tagSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    public String getTags() {
        return tags;
    }

    // private function ::
    private void parseStringTagsToSet(){
        tagSet = new HashSet<String>();
        if(tags.length() == 1){
            return;
        }
        int i = 0 , j;
        StringBuilder string  = new StringBuilder(tags);
        while(!string.equals(",") && !string.toString().isEmpty()){
            String tmp = string.substring(0 , string.indexOf(","));
            string.delete(0 , string.indexOf(",")+1);
            tagSet.add(tmp);
        }
    }

    // public function ::
    public void printAll() {
        System.out.println("*************** code ***************");
        System.out.println("name{" + name + "} , type{" + type + "}");
        System.out.println("description{" + description + "}");
        System.out.println(code);
        System.out.println("tags :: " + tags);
        System.out.println("*************** code ***************");
    }

    /**
     *
     * @param tagsString which is string of tags
     */
    public void addStringToSet(String tagsString){
        if(tagSet == null){
            tagSet = new HashSet<String>();
            tags = "";
        }
        tagSet.add(tagsString);
        tags = tags + tagsString + ",";
    }
}
