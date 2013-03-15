package com.rubik.eds.service.soil;

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
import com.rubik.eds.dao.TbSoilErosionMonitorDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbSoilErosionMonitor;
import com.rubik.eds.entity.TbSoilErosionReport;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.support.common.utils.DateUtils;

@Service
@Transactional
public class SoilMonitorService extends FtpShareLocalFile{

	@Autowired
	private TbSoilErosionMonitorDao erosionMonitorDao;
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbSoilErosionMonitor> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return erosionMonitorDao.getSoilErosionByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbSoilErosionReport> getSoilErosionReportData(ReportHeader reportHeader){
		return erosionMonitorDao.getSoilErosionReportData(reportHeader);
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbSoilErosionMonitor findById(Integer id){
		return erosionMonitorDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbSoilErosionMonitor user){
		return erosionMonitorDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbSoilErosionMonitor user){
		return erosionMonitorDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return erosionMonitorDao.deleteById(id);
	}
	
	/**
	 * 生成报表
	 * @param response
	 * @param dataSource
	 */
	public void createReport(HttpServletResponse response, ReportHeader reportHeader, List<TbSoilErosionReport> erosionReports) {
		try{
			//TbSoilErosionReport erosionReport = erosionReports.get(0);
			
			String filename = "Z_STJC_TRF_C5_"+ reportHeader.getReportStationId() +"_"
			 +DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSS);
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");   
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".xls");
			
			ServletOutputStream out = response.getOutputStream();
			
			File file = new File(super.getPathname(filename));
			OutputStream outputStream = new FileOutputStream(file);
			WritableWorkbook book = Workbook.createWorkbook(outputStream);
           // 生成名为“第一页”的工作表，参数0表示这是第一页
           WritableSheet sheet = book.createSheet("土壤风蚀监测数据报表", 0);
           
           //合并单元格
           sheet.mergeCells(0, 0, 5, 0);
           sheet.mergeCells(0, 1, 2, 1);
           sheet.mergeCells(3, 1, 5, 1);
           
           
           //设置高度
           sheet.setRowView(0, 650);
           for(int i=1;i<8;i++){
        	   sheet.setRowView(i, 500);
           }
           
           //设置宽度
           sheet.setColumnView(0, 30);
           for(int c=1; c<6; c++){
        	   sheet.setColumnView(c, 15);
           }
           
           WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf = new WritableCellFormat(wf);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf.setAlignment(Alignment.CENTRE);
           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           
           Label label = new Label(0,0, "土壤风蚀监测数据报表", wcf);
           sheet.addCell(label);
           
           WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),9,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf2 = new WritableCellFormat(wf2);
           //wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
           wcf2.setAlignment(Alignment.LEFT);
           wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           sheet.addCell(new Label(0,1, "台站名称:"+reportHeader.getReportStationName(), wcf2));
           
