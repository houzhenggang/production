package com.rubik.eds.service.grass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
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

import com.rubik.eds.dao.TbGrassMonitorDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbGrassMonitor;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.support.common.utils.DateUtils;

@Service
@Transactional
public class GrassMonitorService extends FtpShareLocalFile{

	@Autowired
	private TbGrassMonitorDao grassMonitorDao;
	
	/**
	 * 查询所有的天然草场监测数据
	 * @return
	 */
	public List<TbGrassMonitor> findAll(){
		return grassMonitorDao.findAll();
	}
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbGrassMonitor> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return grassMonitorDao.getGrassByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbGrassMonitor> getGrassReportData(ReportHeader reportHeader){
		return grassMonitorDao.getGrassReportData(reportHeader);
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbGrassMonitor findById(Integer id){
		return grassMonitorDao.findById(id);
	}
	
	/**
	 * 查询最新的一条天然草场记录
	 * @param stationId
	 * @return
	 */
	public TbGrassMonitor findLastedRecord(String stationId){
		return grassMonitorDao.findLastedRecord(stationId);
	}
	
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbGrassMonitor user){
		return grassMonitorDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbGrassMonitor user){
		return grassMonitorDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return grassMonitorDao.deleteById(id);
	}
	
	/**
	 * 生成报表
	 * @param response
	 * @param dataSource
	 */
	public void createReport(HttpServletResponse response, ReportHeader reportHeader,
			List<TbGrassMonitor> dataSource) {
		try{
			String filename = "Z_STJC_TRE_C5_"+ reportHeader.getReportStationId() +"_"
			 +DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS);
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			
			ServletOutputStream out = response.getOutputStream();
			
			File file = new File(super.getPathname(filename));
			OutputStream outputStream = new FileOutputStream(file);
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
           // 生成名为“第一页”的工作表，参数0表示这是第一页
           WritableSheet sheet = book.createSheet("天然草场监测数据报表", 0);
           
           //合并单元格
           sheet.mergeCells(0, 0, 10, 0);
           sheet.mergeCells(0, 1, 3, 1);
           sheet.mergeCells(0, 2, 1, 2);
           sheet.mergeCells(5, 1, 7, 1);
           sheet.mergeCells(8, 1, 10, 1);
           
           
           //设置高度
           sheet.setRowView(0, 650);
           sheet.setRowView(1, 450);
           sheet.setRowView(2, 500);
           
           //设置宽度
           for(int c=2; c<11; c++){
        	   sheet.setColumnView(c, 12);
           }
           
           WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf = new WritableCellFormat(wf);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf.setAlignment(Alignment.CENTRE);
           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           
           Label label = new Label(0,0, "天然草场监测数据报表", wcf);
           sheet.addCell(label);
           
           WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf2 = new WritableCellFormat(wf2);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf2.setAlignment(Alignment.LEFT);
           wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           sheet.addCell(new Label(0,1, "台站名称:"+reportHeader.getReportStationName(), wcf2));
           
           sheet.addCell(new Label(5,1, "监测地段："+reportHeader.getReportMonitorArea(),wcf2));
           
           sheet.addCell(new Label(8,1, "监测日期:"
                   +DateUtils.formatDate(reportHeader.getReportStartDate(), "yyyy年MM月11日")+"--"
                   +DateUtils.formatDate(reportHeader.getReportStartDate(), "yyyy年MM月20日"), wcf2));
           
           
           //
           WritableFont wf3 = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf3 = new WritableCellFormat(wf3);
           wcf3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
           wcf3.setAlignment(Alignment.CENTRE);
           wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           //wcf3.setBorder(jxl.format.Border.TOP,jxl.format.BorderLineStyle.MEDIUM);
           wcf3.setWrap(true);
           sheet.addCell(new Label(0,2, "牧草名称", wcf3));
           sheet.addCell(new Label(2,2, "返春期", wcf3));
           sheet.addCell(new Label(3,2, "开花期", wcf3));
           sheet.addCell(new Label(4,2, "黄枯期", wcf3));
           sheet.addCell(new Label(5,2, "绝对高度", wcf3));
           sheet.addCell(new Label(6,2, "草层高度", wcf3));
           sheet.addCell(new Label(7,2, "盖度", wcf3));
           sheet.addCell(new Label(8,2, "地上生物量", wcf3));
           sheet.addCell(new Label(9,2, "优良牧草比例", wcf3));
           sheet.addCell(new Label(10,2, "备注", wcf3));
           
           /**
            * 报表数据填充开始
            */
           int nextRow;
           //牧草鲜重
           TbGrassMonitor grassWeight = new TbGrassMonitor();
           //混合草本
           TbGrassMonitor exGrass = new TbGrassMonitor();
           
           StringBuffer remarkList = new StringBuffer();
           if(dataSource != null && dataSource.size() > 0){
	           for(int i=0; i < dataSource.size(); i++){
	        	   if("牧草鲜重".equals(dataSource.get(i).getGrassName())){
	        		   grassWeight = dataSource.get(i);
	        	   }else if("混合草本".equals(dataSource.get(i).getGrassName())){
	        		   exGrass = dataSource.get(i);
	        	   }else{
	        		   nextRow = sheet.getRows();
		        	   sheet.setRowView(nextRow, 500);
		        	   sheet.mergeCells(0, nextRow, 1, nextRow);
		        	   sheet.addCell(new Label(0,nextRow,dataSource.get(i).getGrassName(),wcf3));
		        	   sheet.addCell(new Label(2,nextRow,dataSource.get(i).getBackDate(),wcf3));
		        	   sheet.addCell(new Label(3,nextRow,dataSource.get(i).getFlowerDate(),wcf3));
		        	   sheet.addCell(new Label(4,nextRow,dataSource.get(i).getYellowDate(),wcf3));
		        	   sheet.addCell(new Label(5,nextRow,dataSource.get(i).getAbsoluteHeigth(),wcf3));
		        	   sheet.addCell(new Label(6,nextRow,dataSource.get(i).getGrassHeigth(),wcf3));
		        	   sheet.addCell(new Label(7,nextRow,dataSource.get(i).getCoverDegree(),wcf3));
		        	   sheet.addCell(new Label(8,nextRow,dataSource.get(i).getEarthBiomass(),wcf3));
		        	   sheet.addCell(new Label(9,nextRow,dataSource.get(i).getBetterGrassRate(),wcf3));
		        	   if(dataSource.get(i).getRemarks() != null){
		        		   remarkList.append(dataSource.get(i).getRemarks());
		        	   }
	        	   }
	           }
           }
           
           /**
            * 生成空表格
            */
        	   nextRow = sheet.getRows();
        	   sheet.setRowView(nextRow, 500);
        	   sheet.mergeCells(0, nextRow, 1, nextRow);
        	   sheet.addCell(new Label(0,nextRow,null,wcf3));
	           for(int i=2; i <5; i++){
	               sheet.addCell(new Label(i,nextRow, null, wcf3));
	           }
	           
	           for(int i=5; i <10; i++){
	               sheet.addCell(new Label(i,nextRow, "XXXXX", wcf3));
	           }
           
           //添加牧草鲜重
           nextRow = sheet.getRows();
    	   sheet.setRowView(nextRow, 500);
    	   sheet.mergeCells(0, nextRow, 1, nextRow);
    	   sheet.addCell(new Label(0,nextRow,"牧草鲜重",wcf3));
    	   sheet.addCell(new Label(2,nextRow,grassWeight.getBackDate(),wcf3));
    	   sheet.addCell(new Label(3,nextRow,grassWeight.getFlowerDate(),wcf3));
    	   sheet.addCell(new Label(4,nextRow,grassWeight.getYellowDate(),wcf3));
    	   sheet.addCell(new Label(5,nextRow,grassWeight.getAbsoluteHeigth(),wcf3));
    	   sheet.addCell(new Label(6,nextRow,grassWeight.getGrassHeigth(),wcf3));
    	   sheet.addCell(new Label(7,nextRow,grassWeight.getCoverDegree(),wcf3));
    	   sheet.addCell(new Label(8,nextRow,grassWeight.getEarthBiomass(),wcf3));
    	   sheet.addCell(new Label(9,nextRow,grassWeight.getBetterGrassRate(),wcf3));
    	   if(grassWeight.getRemarks() != null){
    		   remarkList.append(grassWeight.getRemarks());
    	   }
           
    	   //牧草鲜重后面表格
           nextRow = sheet.getRows();
           for(int j=0; j<5; j++){
        	   sheet.setRowView(nextRow+j, 500);
        	   for(int i=0;i<11;i++){
        		   sheet.addCell(new Label(i,nextRow+j,null,wcf3));
           }
           }
           sheet.addCell(new Label(0,nextRow,"灌",wcf3));
           sheet.addCell(new Label(0,nextRow+3,"木",wcf3));
           sheet.addCell(new Label(1,nextRow+3,"混合灌木",wcf3));
           sheet.addCell(new Label(1,nextRow+4,"混合草本",wcf3));
           
           for(int i=0;i<3;i++){
        	   sheet.addCell(new Label(7,nextRow+i,"XXXXX",wcf3));
               sheet.addCell(new Label(9,nextRow+i,"XXXXX",wcf3));
           }
           
           sheet.addCell(new Label(6,nextRow+1,"XXXXX",wcf3));
           sheet.addCell(new Label(6,nextRow+2,"XXXXX",wcf3));
          
         //添加混合灌木
           sheet.addCell(new Label(2,nextRow+3,"XXXXX",wcf3));
    	   sheet.addCell(new Label(3,nextRow+3,"XXXXX",wcf3));
    	   sheet.addCell(new Label(4,nextRow+3,null,wcf3));
    	   sheet.addCell(new Label(5,nextRow+3,"XXXXX",wcf3));
    	   sheet.addCell(new Label(6,nextRow+3,"XXXXX",wcf3));
    	   sheet.addCell(new Label(7,nextRow+3,null,wcf3));
    	   sheet.addCell(new Label(8,nextRow+3,null,wcf3));
    	   sheet.addCell(new Label(9,nextRow+3,null,wcf3));
           
           //添加混合草本
    	   if(exGrass.getBackDate() != null){
    		   sheet.addCell(new Label(2,nextRow+4,exGrass.getBackDate(),wcf3));
    	   }else{
    		   sheet.addCell(new Label(2,nextRow+4,"XXXXX",wcf3));
    	   }
    	   if(exGrass.getFlowerDate() != null){
    		   sheet.addCell(new Label(3,nextRow+4,exGrass.getFlowerDate(),wcf3));
    	   }else{
    		   sheet.addCell(new Label(3,nextRow+4,"XXXXX",wcf3));
    	   }
    	   sheet.addCell(new Label(4,nextRow+4,exGrass.getYellowDate(),wcf3));
    	   if(exGrass.getAbsoluteHeigth() != null){
    		   sheet.addCell(new Label(5,nextRow+4,exGrass.getAbsoluteHeigth(),wcf3));
    	   }else{
    		   sheet.addCell(new Label(5,nextRow+4,"XXXXX",wcf3));
    	   }
    	   sheet.addCell(new Label(6,nextRow+4,exGrass.getGrassHeigth(),wcf3));
    	   sheet.addCell(new Label(7,nextRow+4,exGrass.getCoverDegree(),wcf3));
    	   sheet.addCell(new Label(8,nextRow+4,exGrass.getEarthBiomass(),wcf3));
    	   sheet.addCell(new Label(9,nextRow+4,exGrass.getBetterGrassRate(),wcf3));
    	   if(exGrass.getRemarks() != null){
    		   remarkList.append(exGrass.getRemarks());
    	   }
    	   
           /**
            * 报表数据填充结束
            */
           //最后添加备注
           int totalRows = sheet.getRows();
           sheet.mergeCells(10, 3, 10, totalRows-1);
           sheet.addCell(new Label(10,3,remarkList.toString(),wcf3));
           
           sheet.mergeCells(0, totalRows, 2, totalRows);
           sheet.mergeCells(8, totalRows, 10, totalRows);
           //设置高度
           sheet.setRowView(totalRows, 450);
           Label label01 = new Label(0,totalRows, "填表人:"+reportHeader.getReporterName(), wcf2);
           sheet.addCell(label01);
           Label label02 = new Label(8,totalRows, "上报时间:"
           +DateUtils.formatDate(reportHeader.getReportDate(), DateUtils.CH_YYYYMMDD), wcf2);
           sheet.addCell(label02);
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
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return grassMonitorDao.getTotalCount();
	}
}
