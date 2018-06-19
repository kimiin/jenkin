package com.jenkin.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;

public class FileHelper {

	public static String getFolderPath(String folderPath) {
		Path path = Paths.get(folderPath.trim());

		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path.toString();

	}

	public static String getLatestModifiedFile(String filePath) throws IOException {
		Path dir = Paths.get(filePath);

		Optional<Path> latestModifiedFileFilePath = Files.list(dir).filter(f -> !Files.isDirectory(f))
				.max(Comparator.comparingLong(f -> f.toFile().lastModified()));

		if (latestModifiedFileFilePath.isPresent()) {
			return latestModifiedFileFilePath.get().getFileName().toString();
		} else {
			return "";
		}

	}

	public static String getLatestJenkinBuild(String urlPath) throws IOException {
		URL url = new URL(urlPath);
		String line = "";

		try (final BufferedReader br = new BufferedReader(
				new InputStreamReader(url.openStream(), Charset.forName("UTF-8")))) {

			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				if (line.contains("unsigned")) {
					line = line.substring(line.lastIndexOf("</a> <a href=\"") + 14, line.lastIndexOf("/*view*/"));
					break;
				}
			}
		}

		return line;
	}
	
	public static int getNumberOfBuild(String buildName) {
		if(buildName.equals(""))
			return 0;
		return Integer.parseInt(buildName.substring(buildName.indexOf("SYN-") + 4, buildName.indexOf(".apk")));
	}

	public static boolean downloadBuild(String urlPath, String folderPath) throws IOException{
		
		boolean isCompleted = false;
		URL url = new URL(urlPath);
		URLConnection connection = url.openConnection();
		connection.connect();

		long fileLenth = connection.getContentLength();
		int completed = 0;

		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		FileOutputStream fis = new FileOutputStream(folderPath);

		byte[] buffer = new byte[1024];
		int count = 0;

		while ((count = bis.read(buffer, 0, 1024)) != -1) {
			completed += count;
			//System.out.println(latestJenkinsBuildName + " " + Math.round((((float) completed / (float) fileLenth) * 100f)) + " %");
			fis.write(buffer, 0, count);
		}

		FileChannel fc = fis.getChannel();
		if (fc.size() >= fileLenth) {
			//report.setLocalBuildPath(folderPath);
			isCompleted = true;
		}

		fis.close();
		bis.close();
		return isCompleted;
	}
	
	
}
