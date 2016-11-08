package ns.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import ns.common.BaseAction;
import ns.exception.BaseException;
import ns.major.employ.service.UnitService;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

/**
 * 上传文件操作类
 * 
 * @author Administrator
 * 
 */
public class UploadFileUtil extends BaseAction {
	

	/**
	 * 文件上传
	 * 
	 * @param file
	 *            文件
	 * @param fileContentType
	 *            类型
	 * @param fileName
	 *            名字
	 * @return
	 */
	public static String uploadFile(File file, String fileContentType,
			String fileName) throws Exception {
		Date date = new Date();
		// 服务器文件存放名
		String realName = "";
		// 后缀
		String fileFix = fileName.split("\\.")[1];
		// 放到临时文件夹下面
		String filePath = "/upload/temp";
		// 图片跟文件存放的目录不同
		if (fileFix.equals("png") || fileFix.equals("jpg")
				|| fileFix.equals("gif")) {
			filePath += "/image";
		} else if (fileFix.equals("rar") || fileFix.equals("zip")
				|| fileFix.equals("xls") || fileFix.equals("xlsx")||fileFix.equals("doc")||fileFix.equals("docx")) {
			filePath += "/docs";
		} else {
			throw new Exception("上传文件类型不符合要求");
		}
		realName = fileName;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		filePath += "/" + sdf.format(date);

		// 文件服务器存放目录
		String realPath = ServletActionContext.getServletContext().getRealPath(
				filePath);
		// 创建临时文件 默认在 work\Catalina\localhost\yw-nm下面
		file.createNewFile();
		File saveFile = new File(realPath, realName);
		if (!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		FileUtils.copyFile(file, saveFile);
		// 删除创建临时文件
		file.delete();

		return filePath + "/" + realName; // 返回文件路径
	}

	/**
	 * 移动上传的临时文件到正式目录
	 * 
	 * @param tempFilePath
	 * @return 正式目录路径
	 */
	public static String moveUploadFile(String tempFilePath) throws Exception {
		if (tempFilePath != null && tempFilePath != "") {
			String tfilePath = ServletActionContext.getServletContext()
					.getRealPath(tempFilePath);
			File tempfile = new File(tfilePath);
			// 文件服务器存放目录
			String realPath = tempFilePath.replace("/temp", "");
			String savePath = ServletActionContext.getServletContext()
					.getRealPath(realPath).replace(tempfile.getName(), "");
			File saveFile = new File(savePath, tempfile.getName());
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(tempfile, saveFile);
			// 删除临时文件  这里不用系统删，以后人工删除把
			//tempfile.delete();

			return realPath;
		} else {
			return null;
		}

	}

	/**
	 * 删除上传的文件
	 * 
	 * @param filePath
	 *            上传文件路径
	 */
	public static void removeUploadFile(String filePath) {

		filePath = ServletActionContext.getServletContext().getRealPath(
				filePath);
		File file = new File(filePath);
		if (file.isFile()) {
			file.delete();
		}
	}

}
