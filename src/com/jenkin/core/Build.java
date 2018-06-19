package com.jenkin.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Build {

	private String buildName;
	private String downloadTo;
	private DownloadStatus download;
	private ExecuteStatus execute;
	private String url;

	public Build(String buildName, String downloadTo) {
		this.buildName = buildName;
		this.url = Constants.URL + buildName + "-SYN/lastBuild/artifact/";
		this.downloadTo = downloadTo;
		this.download = new DownloadStatus();
		this.execute = new ExecuteStatus();
	}

	public String getDownloadTo() {
		return downloadTo;
	}

	public void setDownloadTo(String downloadTo) {

		this.downloadTo = FileHelper.getFolderPath(downloadTo);
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getReleasedStatus() {
		if (download.isReleased()) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public String getDownloadStatus() {
		if (download.isDownloaded()) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public String getExecuteStatus() {
		if (execute.isExecuted()) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public String getBadBuildStatus() {
		if (execute.isBadBuild()) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public String getOutputPath() {
		return execute.getOutputPath();
	}

	public void startDownload() throws IOException {
		
		if(download.checkRelease(this.url, this.downloadTo)){
			download.download(this.url, this.downloadTo);
		}
		
		
	}

	public void startExecute() {
		// execute.setExecuted(download.getLocalDownloadPath());
		execute.setBadBuild();
		execute.setOutputPath();
	}

}
