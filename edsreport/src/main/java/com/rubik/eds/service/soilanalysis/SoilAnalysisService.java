package com.rubik.eds.service.soilanalysis;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.common.utils.CommonUtils;
import com.rubik.eds.dao.TbSoilAnalysisMonitorDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.SoilMonitorConstant;
import com.rubik.eds.entity.TbSoilAnalysisMonitor;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.support.common.utils.DateUtils;

@Service
@Transactional
public class SoilAnalysisService extends FtpShareLocalFile{
	@Autowired
	private TbSoilAnalysisMonitorDao analysisMonitorDao;
	
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbSoilAnalysisMonitor> getSoilAnalysisByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return analysisMonitorDao.getSoilAnalysisByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbSoilAnalysisMonitor findById(Integer id){
		return analysisMonitorDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbSoilAnalysisMonitor user){
		return analysisMonitorDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbSoilAnalysisMonitor user){
		return analysisMonitorDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return analysisMonitorDao.deleteById(id);
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return analysisMonitorDao.getTotalCount();
	}
	/**
	 * 根据报文头查询报文数据
	 * @param reportHeader
	 * @return
	 */
	public TbSoilAnalysisMonitor getReportDataByDate(ReportHeader reportHeader){
		return analysisMonitorDao.getReportDataByDate(reportHeader);
	}
	
	/**
	 * 测试FTP是否连通
	 * @throws Exception
	 */
	public void pingFtpConnect() throws Exception{
    	this.connectFTPServer();
    	if(!this.changeDirectory(super.getFtpShareLocalEntity().getFtpBasePath())){
    		closeFTPClient();
    		throw new Exception ("FTP服务器路径"+super.getFtpShareLocalEntity().getFtpBasePath()+"不存在!");
    	}
    	closeFTPClient();
    }
	
