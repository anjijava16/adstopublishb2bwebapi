package com.atp.b2bweb.util;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpFileUpdate {
     
     public static void sadsa( ){
    	 //String server = "www.codejava.net";
         int port = 21;
         String user = "saptalabs"; 
         String password = "sapta123"; 
         String server = "192.168.1.24";
	     FTPClient ftpClient = new FTPClient();
	
	     try {
	    	 System.out.println("ftp");
	         // connect and login to the server
	         ftpClient.connect(server, port);
	         
	         System.out.println(ftpClient.getReplyCode()+"replay ---------"+FTPReply.isPositiveCompletion(ftpClient.getReplyCode()));
	         ftpClient.login(user, password);
	
	         // use local passive mode to pass firewall
	         ftpClient.enterLocalPassiveMode();
	
	         System.out.println("Connected");
	
	         String remoteDirPath = "/";
	         String localDirPath = "C:/Users/SAPTALABS/Desktop/localpath";
	
	         FTPUtil.uploadDirectory(ftpClient, remoteDirPath, localDirPath, "");
	
	         // log out and disconnect from the server
	         ftpClient.logout();
	         ftpClient.disconnect();
	
	         System.out.println("Disconnected");
	     } catch (IOException ex) {
	         ex.printStackTrace();
	     }
     }
}
