package com.rubik.eds.service;  
  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;  
import java.util.Arrays;  
import java.util.List;  

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
  
import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPClientConfig;  
import org.apache.commons.net.ftp.FTPFile;  
import org.apache.commons.net.ftp.FTPListParseEngine;  
import org.apache.commons.net.ftp.FTPReply;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubik.eds.dao.FtpShareLocalDao;
import com.rubik.eds.entity.FtpShareLocalEntity;

@Service
public class FtpShareLocalFile {  
 
	@Autowired
	private FtpShareLocalDao ftpShareLocalDao;
	private FtpShareLocalEntity ftpShareLocalEntity;
    private FTPClient ftp;

    /**
	 * @return the ftpShareLocalEntity
	 */
	public FtpShareLocalEntity getFtpShareLocalEntity() {
		return ftpShareLocalEntity;
	}

	/**
	 * @param ftpShareLocalEntity the ftpShareLocalEntity to set
	 */
	public void setFtpShareLocalEntity(FtpShareLocalEntity ftpShareLocalEntity) {
		this.ftpShareLocalEntity = ftpShareLocalEntity;
	}

	/**
     * FTP连接测试
     * @throws Exception
     */
    public void pingFtpConnectTest(FtpShareLocalEntity ftpShareLocalEntity) throws Exception{
    	this.connectFTPServer(ftpShareLocalEntity);
    	if(!this.changeDirectory(ftpShareLocalEntity.getFtpBasePath())){
    		closeFTPClient();
    		throw new Exception ("FTP服务器路径<"+ftpShareLocalEntity.getFtpBasePath()+">不存在!");
    	}
    	closeFTPClient();
    }
    
    /** 
     * 连接FTP服务器 
     *  
     * @param server 
     * @param uname 
     * @param password 
     * @return 
     * @throws Exception 
     */  
    public FTPClient connectFTPServer(FtpShareLocalEntity ftpShareLocalEntity) throws Exception {
    	ftp = new FTPClient();
        ftp.configure(getFTPClientConfig()); 
        ftp.setConnectTimeout(1000);
        try{
	        ftp.connect(ftpShareLocalEntity.getFtpAddress(), ftpShareLocalEntity.getFtpPort());
        }catch (Exception e) {
        	ftp.disconnect();
            ftp = null;
            throw new Exception("无法连接FTP服务器,请检查服务器地址!Server:" + ftpShareLocalEntity.getFtpAddress());
		}
        if (!ftp.login(ftpShareLocalEntity.getFtpUsername(), ftpShareLocalEntity.getFtpPassword())) {
            ftp.logout();  
            ftp.disconnect();
            ftp = null;
            throw new Exception("无法登录FTP服务器,请检查登录用户名及密码！");
        }
  
         // 文件类型,默认是ASCII  
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
        ftp.setControlEncoding("GBK");  
        // 设置被动模式  
        ftp.enterLocalPassiveMode();  
        ftp.setBufferSize(1024);  
        // 响应信息  
        int replyCode = ftp.getReplyCode();  
        if ((!FTPReply.isPositiveCompletion(replyCode))) {  
            // 关闭Ftp连接  
            closeFTPClient();  
            // 释放空间  
            ftp = null;  
            throw new Exception("FTP服务器无响应,请稍后再试![Server:");
        } else {  
            return ftp;  
        }  
    } 
    
