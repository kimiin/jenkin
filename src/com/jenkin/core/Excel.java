package com.jenkin.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	private static final int JENKIN_BUILD_PATH_COLUMN_INDEX = 0;
	private static final int LOCAL_PATH_COLUMN_INDEX = 1;

	public static List<String> readFile(String filePath) throws IOException {

		try {
			// String [] arrColumns = new String[2];
			List<String> listReport = new ArrayList<String>();
			FileInputStream fis = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Row row;
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				String s = "";
				row = rowIterator.next();
				if (row.getRowNum() == 0)// ignore first row
					continue;
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					if (currentCell.getColumnIndex() == JENKIN_BUILD_PATH_COLUMN_INDEX)
						s = currentCell.getStringCellValue() + "~";
					else if (currentCell.getColumnIndex() == LOCAL_PATH_COLUMN_INDEX)
						s += currentCell.getStringCellValue();
					else
						break;
				}
				listReport.add(s);
			}
			return listReport;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
