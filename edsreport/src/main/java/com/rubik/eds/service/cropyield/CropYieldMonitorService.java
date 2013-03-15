package com.rubik.eds.service.cropyield;

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
import com.rubik.eds.dao.TbCropYieldMonitorDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbCropYieldMonitor;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.support.common.utils.DateUtils;

@Service
@Transactional
public class CropYieldMonitorService extends FtpShareLocalFile{

	@Autowired
	private TbCropYieldMonitorDao cropYieldMonitorDao;
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbCropYieldMonitor> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return cropYieldMonitorDao.getCropYieldByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbCropYieldMonitor> getCropYieldReportData(ReportHeader reportHeader){
		return cropYieldMonitorDao.getCropYieldReportData(reportHeader);
	}
	/**
	 * 查询所有农作物名称
	 * @return
	 */
	public List<String> getCropNames(Map<Object, Object> userIdMap){
		return cropYieldMonitorDao.getCropNames(userIdMap);
	}
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbCropYieldMonitor findById(Integer id){
		return cropYieldMonitorDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbCropYieldMonitor user){
		return cropYieldMonitorDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbCropYieldMonitor user){
		return cropYieldMonitorDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return cropYieldMonitorDao.deleteById(id);
	}
	
	/**
	 * 生成报表
	 * @param response
	 * @param dataSource
	 */
	public void createReport(HttpServletResponse response, ReportHeader reportHeader,
			List<TbCropYieldMonitor> dataSource) {
		try{
			String filename = "Z_STJC_ZWG_C5_"+ reportHeader.getReportStationId() +"_"
			 +DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS);
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			
			ServletOutputStream out = response.getOutputStream();
			
			File file = new File(super.getPathname(filename));
			OutputStream outputStream = new FileOutputStream(file);
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
           // 生成名为“第一页”的工作表，参数0表示这是第一页
           WritableSheet sheet = book.createSheet("农作物产量结构监测数据报表", 0);
           
           //合并单元格
           sheet.mergeCells(0, 0, 9, 0);
           sheet.mergeCells(0, 1, 3, 1);
           sheet.mergeCells(7, 1, 9, 1);
           for(int c=1; c<10; c++){
        	   sheet.mergeCells(c, 2, c, 3);
           }
           
           //设置高度
           sheet.setRowView(0, 650);
           sheet.setRowView(1, 450);
           sheet.setRowView(2, 450);
           sheet.setRowView(3, 450);
           
           //设置宽度
           for(int c=0; c<10; c++){
        	   sheet.setColumnView(c, 12);
           }
           
           WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf = new WritableCellFormat(wf);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf.setAlignment(Alignment.CENTRE);
           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           
           Label label = new Label(0,0, "农作物产量结构监测数据报表", wcf);
           sheet.addCell(label);
           
           WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf2 = new WritableCellFormat(wf2);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf2.setAlignment(Alignment.LEFT);
           wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           Label label2 = new Label(0,1, "台站名称:"+reportHeader.getReportStationName(), wcf2);
           sheet.addCell(label2);
           
           Label label4 = new Label(7,1, "监测日期:"
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
           sheet.addCell(new Label(0,2, "项目", wcf3));
           sheet.addCell(new Label(0,3, "作物名称", wcf3));
           sheet.addCell(new Label(1,2, "小麦穗长", wcf3));
           sheet.addCell(new Label(2,2, "小穗数", wcf3));
           sheet.addCell(new Label(3,2, "不孕小穗数", wcf3));
           sheet.addCell(new Label(4,2, "结实粒数", wcf3));
           sheet.addCell(new Label(5,2, "茎粗", wcf3));
           sheet.addCell(new Label(6,2, "果穗长", wcf3));
           sheet.addCell(new Label(7,2, "果穗粗", wcf3));
           sheet.addCell(new Label(8,2, "双穗率", wcf3));
           sheet.addCell(new Label(9,2, "作物产量", wcf3));
           
           /**
            * 报表数据填充开始
            */
           if(dataSource != null && dataSource.size() > 0){
	           for(int i=4; i < dataSource.size()+4; i++){
	        	   sheet.setRowView(i, 550);
	        	   sheet.addCell(new Label(0,i,dataSource.get(i-4).getCropName(),wcf3));
	        	   sheet.addCell(new Label(1,i, CommonUtils.formatNumber(dataSource.get(i-4).getWheatLength(),2), wcf3));
                   sheet.addCell(new Label(2,i, CommonUtils.formatNumber(dataSource.get(i-4).getWheatCount(),2), wcf3));
                   sheet.addCell(new Label(3,i, CommonUtils.formatNumber(dataSource.get(i-4).getInfertilityWheatCount(),2), wcf3));
                   sheet.addCell(new Label(4,i, CommonUtils.formatNumber(dataSource.get(i-4).getSolidCount(),2), wcf3));
                   sheet.addCell(new Label(5,i, CommonUtils.formatNumber(dataSource.get(i-4).getStemWidth(),2), wcf3));
                   sheet.addCell(new Label(6,i, CommonUtils.formatNumber(dataSource.get(i-4).getCropEarLength(),2), wcf3));
                   sheet.addCell(new Label(7,i, CommonUtils.formatNumber(dataSource.get(i-4).getCropEarWidth(),2), wcf3));
                   sheet.addCell(new Label(8,i, dataSource.get(i-4).getDoubleEarCount(), wcf3));
                   sheet.addCell(new Label(9,i, CommonUtils.formatNumber(dataSource.get(i-4).getCropYield(),2)+"公斤/公顷", wcf3));
	           }
           }
           /**
            * 生成空表格
            */
           for(int j =0; j<2; j++){
        	   int nextRow = sheet.getRows();
        	   sheet.setRowView(nextRow, 550);
	           for(int i=0; i <10; i++){
	               sheet.addCell(new Label(i,nextRow, null, wcf3));
	           }
           }
           
           /**
            * 报表数据填充结束
            */
           
           int totalRows = sheet.getRows();
           sheet.mergeCells(0, totalRows, 1, totalRows);
           sheet.mergeCells(7, totalRows, 9, totalRows);
           //设置高度
           sheet.setRowView(totalRows, 450);
           Label label01 = new Label(0,totalRows, "填表人:"+reportHeader.getReporterName(), wcf2);
           sheet.addCell(label01);
           Label label02 = new Label(7,totalRows, "上报时间:"
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
		return cropYieldMonitorDao.getTotalCount();
	}
}