           sheet.addCell(new Label(3,1, "监测日期:"
                   +DateUtils.formatDate(CommonUtils.getLastdayOfLastmonth(reportHeader.getReportStartDate()),DateUtils.CH_YYYYMMDD), wcf2));
           
           
           //
           WritableFont wf3 = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD,false); 
           WritableCellFormat wcf3 = new WritableCellFormat(wf3);
           wcf3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
           wcf3.setAlignment(Alignment.CENTRE);
           wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
           
           //wcf3.setBorder(jxl.format.Border.TOP,jxl.format.BorderLineStyle.MEDIUM);
           wcf3.setWrap(true);
           sheet.addCell(new Label(0,2,"测定日期（月、日）",wcf3));
           sheet.addCell(new Label(0,3,"观测区域内平均风积厚度（CM）",wcf3));
           sheet.addCell(new Label(0,4,"观测区域内平均风蚀厚度（CM）",wcf3));
           sheet.addCell(new Label(0,5,"观测区域外平均风积厚度（CM）",wcf3));
           sheet.addCell(new Label(0,6,"观测区域外平均风蚀厚度（CM）",wcf3));
           for(int i=2; i<7; i++){
        	   for(int j=1;j<6;j++){
        		   sheet.addCell(new Label(j,i,null,wcf3));
        	   }
           }
           /**
            * 填写真实数据
            */
           int length = erosionReports.size();
           for(int i=length-1; i>=0 ; i--){
           sheet.addCell(new Label(length-i,2,
        		   DateUtils.formatDate(erosionReports.get(i).getSoilMonitorDate(),DateUtils.SOIL_FORMAT), wcf3));
           sheet.addCell(new Label(length-i,3,CommonUtils.formatString(erosionReports.get(i).getAvgInnerSoilProduct()),wcf3));
           sheet.addCell(new Label(length-i,4,CommonUtils.formatString(erosionReports.get(i).getAvgInnerSoilLose()),wcf3));
           sheet.addCell(new Label(length-i,5,CommonUtils.formatString(erosionReports.get(i).getAvgOutSoilProduct()),wcf3));
           sheet.addCell(new Label(length-i,6,CommonUtils.formatString(erosionReports.get(i).getAvgOutSoilLose()),wcf3));
           }
           /**
            * 报表数据填充开始
            */
           int totalRows = 7;
           sheet.mergeCells(0, totalRows, 2, totalRows);
           sheet.mergeCells(3, totalRows, 5, totalRows);
           //设置高度
           sheet.setRowView(totalRows, 500);
           sheet.addCell(new Label(0,totalRows, "填表人:"+reportHeader.getReporterName(), wcf2));
           sheet.addCell(new Label(3,totalRows, "上报时间:"
                   +DateUtils.formatDate(reportHeader.getReportDate(), DateUtils.CH_YYYYMMDD), wcf2));
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
	 * 根据两个月数据计算风积，风蚀数据
	 * @param erosionMonitors
	 * @return
	 */
	public TbSoilErosionReport generateReportDate(List<TbSoilErosionReport> erosionReports, ReportHeader reportHeader){
		/**
		 * 定义并初始化
		 */
		TbSoilErosionReport erosionReport = null;
		/**
		 * 循环遍历两个月数据
		 */
		if(erosionReports != null && erosionReports.size() > 0 ){
			if(erosionReports.get(0).getOperationTime().equals(reportHeader.getReportStartDate())){
				erosionReport = erosionReports.get(0);
				erosionReport.setAvgInnerSoilLose(Float.valueOf(CommonUtils.formatNumber(erosionReport.getInnerSoilErosion(),1)));
				erosionReport.setAvgOutSoilLose(Float.valueOf(CommonUtils.formatNumber(erosionReport.getOutSoilErosion(),1)));
				erosionReport.setSoilMonitorDate(CommonUtils.getLastdayOfLastmonth(erosionReport.getOperationTime()));
				
				//根据第二个月数据计算本月报表数据
				if(erosionReports.size() == 2){
					TbSoilErosionReport tempReport = new TbSoilErosionReport();
					tempReport = erosionReports.get(1);
					float innerTemp = erosionReport.getInnerSoilErosion() - tempReport.getInnerSoilErosion();
					float outTemp = erosionReport.getOutSoilErosion() - tempReport.getOutSoilErosion();
					if(innerTemp >= 0){
						erosionReport.setAvgInnerSoilLose(innerTemp);
					}else{
						erosionReport.setAvgInnerSoilLose(null);
						erosionReport.setAvgInnerSoilProduct(Math.abs(innerTemp));
					}
					
					if(outTemp >= 0){
						erosionReport.setAvgOutSoilLose(outTemp);
					}else{
						erosionReport.setAvgOutSoilLose(null);
						erosionReport.setAvgOutSoilProduct(Math.abs(outTemp));
					}
				}
			}
		}
		return erosionReport;
	}
	/**
	 * 删除相同月份的监测报表数据
	 * @param soilErosionReport
	 * @return
	 */
	public int deleteByTime(TbSoilErosionMonitor soilErosionMonitor){
		return erosionMonitorDao.deleteByTime(soilErosionMonitor);
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return erosionMonitorDao.getTotalCount();
	}
}