    /** 
     * 连接FTP服务器 
     *  
     * @param server 
     * @param uname 
     * @param password 
     * @return 
     * @throws Exception 
     */  
    public FTPClient connectFTPServer() throws Exception {
    	ftp = new FTPClient();
    	ftpShareLocalEntity = ftpShareLocalDao.findUnique();
        ftp.configure(getFTPClientConfig()); 
        ftp.setConnectTimeout(1000);
        try{
	        ftp.connect(ftpShareLocalEntity.getFtpAddress(), ftpShareLocalEntity.getFtpPort());
        }catch (Exception e) {
        	ftp.disconnect();
            ftp = null;
        	throw new Exception("无法连接FTP服务器,请检查服务器地址!Server:" + ftpShareLocalEntity.getFtpAddress());
		}
        if (!ftp.login(ftpShareLocalEntity.getFtpUsername(), ftpShareLocalEntity.getFtpPassword())) {
            ftp.logout();  
            ftp.disconnect();
            ftp = null;
            throw new Exception("无法登录FTP服务器,请检查登录用户名及密码！");
        }
       
  
            // 文件类型,默认是ASCII  
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
        ftp.setControlEncoding("UTF-8");  
        // 设置被动模式  
        ftp.enterLocalPassiveMode();  
        ftp.setBufferSize(1024);  
        // 响应信息  
        int replyCode = ftp.getReplyCode();  
        if ((!FTPReply.isPositiveCompletion(replyCode))) {  
            // 关闭Ftp连接  
            closeFTPClient();  
            // 释放空间  
            ftp = null;  
            throw new Exception("FTP服务器无响应,请稍后再试![Server:");
        } else {  
            return ftp;  
        }  
    } 
  
    /** 
     * 配置FTP连接参数 
     *  
     * @return 
     * @throws Exception 
     */  
    public FTPClientConfig getFTPClientConfig() throws Exception {  
        String systemKey = FTPClientConfig.SYST_NT;  
        String serverLanguageCode = "zh";  
        FTPClientConfig conf = new FTPClientConfig(systemKey);  
        conf.setServerLanguageCode(serverLanguageCode);  
        conf.setDefaultDateFormatStr("yyyy-MM-dd");  
        return conf;  
    }  
  
    /** 
     * 向FTP根目录上传文件 
     *  
     * @param localFile 
     * @param newName 
     *            新文件名 
     * @throws Exception 
     */  
    public Boolean uploadFile(File reportFile, String fileName)  
            throws Exception {
    	 InputStream input = null;
        boolean success = false;
        input = new FileInputStream(reportFile);  
        success = ftp.storeFile(new String(fileName.getBytes("GBK"),"ISO-8859-1"), input);  
        if (!success) {
        	if (input != null) {  
                input.close();  
            }
            throw new Exception("文件上传FTP失败!");  
        }  
        return success;  
    }  
  
    /** 
     * 向FTP根目录上传文件 
     *  
     * @param input 
     * @param newName 
     *            新文件名 
     * @throws Exception 
     */  
    public Boolean uploadFile(InputStream input, String fileName) throws Exception {  
    	boolean success = false;  
        // 改变当前路径到指定路径
        if (!this.changeDirectory(ftpShareLocalEntity.getFtpBasePath())) {
        	if (input != null) {  
                input.close();  
            }
        	throw new Exception ("FTP服务器路径<"+ftpShareLocalEntity.getFtpBasePath()+">不存在!");
        }  
        success = ftp.storeFile(new String(fileName.getBytes("GBK"),"ISO-8859-1"), input);  
        if (!success) {
			if (input != null) {  
			    input.close();  
			}
           throw new Exception("文件上传FTP失败!");
        }
        return success;
    }  
  
    /** 
     * 向FTP指定路径上传文件 
     *  
     * @param localFile 
     * @param newName 
     *            新文件名 
     * @param remoteFoldPath 
     * @throws Exception 
     */  
    public Boolean uploadFile(File reportFile, String fileName, String remoteFoldPath) throws Exception {  
        InputStream input = null;  
        boolean success = false;  
        input = new FileInputStream(reportFile);  
        // 改变当前路径到指定路径  
        if (!this.changeDirectory(remoteFoldPath)) {
        	if (input != null) {  
                input.close();  
            }
        	throw new Exception ("FTP服务器路径<"+remoteFoldPath+">不存在!");
        }  
        success = ftp.storeFile(new String(fileName.getBytes("GBK"),"ISO-8859-1"), input);  
        if (!success) {
        	if (input != null) {  
                input.close();  
            }
            throw new Exception("文件上传FTP失败!");
        }  
        return success;  
    }  
  
