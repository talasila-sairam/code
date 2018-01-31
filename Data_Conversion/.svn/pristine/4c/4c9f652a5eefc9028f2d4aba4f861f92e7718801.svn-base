/*package com.appstek.dc.util;

import java.io.*;
import java.net.URI;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPictureNonVisual;
import org.apache.poi.util.IOUtils;

public class AddImageExampleXLSX {
	public static void main(String str[])
	{
		try
		{
			 XSSFWorkbook my_workbook = new XSSFWorkbook();
	         XSSFSheet my_sheet = my_workbook.createSheet("MyLogo");        
	         
	         XSSFCreationHelper helper= my_workbook.getCreationHelper();
	         Now use createHyperlink method to get XSSFHyperlink 
	         XSSFHyperlink file_link=helper.createHyperlink(Hyperlink.LINK_FILE);
	         file_link.setAddress("file:///d://createdocument.docx");
	         Row row = my_sheet.createRow(1); 
	         Cell cell = row.createCell(2);
	       //  cell.setCellValue("Click to Open the file");            
	         cell.setHyperlink(file_link);
	          Read input PNG / JPG Image into FileInputStream Object
	         InputStream my_banner_image = new FileInputStream("D:\\index.jpg");
	      //   InputStream my_banner_image = new FileInputStream("D:\\AK_Sitka_06 QC Validation Results.docx");
	          Convert picture to be added into a byte array 
	         byte[] bytes = IOUtils.toByteArray(my_banner_image);
	          Add Picture to Workbook, Specify picture type as PNG and Get an Index 
	         int my_picture_id = my_workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
	          Close the InputStream. We are ready to attach the image to workbook now 
	         my_banner_image.close();                
	          Create the drawing container 
	         XSSFDrawing drawing = my_sheet.createDrawingPatriarch();
	          Create an anchor point 
	         XSSFClientAnchor my_anchor = new XSSFClientAnchor();
	          Define top left corner, and we can resize picture suitable from there 
	         my_anchor.setCol1(3);
	         my_anchor.setRow1(5);   
	         my_anchor.setDx1(1);
	         my_anchor.setDy1(1);
	          Invoke createPicture and pass the anchor point and ID 
	         XSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
	         
	        // drawing.getPackagePart().addExternalRelationship(target, relationshipType)
	         String url = "D:\\createdocument.docs";
	         InputStream is = new FileInputStream(new File(url));
	          PackageRelationship rel = ((XSSFDrawing)drawing).getPackagePart().addRelationship(new URI("http://poi.apache.org"),
               TargetMode.EXTERNAL, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/hyperlink");
	         ((XSSFDrawing)drawing).addRelation(rel.getId(),new POIXMLDocumentPart());
	         CTPictureNonVisual nvPicPr = my_picture.getCTPicture().getNvPicPr();
	         CTHyperlink hLinkClick = nvPicPr.getCNvPr().addNewHlinkClick();
	         hLinkClick.setId(rel.getId());
	          Call resize method, which resizes the image 
	         my_picture.resize();            
	          Write changes to the workbook 
	         FileOutputStream out = new FileOutputStream(new File("D:\\insert_png_xlsx_example.xlsx"));
	         my_workbook.write(out);
	         out.close();
	         System.out.print("Excel creation successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
*/