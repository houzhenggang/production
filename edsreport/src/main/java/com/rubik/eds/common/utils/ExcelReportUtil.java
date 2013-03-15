/**
 * 
 */
package com.rubik.eds.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author Administrator
 *
 */
public class ExcelReportUtil {

	public void CreateExcel(javax.servlet.http.HttpServletResponse response){
		
		try{
			 // 打开文件
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
			String filename = "Z_STJC__C5_53644_"+format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			
			ServletOutputStream out = response.getOutputStream();
            WritableWorkbook book = Workbook.createWorkbook(out);
            // 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("report1", 0);
            
            //合并单元格
            sheet.mergeCells(4, 0, 8, 0);
            sheet.mergeCells(1, 2, 4, 2);
            sheet.mergeCells(5, 2, 6, 2);
            sheet.mergeCells(7, 2, 8, 2);
            sheet.mergeCells(10, 2, 14, 2);
            sheet.mergeCells(1, 3, 2, 3);
            sheet.mergeCells(3, 3, 4, 3);
            
            for(int i=5; i< 15 ;i++){
            	sheet.mergeCells(i, 3, i, 4);
            }
            
            sheet.mergeCells(0, 3, 0, 4);
            
            //设置高度
            sheet.setRowView(0, 600);
            sheet.setRowView(2, 600);
            sheet.setRowView(3, 450);
            sheet.setRowView(4, 450);
            
            //设置宽度
            sheet.setColumnView(0, 4);
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 15);
            sheet.setColumnView(3, 14);
            sheet.setColumnView(4, 14);
            
            WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.NO_BOLD,false); 
            WritableCellFormat wcf = new WritableCellFormat(wf);
            //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            
            
            Label label = new Label(4,0, "水体检查数据报表", wcf);
            sheet.addCell(label);
            
            WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
            WritableCellFormat wcf2 = new WritableCellFormat(wf2);
            //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
            wcf2.setAlignment(Alignment.LEFT);
            wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
            
            Label label2 = new Label(1,2, "台站名称:乌审旗生态与农业气象监测站", wcf2);
            sheet.addCell(label2);
            
            Label label3 = new Label(7,2, "水体名称：巴图湾 ", wcf2);
            sheet.addCell(label3);
            
            Label label4 = new Label(10,2, "监测日期:2007年8月18日--2007年8月20日", wcf2);
            sheet.addCell(label4);
            
            
            //
            WritableFont wf3 = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD,false); 
            WritableCellFormat wcf3 = new WritableCellFormat(wf3);
            wcf3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
            wcf3.setAlignment(Alignment.CENTRE);
            wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
            
            wcf3.setBorder(jxl.format.Border.TOP,jxl.format.BorderLineStyle.MEDIUM);
            wcf3.setWrap(true);
            Label label5 = new Label(0,3, "序列号", wcf3);
            sheet.addCell(label5);
            Label label6 = new Label(1,3, "取样点位置坐标", wcf3);
            sheet.addCell(label6);
            Label label7 = new Label(3,3, "拐点坐标", wcf3);
            sheet.addCell(label7);
            Label label8 = new Label(5,3, "水体面积", wcf3);
            sheet.addCell(label8);
            Label label9 = new Label(6,3, "水位", wcf3);
            sheet.addCell(label9);
            Label label10 = new Label(7,3, "透明度", wcf3);
            sheet.addCell(label10);
            Label label11 = new Label(8,3, "水色", wcf3);
            sheet.addCell(label11);
            Label label12 = new Label(9,3, "水体温度", wcf3);
            sheet.addCell(label12);
            Label label13 = new Label(10,3, "PH值", wcf3);
            sheet.addCell(label13);
            Label label14 = new Label(11,3, "水体全盐含量", wcf3);
            sheet.addCell(label14);
            Label label15 = new Label(12,3, "氯化物含量", wcf3);
            sheet.addCell(label15);
            Label label16 = new Label(13,3, "硫化物含量", wcf3);
            sheet.addCell(label16);
            Label label17 = new Label(14,3, "备注", wcf3);
            sheet.addCell(label17);
            
            
            //
            WritableFont wf4 = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD,false); 
            WritableCellFormat wcf4 = new WritableCellFormat(wf4);
            wcf4.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
            wcf4.setAlignment(Alignment.CENTRE);
            wcf4.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf4.setWrap(true);
            
            Label label18 = new Label(1,4, "经度", wcf4);
            sheet.addCell(label18);
            Label label19 = new Label(2,4, "纬度", wcf4);
            sheet.addCell(label19);
            Label label20 = new Label(3,4, "经度", wcf4);
            sheet.addCell(label20);
            Label label21 = new Label(4,4, "纬度", wcf4);
            sheet.addCell(label21);
            //一下报表数据填充
            WritableFont wf5 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
            WritableCellFormat wcf5 = new WritableCellFormat(wf5);
            wcf5.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
            wcf5.setAlignment(Alignment.LEFT);
            wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
            
            WritableFont wf6 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
            WritableCellFormat wcf6 = new WritableCellFormat(wf6);
            wcf6.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
            wcf6.setAlignment(Alignment.CENTRE);
            wcf6.setVerticalAlignment(VerticalAlignment.CENTRE);
            
            for(int i=5; i < 20; i++){
            	for(int j=0; j < 15; j++){
            		if(j == 0){
            			jxl.write.Number number = new jxl.write.Number(j,i,i-4, wcf6);
                        sheet.addCell(number);
            		}else{
            			jxl.write.Number number = new jxl.write.Number(j,i, Math.random(), wcf5);
                        sheet.addCell(number);
            		}
            	}
            }
            int totalRows = sheet.getRows();
            
            sheet.mergeCells(1, totalRows, 2, totalRows);
            sheet.mergeCells(10, totalRows, 15, totalRows);
            //设置高度
            sheet.setRowView(totalRows, 450);
            Label label01 = new Label(1,totalRows, "填表人:刘志宏", wcf2);
            sheet.addCell(label01);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
            Label label02 = new Label(10,totalRows, "上报时间:"+format2.format(new Date()), wcf2);
            sheet.addCell(label02);
            book.write();
            book.close();
            out.flush();   
    	    out.close();
    	    
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
