package ns.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.springframework.util.FileCopyUtils;

import net.sf.json.JSONObject;
import ns.exception.BaseException;
import ns.util.UploadFileUtil;

public class UploadAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File [] file;
	private String [] fileContentType;
	private String [] fileFileName;
	
	public File[] getFile() {
		return file;
	}


	public void setFile(File[] file) {
		this.file = file;
	}


	public String[] getFileContentType() {
		return fileContentType;
	}


	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}


	public String[] getFileFileName() {
		return fileFileName;
	}


	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}


	@Override
	public String execute() throws Exception{
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(file != null){
			try {
				long size = 0;
				for (File f : file) {
				    size += FileCopyUtils.copyToByteArray(new FileInputStream(f)).length;
				}
			    if(size > 20971520){
			    	throw new BaseException("文件总大小超过20M");
			    }
			    String filePath = "",fileUrl = "";
			    //上传处理
			    for (int i = 0; i < file.length; i++) {
			        String path = UploadFileUtil.uploadFile(file[i], fileContentType[i], fileFileName[i]);
			        filePath +=","+path;
			        fileUrl +=","+this.getHostURL()+path;
				}
				map.put("code",0);
				map.put("filePath", filePath.substring(1));  				//相对路径
				map.put("fileUrl", fileUrl.substring(1)); 					//文件URL地址
			} catch (Exception e) {
				e.printStackTrace();
				map.put("code",-1);
				map.put("msg", "上传失败:"+e.getMessage());
			}
			//输出JSON
			this.outJsonString(JSONObject.fromObject(map).toString());
		}
		return null;
	}
}
