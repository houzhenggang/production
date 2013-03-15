/**
 * 
 */
package com.rubik.eds.service.plant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.dao.TbPlantSpeciesMonitorDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbPlantSpeciesMonitor;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.support.common.utils.DateUtils;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PlantSpeciesMonitorService extends FtpShareLocalFile{

	@Autowired
	private TbPlantSpeciesMonitorDao plantSpeciesMonitorDao;
	
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbPlantSpeciesMonitor> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return plantSpeciesMonitorDao.getPlantSepeciesByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbPlantSpeciesMonitor findById(Integer id){
		return plantSpeciesMonitorDao.findById(id);
	}

	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbPlantSpeciesMonitor user){
		return plantSpeciesMonitorDao.insert(user);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbPlantSpeciesMonitor user){
		return plantSpeciesMonitorDao.update(user);
	}
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return plantSpeciesMonitorDao.deleteById(id);
	}
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return plantSpeciesMonitorDao.getTotalCount();
	}
	/**
	 * 通过年月日期查询记录
	 * @param plantMonitorDate
	 * @return
	 */
	public int findByDate(TbPlantSpeciesMonitor plantSpeciesMonitor){
		return plantSpeciesMonitorDao.findByDate(plantSpeciesMonitor);
	}
	/**
	 * 查询报表数据
	 * @param reportHeader
	 * @return
	 */
	public List<TbPlantSpeciesMonitor> getPlantSpeciesReportData(ReportHeader reportHeader){
		return plantSpeciesMonitorDao.getPlantSpeciesReportData(reportHeader);
	}
	
	/**
	 * 返回报表数据
	 * @param speciesMonitors
	 * @return
	 */
	public Map<String, List<String>> getReportMapData(List<TbPlantSpeciesMonitor> speciesMonitors){
		Map<String, List<String>> mapData = new LinkedHashMap<String, List<String>>();
		List<String> inner1 = new ArrayList<String>();
		List<String> inner2 = new ArrayList<String>();
		List<String> inner3 = new ArrayList<String>();
		List<String> inner4 = new ArrayList<String>();
		List<String> out1 = new ArrayList<String>();
		List<String> out2 = new ArrayList<String>();
		List<String> out3 = new ArrayList<String>();
		List<String> out4 = new ArrayList<String>();
		
		/**
		 * 遍历数据
		 */
		String tempPlantName = "";
		String tempPlantArea = "";
		for(TbPlantSpeciesMonitor plantSpeciesMonitor : speciesMonitors){
			tempPlantName = plantSpeciesMonitor.getPlantDetails().getPlantName();
			tempPlantArea = plantSpeciesMonitor.getPlantDetails().getPlantMonitorArea();
			if("inner1".equals(tempPlantArea)){
				inner1.add(tempPlantName);
			}else if("inner2".equals(tempPlantArea)){
				inner2.add(tempPlantName);
			}else if("inner3".equals(tempPlantArea)){
				inner3.add(tempPlantName);
			}else if("inner4".equals(tempPlantArea)){
				inner4.add(tempPlantName);
			}else if("out1".equals(tempPlantArea)){
				out1.add(tempPlantName);
			}else if("out2".equals(tempPlantArea)){
				out2.add(tempPlantName);
			}else if("out3".equals(tempPlantArea)){
				out3.add(tempPlantName);
			}else if("out4".equals(tempPlantArea)){
				out4.add(tempPlantName);
			}
		}
		
		mapData.put("area"+1, inner1);
		mapData.put("area"+2, inner2);
		mapData.put("area"+3, inner3);
		mapData.put("area"+4, inner4);
		mapData.put("area"+5, out1);
		mapData.put("area"+6, out2);
		mapData.put("area"+7, out3);
		mapData.put("area"+8, out4);
		
		return mapData;
	}
	
	/**
	 * 生成报表
	 * @param response
	 * @param dataSource
	 */
	public void createReportInner(HttpServletResponse response, ReportHeader reportHeader,
			List<TbPlantSpeciesMonitor> dataSource) {
		try{
			// 打开文件
			String filename = "Z_STJC_ZWC_C5__"+ reportHeader.getReportStationId() +"_"
			 +DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS);
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			
			ServletOutputStream out = response.getOutputStream();
			
			File file = new File(super.getPathname(filename));
			OutputStream outputStream = new FileOutputStream(file);
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("植物物种多样性调查表", 0);
   
			//单元格式化style
			WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.NO_BOLD,false); 
			WritableCellFormat head = new WritableCellFormat(wf);
			//wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
			head.setAlignment(Alignment.CENTRE);
			head.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
	        WritableCellFormat head2 = new WritableCellFormat(wf2);
	        //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
	        head2.setAlignment(Alignment.CENTRE);
	        head2.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont wf3 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
	        WritableCellFormat head3 = new WritableCellFormat(wf3);
	        //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
	        head3.setAlignment(Alignment.LEFT);
	        head3.setVerticalAlignment(VerticalAlignment.CENTRE);
   
	        WritableFont wf4 = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD,false); 
	        WritableCellFormat mainText = new WritableCellFormat(wf4);
	        mainText.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        mainText.setAlignment(Alignment.LEFT);
	        mainText.setVerticalAlignment(VerticalAlignment.CENTRE);
	        int totalRows = 0;
	        //设置宽度
	        sheet.setColumnView(0, 35);
	        sheet.setColumnView(1, 35);
	        
	        //组织制定格式的数据
	        Map<String,List<String>> tableData = this.getReportMapData(dataSource);
	        
	        for(int i=1; i<5; i++){
		        //表1
		        totalRows = sheet.getRows(); 
		        sheet.mergeCells(0, totalRows, 1, totalRows);
		        sheet.setRowView(totalRows, 650);
		        sheet.addCell(new Label(0, totalRows, "植物物种多样性调查表"+i, head));
		        
		        totalRows = sheet.getRows();
		        sheet.mergeCells(0, totalRows, 1, totalRows);
		        sheet.setRowView(totalRows, 400);
		        if(i<5){
		        	sheet.addCell(new Label(0, totalRows, "（样方内编号：围栏内       "+ i +" ）", head2));
		        }else{
		        	sheet.addCell(new Label(0, totalRows, "（样方内编号：围栏外       "+ (i-4) +" ）", head2));
		        }
		        
		        totalRows = sheet.getRows();
		        sheet.addCell(new Label(0, totalRows, "台站名称："+reportHeader.getReportStationName(), head3));
		        sheet.addCell(new Label(1, totalRows, "监测日期："
		        		+DateUtils.formatDate(reportHeader.getReportEndDate(), DateUtils.CH_YYYYMMDD), head3));
		        
		        totalRows=sheet.getRows();
		        sheet.addCell(new Label(0, totalRows, "植物中文名称", mainText));
		        sheet.addCell(new Label(1, totalRows, "有或无", mainText));
		        
		        //填入报表数据
		        
		        for(String name : tableData.get("area"+i)){
		        	totalRows = sheet.getRows();
		        	sheet.addCell(new Label(0, totalRows, name , mainText));
		        	sheet.addCell(new Label(1, totalRows, "√" , mainText));
		        }
		        
		        //生成两行空表格
		        for(int j=0; j<2; j++){
		        	totalRows = sheet.getRows();
		        	sheet.addCell(new Label(0, totalRows, null , mainText));
		        	sheet.addCell(new Label(1, totalRows, null , mainText));
		        }
		        
	        	//统计物种种类总数
	        	totalRows = sheet.getRows();
	        	sheet.addCell(new Label(0, totalRows, "植物种类", mainText));
		        sheet.addCell(new jxl.write.Number(1, totalRows, tableData.get("area"+i).size(), mainText));
	        	
		        //表1结尾
				totalRows = sheet.getRows();
				//设置高度
				sheet.setRowView(totalRows, 400);
				sheet.addCell(new Label(0,totalRows, "填表人:"+reportHeader.getReporterName(), head3));
				sheet.addCell(new Label(1,totalRows, "上报时间:"
						+DateUtils.formatDate(reportHeader.getReportDate(), DateUtils.CH_YYYYMMDD), head3));
				//生成一行隔表
				totalRows = sheet.getRows();
				sheet.addCell(new Label(0, totalRows, null , head3));
				sheet.addCell(new Label(1, totalRows, null , head3));
	        }
	        	/**
	            * 保存到共享目录
	            */
	           book.write();
	           book.close();
	           outputStream.flush();   
	           outputStream.close();
	          
	          //reponse返回数据流
	          Workbook workbook = Workbook.getWorkbook(file);
	          WritableWorkbook repWorkbook = Workbook.createWorkbook(out, workbook);
	          repWorkbook.write();
	          repWorkbook.close();
	          out.flush();   
	          out.close();
	          
	        //如果只导出客户端，则删除共享数据
	          if("0".equals(reportHeader.getHowSaveFile())){
	        	  file.delete();
	          }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成报表
	 * @param response
	 * @param dataSource
	 */
	public void createReportOuter(HttpServletResponse response, ReportHeader reportHeader,
			List<TbPlantSpeciesMonitor> dataSource) {
		try{
			// 打开文件
			String filename = "Z_STJC_ZWL_C5__"+ reportHeader.getReportStationId() +"_"
			 +DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS);
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			
			ServletOutputStream out = response.getOutputStream();
			
			File file = new File(super.getPathname(filename));
			OutputStream outputStream = new FileOutputStream(file);
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("植物物种多样性调查表", 0);
   
			//单元格式化style
			WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.NO_BOLD,false); 
			WritableCellFormat head = new WritableCellFormat(wf);
			//wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
			head.setAlignment(Alignment.CENTRE);
			head.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
	        WritableCellFormat head2 = new WritableCellFormat(wf2);
	        //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
	        head2.setAlignment(Alignment.CENTRE);
	        head2.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont wf3 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
	        WritableCellFormat head3 = new WritableCellFormat(wf3);
	        //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
	        head3.setAlignment(Alignment.LEFT);
	        head3.setVerticalAlignment(VerticalAlignment.CENTRE);
   
	        WritableFont wf4 = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD,false); 
	        WritableCellFormat mainText = new WritableCellFormat(wf4);
	        mainText.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        mainText.setAlignment(Alignment.LEFT);
	        mainText.setVerticalAlignment(VerticalAlignment.CENTRE);
	        int totalRows = 0;
	        //设置宽度
	        sheet.setColumnView(0, 45);
	        sheet.setColumnView(1, 45);
	        
	        //组织制定格式的数据
	        Map<String,List<String>> tableData = this.getReportMapData(dataSource);
	        
	      //表2
	        totalRows = sheet.getRows(); 
	        sheet.mergeCells(0, totalRows, 1, totalRows);
	        sheet.setRowView(totalRows, 650);
	        sheet.addCell(new Label(0, totalRows, "植物物种多样性调查表"+2, head));
	        
	        totalRows = sheet.getRows();
	        sheet.mergeCells(0, totalRows, 1, totalRows);
	        sheet.setRowView(totalRows, 400);
	        sheet.addCell(new Label(0, totalRows, "（样方外）", head2));
	        
	        totalRows = sheet.getRows();
	        sheet.addCell(new Label(0, totalRows, "台站名称："+reportHeader.getReportStationName(), head3));
	        sheet.addCell(new Label(1, totalRows, "监测日期："
	        		+DateUtils.formatDate(reportHeader.getReportEndDate(), DateUtils.CH_YYYYMMDD), head3));
	        
	        totalRows=sheet.getRows();
	        sheet.addCell(new Label(0, totalRows, "植物中文名称", mainText));
	        sheet.addCell(new Label(1, totalRows, "有或无", mainText));
	        
	        int specialCount = 0;
	        
	        for(int i=5; i<9; i++){
		        //填入报表数据
		        for(String name : tableData.get("area"+i)){
		        	totalRows = sheet.getRows();
		        	sheet.addCell(new Label(0, totalRows, name , mainText));
		        	sheet.addCell(new Label(1, totalRows, "√" , mainText));
		        	specialCount++;
		        }
	        }
	        
	        //生成两行空表格
	        for(int j=0; j<2; j++){
	        	totalRows = sheet.getRows();
	        	sheet.addCell(new Label(0, totalRows, null , mainText));
	        	sheet.addCell(new Label(1, totalRows, null , mainText));
	        }
	        //统计物种种类总数
        	totalRows = sheet.getRows();
        	sheet.addCell(new Label(0, totalRows, "植物种类", mainText));
	        sheet.addCell(new jxl.write.Number(1, totalRows, specialCount, mainText));
        	
	        //表2结尾
			totalRows = sheet.getRows();
			//设置高度
			sheet.setRowView(totalRows, 400);
			sheet.addCell(new Label(0,totalRows, "填表人:"+reportHeader.getReporterName(), head3));
			sheet.addCell(new Label(1,totalRows, "上报时间:"
					+DateUtils.formatDate(reportHeader.getReportDate(), DateUtils.CH_YYYYMMDD), head3));
			
	        	/**
	            * 保存到共享目录
	            */
	           book.write();
	           book.close();
	           outputStream.flush();   
	           outputStream.close();
	          
	          //reponse返回数据流
	          Workbook workbook = Workbook.getWorkbook(file);
	          WritableWorkbook repWorkbook = Workbook.createWorkbook(out, workbook);
	          repWorkbook.write();
	          repWorkbook.close();
	          out.flush();   
	          out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