    /** 
     * 向FTP指定路径上传文件 
     *  
     * @param input 
     * @param newName 
     *            新文件名 
     * @param remoteFoldPath 
     * @throws Exception 
     */  
    public Boolean uploadFile(InputStream input, String fileName,  String remoteFoldPath) throws Exception {  
        boolean success = false;  
        // 改变当前路径到指定路径
        if (!this.changeDirectory(remoteFoldPath)) {
        	if (input != null) {  
                input.close();  
            }
        	throw new Exception ("FTP服务器路径<"+remoteFoldPath+">不存在!");
        }  
        success = ftp.storeFile(new String(fileName.getBytes("GBK"),"ISO-8859-1"), input);  
        if (!success) {
			if (input != null) {  
			    input.close();  
			}
           throw new Exception("文件上传FTP失败!");
        }
        return success;
    }  
  
    /** 
     * 获取FTP服务器上指定路径下的文件列表 
     *  
     * @param filePath 
     * @return 
     */  
    public List<FTPFile> getFtpServerFileList(String remotePath)  throws Exception {  
        FTPListParseEngine engine = ftp.initiateListParsing(new String(remotePath.getBytes("GBK"),"ISO-8859-1"));  
        List<FTPFile> ftpfiles = Arrays.asList(engine.getNext(25));  
        return ftpfiles;  
    }  
  
    /** 
     * 获取FTP服务器上[指定路径]下的文件列表 
     *  
     * @param path 
     * @return 
     * @throws Exception 
     */  
    public List<FTPFile> getFileList(String remotePath) throws Exception {  
        List<FTPFile> ftpfiles = Arrays.asList(ftp.listFiles(new String(remotePath.getBytes("GBK"),"ISO-8859-1")));  
        return ftpfiles;  
    }  
  
    /** 
     * 获取FTP服务器[当前工作路径]下的文件列表 
     *  
     * @param path 
     * @return 
     * @throws Exception 
     */  
    public List<FTPFile> getFileList() throws Exception {  
        List<FTPFile> ftpfiles = Arrays.asList(ftp.listFiles());  
        return ftpfiles;  
    }  
  
    /** 
     * 改变FTP服务器工作路径  
     *  
     * @param remoteFoldPath 
     */  
    public Boolean changeDirectory(String remoteFoldPath) throws Exception {
        return ftp.changeWorkingDirectory(new String(remoteFoldPath.getBytes("GBK"),"ISO-8859-1"));  
    }  
  
    /** 
     * 删除文件 
     *  
     * @param remoteFilePath 
     * @return 
     * @throws Exception 
     */  
    public Boolean deleteFtpServerFile(String remoteFilePath) throws Exception {
        return ftp.deleteFile(new String(remoteFilePath.getBytes("GBK"),"ISO-8859-1"));  
    }  
  
    /** 
     * 创建目录 
     *  
     * @param remoteFoldPath 
     * @return 
     */  
    public boolean createFold(String remoteFoldPath) throws Exception {  
        boolean flag = ftp.makeDirectory(new String(remoteFoldPath.getBytes("GBK"),"ISO-8859-1"));  
        if (!flag) {  
            throw new Exception("创建FTP目录失败");  
        }  
        return false;  
    }  
  
    /** 
     * 删除目录 
     * @param remoteFoldPath 
     * @return 
     * @throws Exception 
     */  
    public boolean deleteFold(String remoteFoldPath) throws Exception {  
        return ftp.removeDirectory(new String(remoteFoldPath.getBytes("GBK"),"ISO-8859-1")) ;  
    }  
  
