package com.rubik.eds.common.utils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.write.NumberFormat;

import org.apache.ibatis.metadata.Column;

import com.rubik.eds.entity.FtpShareLocalEntity;
import com.rubik.eds.entity.SoilMonitorConstant;
import com.rubik.eds.entity.TbCropGrowthMonitor;
import com.rubik.eds.entity.TbCropYieldMonitor;
import com.rubik.eds.entity.TbDuneMoveMonitor;
import com.rubik.eds.entity.TbDustMonitor;
import com.rubik.eds.entity.TbGrassMonitor;
import com.rubik.eds.entity.TbPlantDetails;
import com.rubik.eds.entity.TbPlantSpeciesMonitor;
import com.rubik.eds.entity.TbSoilAnalysisMonitor;
import com.rubik.eds.entity.TbSoilErosionMonitor;
import com.rubik.eds.entity.TbSoilErosionReport;
import com.rubik.eds.entity.TbWaterMonitor;
import com.rubik.support.entity.TbGbLoginLog;
import com.rubik.support.entity.TbGbOperatingLog;
import com.rubik.support.entity.TbSystemDatadict;
import com.rubik.support.entity.TbSystemUser;

public class GenerateTable {

	
	
	/**
	 * 根据类获取类定义的变量集合
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Field> getTableColumn(Class clazz){
		//获取变量集合
		Field[] columnNames = clazz.getDeclaredFields();
		List<Field> columnList = new ArrayList<Field>();
		//循环获取变量名
		for(Field field : columnNames){
			columnList.add(field);
		}
		return columnList;
	}
	
	public void getCreateTableSQL(List<Field> columnList, String tableName){
		StringBuilder sb = new StringBuilder();
		sb.append("create table ");
		sb.append(tableName);
		sb.append("(");
		for(Field column : columnList){
			sb.append(column.getName());
			sb.append(" float(10,2) default null, \n");
		}
		sb.append(")");
		System.out.println(sb.toString());
	}
	/**
	 * IBATIS数据列字符串
	 * @param columnList
	 */
	public void getColumnAppend(List<Field> columnList){
		StringBuilder sb = new StringBuilder();
		for(Field column : columnList){
			//sb.append("smc.");
			sb.append(column.getName());
			sb.append(", ");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * IBATIS数据值字符串
	 * @param columnList
	 */
	public void getColumnValueAppend(List<Field> columnList){
		StringBuilder sb = new StringBuilder();
		for(Field column : columnList){
			sb.append("#{");
			sb.append(column.getName());
			sb.append("}, ");
		}
		System.out.println(sb.toString());
	}
	/**
	 * IBATIS结果集resultMap字符串
	 * @param columnList
	 */
	public void getResultMapAppend(List<Field> columnList){
		StringBuilder sb = new StringBuilder();
		for(Field column : columnList){
			sb.append("<result property=\"");
			sb.append(column.getName());
			sb.append("\" column=\"");
			sb.append(column.getName());
			sb.append("\"/>\n");
		}
		System.out.println(sb.toString());
	}
	/**
	 * 获取数据库插入语句
	 */
	public void getUpdateSQL(List<Field> columnList, String tableName){
		StringBuilder sb = new StringBuilder("UPDATE ");
		sb.append(tableName);
		sb.append(" SET ");
		for(Field column : columnList){
			sb.append(column.getName());
			sb.append("=#{");
			sb.append(column.getName());
			sb.append("}, ");
		}
		sb.append(" WHERE id = #{id}");
		System.out.println(sb.toString());
	}
	/**
	 * 获取校验html
	 */
	public void getValidators(List<String> columnList){
		StringBuilder sb = new StringBuilder("");
		for(String column : columnList){
			sb.append("<li><label for=\"creditCardVO.");
			sb.append(column);
			sb.append("\" class=\"error\"></label></li>\n");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * 根据实体生成接口报文协议
	 */
	public void getInterfaceDetails(List<String> columnList){
		StringBuilder sb = new StringBuilder("<root>\n");
		//<root><controlNum></controlNum></root>
		for(String column : columnList){
			sb.append("  <");
			sb.append(column);
			sb.append(">");
			sb.append("</");
			sb.append(column);
			sb.append(">\n");
		}
		sb.append("</root>");
		System.out.println(sb.toString());
	}
	
	/**
	 * xml转换成实体java语句
	 * @param columnList
	 */
	public void xmlTocolumn(List<String> columnList){
		StringBuilder sb = new StringBuilder("");
		//westernUnionVO.setRunningNum(doc.selectSingleNode("//root/serialCode").getText());
		for(String column : columnList){
			sb.append("westernUnionVO.set");
			sb.append(column.substring(0, 1).toUpperCase()+column.substring(1, column.length()));
			sb.append("(doc.selectSingleNode(\"//root/");
			sb.append(column);
			sb.append("\").getText());\n");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * 实体转换成xmljava语句
	 * @param columnList
	 */
	public void columnToxml(List<String> columnList){
		StringBuilder sb = new StringBuilder("");
		//westernUnionVO.setRunningNum(doc.selectSingleNode("//root/serialCode").getText());
		for(String column : columnList){
//			if(StringUtils.isNotEmpty(westernUnionVO.getRunningNum())){
//				doc.selectSingleNode("//root/serialCode").setText(westernUnionVO.getRunningNum());
//			}else{}
			sb.append("if(StringUtils.isNotEmpty(");
			sb.append("westernUnionVO.get");
			sb.append(column.substring(0, 1).toUpperCase()+column.substring(1, column.length()));
			sb.append("())){\n");
			sb.append("  doc.selectSingleNode(\"//root/");
			sb.append(column);
			sb.append("\").setText(");
			sb.append("westernUnionVO.get");
			sb.append(column.substring(0, 1).toUpperCase()+column.substring(1, column.length()));
			sb.append("());\n");
			sb.append("}else{\n");
			sb.append("  doc.selectSingleNode(\"//root/");
			sb.append(column);
			sb.append("\").setText(\"\");\n");
			sb.append("}\n");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * ajax页面数据展示语句
	 */
	public void ajaxGridDataColumn(List<Field> columnList){
		StringBuilder sb = new StringBuilder("");
		//<p:griddata.column valuePropertyName="runningNum" name="runningNum" />
		for(Field column : columnList){
			sb.append("<p:griddata.column valuePropertyName=\"");
			sb.append(column.getName());
			sb.append("\" name=\"");
			sb.append(column.getName());
			sb.append("\" />\n");
		}
		System.out.println(sb.toString());
	}
	/**
	 * Ajax获取数据自动填充表单语句
	 */
	public void autoFillFormColumn(List<Field> columnList){
		StringBuilder sb = new StringBuilder("");
		//<p:griddata.column valuePropertyName="runningNum" name="runningNum" />
		for(Field column : columnList){
			sb.append("document.getElementById(\"");
			sb.append(column.getName());
			sb.append("\").value=rspJson[0].");
			sb.append(column.getName());
			sb.append(";\n");
		}
		System.out.println(sb.toString());
	}
	/**
	 * 报表打印组织数据
	 * @param columnList
	 */
	public void reportDataAppend(List<Field> columnList, String entityName){
		StringBuilder sb = new StringBuilder("");
		//<p:griddata.column valuePropertyName="runningNum" name="runningNum" />
		for(Field column : columnList){
			sb.append("tempParams.append(\";");
			sb.append(column.getName());
			sb.append("=\");\n");
			sb.append("tempParams.append(");
			sb.append(entityName);
			sb.append(".get");
			sb.append(column.getName().substring(0, 1).toUpperCase()+column.getName().substring(1, column.getName().length()));
			sb.append("());\n");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * 报表打印组织数据
	 * @param columnList
	 * 
	 */
	public void jsonDataAppend(List<Field> columnList, String entityName){
		StringBuilder sb = new StringBuilder("");
		//"rcvFirstName":"${westernUnionVO.rcvFirstName}"
		for(Field column : columnList){
			sb.append("\"");
			sb.append(column.getName());
			sb.append("\":\"${");
			sb.append(entityName);
			sb.append(".");
			sb.append(column.getName());
			sb.append("}\",");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * 生成前台页面
	 */
	public void getTableHtml(List<Field> columnList){
		StringBuilder sb = new StringBuilder("");
		//"rcvFirstName":"${westernUnionVO.rcvFirstName}"
		//<th data-options="field:'soilName'" >牧草名称</th>
		int i=1;
		for(Field column : columnList){
			sb.append("<th data-options=\"field:'");
			sb.append(column.getName());
			sb.append("'\" >区域外");
			sb.append(i-27);
			sb.append("</th>\n");
			i++;
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * 生成前台页面
	 */
	public void getTableValueHtml(List<Field> columnList){
		StringBuilder sb = new StringBuilder("");
		//"rcvFirstName":"${westernUnionVO.rcvFirstName}"
		//<th data-options="field:'soilName'" >牧草名称</th>
		// <td>${soil.stationId }</td>
		for(Field column : columnList){
			sb.append("<td>${soilanalysis.");
			sb.append(column.getName());
			sb.append(" }</td>\n");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		GenerateTable generateTable = new GenerateTable();
		generateTable.getCreateTableSQL(generateTable.getTableColumn(FtpShareLocalEntity.class), "tb_ftp_share_local");
		generateTable.getColumnAppend(generateTable.getTableColumn(FtpShareLocalEntity.class));
		generateTable.getColumnValueAppend(generateTable.getTableColumn(FtpShareLocalEntity.class));
		generateTable.getResultMapAppend(generateTable.getTableColumn(FtpShareLocalEntity.class));
		generateTable.getUpdateSQL(generateTable.getTableColumn(FtpShareLocalEntity.class),"tb_ftp_share_local");
		generateTable.getTableHtml(generateTable.getTableColumn(FtpShareLocalEntity.class));
		generateTable.getTableValueHtml(generateTable.getTableColumn(FtpShareLocalEntity.class));
		//generateTable.getValidators(generateTable.getTableColumn(CreditCardVO.class));
		//generateTable.getInterfaceDetails(generateTable.getTableColumn(WesternUnionVO.class));
		//generateTable.xmlTocolumn(generateTable.getTableColumn(WesternUnionVO.class));
		//generateTable.columnToxml(generateTable.getTableColumn(WesternUnionVO.class));
		//generateTable.ajaxGridDataColumn(generateTable.getTableColumn(WesternUnionSendVO.class));
		generateTable.autoFillFormColumn(generateTable.getTableColumn(FtpShareLocalEntity.class));
		//generateTable.reportDataAppend(generateTable.getTableColumn(WesternUnionSendVO.class), "westernUnionSendVO");
		//generateTable.jsonDataAppend(generateTable.getTableColumn(WesternUnionSendVO.class), "westernUnionSendVO");
		//		Pattern pattern = Pattern.compile("[\\d]*[\\s]*[◆]*");
//		Matcher matcher = pattern.matcher("22");
		//System.out.println("".replaceAll("[[\\d]*[\\s]*[◆]*]", ""));
	}
}
