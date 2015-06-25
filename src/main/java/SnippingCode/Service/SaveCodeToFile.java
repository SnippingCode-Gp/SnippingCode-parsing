package SnippingCode.Service;

/**
 * Created by nasser on 22/06/15.
 */

import SnippingCode.Domain.CodeDomainParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class SaveCodeToFile {

    public SaveCodeToFile(){
        System.out.println(user);
        pathLinux = "/home/"+user+"/.SC";
        pathLinuxCode = pathLinux + "/codes/";
        dirOperation();
    }

    private final String user = executeCommand("whoami");
    public static String pathLinux;
    public static String pathLinuxCode;
    public static String pathWin = "C:\\\\/SC";

    public static String code = "code";
    public static String name = "name";
    public static String version = "version";
    public static String tags = "tags";
    public static String description = "description";
    public static String codeBody = "codeBody";
    public static String type = "type";

    private String OperatingSystemVersion(){
        return System.getProperty("os.name");
    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                return line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    private boolean dirOperation(){
        File dir = null;
        File dirFiles = null;

        if(OperatingSystemVersion().equals("Linux")){
            System.out.println("linux path");
            dir = new File(pathLinux);
            dirFiles = new File(pathLinux + "/codes");
        }else {
            dir = new File(pathWin);
            dirFiles = new File(pathWin + "/codes");
        }

        if(!dir.exists()){
            try {
                dir.mkdir();
            }catch (SecurityException e){
                e.printStackTrace();
            }
        }

        if(!dirFiles.exists()){
            try {
                dirFiles.mkdir();
            }catch (SecurityException e){
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean saveCodeToFile(CodeDomainParser codeDomainParser){
        PrintWriter out = null;
        try {
            out = new PrintWriter(pathLinuxCode + codeDomainParser.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put(name , codeDomainParser.getName());
            obj.put(type , codeDomainParser.getType());
            obj.put(version , codeDomainParser.getVersion());
            obj.put(description , codeDomainParser.getDescription());
            obj.put(codeBody , codeDomainParser.getCode());
            JSONArray jsonArray = new JSONArray();

//            System.out.println(codeDomainParser.getTagSet().size());
            for(String t : codeDomainParser.getTagSet())
                jsonArray.put(t);

            obj.put(tags , jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        out.println(obj.toString());
        out.close();
        return true;
    }

}
