package SnippingCode.Domain;

/**
 * Created by nasser on 17/06/15.
 */
public class Code {
    String name;
    String version;

    public static final String CODE = "code";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";
    public static final String TAGS = "tags";
    public static final String TAG = "tag";
    public static final String CODE_BODY = "codeBody";
    public static final String VERSION = "version";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}