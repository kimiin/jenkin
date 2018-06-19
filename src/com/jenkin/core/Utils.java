package com.jenkin.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;

public class Utils {
	
	public static boolean downloadUsingStream(String urlStr, String file) throws IOException {

		boolean isComplete = false;
		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		connection.connect();

		long fileLenth = connection.getContentLength();
		int completed = 0;

		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		FileOutputStream fis = new FileOutputStream(file);

		byte[] buffer = new byte[1024];
		int count = 0;

		while ((count = bis.read(buffer, 0, 1024)) != -1) {
			completed += count;
			System.out.println(Math.round((((float) completed / (float) fileLenth) * 100f)) + " %");
			fis.write(buffer, 0, count);
		}

		FileChannel fc = fis.getChannel();
		if (fc.size() >= fileLenth) {
			isComplete = true;
		}

		fis.close();
		bis.close();
		return isComplete;
	}

}
