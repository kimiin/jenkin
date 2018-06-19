package com.jenkin.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;

public class DownloadStatus {

	private boolean isReleased;
	private boolean isDownloaded;

	public DownloadStatus() {
		this.isReleased = false;
		this.isDownloaded = false;
	}

	public boolean isReleased() {
		return isReleased;
	}

	// public void setReleased(boolean isReleased) {
	// this.isReleased = isReleased;
	// }

	public void setDownloaded(boolean isDownloaded) {
		this.isDownloaded = isDownloaded;
	}

	public boolean isDownloaded() {
		return isDownloaded;
	}

	public boolean checkRelease(String url, String filePath) throws IOException {
		
		int localBuild = FileHelper.getNumberOfBuild(FileHelper.getLatestModifiedFile(filePath));
		int newBuild = FileHelper.getNumberOfBuild(FileHelper.getLatestJenkinBuild(url));

		if (localBuild < newBuild) {
			this.isReleased = true;
		}
		System.out.println("The build is released " + this.isReleased);
		System.out.println("The local build :" + localBuild);
		System.out.println("The new build :" + newBuild);
		return this.isReleased;

	}

	public void download(String url, String filePath) throws IOException {
		if(FileHelper.downloadBuild(url, filePath)){
			this.isDownloaded=true;
		}
	}
}
