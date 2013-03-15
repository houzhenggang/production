package com.rubik.eds.service.cropgrowth;

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
import com.rubik.eds.dao.TbCropGrowthMonitorDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbCropGrowthMonitor;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.support.common.utils.DateUtils;

@Service
@Transactional
public class CropGrowthMonitorService extends FtpShareLocalFile{

	@Autowired
	private TbCropGrowthMonitorDao cropGrowthMonitorDao;
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbCropGrowthMonitor> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return cropGrowthMonitorDao.getCropGrowthByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbCropGrowthMonitor> getCropGrowthReportData(ReportHeader reportHeader){
		return cropGrowthMonitorDao.getCropGrowthReportData(reportHeader);
	}
	/**
	 * 查询所有农作物名称
	 * @return
	 */
	public List<String> getCropNames(Map<Object, Object> userIdMap){
		return cropGrowthMonitorDao.getCropNames(userIdMap);
	}
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbCropGrowthMonitor findById(Integer id){
		return cropGrowthMonitorDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbCropGrowthMonitor user){
		return cropGrowthMonitorDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbCropGrowthMonitor user){
		return cropGrowthMonitorDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return cropGrowthMonitorDao.deleteById(id);
	}
	
	/**
	 * 生成报表
	 * @param response
	 * @param dataSource
	 */
	public void createReport(HttpServletResponse response, ReportHeader reportHeader,
			List<TbCropGrowthMonitor> dataSource) {
		try{
			String filename = "Z_STJC_ZWZ_C5_"+ reportHeader.getReportStationId() +"_"
			 +DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS);
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			
			ServletOutputStream out = response.getOutputStream();
			
			File file = new File(super.getPathname(filename));
			OutputStream outputStream = new FileOutputStream(file);
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
           // 生成名为“第一页”的工作表，参数0表示这是第一页
           WritableSheet sheet = book.createSheet("农作物生长发育状况监测数据报表", 0);
           
           //合并单元格
           sheet.mergeCells(0, 0, 12, 0);
           sheet.mergeCells(0, 1, 6, 1);
           sheet.mergeCells(8, 1, 12, 1);
           for(int c=1; c<13; c++){
        	   sheet.mergeCells(c, 2, c, 3);
           }
           
           //设置高度
           sheet.setRowView(0, 650);
           sheet.setRowView(1, 450);
           sheet.setRowView(2, 450);
           sheet.setRowView(3, 450);
           
           //设置宽度
           for(int c=0; c<13; c++){
        	   sheet.setColumnView(c, 12);
           }
           
           WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf = new WritableCellFormat(wf);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf.setAlignment(Alignment.CENTRE);
           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           
           Label label = new Label(0,0, "农作物生长发育状况监测数据报表", wcf);
           sheet.addCell(label);
           
           WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf2 = new WritableCellFormat(wf2);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf2.setAlignment(Alignment.LEFT);
           wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           Label label2 = new Label(0,1, "台站名称:"+reportHeader.getReportStationName(), wcf2);
           sheet.addCell(label2);
           
           Label label4 = new Label(8,1, "监测日期:"
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
           sheet.addCell(new Label(1,2, "播种期", wcf3));
           sheet.addCell(new Label(2,2, "出苗期", wcf3));
           sheet.addCell(new Label(3,2, "三叶期", wcf3));
           sheet.addCell(new Label(4,2, "拔节期", wcf3));
           sheet.addCell(new Label(5,2, "抽穗期", wcf3));
           sheet.addCell(new Label(6,2, "抽雄期", wcf3));
           sheet.addCell(new Label(7,2, "开花期", wcf3));
           sheet.addCell(new Label(8,2, "吐丝期", wcf3));
           sheet.addCell(new Label(9,2, "乳熟期", wcf3));
           sheet.addCell(new Label(10,2, "成熟期", wcf3));
           sheet.addCell(new Label(11,2, "生长高度", wcf3));
           sheet.addCell(new Label(12,2, "生长状况", wcf3));
           
           /**
            * 报表数据填充开始
            */
           if(dataSource != null && dataSource.size() > 0){
	           for(int i=4; i < dataSource.size()+4; i++){
	        	   sheet.setRowView(i, 550);
	        	   sheet.addCell(new Label(0,i,dataSource.get(i-4).getCropName(),wcf3));
                   sheet.addCell(new Label(1,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getSowingDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(2,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getSeedlingDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(3,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getTrefoilDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(4,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getJointingDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(5,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getHeadingDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(6,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getTasselingDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(7,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getFloweringDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(8,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getSilkingDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(9,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getMilkyDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(10,i, 
                		   DateUtils.formatDate(dataSource.get(i-4).getMaturityDate(), DateUtils.YYYY_MM_DD), wcf3));
                   sheet.addCell(new Label(11,i,CommonUtils.formatNumber(dataSource.get(i-4).getGrowthHeight(),2), wcf3));
                   sheet.addCell(new Label(12,i, dataSource.get(i-4).getGrowthCondition(), wcf3));
	           }
           }
           /**
            * 生成空表格
            */
           for(int j =0; j<2; j++){
        	   int nextRow = sheet.getRows();
        	   sheet.setRowView(nextRow, 550);
	           for(int i=0; i <13; i++){
	               sheet.addCell(new Label(i,nextRow, null, wcf3));
	           }
           }
           
           /**
            * 报表数据填充结束
            */
           int nextRow = sheet.getRows();
           sheet.setRowView(nextRow, 450);
           sheet.addCell(new Label(0,nextRow, "备注", wcf3));
           sheet.mergeCells(1, nextRow, 12, nextRow);
           sheet.addCell(new Label(1,nextRow, reportHeader.getReportRemarks(), wcf3));
           
           int totalRows = sheet.getRows();
           sheet.mergeCells(0, totalRows, 2, totalRows);
           sheet.mergeCells(8, totalRows, 12, totalRows);
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
		return cropGrowthMonitorDao.getTotalCount();
	}
}
