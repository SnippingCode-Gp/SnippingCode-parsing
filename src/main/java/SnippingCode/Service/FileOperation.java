package SnippingCode.Service;

/**
 * Created by nasser on 22/06/15.
 */

import SnippingCode.Domain.Code;

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
 * <h1>File operaion manage all file operation with xml and json file</h1>
 * 
 * @author nasser
 */
public class FileOperation {

	public FileOperation() {
		pathLinux = "/home/" + user + "/.SC";
		pathLinuxCode = pathLinux + "/codes/";
		dirOperation();
	}

	private final String user = executeCommand("whoami");
	private final String pathLinux;
	private final String pathLinuxCode;
	private final String pathWin = "C:\\\\/SC";

	// init xml file
	private String OperatingSystemVersion() {
		return System.getProperty("os.name");
	}

	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();
		Process process;
		try {
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				return line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "error";
	}

	private boolean dirOperation() {
		File dir = null;
		File dirFiles = null;

		if (OperatingSystemVersion().equals("Linux")) {
			dir = new File(pathLinux);
			dirFiles = new File(pathLinux + "/codes");
		} else {
			dir = new File(pathWin);
			dirFiles = new File(pathWin + "/codes");
		}

		if (!dir.exists()) {
			dir.mkdir();
		}

		if (!dirFiles.exists()) {
			dirFiles.mkdir();
		}

		return true;
	}

	/**
	 * <p>
	 * save code object to file json
	 * </p>
	 * 
	 * @param code
	 *            Code object
	 * @return true if file save false other wise
	 */
	public boolean saveCodeToFile(Code code) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(pathLinuxCode + code.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put(Code.NAME, code.getName());
			obj.put(Code.TYPE, code.getType());
			obj.put(Code.DESCRIPTION, code.getDescription());
			obj.put(Code.CODE_BODY, code.getCode());
			JSONArray jsonArray = new JSONArray();

			for (String t : code.getTagSet())
				jsonArray.put(t);

			obj.put(Code.TAGS, jsonArray);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		out.println(obj.toString());
		out.close();
		return true;
	}

	/**
	 * <p>
	 * to generate xml file
	 * </p>
	 * 
	 * @param codes
	 *            is ArrayList of Code
	 * @return true when save false otherwise
	 */
	public boolean initXmlFile(ArrayList<Code> codes) throws JSONException {
		PrintWriter out = null;
		try {
			out = new PrintWriter(pathLinux + "/Codes.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		out.println("<Codes>");

		for (Code code : codes) {
			out.println("<code>");
			out.println("<name>" + code.getName() + "</name>");
			out.println("</code>");
		}
		out.println("</Codes>");
		out.close();
		return true;
	}

	/**
	 * @param path
	 *            string of path of file xml to parse code
	 * @return List of code which only name and version
	 */
	public List<Code> parseXmlFile() {
		String path = "/home/" + executeCommand("whoami") + "/.SC/Codes.xml";
		List codes = new ArrayList<Code>();

		try {

			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(Code.CODE);

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Code code = new Code();
					Element eElement = (Element) nNode;
					code.setName(eElement.getElementsByTagName(Code.NAME)
							.item(0).getTextContent());
					codes.add(code);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codes;
	}

	// read json file which is in local
	private String readFile(String path) {
		BufferedReader rd = null;
		StringBuilder response = new StringBuilder();
		String line;
		try {
			rd = new BufferedReader(new FileReader(path));
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	/**
	 * <p>
	 * parse Code from Json to Code object
	 * </p>
	 * 
	 * @param path
	 *            string of path of file to parse code
	 * @return Code object
	 */
	public Code parseCodeJsonFile(String path) {
		Code code = new Code();

		String name = null, description = null, codeBody = null, type = null;
		JSONArray jsonArray = null;

		try {
			JSONObject object = new JSONObject(readFile(path));

			jsonArray = object.getJSONArray(Code.TAGS);

			code.setName(object.getString(Code.NAME));
			code.setType(object.getString(Code.TYPE));
			code.setDescription(object.getString(Code.DESCRIPTION));
			code.setCode(object.getString(Code.CODE_BODY));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < jsonArray.length(); i++)
			try {
				code.addStringToSet(jsonArray.getString(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}

		return code;
	}
}
