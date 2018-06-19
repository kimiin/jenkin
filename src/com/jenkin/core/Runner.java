package com.jenkin.core;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Runner {

	public static void main(String[] args) throws IOException {
		
		
			Report report =  new Report("ExcelPath");;
		    for(String str: Excel.readFile("/Users/minhdo/Downloads/DataTemplate.xlsx")){
		    	Report reportTemp = new Report("ExcelPath");
			    	String[] temp=str.split("~");
			    	reportTemp.add(new Build(temp[0], temp[1]));
			    	reportTemp.downloadBuild();
			    	reportTemp.executeBuild();
			    	reportTemp.writeToExecel("");
			    report = reportTemp;
				}
		    
		    
		    report.getArrBuilds();
	}
	
}
