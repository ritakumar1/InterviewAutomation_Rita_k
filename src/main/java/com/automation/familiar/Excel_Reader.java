package com.automation.familiar;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
	
	public String path;
	XSSFWorkbook Workbook;
	;
	XSSFRow row;
	XSSFCell cell;
	
	public Excel_Reader(String Path)
	{
	 this.path = Path;	
	 FileInputStream FInSt;
	 try
		{
			FInSt = new FileInputStream(path);
			 Workbook = new XSSFWorkbook(FInSt);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 
	}
	
	@SuppressWarnings({ "static-access"})
	public String getCellData(String SheetName, String Colname, int RowNum)
	{
		try
		{
			int col_Num = 0;
			int index =  Workbook.getSheetIndex(SheetName);			
			XSSFSheet sheet = Workbook.getSheetAt(index);
			XSSFRow row =sheet.getRow(0);
		
			for(int i=0; i<row.getLastCellNum();i++)
			{
				if(row.getCell(i).getStringCellValue().equals(Colname))
				{
					col_Num=i;
				}
			}
			row=sheet.getRow(RowNum-1);
			cell=row.getCell(col_Num);
			
			if(cell.getCellType() == cell.getCellType().STRING)
			{
				return cell.getStringCellValue();
			}
			else if(cell.getCellType() ==cell.getCellType().NUMERIC)
			{
				return String.valueOf(cell.getNumericCellValue());
			}
			else if (cell.getCellType() == cell.getCellType().BOOLEAN)
			{
				return String.valueOf(cell.getBooleanCellValue());
			}
			else if(cell.getCellType() == cell.getCellType().BLANK)
			{
				return "";
			}
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	
	}
	