    /** 
     * 删除目录以及文件 
     *  
     * @param remoteFoldPath 
     * @return 
     */  
    public boolean deleteFoldAndsubFiles(String remoteFoldPath)  throws Exception {
    	remoteFoldPath = new String(remoteFoldPath.getBytes("GBK"),"ISO-8859-1");
        boolean success = false;  
        List<FTPFile> list = this.getFileList(remoteFoldPath);  
        if (list == null || list.size() == 0) {  
            return deleteFold(remoteFoldPath);  
        }  
        for (FTPFile ftpFile : list) {  
            String name = new String(ftpFile.getName().getBytes("GBK"),"ISO-8859-1");  
            if (ftpFile.isDirectory()) {  
                success = deleteFoldAndsubFiles(remoteFoldPath + "/" + name);  
                if (!success)  
                    break;  
            } else {  
                success = deleteFtpServerFile(remoteFoldPath + "/" + name);  
                if (!success)  
                    break;  
            }  
        }  
        if (!success)  
            return false;  
        success = deleteFold(remoteFoldPath);  
        return success;  
    }  
      
    /** 
     * 关闭FTP连接 
     *  
     * @param ftp 
     * @throws Exception 
     */  
    public void closeFTPClient(FTPClient ftp) throws Exception {  
        try {  
            if (ftp.isConnected()){
                ftp.logout();  
                ftp.disconnect();
            }
        } catch (Exception e) {  
            throw new Exception("关闭FTP服务出错!");
        }  
    }  
    /** 
     * 关闭FTP连接 
     *  
     * @throws Exception 
     */  
    public void closeFTPClient() throws Exception {  
        this.closeFTPClient(this.ftp);  
    }
    
    /**
     * 保存到共享文件夹
     * @throws IOException 
     * @throws WriteException 
     * @throws BiffException 
     */
    public String getPathname(String filename){
    	//获取共享保存路径
    	ftpShareLocalEntity = ftpShareLocalDao.findUnique();
    	String pathname = ftpShareLocalEntity.getShareBasePath() + "\\"+filename+".xls";
    	return pathname;
    }
    
    /**
     * 将内容写入文件
     * @param content
     * @param filename
     * @throws IOException
     */
    public void saveToShare(String content, String filename) throws IOException{
    	String pathname = ftpShareLocalEntity.getShareBasePath() + "\\"+filename;
    	File file = new File(pathname);
    	if(!file.exists()){
    		file.createNewFile();
    	}
    	FileOutputStream out = new FileOutputStream(file);
    	out.write(content.getBytes("UTF-8"));
    	out.flush();
    	out.close();
    }
//    /** 
//     * 主方法(测试) 
//     *  
//     * 问题：上传时命名的新文件名不能有中文，否则上传失败. 
//     *  
//     * @param args 
//     */  
//    public static void main(String[] args) {  
////        try {  
////            FtpShareLocalFile ftpShareLocalFile = new FtpShareLocalFile();  
////            ftpShareLocalFile.connectFTPServer();
////            ftpShareLocalFile.uploadFile(new FileInputStream("C:\\Users\\Administrator\\Desktop\\CTG-Java-App.doc"), "aa.doc", "rubik");
////        } catch (Exception e) {
////        	e.printStackTrace();
////            System.out.println("异常信息：" + e.getMessage());
////        }  
//    	try {
//    		FtpShareLocalFile ftpShareLocalFile = new FtpShareLocalFile();
//        	FtpShareLocalEntity ftpShareLocalEntity = new FtpShareLocalEntity();
//        	ftpShareLocalEntity.setFtpBasePath(new String("报文".getBytes("GBK"),"ISO-8859-1"));
//        	ftpShareLocalEntity.setFtpAddress("192.168.0.125");
//        	ftpShareLocalEntity.setFtpPort(21);
//        	ftpShareLocalEntity.setFtpUsername("administrator");
//        	ftpShareLocalEntity.setFtpPassword("admin");
//			ftpShareLocalFile.pingFtpConnectTest(ftpShareLocalEntity);
//			//ftpShareLocalFile.connectFTPServer();
//			//ftpShareLocalFile.uploadFile(new FileInputStream("C:\\Users\\Administrator\\Desktop\\CTG-Java-App.doc"), "aa.doc", "rubik");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }  
}  