package com.jenkin.core;

public class ExecuteStatus {
	
	private boolean isExecuted;
	private boolean isBadBuild;
	private String outputPath;
	
	public ExecuteStatus() {
		
	}
	public boolean isExecuted() {
		return isExecuted;
	}
	public void setExecuted(String localBuildPath) {
		//execute and return 
		//this.isExecuted = isExecuted;
		System.out.println(localBuildPath + "is Executed");
	}
	public boolean isBadBuild() {
		return isBadBuild;
	}
	public void setBadBuild() {
		//Check bad build and return the result
		System.out.println("the build is BadBuild");
	}
	public String getOutputPath() {
		return outputPath;
	}
	public void setOutputPath() {
		//this.outputPath = outputPath;
		System.out.println("Output here");
	}
	
	
	
}