	/**
	 * 生成报文
	 */
	public void createReport(HttpServletResponse response, TbSoilAnalysisMonitor soilAnalysisMonitor, 
			SoilMonitorConstant soilMonitorConstant, ReportHeader reportHeader){
		try{
			String filename = "A0" + DateUtils.formatDate(new Date(), DateUtils.SOIL_ANALYSIS);
			response.setContentType("text/plain;charset=UTF-8");   
			ServletOutputStream out = null;
			StringBuilder reports = new StringBuilder();
		// 定义文件名
		if(soilAnalysisMonitor != null && soilMonitorConstant != null){
			filename = filename+"."+soilMonitorConstant.getReportSuffix();
			response.addHeader("Content-Disposition", "attachment; filename="+ filename);
			out = response.getOutputStream();
			Map<String, String> soilHumidities = this.getSoilHumidities(
					this.getStepSoilWeights(soilAnalysisMonitor, soilMonitorConstant), soilMonitorConstant);
			//ZCZC
			reports.append("ZCZC\r\n");
			//ABCI50 BUWS 060000
			reports.append("ABCI50 B");
			reports.append(soilMonitorConstant.getReportSuffix());
			reports.append(" ");
			reports.append(DateUtils.formatDate(reportHeader.getReportStartDate(), "dd"));
			reports.append("0000\r\n");
			//ABXX  08061
			reports.append("ABXX ");
			reports.append(DateUtils.formatDate(reportHeader.getReportStartDate(), "MMdd"));
			reports.append("1\r\n");
			//(TR)  53644 01199 17834 23334 317//=
			reports.append("(TR)  ");
			reports.append(reportHeader.getReportStationId());
			reports.append(" 011");
			reports.append(soilHumidities.get("soilHumidity10"));
			reports.append(" 1");
			reports.append(soilHumidities.get("soilHumidity20"));
			reports.append(soilHumidities.get("soilHumidity30"));
			reports.append(" 2");
			reports.append(soilHumidities.get("soilHumidity40"));
			reports.append(soilHumidities.get("soilHumidity50"));
			reports.append(CommonUtils.formatLength(soilAnalysisMonitor.getDrySoilHeight()));
			reports.append("=\r\n");
			reports.append("NNNN");
			
			if("2".equals(reportHeader.getHowSaveFile()) || "3".equals(reportHeader.getHowSaveFile())){
				//上传到FTP服务器
				super.connectFTPServer();
				super.uploadFile(new ByteArrayInputStream(reports.toString().getBytes("UTF-8")), filename);
			}
			//如果用户选择保存共享
	        if("1".equals(reportHeader.getHowSaveFile()) || "3".equals(reportHeader.getHowSaveFile())){
	        	  super.saveToShare(reports.toString(), filename);
	        }
			
		}else{
			response.addHeader("Content-Disposition", "attachment; filename="+ filename +".error");
			out = response.getOutputStream();
			reports.append("无数据导出，可能无对应日期的土壤水分观测数据报表！");
		}
		out.write(reports.toString().getBytes("UTF-8"));
		out.flush();   
	    out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 计算各层的土壤含水率
	 * @param soilAnalysisMonitor
	 * @param soilMonitorConstant
	 * @return
	 */
	public Map<String, Float> getStepSoilWeights(TbSoilAnalysisMonitor soilAnalysisMonitor, 
			SoilMonitorConstant soilMonitorConstant){
		//定义并初始化
		Map<String, Float> soilWeights = new HashMap<String, Float>();
		Float point1_10 = 
				(soilAnalysisMonitor.getWetSoilboxWeight1_10() - soilAnalysisMonitor.getDrySoilboxWeight1_10())/
				(soilAnalysisMonitor.getDrySoilboxWeight1_10() - soilMonitorConstant.getBoxWeight1_10());
		Float point2_10 = 
				(soilAnalysisMonitor.getWetSoilboxWeight2_10() - soilAnalysisMonitor.getDrySoilboxWeight2_10())/
				(soilAnalysisMonitor.getDrySoilboxWeight2_10() - soilMonitorConstant.getBoxWeight2_10());
		Float point3_10 = 
				(soilAnalysisMonitor.getWetSoilboxWeight3_10() - soilAnalysisMonitor.getDrySoilboxWeight3_10())/
				(soilAnalysisMonitor.getDrySoilboxWeight3_10() - soilMonitorConstant.getBoxWeight3_10());
		Float point4_10 = 
				(soilAnalysisMonitor.getWetSoilboxWeight4_10() - soilAnalysisMonitor.getDrySoilboxWeight4_10())/
				(soilAnalysisMonitor.getDrySoilboxWeight4_10() - soilMonitorConstant.getBoxWeight4_10());
		//计算第一层含水率
		Float soilWeight10 = (point1_10+point2_10+point3_10+point4_10)/4;
		soilWeights.put("soilWeight10", Float.valueOf(CommonUtils.formatNumber(Float.valueOf(Math.round(soilWeight10*1000))/10, 1)));
		
		Float point1_20 = 
				(soilAnalysisMonitor.getWetSoilboxWeight1_20() - soilAnalysisMonitor.getDrySoilboxWeight1_20())/
				(soilAnalysisMonitor.getDrySoilboxWeight1_20() - soilMonitorConstant.getBoxWeight1_20());
		Float point2_20 = 
				(soilAnalysisMonitor.getWetSoilboxWeight2_20() - soilAnalysisMonitor.getDrySoilboxWeight2_20())/
				(soilAnalysisMonitor.getDrySoilboxWeight2_20() - soilMonitorConstant.getBoxWeight2_20());
		Float point3_20 = 
				(soilAnalysisMonitor.getWetSoilboxWeight3_20() - soilAnalysisMonitor.getDrySoilboxWeight3_20())/
				(soilAnalysisMonitor.getDrySoilboxWeight3_20() - soilMonitorConstant.getBoxWeight3_20());
		Float point4_20 = 
				(soilAnalysisMonitor.getWetSoilboxWeight4_20() - soilAnalysisMonitor.getDrySoilboxWeight4_20())/
				(soilAnalysisMonitor.getDrySoilboxWeight4_20() - soilMonitorConstant.getBoxWeight4_20());
		//计算第二层含水率
		Float soilWeight20 = (point1_20+point2_20+point3_20+point4_20)/4;
		soilWeights.put("soilWeight20", Float.valueOf(CommonUtils.formatNumber(Float.valueOf(Math.round(soilWeight20*1000))/10, 1)));
		
		Float point1_30 = 
				(soilAnalysisMonitor.getWetSoilboxWeight1_30() - soilAnalysisMonitor.getDrySoilboxWeight1_30())/
				(soilAnalysisMonitor.getDrySoilboxWeight1_30() - soilMonitorConstant.getBoxWeight1_30());
		Float point2_30 = 
				(soilAnalysisMonitor.getWetSoilboxWeight2_30() - soilAnalysisMonitor.getDrySoilboxWeight2_30())/
				(soilAnalysisMonitor.getDrySoilboxWeight2_30() - soilMonitorConstant.getBoxWeight2_30());
		Float point3_30 = 
				(soilAnalysisMonitor.getWetSoilboxWeight3_30() - soilAnalysisMonitor.getDrySoilboxWeight3_30())/
				(soilAnalysisMonitor.getDrySoilboxWeight3_30() - soilMonitorConstant.getBoxWeight3_30());
		Float point4_30 = 
				(soilAnalysisMonitor.getWetSoilboxWeight4_30() - soilAnalysisMonitor.getDrySoilboxWeight4_30())/
				(soilAnalysisMonitor.getDrySoilboxWeight4_30() - soilMonitorConstant.getBoxWeight4_30());
		//计算第三层含水率
		Float soilWeight30 = (point1_30+point2_30+point3_30+point4_30)/4;
		soilWeights.put("soilWeight30", Float.valueOf(CommonUtils.formatNumber(Float.valueOf(Math.round(soilWeight30*1000))/10, 1)));
		
		Float point1_40 = 
				(soilAnalysisMonitor.getWetSoilboxWeight1_40() - soilAnalysisMonitor.getDrySoilboxWeight1_40())/
				(soilAnalysisMonitor.getDrySoilboxWeight1_40() - soilMonitorConstant.getBoxWeight1_40());
		Float point2_40 = 
				(soilAnalysisMonitor.getWetSoilboxWeight2_40() - soilAnalysisMonitor.getDrySoilboxWeight2_40())/
				(soilAnalysisMonitor.getDrySoilboxWeight2_40() - soilMonitorConstant.getBoxWeight2_40());
		Float point3_40 = 
				(soilAnalysisMonitor.getWetSoilboxWeight3_40() - soilAnalysisMonitor.getDrySoilboxWeight3_40())/
				(soilAnalysisMonitor.getDrySoilboxWeight3_40() - soilMonitorConstant.getBoxWeight3_40());
		Float point4_40 = 
				(soilAnalysisMonitor.getWetSoilboxWeight4_40() - soilAnalysisMonitor.getDrySoilboxWeight4_40())/
				(soilAnalysisMonitor.getDrySoilboxWeight4_40() - soilMonitorConstant.getBoxWeight4_40());
		//计算第四层含水率
		Float soilWeight40 = (point1_40+point2_40+point3_40+point4_40)/4;
		soilWeights.put("soilWeight40", Float.valueOf(CommonUtils.formatNumber(Float.valueOf(Math.round(soilWeight40*1000))/10, 1)));
		
		Float point1_50 = 
				(soilAnalysisMonitor.getWetSoilboxWeight1_50() - soilAnalysisMonitor.getDrySoilboxWeight1_50())/
				(soilAnalysisMonitor.getDrySoilboxWeight1_50() - soilMonitorConstant.getBoxWeight1_50());
		Float point2_50 = 
				(soilAnalysisMonitor.getWetSoilboxWeight2_50() - soilAnalysisMonitor.getDrySoilboxWeight2_50())/
				(soilAnalysisMonitor.getDrySoilboxWeight2_50() - soilMonitorConstant.getBoxWeight2_50());
		Float point3_50 = 
				(soilAnalysisMonitor.getWetSoilboxWeight3_50() - soilAnalysisMonitor.getDrySoilboxWeight3_50())/
				(soilAnalysisMonitor.getDrySoilboxWeight3_50() - soilMonitorConstant.getBoxWeight3_50());
		Float point4_50 = 
				(soilAnalysisMonitor.getWetSoilboxWeight4_50() - soilAnalysisMonitor.getDrySoilboxWeight4_50())/
				(soilAnalysisMonitor.getDrySoilboxWeight4_50() - soilMonitorConstant.getBoxWeight4_50());
		//计算第五层含水率
		Float soilWeight50 = (point1_50+point2_50+point3_50+point4_50)/4;
		soilWeights.put("soilWeight50", Float.valueOf(CommonUtils.formatNumber(Float.valueOf(Math.round(soilWeight50*1000))/10, 1)));
		
		//返回
		return soilWeights;
	}
	
	/**
	 * 计算土壤相对湿度
	 * @param soilWeights
	 * @param soilMonitorConstant
	 * @return
	 */
	public Map<String, String> getSoilHumidities(Map<String, Float> soilWeights, SoilMonitorConstant soilMonitorConstant){
		//定义并初始化
		Map<String, String> soilHumidities = new HashMap<String, String>();
		Float soilHumidity10 = (soilWeights.get("soilWeight10")/soilMonitorConstant.getFieldCapacity10())*100;
		Float soilHumidity20 = (soilWeights.get("soilWeight20")/soilMonitorConstant.getFieldCapacity20())*100;
		Float soilHumidity30 = (soilWeights.get("soilWeight30")/soilMonitorConstant.getFieldCapacity30())*100;
		Float soilHumidity40 = (soilWeights.get("soilWeight40")/soilMonitorConstant.getFieldCapacity40())*100;
		Float soilHumidity50 = (soilWeights.get("soilWeight50")/soilMonitorConstant.getFieldCapacity50())*100;
		
		//格式化土壤湿度
		soilHumidities.put("soilHumidity10", CommonUtils.formatRound(soilHumidity10));
		soilHumidities.put("soilHumidity20", CommonUtils.formatRound(soilHumidity20));
		soilHumidities.put("soilHumidity30", CommonUtils.formatRound(soilHumidity30));
		soilHumidities.put("soilHumidity40", CommonUtils.formatRound(soilHumidity40));
		soilHumidities.put("soilHumidity50", CommonUtils.formatRound(soilHumidity50));
		
		//返回
		return soilHumidities;
	} 
}
