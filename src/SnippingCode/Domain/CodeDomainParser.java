package SnippingCode.Domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CodeDomainParser implements Serializable {

	public CodeDomainParser() {

	}

	private String name;
	private String type;
	private String code;
    private String description;
    private String tags;
    private Set<String> tagSet;

    public Set<String> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<String> tagSet) {
        this.tagSet = tagSet;
    }

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

    public void setTags(String tags) {
        this.tags = tags;
        parseStringTagsToSet();
    }

    public void printAll() {
        System.out.println("*************** code ***************");
        System.out.println("name{" + name + "} , type{" + type + "}");
        System.out.println("description{" + description + "}");
        System.out.println(code);
        System.out.println("tags :: " + tags);
        System.out.println("*************** code ***************");
    }
}
