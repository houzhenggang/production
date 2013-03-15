package com.rubik.eds.service.water;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.rubik.eds.dao.TbWaterMonitorDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbWaterMonitor;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.support.common.utils.DateUtils;

@Service
@Transactional
public class WaterMonitorService  extends FtpShareLocalFile{

	/**
	 * 构造函数
	 * @throws IOException
	 */
	public WaterMonitorService() throws IOException {
		super();
	}

	@Autowired
	private TbWaterMonitorDao waterMonitorDao;
	
	/**
	 * 查询所有记录
	 * @param useIdMap
	 * @return
	 */
	public List<TbWaterMonitor> findAll(Map<Object, Object> useIdMap){
		return waterMonitorDao.findAll(useIdMap);
	}
	
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbWaterMonitor> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		
		return waterMonitorDao.getWaterByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 查询已登记的水体名称
	 * @param rowBounds
	 * @return
	 */
	public List<TbWaterMonitor> getWaterNames(Map<Object, Object> userIdMap){
		return waterMonitorDao.getWaterNames(userIdMap);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbWaterMonitor> getWaterReportData(ReportHeader reportHeader){
		return waterMonitorDao.getWaterReportData(reportHeader);
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbWaterMonitor findById(Integer id){
		return waterMonitorDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbWaterMonitor user){
		return waterMonitorDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbWaterMonitor user){
		return waterMonitorDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return waterMonitorDao.deleteById(id);
	}
	
	/**
	 * 生成报表
	 * @param response
	 * @param dataSource
	 */
	public void createReport(HttpServletResponse response, ReportHeader reportHeader,
			List<TbWaterMonitor> dataSource) {
		try{
			 // 打开文件
			String filename = "Z_STJC_C5_"+ reportHeader.getReportStationId() +"_"
			 +DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS);
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			ServletOutputStream out = response.getOutputStream();
			
			File file = new File(super.getPathname(filename));
			OutputStream outputStream = new FileOutputStream(file);
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("水体监测数据报表", 0);
           
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
           
           
           Label label = new Label(4,0, "水体监测数据报表", wcf);
           sheet.addCell(label);
           
           WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf2 = new WritableCellFormat(wf2);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf2.setAlignment(Alignment.LEFT);
           wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           Label label2 = new Label(1,2, "台站名称:"+reportHeader.getReportStationName(), wcf2);
           sheet.addCell(label2);
           
           Label label3 = new Label(7,2, "水体名称："+reportHeader.getReportWaterName(), wcf2);
           sheet.addCell(label3);
           
//           Label label4 = new Label(10,2, "监测日期:"
//           +DateUtils.formatDate(reportHeader.getReportStartDate(),DateUtils.CH_YYYYMMDD)+"--"
//           +DateUtils.formatDate(reportHeader.getReportEndDate(),DateUtils.CH_YYYYMMDD), wcf2);
//           sheet.addCell(label4);
           
           Label label4 = new Label(10,2, "监测日期:"
                   +DateUtils.formatDate(reportHeader.getReportStartDate(),DateUtils.CH_YYYYMMDD), wcf2);
                   sheet.addCell(label4);
           
           //
           WritableFont wf3 = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD,false); 
           WritableCellFormat wcf3 = new WritableCellFormat(wf3);
           wcf3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
           wcf3.setAlignment(Alignment.CENTRE);
           wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           wcf3.setBorder(jxl.format.Border.TOP,jxl.format.BorderLineStyle.MEDIUM);
           wcf3.setWrap(true);
           Label label5 = new Label(0,3, "序列数", wcf3);
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
           //以下报表数据填充
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
           
           /**
            * 报表数据填充开始
            */
           if(dataSource != null && dataSource.size() > 0){
	           for(int i=5; i < dataSource.size()+5; i++){
	        	   sheet.setRowView(i, 550);
                   sheet.addCell(new jxl.write.Number(0,i,i-4, wcf6));
                   sheet.addCell(new Label(1,i, dataSource.get(i-5).getFetchLongitude(), wcf5));
                   sheet.addCell(new Label(2,i, dataSource.get(i-5).getFetchLatitude(), wcf5));
                   sheet.addCell(new Label(3,i, dataSource.get(i-5).getTurnLongitude(), wcf5));
                   sheet.addCell(new Label(4,i, dataSource.get(i-5).getTurnLatitude(), wcf5));
                   sheet.addCell(new Label(5,i, dataSource.get(i-5).getWaterArea(), wcf5));
                   sheet.addCell(new Label(6,i, dataSource.get(i-5).getWaterLevel(), wcf5));
                   sheet.addCell(new Label(7,i, dataSource.get(i-5).getWaterOpacity(), wcf5));
                   sheet.addCell(new Label(8,i, dataSource.get(i-5).getWaterColor(), wcf5));
                   sheet.addCell(new Label(9,i, dataSource.get(i-5).getWaterTemperature(), wcf5));
                   sheet.addCell(new Label(10,i, dataSource.get(i-5).getWaterPh(), wcf5));
                   sheet.addCell(new Label(11,i, dataSource.get(i-5).getWaterTotalSalt(), wcf5));
                   sheet.addCell(new Label(12,i, dataSource.get(i-5).getWaterChlorine(), wcf5));
                   sheet.addCell(new Label(13,i, dataSource.get(i-5).getWaterSulfide(), wcf5));
                   sheet.addCell(new Label(14,i, dataSource.get(i-5).getRemarks(), wcf5));
	           }
           }
           /**
            * 报表数据填充结束
            */
           /**
            * 生成空表格
            */
           for(int j =0; j<2; j++){
        	   int nextRow = sheet.getRows();
        	   sheet.setRowView(nextRow, 550);
	           for(int i=0; i <15; i++){
	               sheet.addCell(new Label(i,nextRow, null, wcf5));
	           }
           }
           
           int totalRows = sheet.getRows();
           
           sheet.mergeCells(1, totalRows, 2, totalRows);
           sheet.mergeCells(10, totalRows, 15, totalRows);
           //设置高度
           sheet.setRowView(totalRows, 450);
           Label label01 = new Label(1,totalRows, "填表人:"+reportHeader.getReporterName(), wcf2);
           sheet.addCell(label01);
           Label label02 = new Label(10,totalRows, "上报时间:"
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
		return waterMonitorDao.getTotalCount();
	}
}
