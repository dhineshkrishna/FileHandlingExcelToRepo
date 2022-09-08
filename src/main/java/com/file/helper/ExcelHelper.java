package com.file.helper;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.file.model.Userexcel;

public class ExcelHelper {
	  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };
	  static String SHEET = "Tuto";

	  public static boolean hasExcelFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static ByteArrayInputStream tutorialsToExcel(List<Userexcel> tutorials) {

	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	      Sheet sheet = workbook.createSheet(SHEET);

	      // Header
	      Row headerRow = sheet.createRow(0);

	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	      }

	      int rowIdx = 1;
	      for (Userexcel tutorial : tutorials) {
	        Row row = sheet.createRow(rowIdx++);

	        row.createCell(0).setCellValue(tutorial.getId());
	        row.createCell(1).setCellValue(tutorial.getName());
	        row.createCell(2).setCellValue(tutorial.getAddress());
	        row.createCell(3).setCellValue(tutorial.isValue());
	      }

	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    }
	  }

	  public static List<Userexcel> excelToTutorials(InputStream is) {
	    try {
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheet(SHEET);
	      Iterator<Row> rows = sheet.iterator();

	      List<Userexcel> tutorials = new ArrayList<Userexcel>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        Userexcel tutorial = new Userexcel();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();

	          switch (cellIdx) {
	          case 0:
	            tutorial.setId((long) currentCell.getNumericCellValue());
	            break;

	          case 1:
	            tutorial.setName(currentCell.getStringCellValue());
	            break;

	          case 2:
	            tutorial.setAddress(currentCell.getStringCellValue());
	            break;

	          case 3:
	            tutorial.setValue(currentCell.getBooleanCellValue());
	            break;

	          default:
	            break;
	          }

	          cellIdx++;
	        }

	        tutorials.add(tutorial);
	      }

	      workbook.close();

	      return tutorials;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }
	}