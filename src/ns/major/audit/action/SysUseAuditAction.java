package ns.major.audit.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ns.common.BaseAction;
import ns.common.Global;
import ns.common.Page;
import ns.major.audit.dao.domain.SysUseAudit;
import ns.major.audit.query.SysUseAuditQuery;
import ns.major.audit.service.SysUseAuditService;
import ns.util.office.ExcelMakes;

public class SysUseAuditAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private SysUseAuditService sysUseService;
	private SysUseAuditQuery query;
	private SysUseAudit sysUseAudit;
	
	/**
	 * 系统使用列表
	 * @return
	 * @throws Exception 
	 */
	public String list() throws Exception{
			try {
				//初始化模块功能选选择
				List<String> umodule=sysUseService.getInitUmodule();
				getHttpRequest().setAttribute("umodule", umodule);
				query = this.getForm(SysUseAuditQuery.class);
			    setPage(sysUseService.query(query));
			    setQuery(query);
			} catch (Exception e) {
				e.printStackTrace();
				this.logSysExcption(Global.AUDIT_MODEL, e.toString());
				return "error";
			}
			return "success";
	}

	/**
	 * 删除--批量
	 * @throws Exception 
	 */
	public void delete() throws Exception{
		String[] sysIds = getForm(SysUseAuditQuery.class).getLogIds().split(",");
		try {
			List ids = new ArrayList();
			for(int i=0;i<sysIds.length;i++){
				ids.add(Integer.parseInt(sysIds[i]));
			}
			sysUseService.delete(ids);
			success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.AUDIT_MODEL, e.toString());
		}
		getHttpResponse().sendRedirect(getContextPath()+"/audit/sysUseAudit/list.do");
	}
	
	/**
	 * 导出excel
	 * @throws Exception 
	 */
	public void export() throws Exception {
		query = this.getForm(SysUseAuditQuery.class);
		query.setPageSize(100000);
		Page page = sysUseService.query(query);
        List<SysUseAudit> list =  (List<SysUseAudit>)page.getResult();
        //表列名
        String[] cellNames = {"用户名", "操作Ip","功能模块", "操作时间", "操作内容"};
        String[] orderedAttrs = {"userName","auditIp", "umodule", "auditTimeStr", "operation"};
        
        ExcelMakes excel = new ExcelMakes("系统使用", cellNames, orderedAttrs);
        Iterator iterator = list.iterator();
   /*     while(iterator.hasNext()){
        	System.out.println(iterator.next());
        }*/
        System.out.println(list);
        excel.setTitile("系统使用");
        excel.addData(list);
    }

	
	
	//-----------------------------------
	public SysUseAuditService getSysUseService() {
		return sysUseService;
	}

	public void setSysUseService(SysUseAuditService sysUseService) {
		this.sysUseService = sysUseService;
	}

	@Override
	public SysUseAuditQuery getQuery() {
		return query;
	}

	public void setQuery(SysUseAuditQuery query) {
		this.query = query;
	}

	public SysUseAudit getSysUseAudit() {
		return sysUseAudit;
	}

	public void setSysUseAudit(SysUseAudit sysUseAudit) {
		this.sysUseAudit = sysUseAudit;
	}

}
