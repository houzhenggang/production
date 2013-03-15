package com.rubik.eds.service.dust;

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
import com.rubik.eds.dao.TbDustMonitorDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbDustMonitor;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.support.common.utils.DateUtils;

@Service
@Transactional
public class DustMonitorService extends FtpShareLocalFile{

	@Autowired
	private TbDustMonitorDao dustMonitorDao;
	
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbDustMonitor> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return dustMonitorDao.getDustByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbDustMonitor> getDustReportData(ReportHeader reportHeader){
		return dustMonitorDao.getDustReportData(reportHeader);
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbDustMonitor findById(Integer id){
		return dustMonitorDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbDustMonitor user){
		return dustMonitorDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbDustMonitor user){
		return dustMonitorDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return dustMonitorDao.deleteById(id);
	}
	
	/**
	 * 生成报表
	 * @param response
	 * @param dataSource
	 */
	public void createReport(HttpServletResponse response, ReportHeader reportHeader,
			List<TbDustMonitor> dataSource) {
		try{
			 // 打开文件
			String filename = "Z_STJC_GCJ_C5_"+ reportHeader.getReportStationId() +"_"
			 +DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS);
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			
			ServletOutputStream out = response.getOutputStream();
			
			File file = new File(super.getPathname(filename));
			OutputStream outputStream = new FileOutputStream(file);
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
           // 生成名为“第一页”的工作表，参数0表示这是第一页
           WritableSheet sheet = book.createSheet("沙尘天气、干尘降监测数据报表", 0);
           
           //合并单元格
           sheet.mergeCells(0, 0, 5, 0);
           sheet.mergeCells(0, 1, 3, 1);
           sheet.mergeCells(0, 2, 0, 3);
           sheet.mergeCells(1, 2, 1, 3);
           sheet.mergeCells(2, 2, 2, 3);
           sheet.mergeCells(3, 2, 4, 2);
           sheet.mergeCells(5, 2, 5, 3);
           
           
           //设置高度
           sheet.setRowView(0, 650);
           sheet.setRowView(1, 450);
           sheet.setRowView(2, 450);
           sheet.setRowView(3, 450);
           
           //设置宽度
           sheet.setColumnView(0, 32);
           sheet.setColumnView(1, 15);
           sheet.setColumnView(2, 15);
           sheet.setColumnView(3, 15);
           sheet.setColumnView(4, 40);
           sheet.setColumnView(5, 40);
           
           WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf = new WritableCellFormat(wf);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf.setAlignment(Alignment.CENTRE);
           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           
           Label label = new Label(0,0, "沙尘天气、干尘降监测数据报表", wcf);
           sheet.addCell(label);
           
           WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf2 = new WritableCellFormat(wf2);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf2.setAlignment(Alignment.LEFT);
           wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           Label label2 = new Label(0,1, "台站名称:"+reportHeader.getReportStationName(), wcf2);
           sheet.addCell(label2);
           //获取上个月最后一天
           reportHeader.setReportStartDate(CommonUtils.getLastdayOfLastmonth(reportHeader.getReportStartDate()));
           Label label4 = new Label(5,1, "监测日期:"
           +DateUtils.formatDate(reportHeader.getReportStartDate(),DateUtils.CH_YYYYMMDD), wcf2);
           sheet.addCell(label4);
           
           
           //
           WritableFont wf3 = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf3 = new WritableCellFormat(wf3);
           wcf3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
           wcf3.setAlignment(Alignment.CENTRE);
           wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           //wcf3.setBorder(jxl.format.Border.TOP,jxl.format.BorderLineStyle.MEDIUM);
           wcf3.setWrap(true);
           sheet.addCell(new Label(0,2, "日期", wcf3));
           sheet.addCell(new Label(1,2, "降尘干重(g)", wcf3));
           sheet.addCell(new Label(2,2, "平均降尘干重(g)", wcf3));
           sheet.addCell(new Label(3,2, "沙尘天气", wcf3));
           sheet.addCell(new Label(3,3, "种类", wcf3));
           sheet.addCell(new Label(4,3, "持续时间", wcf3));
           sheet.addCell(new Label(5,2, "备注", wcf3));
           
           /**
            * 报表数据填充开始
            */
           if(dataSource != null && dataSource.size() > 0){
	           for(int i=4; i < dataSource.size()+4; i++){
	        	   sheet.setRowView(i, 550);
                   //01-月末
	        	   sheet.addCell(new Label(0,i,"1-"+DateUtils.formatDate(reportHeader.getReportStartDate(), DateUtils.DUST_MONITOR_END), wcf3));
	        	   //sheet.addCell(new Label(0,i, DateUtils.formatDate(reportHeader.getReportStartDate(), DateUtils.DUST_MONITOR_END), wcf3));
                   
                   sheet.addCell(new Label(1,i, CommonUtils.formatString(dataSource.get(i-4).getDustDryWeight()), wcf3));
                   sheet.addCell(new Label(2,i, CommonUtils.formatString(dataSource.get(i-4).getDustAvgDryWeight()), wcf3));
                   sheet.addCell(new Label(3,i, dataSource.get(i-4).getDustType(), wcf3));
                   sheet.addCell(new Label(4,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getDustStartTime(), DateUtils.YY_MM_DD_HH_MM)
                		   +" 至 "+
                		   DateUtils.formatDate(dataSource.get(i-4).getDustEndTime(), DateUtils.YY_MM_DD_HH_MM)
                		   , wcf3));
                   sheet.addCell(new Label(5,i, dataSource.get(i-4).getRemarks(), wcf3));
	           }
           }
           
           /**
            * 生成空表格
            */
           for(int j =0; j<2; j++){
        	   int nextRow = sheet.getRows();
        	   sheet.setRowView(nextRow, 550);
	           for(int i=0; i <6; i++){
	               sheet.addCell(new Label(i,nextRow, null, wcf3));
	           }
           }
           
           /**
            * 报表数据填充结束
            */
           
           int totalRows = sheet.getRows();
           
           sheet.mergeCells(0, totalRows, 2, totalRows);
           //sheet.mergeCells(4, totalRows, 5, totalRows);
           //设置高度
           sheet.setRowView(totalRows, 450);
           Label label01 = new Label(0,totalRows, "填表人:"+reportHeader.getReporterName(), wcf2);
           sheet.addCell(label01);
           Label label02 = new Label(5,totalRows, "上报时间:"
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
		return dustMonitorDao.getTotalCount();
	}
}
