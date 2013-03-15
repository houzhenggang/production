package com.rubik.eds.service.dunemove;

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

import com.rubik.eds.common.utils.CommonUtils;
import com.rubik.eds.dao.TbDuneMoveMonitorDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbDuneMoveMonitor;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.support.common.utils.DateUtils;

@Service
@Transactional
public class DuneMoveMonitorService extends FtpShareLocalFile{
	@Autowired
	private TbDuneMoveMonitorDao duneMoveMonitorDao;
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbDuneMoveMonitor> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return duneMoveMonitorDao.getDuneMoveByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public TbDuneMoveMonitor getDuneMoveReportData(ReportHeader reportHeader){
		return duneMoveMonitorDao.getDuneMoveReportData(reportHeader);
	}
	/**
	 * 获取原始报表数据
	 * @param reportHeader
	 * @return
	 */
	public List<TbDuneMoveMonitor> getRealDuneMoveReportData(ReportHeader reportHeader){
		return duneMoveMonitorDao.getRealDuneMoveReportData(reportHeader);
	}
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbDuneMoveMonitor findById(Integer id){
		return duneMoveMonitorDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbDuneMoveMonitor user){
		return duneMoveMonitorDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbDuneMoveMonitor user){
		return duneMoveMonitorDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return duneMoveMonitorDao.deleteById(id);
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return duneMoveMonitorDao.getTotalCount();
	}
	/**
	 * 获取经度集合
	 * @param userId
	 * @return
	 */
	public List<TbDuneMoveMonitor> getLongtitudes(Map<Object, Object> userIdMap){
		return duneMoveMonitorDao.getLongtitudes(userIdMap);
	}
	/**
	 * 获取纬度集合
	 * @param userId
	 * @return
	 */
	public List<TbDuneMoveMonitor> getLatitudes(Map<Object, Object> userIdMap){
		return duneMoveMonitorDao.getLatitudes(userIdMap);
	}
	/**
	 * 生成报表
	 * @param response
	 * @param dataSource
	 */
	public void createReport(HttpServletResponse response, ReportHeader reportHeader, List<TbDuneMoveMonitor> duneMoveMonitors) {
		try{
			 // 打开文件
			String filename = "Z_STJC_SQD_C5_"+ reportHeader.getReportStationId() +"_"
			 +DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS);
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			
			ServletOutputStream out = response.getOutputStream();
			
			File file = new File(super.getPathname(filename));
			OutputStream outputStream = new FileOutputStream(file);
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
           // 生成名为“第一页”的工作表，参数0表示这是第一页
           WritableSheet sheet = book.createSheet("沙丘移动监测数据报表", 0);
           
           //合并单元格
           sheet.mergeCells(0, 0, 7, 0);
           sheet.mergeCells(0, 1, 2, 1);
           sheet.mergeCells(5, 1, 7, 1);
           
           //设置高度
           sheet.setRowView(0, 650);
           for(int i=1;i<16;i++){
        	   sheet.setRowView(i, 450);
           }
           
           //设置宽度
           sheet.setColumnView(0, 30);
           for(int c=1; c<8; c++){
        	   sheet.setColumnView(c, 15);
           }
           
           WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf = new WritableCellFormat(wf);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf.setAlignment(Alignment.CENTRE);
           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           
           Label label = new Label(0,0, "沙丘移动监测数据报表", wcf);
           sheet.addCell(label);
           
           WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf2 = new WritableCellFormat(wf2);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf2.setAlignment(Alignment.LEFT);
           wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           Label label2 = new Label(0,1, "台站名称:"+reportHeader.getReportStationName(), wcf2);
           sheet.addCell(label2);
           
           //
           WritableFont wf3 = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf3 = new WritableCellFormat(wf3);
           wcf3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
           wcf3.setAlignment(Alignment.CENTRE);
           wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           //wcf3.setBorder(jxl.format.Border.TOP,jxl.format.BorderLineStyle.MEDIUM);
           wcf3.setWrap(true);
           sheet.addCell(new Label(0,2, "测定日期(月、日)", wcf3));
           sheet.addCell(new Label(0,3, "被监测沙丘经度", wcf3));
           sheet.addCell(new Label(0,4, "被监测沙丘纬度", wcf3));
           sheet.addCell(new Label(0,5, "沙丘海拔高度", wcf3));
           sheet.addCell(new Label(0,6, "沙丘高度变化", wcf3));
           sheet.addCell(new Label(0,7, "沙丘迎风坡脚移动距离", wcf3));
           sheet.addCell(new Label(0,8, "沙丘丘顶移动距离", wcf3));
           sheet.addCell(new Label(0,9, "沙丘背风坡脚移动距离", wcf3));
           sheet.addCell(new Label(0,10, "沙丘迎风坡脚方位角", wcf3));
           sheet.addCell(new Label(0,11, "沙丘丘顶方位角", wcf3));
           sheet.addCell(new Label(0,12, "沙丘背风坡脚方位角", wcf3));
           sheet.addCell(new Label(0,13, "风向", wcf3));
           sheet.addCell(new Label(0,14, "风速", wcf3));
           
           /**
            * 生成空表格
            */
           for(int c=1; c<8; c++){
        	   for(int r=2; r<15; r++){
        		   sheet.addCell(new Label(c,r, null, wcf3));
        	   }
           }
           
           //
           sheet.addCell(new Label(6,3,"合计", wcf3));
           sheet.addCell(new Label(7,3,"平均", wcf3));
           sheet.addCell(new Label(6,4,"—", wcf3));
           sheet.addCell(new Label(7,4,"—", wcf3));
           sheet.addCell(new Label(6,5,"—", wcf3));
           sheet.addCell(new Label(7,5,"—", wcf3));
           
           sheet.addCell(new Label(6,10,"最多方位", wcf3));
           sheet.addCell(new Label(6,11,"最多方位", wcf3));
           sheet.addCell(new Label(6,12,"最多方位", wcf3));
           sheet.addCell(new Label(6,13,"主风向", wcf3));
           sheet.addCell(new Label(6,14,"—", wcf3));
           
           String monitorDate = null;
           String mainWindDirection = null;
           int length = duneMoveMonitors.size();
           for(int i=length-1; i>=0 ; i--){
	           //添加报表数据
	           if((reportHeader.getReportStartDate().equals(duneMoveMonitors.get(i).getDuneMoveMonitorDate()))){
	            	monitorDate = DateUtils.formatDate(duneMoveMonitors.get(i).getDuneMoveMonitorDate(),DateUtils.CH_YYYYMMDD);
	            	mainWindDirection = duneMoveMonitors.get(i).getWindDirection();
	           }
               
	           sheet.addCell(new Label(length-i,2, 
	        		   DateUtils.formatDate(duneMoveMonitors.get(i).getDuneMoveMonitorDate(),DateUtils.YYYY_MM_DD), wcf3));
	           sheet.addCell(new Label(length-i,3, duneMoveMonitors.get(i).getDuneMoveLongtitude(), wcf3));
	           sheet.addCell(new Label(length-i,4, duneMoveMonitors.get(i).getDuneMoveLatitude(), wcf3));
	           sheet.addCell(new Label(length-i,5, CommonUtils.formatNumber(duneMoveMonitors.get(i).getDuneAltitude(),2), wcf3));
	           
	           sheet.addCell(new Label(length-i,6, CommonUtils.formatNumber(duneMoveMonitors.get(i).getDuneHeight(),2), wcf3));
	           sheet.addCell(new Label(length-i,7, CommonUtils.formatNumber(duneMoveMonitors.get(i).getDuneWindwardSlope(),2), wcf3));
	           sheet.addCell(new Label(length-i,8, CommonUtils.formatNumber(duneMoveMonitors.get(i).getDuneHilltop(),2), wcf3));
	           sheet.addCell(new Label(length-i,9, CommonUtils.formatNumber(duneMoveMonitors.get(i).getDuneLeewardSlope(),2), wcf3));
	           sheet.addCell(new Label(length-i,10, CommonUtils.formatNumber(duneMoveMonitors.get(i).getDuneWindPosition(),2), wcf3));
	           sheet.addCell(new Label(length-i,11, CommonUtils.formatNumber(duneMoveMonitors.get(i).getHilltopPosition(),2), wcf3));
	           sheet.addCell(new Label(length-i,12, CommonUtils.formatNumber(duneMoveMonitors.get(i).getLeewardPosition(),2), wcf3));
	           //sheet.addCell(new Label(length-i,13, duneMoveMonitors.get(i).getWindDirection(), wcf3));
	           sheet.addCell(new Label(length-i,14, CommonUtils.formatNumber(duneMoveMonitors.get(i).getWindSpeed(),2), wcf3));
           }
           
           Label label4 = new Label(5,1, "监测日期:"
	               +monitorDate, wcf2);
	               sheet.addCell(label4);
	       
	      sheet.addCell(new Label(7,13, mainWindDirection, wcf3));
           /**
            * 报表数据填充结束
            */
           
           int totalRows = sheet.getRows();
           
           sheet.mergeCells(5, totalRows-1, 7, totalRows-1);
           //设置高度

           Label label01 = new Label(0,totalRows-1, "填表人:"+reportHeader.getReporterName(), wcf2);
           sheet.addCell(label01);
           Label label02 = new Label(5,totalRows-1, "上报时间:"
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
}
