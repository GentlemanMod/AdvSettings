package com.michellgaby.advsettings;

import java.io.File;
import java.io.PrintWriter;

import android.app.ProgressDialog;
import android.util.Log;

public class AdvUtils {

	private static String settingsPath = "/data/data/com.michellgaby.advsettings/";

    public static boolean isFileExists(String name)
    {
    	String fullname = settingsPath + "/" + name;
    	File file = new File(fullname);
    	return file.exists();
    }
    public static boolean isFileNotExists(String name)
    {
    	String fullname = settingsPath + "/" + name;
    	File file = new File(fullname);
    	return !file.exists();
    }
    public static void runCmd(String cmd)
    {
    	try {
    		boolean root = true;
    		if (root) {
    			Process p = Runtime.getRuntime().exec("su");
    			PrintWriter pw = new PrintWriter(p.getOutputStream());
    			pw.println(cmd);
    			pw.flush();
    			pw.close();
    			p.waitFor();
    		} else {
	    		Process p = Runtime.getRuntime().exec(cmd);
	    		p.waitFor();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
