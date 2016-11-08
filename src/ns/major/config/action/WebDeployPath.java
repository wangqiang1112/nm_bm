package ns.major.config.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import ns.common.BaseAction;


/**
 * 
 * 作者：何荣靖
 * 创建时间：Jan 20, 2014 9:30:40 AM
 * 备注：向系统目录下写入项目的部署目录，用于更新包升级
 */
public class WebDeployPath {

	/**
	 * 将系统部署目录记录到系统目录下，便于升级包读取
	 * @param webPath
	 * WebDeployPath.outPutWebDeployPath(super.getHttpSession()
				.getServletContext().getRealPath("/"));
	 */
	
	public static void outPutWebDeployPath(String webPath){
	 
		BaseAction baseAction=new BaseAction();
		HttpServletRequest request=baseAction.getHttpRequest();
		String path=request.getRealPath("/");
		//System.out.println(path);
		//SMP  web.install		审计  cas_web.install		soc   soc_web.install       检查 check_web.install 评估  scep_web.install
		String fileName = "secretmanage_web.install";
		//true:指向到项目上级目录  false:指向到项目
		boolean flag = true;
		
		try {
			webPath = webPath.replaceAll("\\\\", "/");
			if(flag){
				File file = new File(webPath);
				webPath = file.getParent();
			}			
			
			File file = new File(System.getenv("windir")+"/"+ fileName);
			if(file.exists()){
				file.createNewFile();
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			//String urlpath = request.getContextPath();
			//String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+urlpath+"/";
			
			output.write("newpath="+path);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
