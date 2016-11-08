package ns.major.config.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ns.common.BaseAction;
import ns.common.Global;
import ns.major.common.service.BaseDataService;
import ns.major.config.dao.domain.Department;
import ns.major.config.dao.domain.Dictionary;
import ns.major.config.dao.domain.FamilyInfo;
import ns.major.config.dao.domain.Staff;
import ns.major.config.dao.domain.StaffEdu;
import ns.major.config.dao.domain.StaffJob;
import ns.major.config.query.StaffQuery;
import ns.major.config.service.StaffConfigService;
import ns.util.IdcardValidator;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.struts2.ServletActionContext;

public class StaffConfigAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StaffConfigService staffConfServ;
	private BaseDataService baseDataServ;
	private String fileFileName;
	private File file;
	private static int successCount = 0;
	private static int successCountEdu = 0;
	private static int successCountJob = 0;
	private static int successCountFamily = 0;
	public String list() throws Exception {
		try {
			StaffQuery queryForm = getForm(StaffQuery.class);
			setPage(staffConfServ.getAll(queryForm));
			setQuery(queryForm);
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
			return "error";
		}

		return "success";
	}

	// 人员预览
	public String view() throws Exception {
		Integer stafferId = Integer.parseInt(super.getHttpRequest()
				.getParameter("stafferId"));
		Staff staff = staffConfServ.getStaffByStafferId(stafferId);
		if (null != staff.getNationality())
			staff.setNationality_str(baseDataServ.getDictionaryByDictid(
					staff.getNationality()).getFirstName());
		if (null != staff.getPoliticalStatus())
			staff.setPoliticalStatus_str(baseDataServ.getDictionaryByDictid(
					staff.getPoliticalStatus()).getFirstName());
		if (null != staff.getNation())
			staff.setNation_str(baseDataServ.getDictionaryByDictid(
					staff.getNation()).getFirstName());
		/*
		 * if(null!=staff.getSUnit()) staff.setSUnit_str(
		 * baseDataServ.getDictionaryByDictid(staff.getSUnit()).getFirstName());
		 */
		if (null != staff.getSDepart())
			staff.setSDepart_str(baseDataServ.getDictionaryByDictid(
					staff.getSDepart()).getFirstName());
		if (null != staff.getSPost())
			staff.setSPost_str(baseDataServ.getDictionaryByDictid(
					staff.getSPost()).getFirstName());
		if (null != staff.getSJob())
			staff.setSJob_str(baseDataServ.getDictionaryByDictid(
					staff.getSJob()).getFirstName());
		// 教育经历
		List<StaffEdu> staffEduList = staffConfServ.getStaffEduList(stafferId);
		setBean("staffEduList", staffEduList);
		// 工作经历
		List<StaffJob> staffJobList = staffConfServ.getStaffJobList(stafferId);
		setBean("staffJobList", staffJobList);
		// 家庭成员
		List<FamilyInfo> familyInfoList = staffConfServ
				.getFamilyInfoList(stafferId);
		setBean("familyInfoList", familyInfoList);
		setBean("bean_Staff", staff);

		return "success";
	}

	public void delete() throws Exception {
		String[] staffIds = getForm(StaffQuery.class).getStafferIds()
				.split(",");
		try {
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < staffIds.length; i++) {
				ids.add(Integer.parseInt(staffIds[i]));
			}
			staffConfServ.delete(ids);
			success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		getHttpResponse().sendRedirect(
				getContextPath() + "/config/personal/list.do");

	}

	/**
	 * 批量导入
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public void importExcel() throws Exception {
		String serverPath = ServletActionContext.getServletContext()
				.getRealPath("/");
		if (serverPath.indexOf("\\") != -1) {
			if (!serverPath.substring(serverPath.length() - 1).equals("\\")) {
				serverPath = serverPath + "\\";
			}
		}
		String path = serverPath + "upload\\personInfo\\";

		if (!path.equals("")) {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}

		}
		File newFile = new File(path, fileFileName);
		// 如果文件存在，删除原有文件
		if (newFile.exists()) {
			newFile.delete();
		}
		try {
			FileUtils.copyFile(file, newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//基本信息
		List<Staff> staffLists = excelToObjectBase(newFile);
		if (staffLists.size() != 0) {
			for (int i = 0; i < staffLists.size(); i++) {
				staffConfServ.importBaseAdd(staffLists.get(i));
			}
		}
		//教育经历
		List<StaffEdu> eduList = excelToObjectEdu(newFile);
		if (eduList.size() != 0) {
			for (int i = 0; i < eduList.size(); i++) 
				staffConfServ.staffEduImport(eduList.get(i));
		}
		//工作经历
		List<StaffJob>jobList = excelToObjectJob(newFile);
		if (jobList.size() != 0) {
			for (int i = 0; i < jobList.size(); i++) 
				staffConfServ.staffJobImport(jobList.get(i));
		}
		//家庭成员
		List<FamilyInfo>familyList = excelToObjectFamily(newFile);
		if (familyList.size() != 0) {
			for (int i = 0; i < familyList.size(); i++) 
				staffConfServ.familyInfoImport(familyList.get(i));
		}
		String baseStr="基本信息中成功插入"+successCount+"条</br>";
		String eduStr="教育经历中成功插入"+successCountEdu+"条</br>";
		String jobStr="工作经历中成功插入"+successCountJob+"条</br>";
		String familyStr="家庭成员中成功插入"+successCountFamily+"条</br>";
		this.success(baseStr+eduStr+jobStr+familyStr);
		getHttpResponse().sendRedirect(
				getContextPath() + "/config/personal/list.do");
	}

	//用户获对应部门员工的信息
		public void getStaff() throws Exception{
			String deptName=getForm(Department.class).getDepartName();
			//拿到该部门所有员工的信息
			List<Staff> staffList=staffConfServ.getStaffByDeptName(deptName);
			JSONArray array=JSONArray.fromObject(staffList);
			getHttpResponse().setContentType("text/json;charset=UTF-8");
			getHttpResponse().getWriter().write(
					array.toString());
			getHttpResponse().getWriter().close();
		}
		    //得到一个员工的相应信息
		public void getStaffInfo() throws Exception{
			int staffId=getForm(Staff.class).getStafferId();
			//拿到该部门所有员工的信息
			Staff staff=staffConfServ.getStaffByStafferId(staffId);
			JSONObject jsonDate=JSONObject.fromObject(staff);
			getHttpResponse().setContentType("text/json;charset=UTF-8");
			getHttpResponse().getWriter().write(jsonDate.toString());
			getHttpResponse().getWriter().close();
		}

	// 跳转到 新增人员 基本资料 页面
	public String baseAdd() throws Exception {
		Object id = super.getHttpRequest().getParameter("stafferId");

		// String id="4";
		if (null != id) {
			setBean("bean_Staff", staffConfServ.getStaffByStafferId(Integer
					.parseInt(id.toString())));
		}

		setBeanForBase();
		return "success";
	}

	// 保存 新增人员 基本资料
	public String baseSave() throws Exception {
		Staff staff = getForm(Staff.class);
		Staff bean = staffConfServ.baseSave(staff);
		setBean("bean_Staff", bean);
		setBeanForUnit();
		return "success";
	}

	// 跳转到 新增人员 单位信息 页面
	public String unitAdd() throws Exception {
		Integer stafferId = Integer.parseInt(getHttpRequest().getParameter(
				"stafferId"));
		Staff staff = staffConfServ.getStaffByStafferId(stafferId);
		setBean("bean_Staff", staff);
		setBeanForUnit();
		return "success";
	}

	// 新增 新增人员 单位信息
	public String unitSave() throws Exception {
		Staff staff = getForm(Staff.class);
		Staff bean = staffConfServ.unitInfoUpdate(staff);
		Staff s = staffConfServ.getStaffByStafferId(bean.getStafferId());
		setBean("bean_Staff", s);
		return "success";
	}

	// 跳转到 新增人员 选择密级 页面
	public String secretAdd() throws Exception {
		Integer stafferId = Integer.parseInt(getHttpRequest().getParameter(
				"stafferId"));
		Staff staff = staffConfServ.getStaffByStafferId(stafferId);
		setBean("bean_Staff", staff);
		return "success";
	}

	// 新增 新增人员 单位信息
	public String secretSave() throws Exception {
		Staff staff = getForm(Staff.class);
		Staff bean = staffConfServ.declassifiedUpdate(staff);
		Staff s = staffConfServ.getStaffByStafferId(bean.getStafferId());
		setBean("bean_Staff", s);
		return "success";
	}

	// 跳转到 新增人员 出境信息 页面
	public String expelAdd() throws Exception {
		Integer stafferId = Integer.parseInt(getHttpRequest().getParameter(
				"stafferId"));
		Staff staff = staffConfServ.getStaffByStafferId(stafferId);
		setBean("bean_Staff", staff);
		return "success";
	}

	// 新增 新增人员 出境信息
	public String expelSave() throws Exception {
		Staff staff = getForm(Staff.class);
		Staff bean = staffConfServ.expelUpdate(staff);
		setBean("stafferId", bean.getStafferId());
		return "success";
	}

	/**
	 * 教育经历
	 */
	// 跳转到 新增人员 教育 页面
	public String eduList() throws Exception {
		//Integer stafferId = Integer.parseInt(getHttpRequest().getParameter("stafferId"));
		Object id=getHttpRequest().getParameter("stafferId");
		if(null==id||""==id)
			id=getHttpSession().getAttribute("StafferId");
		Integer stafferId = Integer.parseInt(id.toString());
	System.out.println("zzy:test:"+stafferId);
		setBean("stafferId", stafferId);
		// 获取教育经历列表
		List<StaffEdu> staffEduList = staffConfServ.getStaffEduList(stafferId);
		setBean("staffEduList", staffEduList);
		return "success";
	}

	// 弹出框 教育经历
	public String eduAdd() throws Exception {
		Object id = super.getHttpRequest().getParameter("eduID");
		if (null != id && "" != id) {// 修改
			StaffEdu staffEdu = staffConfServ.getStaffEduByID(Integer
					.parseInt(id.toString()));
			setBean("bean_StaffEdu", staffEdu);
			setBean("stafferId", staffEdu.getStafferId());
		} else{
			setBean("stafferId", getHttpRequest().getParameter("stafferId"));
		}
			
		return "success";
	}

	// 保存教育经历
	public void eduSave() throws Exception {
		StaffEdu staffEdu = this.getForm(StaffEdu.class);
		if (null == staffEdu.getEduID() || 0 == staffEdu.getEduID()) {
			staffConfServ.staffEduAdd(staffEdu);
			this.success("保存成功", true);
		} else {
			staffConfServ.staffEduUpdate(staffEdu);
			this.success("编辑成功", true);
		}
		// getHttpResponse().sendRedirect("eduList.do?stafferId="+staffEdu.getStafferId());
		getHttpSession().setAttribute("StafferId",staffEdu.getStafferId());
		getHttpResponse().sendRedirect(super.getContextPath() + "/toMessage.do");
	}

	// 删除教育经历
	public void eduDelete() throws Exception {
		Integer eduID = Integer
				.parseInt(getHttpRequest().getParameter("eduID"));
		// 获取stafferId
		Integer stafferId = staffConfServ.getStaffIdByEduID(eduID);
		setBean("stafferId", stafferId);
		// 删除信息
		staffConfServ.staffEduDel(eduID);
		// 获取教育经历列表
		getHttpResponse().sendRedirect("eduList.do?stafferId="+stafferId);
	}

	/**
	 * 工作经历
	 */
	// 跳转到 新增人员 工作经历 页面
	public String jobList() throws Exception {
		//Integer stafferId = Integer.parseInt(getHttpRequest().getParameter("stafferId"));
		String id=getHttpRequest().getParameter("stafferId");
		if(null==id||""==id)
			id=getHttpSession().getAttribute("StafferId").toString();
		Integer stafferId = Integer.parseInt(id);
		setBean("stafferId", stafferId);
		// 获取工作经历列表
		List<StaffJob> staffJobList = staffConfServ.getStaffJobList(stafferId);
		setBean("staffJobList", staffJobList);
		return "success";
	}

	// 弹出框 工作经历
	public String jobAdd() throws Exception {
		Object id = super.getHttpRequest().getParameter("jobID");
		if (null != id && "" != id) {// 修改
			Integer jobID = Integer.parseInt(id.toString());
			StaffJob staffJob = staffConfServ.getStaffJobByID(jobID);
			setBean("bean_StaffJob", staffJob);
			setBean("stafferId", staffJob.getStafferId());
		} else
			setBean("stafferId", getHttpRequest().getParameter("stafferId"));
		setBeanForUnit();
		return "success";
	}

	// 保存工作经历
	public void jobSave() throws Exception {
		StaffJob staffJob = this.getForm(StaffJob.class);
		if (null == staffJob.getJobID() || 0 == staffJob.getJobID()) {
			staffConfServ.staffJobAdd(staffJob);
			this.success("保存成功", true);

		} else {
			staffConfServ.staffJobUpdate(staffJob);
			this.success("编辑成功", true);
		}
		// getHttpResponse().sendRedirect("jobList.do?stafferId="+staffJob.getStafferId());
		getHttpSession().setAttribute("StafferId",staffJob.getStafferId());
		getHttpResponse()
				.sendRedirect(super.getContextPath() + "/toMessage.do");
	}

	// 删除工作经历
	public void jobDelete() throws Exception {
		Integer jobID = Integer
				.parseInt(getHttpRequest().getParameter("jobID"));
		// 获取stafferId
		Integer stafferId = staffConfServ.getStaffIdByJobID(jobID);
		setBean("stafferId", stafferId);
		// 删除信息
		staffConfServ.staffJobDel(jobID);
		// 获取工作经历列表
		getHttpResponse().sendRedirect("jobList.do?stafferId="+stafferId);
	}

	/**
	 * 家庭成员
	 */
	// 跳转到 新增人员 家庭成员 页面
	public String familyList() throws Exception {
		String id=getHttpRequest().getParameter("stafferId");
		if(null==id||""==id)
			id=getHttpSession().getAttribute("StafferId").toString();
		Integer stafferId = Integer.parseInt(id);
		//Integer stafferId = Integer.parseInt(getHttpRequest().getParameter("stafferId"));
		setBean("stafferId", stafferId);
		// 获取家庭成员列表
		List<FamilyInfo> familyInfoList = staffConfServ
				.getFamilyInfoList(stafferId);
		super.getHttpRequest().setAttribute("familyInfoList", familyInfoList);
		return "success";
	}

	// 弹出框 家庭成员
	public String familyAdd() throws Exception {
		Object id = super.getHttpRequest().getParameter("infoID");
		if (null != id && "" != id) {// 修改
			int infoID = Integer.parseInt(id.toString());
			FamilyInfo familyInfo = staffConfServ.getFamilyInfoByID(infoID);
			setBean("bean_FamilyInfo", familyInfo);
			setBean("stafferId", familyInfo.getStafferId());
		} else
			setBean("stafferId", getHttpRequest().getParameter("stafferId"));
		List<Dictionary> list_Nationality = baseDataServ.getNationalityList();
		super.getHttpRequest().setAttribute("list_Nationality",
				list_Nationality);
		return "success";
	}

	// 保存家庭成员
	public void familySave() throws Exception {
		FamilyInfo familyInfo = this.getForm(FamilyInfo.class);
		if (null == familyInfo.getInfoID() || 0 == familyInfo.getInfoID()) {
			staffConfServ.familyInfoAdd(familyInfo);
			this.success("保存成功", true);
		} else {
			staffConfServ.familyInfoUpdate(familyInfo);
			this.success("编辑成功", true);
		}
		// getHttpResponse().sendRedirect("eduList.do?stafferId="+familyInfo.getStafferId());
		getHttpSession().setAttribute("StafferId",familyInfo.getStafferId());
		getHttpResponse()
				.sendRedirect(super.getContextPath() + "/toMessage.do");
	}

	// 删除家庭成员
	public void familyDelete()throws Exception  {
		Integer infoID = Integer.parseInt(super.getHttpRequest().getParameter(
				"infoID"));
		// 获取stafferId
		Integer stafferId = staffConfServ.getStaffIdByInfoID(infoID);
		setBean("stafferId", stafferId);
		// 删除信息
		staffConfServ.familyInfoDel(infoID);

		// 获取家庭成员列表
		getHttpResponse().sendRedirect("familyList.do?stafferId="+stafferId);
	}

	// 跳转到 新增人员 附件 页面
	public String fileList() throws Exception {
		Staff mod = staffConfServ.getStaffByStafferId(Integer
				.parseInt(getHttpRequest().getParameter("stafferId")));
		super.getHttpRequest().setAttribute("bean", mod);
		return "success";
	}

	// 文件信息保存
	public void fileAdd()throws Exception  {
		Staff mod = staffConfServ.getStaffByStafferId(Integer
				.parseInt(getHttpRequest().getParameter("stafferId")));
		mod.setSFilePath(super.getHttpRequest().getParameter("SFilePath"));
		staffConfServ.fileUpdate(mod);
	}

	/**
	 * excel
	 * 
	 */
	/**
	 * 人员教育经历
	 */
	public List<StaffEdu> excelToObjectEdu(File file) throws Exception {
		List<StaffEdu> eduList = new ArrayList<StaffEdu>();
		Workbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {}
		Sheet sheet = workbook.getSheetAt(1);// 获得第二个表单
		successCountEdu = 0;
		int firstRowNum = sheet.getFirstRowNum();// 第一行
		int lastRowNum = sheet.getLastRowNum();
		
		Row titleRow = sheet.getRow(0);
		Cell titleCell = titleRow.getCell(0);

		String title = titleCell.getStringCellValue();
		if (title.indexOf("人员教育经历") == -1) {
			return eduList;
		}

		// 跳过标题行
		for (int i = firstRowNum + 2; i < lastRowNum + 1; i++) {
			StaffEdu edu=new StaffEdu();
			Row row = sheet.getRow(i);
			if (row == null) continue;
			int j = 0;
			Cell cell = row.getCell(j);
			// 身份证号
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					Staff staff=staffConfServ.getStaffByIDNumber(str);
					if(null!=staff){
						edu.setStafferId(staff.getStafferId());
						edu.setStafferName(staff.getStafferName());
					}else continue;
				} else continue;
			} else continue;
			// 起止时间
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) 
					edu.setEStartTime(cell.getDateCellValue());
				else continue;
			} else continue;
			//结束时间
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) 
					edu.setEEndTime(cell.getDateCellValue());
				else continue;
			} else continue;
			//院校名称
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)edu.setInstitutionName(str);
					else continue;
				} else continue;
			} else continue;
			//专业
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)edu.setDiscipline(str);
					else continue;
				} else continue;
			} else continue;
			//学制
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					try{
						String str = getStringCellValue(cell);
						Double y=Double.parseDouble(str);
						edu.setESystem(y.intValue());
					}catch (Exception e) {continue;}
				} else continue;
			} else continue;
			//学位
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					if("小学".equals(str))edu.setDegree("1");
					else if("初中".equals(str))edu.setDegree("2");
					else if("中专".equals(str))edu.setDegree("3");
					else if("高中".equals(str))edu.setDegree("4");
					else if("大专".equals(str))edu.setDegree("5");
					else if("本科".equals(str))edu.setDegree("6");
					else if("硕士".equals(str))edu.setDegree("7");
					else if("博士".equals(str))edu.setDegree("8");
					else if("博士后".equals(str))edu.setDegree("9");
					else continue;
				} else continue;
			} else continue;
			//证明人
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)edu.setEwitness(str);
					else continue;
				} else continue;
			} else continue;
			//备注 非必填
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					edu.setERemarks(str);
				} else continue;
			} 
			
			successCountEdu += 1;
			eduList.add(edu);
		}
		return eduList;
	}

	/**
	 * 人员工作经历
	 */
	public List<StaffJob> excelToObjectJob(File file) throws Exception {
		List<StaffJob>jobList = new ArrayList<StaffJob>();
		Workbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {}
		Sheet sheet = workbook.getSheetAt(2);// 获得第三个表单
		successCountJob = 0;
		int firstRowNum = sheet.getFirstRowNum();// 第一行
		int lastRowNum = sheet.getLastRowNum();
		Row titleRow = sheet.getRow(0);
		Cell titleCell = titleRow.getCell(0);

		String title = titleCell.getStringCellValue();
		if (title.indexOf("人员工作经历") == -1) {
			return jobList;
		}

		// 跳过标题行
		for (int i = firstRowNum + 2; i < lastRowNum + 1; i++) {
			StaffJob job = new StaffJob();
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			int j = 0;
			Cell cell = row.getCell(j);
			// 身份证号
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					Staff staff=staffConfServ.getStaffByIDNumber(str);
					if(null!=staff){
						job.setStafferId(staff.getStafferId());
						job.setStafferName(staff.getStafferName());
					}else continue;
				} else continue;
			} else continue;			
			// 起止时间
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) 
					job.setWStartTime(cell.getDateCellValue());
				else continue;
			} else continue;
			//结束时间
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) 
					job.setWEndTime(cell.getDateCellValue());
				else continue;
			} else continue;
			//单位名称
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)job.setWUnitName(str);
					else continue;
				} else continue;	
			} else continue;
			//职务 WPost
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String string = getStringCellValue(cell);
					Integer y = baseDataServ.getSPostDictId(string);
					if (null != y)job.setWPost(y+"");
					else continue;
				} else continue;
			} else continue;
			//职称 WJob
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String string = getStringCellValue(cell);
					Integer y = baseDataServ.getSJobDictId(string);
					if (null != y)job.setWJob(y+"");
					else continue;
				} else continue;
			} else continue;
			//工作职责 WDuty
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)job.setWDuty(str);
					else continue;
				} else continue;	
			} else continue;
			//证明人 Wwitness
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)job.setWwitness(str);
					else continue;
				} else continue;	
			} else continue;
			//手机 WPhone
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^1[34578]\\d{9}$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)job.setWPhone(str);
					else continue;
				} else continue;
			} else continue;
			//备注 WRemarks
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					job.setWRemarks(str);
				} else continue;
			} 
			successCountJob += 1;
			jobList.add(job);
		}

		return jobList;
	}
	/**
	 * 人员家庭成员
	 */
	public List<FamilyInfo> excelToObjectFamily(File file) throws Exception {
		List<FamilyInfo> familyList = new ArrayList<FamilyInfo>();
		Workbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {}

		Sheet sheet = workbook.getSheetAt(3);// 获得第四个表单
		successCountFamily = 0;
		int firstRowNum = sheet.getFirstRowNum();// 第一行
		int lastRowNum = sheet.getLastRowNum();
		Row titleRow = sheet.getRow(0);
		Cell titleCell = titleRow.getCell(0);

		String title = titleCell.getStringCellValue();
		if (title.indexOf("人员家庭成员") == -1) {
			return familyList;
		}

		// 跳过标题行
		for (int i = firstRowNum + 2; i < lastRowNum + 1; i++) {
			FamilyInfo family = new FamilyInfo();
			Row row = sheet.getRow(i);
			if (row == null) continue;	
			int j = 0;
			Cell cell = row.getCell(j);

			// 身份证号
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					Staff staff=staffConfServ.getStaffByIDNumber(str);
					if(null!=staff){
						family.setStafferId(staff.getStafferId());
						family.setStafferName(staff.getStafferName());
					}else continue;
				} else continue;
			} else continue;	
			//关系
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)family.setRelation(str);
					else continue;
				} else continue;	
			} else continue;
			//姓名
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)family.setFName(str);
					else continue;
				} else continue;	
			} else continue;
			// 性别 
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					if (str.equals("男"))family.setFSex(0);
					else if (str.equals("女"))family.setFSex(1);
					else continue;
				} else continue;
			} else continue;
			//年龄
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String str = getStringCellValue(cell);
				try {
					Double y=Double.parseDouble(str);
					family.setFAge(y.intValue());
				} catch (Exception e) {continue;}
			}
			// 国籍
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String string = getStringCellValue(cell);
					Integer y = baseDataServ.getNationalityDictId(string);
					if (null != y)family.setFNationality(y+"");
					else continue;
				} else continue;
			} else continue;
			//单位
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)family.setFUnit(str);
					else continue;
				} else continue;	
			} else continue;
			//职务 FPost
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)family.setFPost(str);
					else continue;
				} else continue;	
			} else continue;
			//职称 FJob
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)family.setFJob(str);
					else continue;
				} else continue;	
			} else continue;
			//住址 FAddress
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)family.setFAddress(str);
					else continue;
				} else continue;	
			} else continue;
			// 手机 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^1[34578]\\d{9}$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)family.setFPhone(str);
					else continue;
				} else continue;
			} else continue;
			
			successCountFamily += 1;
			familyList.add(family);
		}
		return familyList;
	}
	// 人员基本信息
	public List<Staff> excelToObjectBase(File file) throws Exception {
		Map<String,Integer>baseMap=new HashMap<String,Integer>();
		List<Staff> staffList = new ArrayList<Staff>();
		Workbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {

		}

		Sheet sheet = workbook.getSheetAt(0);// 获得第一个表单
		successCount = 0;
		int firstRowNum = sheet.getFirstRowNum();// 第一行
		int lastRowNum = sheet.getLastRowNum();
		Row titleRow = sheet.getRow(0);
		Cell titleCell = titleRow.getCell(0);

		String title = titleCell.getStringCellValue();
		if (title.indexOf("人员基本信息") == -1) {
			return staffList;
		}

		// 跳过标题行
		for (int i = firstRowNum + 3; i < lastRowNum + 1; i++) {
			Staff staff = new Staff();
			// staff.setProcessType(1);
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			int j = 0;
			Cell cell = row.getCell(j);

			// 姓名 zzy
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					String regex = "^[a-zA-Z\u4e00-\u9fa5]+$";
					boolean flag = Pattern.matches(regex, str);
					if (flag)
						staff.setStafferName(str);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 性别 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					if (str.equals("男"))
						staff.setStafferSex(0);
					else if (str.equals("女"))
						staff.setStafferSex(1);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 出生日期 不需要验证
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					Date date = cell.getDateCellValue();
					staff.setBirthDay(date);
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 国籍 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String string = getStringCellValue(cell);
					Integer y = baseDataServ.getNationalityDictId(string);
					if (null != y)
						staff.setNationality(y);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 政治面貌 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String string = getStringCellValue(cell);
					Integer y = baseDataServ.getPoliticalStatusDictId(string);
					if (null != y)
						staff.setPoliticalStatus(y);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}

			// 民族 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String string = getStringCellValue(cell);
					Integer y = baseDataServ.getNationDictId(string);
					if (null != y)
						staff.setNation(y);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 身份证 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String a = getStringCellValue(cell);
					// 身份证验证
					IdcardValidator iv = new IdcardValidator();
					boolean flag = iv.isValidatedAllIdcard(a);
					if (flag) {
						Staff mod=staffConfServ.getStaffByIDNumber(a);
						if(null!=mod)continue;
						if(baseMap.containsKey(a))continue;
						else {
							staff.setIDNumber(a);
							baseMap.put(a, 1);
						}
					} else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 社团组织 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)
						staff.setAssociations(str);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 手机 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^1[34578]\\d{9}$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)
						staff.setMobilePone(str);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 固定电话 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					if (str.length() == 13 && str.charAt(4) == '-')
						staff.setPhone(str);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 婚姻状况 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String str = getStringCellValue(cell);
					if ("已婚".equals(str))
						staff.setIsMarry(1);
					else if ("未婚".equals(str))
						staff.setIsMarry(1);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 户籍地址 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)
						staff.setCensusAddress(str);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 居住地 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)
						staff.setLiveAddress(str);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 档案所在地 zzy
			cell = row.getCell(++j);
			if (cell != null) {
				if (!emptyStringCell(cell)) {
					String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
					String str = getStringCellValue(cell);
					boolean flag = Pattern.matches(regex, str);
					if (flag)
						staff.setArchivesAdd(str);
					else
						continue;
				} else {
					continue;
				}
			} else {
				continue;
			}
			// 非必填项
			// 犯罪记录
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				staff.setCrimeRecord(getStringCellValue(cell));
			}
			// 境外婚史
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				staff.setOverSeasMarry(getStringCellValue(cell));
			}
			// 部门 zzy
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String str = getStringCellValue(cell);
				List<Integer> list = baseDataServ.getDepartIDByDepartName(str);
				if (list.size() > 0)
					staff.setSDepart(list.get(0));
				else
					continue;
			}
			// 部门负责人 zzy
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String str = getStringCellValue(cell);
				if (str.equals("是"))
					staff.setSDepart(1);
				else if (str.equals("否"))
					staff.setSDepart(2);
				else
					continue;
			}
			// 职务 zzy
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String str = getStringCellValue(cell);
				Integer y = baseDataServ.getSPostDictId(str);
				if (null != y)
					staff.setSPost(y);
				else
					continue;
			}
			// 职称 zzy
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String str = getStringCellValue(cell);
				Integer y = baseDataServ.getSJobDictId(str);
				if (null != y)
					staff.setSJob(y);
				else
					continue;
			}
			// 电子邮件 zzy
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String regex = "^[\\w-]+(\\.[\\w-]+)*\\@([\\.\\w-]+)+$";
				String str = getStringCellValue(cell);
				boolean flag = Pattern.matches(regex, str);
				if (flag)
					staff.setSEmail(str);
				else
					continue;
			}
			// 从事涉密岗位累计年限 zzy
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String str = getStringCellValue(cell);
				try {
					Double x=Double.parseDouble(str);
					staff.setSecrecyYears(x.intValue());
				} catch (Exception e) {
					continue;
				}
			}
			// 出国护照 zzy
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String str = getStringCellValue(cell);
				if ("有".equals(str))
					staff.setPassport(1);
				else if ("无".equals(str))
					staff.setPassport(2);
				else
					continue;
			}
			// 国外绿卡 zzy
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String str = getStringCellValue(cell);
				if ("有".equals(str))
					staff.setGreenCard(1);
				else if ("无".equals(str))
					staff.setGreenCard(2);
				else
					continue;
			}
			// 密级 zzy
			cell = row.getCell(++j);
			if (!emptyStringCell(cell)) {
				String str = getStringCellValue(cell);
				if ("核心涉密人员".equals(str))
					staff.setLevel(1);
				else if ("重要涉密人员".equals(str))
					staff.setLevel(2);
				else if ("一般涉密人员".equals(str))
					staff.setLevel(3);
				else
					continue;
			}

			successCount += 1;
			staffList.add(staff);
		}

		return staffList;

	}
	
	
	/**
	 * 导出批量导入的模版文件
	 */
	public void importModel() throws Exception {
		HttpServletResponse response = super.getHttpResponse();
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + encodeFileName("新增人员模板") + ".xls");
        try {
            
            HSSFWorkbook wb = new HSSFWorkbook();
           
            String[] baseTitle = new String[]{"姓名","性别","出生日期","国籍","政治面貌","民族","身份证号","社团组织","手机","固话","婚姻状况","户籍地址","居住地","档案所在地","犯罪记录","境外婚史","部门","部门负责人","职务","职称","电子邮件","从事涉密岗位累计年限","出国护照","国外绿卡","密级"};
            String[] eduTitle = new String[]{"身份证号","起止时间","结束时间","院校名称","专业","学制","学位","证明人","备注"};
            String[] workTitle = new String[]{"身份证号","起始时间","结束时间","单位名称","职务","职称","工作职责","证明人","手机","备注"};
            String[] familyTitle = new String[]{"身份证","关系","家属姓名","性别","年龄","国籍","单位","职务","职称","住址","电话"};
            
            createsheet(wb,0,"基本信息",baseTitle);
            createsheet(wb,1,"教育经历",eduTitle);
            createsheet(wb,2,"工作经历",workTitle);
            createsheet(wb,3,"家庭成员",familyTitle);
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	protected void createSelect(HSSFSheet sheet,String[] arr,int row,int col){
		 CellRangeAddressList classificationRegions = new CellRangeAddressList(row, row, col, col);
         DVConstraint classificationConstraint = DVConstraint.createExplicitListConstraint(arr);
         HSSFDataValidation classificationDataValidation = new HSSFDataValidation(classificationRegions, classificationConstraint);
         sheet.addValidationData(classificationDataValidation);
	}
	
	public void createSheet(HSSFWorkbook workbook, int sheetNum,String sheetTitle, String[] headers) throws Exception {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(sheetNum, sheetTitle);
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(true);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text.toString());
		}
		// 遍历集合数据，产生数据行
	}
	
	
	public void createsheet(HSSFWorkbook wb, int sheetNum,String sheetTitle,String[] titles) throws Exception{
		HSSFSheet sheet = wb.createSheet();
		wb.setSheetName(sheetNum, sheetTitle);

        //标题样式
        CellStyle styleTitle = wb.createCellStyle();
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 18);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        styleTitle.setAlignment(CellStyle.ALIGN_CENTER);
        styleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        styleTitle.setFont(titleFont);
        
        switch (sheetNum) {
		case 0:
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$Y$1"));
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$Y$2"));
			break;
		case 1:
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$I$1"));
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$I$2"));
			break;
		case 2:
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$J$1"));
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$J$2"));
			break;
		case 3:
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$K$1"));
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$K$2"));
			break;

		default:
			break;
		}
        HSSFRow titleRowss = sheet.createRow(0);
        HSSFCell titleCells = titleRowss.createCell(0);
        titleCells.setCellValue(sheetTitle);
        titleCells.setCellStyle(styleTitle);


        //标注样式
        CellStyle styleAttr = wb.createCellStyle();
        Font attrFont = wb.createFont();
        attrFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        attrFont.setColor(HSSFColor.RED.index);
        styleAttr.setFont(attrFont);

        HSSFRow rowMark = sheet.createRow(1);
        HSSFCell cellMark = rowMark.createCell(0);
        cellMark.setCellStyle(styleAttr);
        switch (sheetNum) {
		case 0:
			cellMark.setCellValue("犯罪记录、境外婚史非必填，其余均为必填项；时间格式：2016-06-06");
			break;
		case 1:
			cellMark.setCellValue("备注非必填，其余均为必填项；时间格式：2016-06-06");
			break;
		case 2:
			cellMark.setCellValue("备注非必填，其余均为必填项；时间格式：2016-06-06");
			break;
		case 3:
			cellMark.setCellValue("均为必填项；时间格式：2016-06-06");
			break;

		default:
			break;
		}
        

        //字段样式
        HSSFRow row = sheet.createRow(2);
        CellStyle styleAttr1 = wb.createCellStyle();
        Font attrFont1 = wb.createFont();
        attrFont1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        attrFont1.setColor(HSSFColor.BLACK.index);
        styleAttr1.setFont(attrFont1);
        attrFont1.setColor(HSSFColor.BLACK.index);
        styleAttr1.setFont(attrFont1);
        
        for(int i = 0;i < titles.length;i++){
        	HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleAttr1);
            cell.setCellValue(titles[i]);
        }
        
        
        //性别
        String[] sexArr= new String[]{"男", "女"};
        //国籍
        List<Dictionary> list_Nationality = baseDataServ.getNationalityList();
        String[] countryArr = new String[50];
        if(list_Nationality.size() > 0){
        	for(int i = 0;i < 50;i++){
        		String s = list_Nationality.get(i).getFirstName().toString();
        		countryArr[i] = s;
        	}
        }
        
        //政治面貌
        List<Dictionary> list_PoliticalStatus = baseDataServ.getPoliticalStatusList();
        String[] politicalArr = new String[list_PoliticalStatus.size()];
        if(list_PoliticalStatus.size() > 0){
        	for(int i = 0;i < list_PoliticalStatus.size();i++){
        		politicalArr[i] = list_PoliticalStatus.get(i).getFirstName().toString();
        	}
        }
        //民族
        List<Dictionary> list_Nation = baseDataServ.getNationList();
        String[] nationArr = new String[list_Nation.size()];
        if(list_Nation.size() > 0){
        	for(int i = 0;i < list_Nation.size();i++){
        		nationArr[i] = list_Nation.get(i).getFirstName().toString();
        	}
        }
        //婚姻状况 、部门负责人
        String[] isArr = new String[]{"是", "否"};
        //部门
        List<Department> deptList = baseDataServ.getAllDept();
        String[] departArr = new String[deptList.size()];
        if(deptList.size() > 0){
        	for(int i = 0;i < deptList.size();i++){
        		departArr[i] = deptList.get(i).getDepartName().toString();
        	}
        }
        //职称
        List<Dictionary> jobList = baseDataServ.getSJobList();
        String[] jobArr = new String[jobList.size()];
        if(jobList.size() > 0){
        	for(int i = 0;i < jobList.size();i++){
        		jobArr[i] = jobList.get(i).getFirstName().toString();
        	}
        }
        //职务
        List<Dictionary> postList = baseDataServ.getSPostList();
        String[] postArr = new String[postList.size()];
        if(postList.size() > 0){
        	for(int i = 0;i < postList.size();i++){
        		postArr[i] = postList.get(i).getFirstName().toString();
        	}
        }
        //护照   绿卡
        String[] passportArr = new String[]{"有", "无"};
        //密级
        String[] levelArr = new String[]{"秘密", "机密", "绝密"};
        
        //学制
        String[] xueZhiArr = new String[]{"1年", "2年", "3年", "4年", "5年", "6年"};
        //学位
        String[] xueWeiArr = new String[]{"小学", "初中", "中专", "高中", "大专", "本科", "硕士", "博士", "博士后"};
        
        //设置事件名称单元格为文本类型
        CellStyle css = wb.createCellStyle();
        DataFormat df = wb.createDataFormat();
        css.setDataFormat(df.getFormat("@"));
        sheet.setDefaultColumnStyle(1, css);
        
        switch (sheetNum) {
		case 0:
			for (int i = 3; i < 1000; i++) {
	            createSelect(sheet,sexArr,i,1);
	            createSelect(sheet,countryArr,i,3);
	            createSelect(sheet,politicalArr,i,4);
	            createSelect(sheet,nationArr,i,5);
	            createSelect(sheet,isArr,i,10);
	            createSelect(sheet,departArr,i,16);
	            createSelect(sheet,isArr,i,17);
	            createSelect(sheet,jobArr,i,19);
	            createSelect(sheet,postArr,i,18);
	            createSelect(sheet,passportArr,i,22);
	            createSelect(sheet,passportArr,i,23);
	            createSelect(sheet,levelArr,i,24);
	            
	        }
			break;
		case 1:
			for (int i = 3; i < 1000; i++) {
	            createSelect(sheet,xueZhiArr,i,5);
	            createSelect(sheet,xueWeiArr,i,6);
	        }
			break;
		
		case 2:
			for (int i = 3; i < 1000; i++) {
	            createSelect(sheet,postArr,i,4);
	            createSelect(sheet,jobArr,i,5);
	        }
			break;
			
		case 3:
			for (int i = 3; i < 1000; i++) {
	            createSelect(sheet,sexArr,i,3);
	            createSelect(sheet,countryArr,i,5);
	            createSelect(sheet,postArr,i,7);
	            createSelect(sheet,jobArr,i,8);
	        }
			break;

		default:
			break;
		}
	}
	
	
	protected static String encodeFileName(String fileName) {
        try {
            return new String(fileName.getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	

	private static boolean emptyStringCell(Cell cell) {

		return cell == null || "".equals(getStringCellValue(cell));
	}

	private static String getStringCellValue(Cell cell) {
		try {
			return cell.getStringCellValue();
		} catch (Exception e) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case Cell.CELL_TYPE_NUMERIC:
				return String.valueOf(cell.getNumericCellValue());
			case Cell.CELL_TYPE_ERROR:
				return String.valueOf(cell.getErrorCellValue());
			default:
				return "";
			}
		}
	}

	public StaffConfigService getStaffConfServ() {
		return staffConfServ;
	}

	public void setStaffConfServ(StaffConfigService staffConfServ) {
		this.staffConfServ = staffConfServ;
	}

	public BaseDataService getBaseDataServ() {
		return baseDataServ;
	}

	public void setBaseDataServ(BaseDataService baseDataServ) {
		this.baseDataServ = baseDataServ;
	}

	public void setBeanForStaffById() {

	}

	// 国籍 政治面貌 民族
	public void setBeanForBase() throws Exception {
		List<Dictionary> list_PoliticalStatus = baseDataServ
				.getPoliticalStatusList();
		setBean("list_PoliticalStatus", list_PoliticalStatus);

		List<Dictionary> list_Nationality = baseDataServ.getNationalityList();
		setBean("list_Nationality", list_Nationality);

		List<Dictionary> list_Nation = baseDataServ.getNationList();
		setBean("list_Nation", list_Nation);
	}

	public void setBeanForUnit() throws Exception {
		// 加载单位名称列表
		// super.getHttpRequest().setAttribute("list_SUnit",baseDataServ.getAllUnit());

		// 加载部门列表
		super.getHttpRequest().setAttribute("list_SDepart",
				baseDataServ.getAllDept());
		// 加载职务列表
		super.getHttpRequest().setAttribute("list_SPost",
				baseDataServ.getSPostList());
		// 加载职称列表
		super.getHttpRequest().setAttribute("list_SJob",
				baseDataServ.getSJobList());
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
