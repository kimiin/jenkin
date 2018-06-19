package com.jenkin.core;

import java.io.IOException;

public class Report {

	private String excelPath;
	private Build[] arrBuilds;
	private int size = 0;

	public Report(String excelPath) {
		this.arrBuilds = new Build[1];
		this.excelPath = excelPath;
	}
	
	

	public Build[] getArrBuilds() {
		return arrBuilds;
	}



	public void add(Build b) {

		addBuild(b);
	}

	private void addBuild(Build item) {
		if (size >= arrBuilds.length) {
			Build[] temp = new Build[arrBuilds.length + 1];
			System.arraycopy(arrBuilds, 0, temp, 0, arrBuilds.length);
			this.arrBuilds = temp;
		}
		arrBuilds[size++] = item;
	}

	public void downloadBuild() throws IOException {
		for (Build build : arrBuilds) {
			build.startDownload();
		}
	}

	public void executeBuild() {
		for (Build build : arrBuilds) {
			build.startExecute();
		}
	}
	
	

	public void writeToExecel(String path) {

	}

}
