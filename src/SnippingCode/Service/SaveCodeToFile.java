package SnippingCode.Service;

/**
 * Created by nasser on 22/06/15.
 */

import SnippingCode.Domain.CodeDomainParser;

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
    public static String tag = "tag";
    public static String description = "description";
    public static String codeBody = "codeBody";

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
            out = new PrintWriter(pathLinuxCode + codeDomainParser.getName() + ".xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("start xml");
        out.println("<"+code+">");
        out.println("<"+name+">" + codeDomainParser.getName() + "</"+name+">");
        out.println("<"+version+">"+codeDomainParser.getVersion()+"</"+version+">");
        out.println("<"+tags+">");
        for(String t : codeDomainParser.getTagSet())
            out.println("<"+tag+">"+t+"</"+tag+">");
        out.println("</"+tags+">");
        out.println("<"+description+">"+codeDomainParser.getDescription()+"</"+description+">");
        out.println("<"+codeBody+">"+codeDomainParser.getCode()+"</"+codeBody+">");
        out.println("</"+code+">");

        out.close();
        return true;
    }

}
