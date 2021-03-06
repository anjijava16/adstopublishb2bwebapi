package com.atp.b2bweb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * This utility class implements method for uploading a whole directory from
 * local computer to a remote FTP server, based on Apache Commons Net library.
 *
 * @author www.codejava.net
 *
 */
public class FTPUtil {

	/**
	 * Upload a whole directory (including its nested sub directories and files)
	 * to a FTP server.
	 *
	 * @param ftpClient
	 *            an instance of org.apache.commons.net.ftp.FTPClient class.
	 * @param remoteDirPath
	 *            Path of the destination directory on the server.
	 * @param localParentDir
	 *            Path of the local directory being uploaded.
	 * @param remoteParentDir
	 *            Path of the parent directory of the current directory on the
	 *            server (used by recursive calls).
	 * @throws IOException
	 *             if any network or IO error occurred.
	 */
	public static void uploadDirectory(FTPClient ftpClient,	String remoteDirPath, String localParentDir, String remoteParentDir) throws IOException {

		File localDir = new File(localParentDir);
		File[] subFiles = localDir.listFiles();
		System.out.println(subFiles.length);
		if (subFiles != null && subFiles.length > 0) {
			for (File item : subFiles) {
				String remoteFilePath = remoteDirPath + "/" + remoteParentDir + "/" + item.getName();
				System.out.println("remoteFilePath "+remoteFilePath);
				if (remoteParentDir.equals("")) {
					remoteFilePath = remoteDirPath + "/chethan" + item.getName();
					System.out.println("remoteFilePath111 "+remoteFilePath);
				}

				if (item.isFile()) {
					// upload the file
					String localFilePath = item.getAbsolutePath();
					System.out.println("About to upload the file: " + localFilePath);
					boolean uploaded = uploadSingleFile(ftpClient,	localFilePath, remoteFilePath);
					if (uploaded) {
						System.out.println("UPLOADED a file to: "+ remoteFilePath);
					} else {
						System.out.println("COULD NOT upload the file: "+ localFilePath);
					}
				} else {
					// create directory on the server
					boolean created = ftpClient.makeDirectory(remoteFilePath);
					if (created) {
						System.out.println("CREATED the directory: "+ remoteFilePath);
					} else {
						System.out.println("COULD NOT create the directory: "+ remoteFilePath);
					}

					// upload the sub directory
					String parent = remoteParentDir + "/" + item.getName();
					if (remoteParentDir.equals("")) {
						parent = item.getName();
					}

					localParentDir = item.getAbsolutePath();
					uploadDirectory(ftpClient, remoteDirPath, localParentDir, parent);
				}
			}
		}
	}

	/**
	 * Upload a single file to the FTP server.
	 *
	 * @param ftpClient
	 *            an instance of org.apache.commons.net.ftp.FTPClient class.
	 * @param localFilePath
	 *            Path of the file on local computer
	 * @param remoteFilePath
	 *            Path of the file on remote the server
	 * @return true if the file was uploaded successfully, false otherwise
	 * @throws IOException
	 *             if any network or IO error occurred.
	 */
	public static boolean uploadSingleFile(FTPClient ftpClient,	String localFilePath, String remoteFilePath) throws IOException {
		System.out.println(ftpClient+"  "+localFilePath +"   "+remoteFilePath);
		File localFile = new File(localFilePath);
		boolean aaa = false;
		InputStream inputStream = new FileInputStream(localFile);
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			aaa =  ftpClient.storeFile(remoteFilePath, inputStream);
			
		} finally {
			inputStream.close();
		}
		return aaa;
	
	}
}