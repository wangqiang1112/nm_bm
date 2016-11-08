package ns.major.config.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ns.common.BaseService;
import ns.common.Page;
import ns.major.common.dao.mapper.BaseDataMapper;
import ns.major.config.dao.domain.FamilyInfo;
import ns.major.config.dao.domain.Staff;
import ns.major.config.dao.domain.StaffEdu;
import ns.major.config.dao.domain.StaffJob;
import ns.major.config.dao.mapper.FamilyInfoMapper;
import ns.major.config.dao.mapper.StaffConfigMapper;
import ns.major.config.dao.mapper.StaffEduMapper;
import ns.major.config.dao.mapper.StaffJobMapper;
import ns.major.config.query.StaffQuery;
import ns.major.config.service.StaffConfigService;
import ns.util.SymmetricEncoder;
import ns.util.UploadFileUtil;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StaffConfigServiceImpl extends BaseService implements StaffConfigService {
    @Autowired
	private StaffConfigMapper staffConfDao;
    @Autowired
    private StaffEduMapper staffEduDao;
    @Autowired
    private StaffJobMapper staffJobDao;
    @Autowired
    private FamilyInfoMapper familyInfoDao;
    @Autowired
    private BaseDataMapper baseDataDao;
	public Page getAll(StaffQuery queryForm)throws Exception {
		return pageQuery(StaffConfigMapper.class, "query", queryForm);
	}
	
	public int delete(List<Integer> ids) throws Exception {
		return staffConfDao.delete(ids);
	}
	
	//人员新增--基本资料 返回主键
	public Staff baseSave(Staff staff) throws Exception {
		//加密
		staff.setIDNumber(SymmetricEncoder.IDNumberEncode(staff.getIDNumber()));
		Staff mod = staffConfDao.getStaffByIDNumber(staff.getIDNumber());
	 	if(null == mod){//新增
			staff.setPhone(staff.getPhone_a()+"-"+staff.getPhone_b());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				date = sdf.parse(staff.getBirthDay_str());
				staff.setBirthDay(date);
				if(!StringUtil.isBlank(staff.getIDCardFile())){
					String filepath = UploadFileUtil.moveUploadFile(staff.getIDCardFile());
					//设置新的路径
					staff.setIDCardFile(filepath);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			 staffConfDao.baseAdd(staff);
			 Staff t= staffConfDao.getStaffByIDNumber(staff.getIDNumber());
			 return t;
		}else{//修改
			staff.setPhone(staff.getPhone_a()+"-"+staff.getPhone_b());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				date = sdf.parse(staff.getBirthDay_str());
				staff.setBirthDay(date);
				if(!StringUtil.isBlank(staff.getIDCardFile())){
					String filepath = UploadFileUtil.moveUploadFile(staff.getIDCardFile());
					//设置新的路径
					staff.setIDCardFile(filepath);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			staffConfDao.baseUpdate(staff);
			Staff t= staffConfDao.getStaffByIDNumber(staff.getIDNumber());
			return t;
		}
	}
	//人员新增--单位信息 返回主键
	public Staff unitInfoUpdate(Staff staff)throws Exception  {
		//staff.setSUnitPhone(staff.getSUnitPhone_a()+"-"+staff.getSUnitPhone_b());
		staffConfDao.unitInfoUpdate(staff);
		return staff;
	}
	//人员信息--选择密级 返回主键
	public Staff declassifiedUpdate(Staff staff)throws Exception {
		 staffConfDao.declassifiedUpdate(staff);
		 return staff;
	}
	//人员信息--出境信息
	public Staff expelUpdate(Staff staff) throws Exception {
		 staffConfDao.expelUpdate(staff);
		 return staff;
	}
	/**
	 * 教育经历
	 */
	//人员信息--教育经历
	public int staffEduAdd(StaffEdu staffEdu)throws Exception  {
		Staff staff=staffConfDao.getStaffByStafferId(staffEdu.getStafferId());
		staffEdu.setStafferId(staff.getStafferId());
		staffEdu.setStafferName(staff.getStafferName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			staffEdu.setEStartTime(sdf.parse(staffEdu.getEStartTime_str()));
			staffEdu.setEEndTime(sdf.parse(staffEdu.getEEndTime_str()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return staffEduDao.add(staffEdu);
	}
	public int staffEduImport(StaffEdu staffEdu)throws Exception {
		return staffEduDao.add(staffEdu);
	}
	//人员信息--教育经历
	public int staffEduUpdate(StaffEdu staffEdu)throws Exception  {
		Staff staff=staffConfDao.getStaffByStafferId(staffEdu.getStafferId());
			staffEdu.setStafferId(staff.getStafferId());
			staffEdu.setStafferName(staff.getStafferName());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
				staffEdu.setEStartTime(sdf.parse(staffEdu.getEStartTime_str()));
				staffEdu.setEEndTime(sdf.parse(staffEdu.getEEndTime_str()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return staffEduDao.update(staffEdu);
	}
	//教育经历删除
	public int staffEduDel(int eduID)throws Exception {
		return staffEduDao.del(eduID);
	}
	//获取教育经历列表
	public List<StaffEdu>getStaffEduList(int stafferId)throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<StaffEdu>list= staffEduDao.getList(stafferId);
		for(int i=0;i<list.size();i++){
			StaffEdu t=list.get(i);
			t.setEStartTime_str(sdf.format(t.getEStartTime()));
			t.setEEndTime_str(sdf.format(t.getEEndTime()));
			if(t.getDegree().equals("1"))t.setDegree_str("小学");
			else if(t.getDegree().equals("2"))t.setDegree_str("初中");
			else if(t.getDegree().equals("3"))t.setDegree_str("中专");
			else if(t.getDegree().equals("4"))t.setDegree_str("高中");
			else if(t.getDegree().equals("5"))t.setDegree_str("大专");
			else if(t.getDegree().equals("6"))t.setDegree_str("本科");
			else if(t.getDegree().equals("7"))t.setDegree_str("硕士");
			else if(t.getDegree().equals("8"))t.setDegree_str("博士");
			else if(t.getDegree().equals("9"))t.setDegree_str("博士后");
		}
		return list;
	}
	//根据主键获取教育经历
	public StaffEdu getStaffEduByID(int eduID)throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		StaffEdu t=staffEduDao.getStaffEduByID(eduID);
		if(t!=null){
			t.setEStartTime_str(sdf.format(t.getEStartTime()));
			t.setEEndTime_str(sdf.format(t.getEEndTime()));
		}
		return t;
	}
	//根据教育经历主键获取
	public Integer getStaffIdByEduID(int eduID)throws Exception {
		return staffEduDao.getStaffIdByEduID(eduID);
	}
	/**
	 * 工作经历
	 */
	//人员信息--工作经历
	public int staffJobAdd(StaffJob staffJob)throws Exception  {
		Staff staff=staffConfDao.getStaffByStafferId(staffJob.getStafferId());
		staffJob.setStafferId(staff.getStafferId());
		staffJob.setStafferName(staff.getStafferName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			staffJob.setWStartTime(sdf.parse(staffJob.getWStartTime_str()));
			staffJob.setWEndTime(sdf.parse(staffJob.getWEndTime_str()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return staffJobDao.add(staffJob);
	}
	public int staffJobImport(StaffJob staffJob)throws Exception {
		return staffJobDao.add(staffJob);
	}
	//人员信息--工作经历
	public int staffJobUpdate(StaffJob staffJob) throws Exception {
		Staff staff=staffConfDao.getStaffByStafferId(staffJob.getStafferId());
			staffJob.setStafferId(staff.getStafferId());
			staffJob.setStafferName(staff.getStafferName());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
				staffJob.setWStartTime(sdf.parse(staffJob.getWStartTime_str()));
				staffJob.setWEndTime(sdf.parse(staffJob.getWEndTime_str()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return staffJobDao.update(staffJob);
	}
	//工作经历删除
	public int staffJobDel(int jobID)throws Exception {
		return staffJobDao.del(jobID);
	}
	//获取工作经历列表
	public List<StaffJob>getStaffJobList(int stafferId)throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<StaffJob>list= staffJobDao.getList(stafferId);
		for(int i=0;i<list.size();i++){
			StaffJob t=list.get(i);
			t.setWStartTime_str(sdf.format(t.getWStartTime()));
			t.setWEndTime_str(sdf.format(t.getWEndTime()));
			//职务 post
			String str=t.getWPost();
			t.setWPost_str(baseDataDao.getDictionaryByDictid(Integer.parseInt(str)).getFirstName());
			//职称 job
			str=t.getWJob();
			t.setWJob_str(baseDataDao.getDictionaryByDictid(Integer.parseInt(str)).getFirstName());
		}
		return list;
	}
	//根据主键获取工作经历
	public StaffJob getStaffJobByID(int jobID)throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		StaffJob t=staffJobDao.getStaffJobByID(jobID);
		t.setWStartTime_str(sdf.format(t.getWStartTime()));
		t.setWEndTime_str(sdf.format(t.getWEndTime()));
		return t;
	}
	//根据工作经历主键获取
	public int getStaffIdByJobID(int jobID) throws Exception{
		return staffJobDao.getStaffIdByJobID(jobID);
	}
	
	/**
	 * 家庭成员
	 */
	//人员信息--家庭成员
	public int familyInfoAdd(FamilyInfo familyInfo)throws Exception  {
		Staff staff=staffConfDao.getStaffByStafferId(familyInfo.getStafferId());
		familyInfo.setStafferId(staff.getStafferId());
		familyInfo.setStafferName(staff.getStafferName());
		return familyInfoDao.add(familyInfo);
	}
	public int familyInfoImport(FamilyInfo familyInfo)throws Exception {
		return familyInfoDao.add(familyInfo);
	}
	//人员信息--家庭成员
	public int familyInfoUpdate(FamilyInfo familyInfo) throws Exception {
		Staff staff=staffConfDao.getStaffByStafferId(familyInfo.getStafferId());
		familyInfo.setStafferId(staff.getStafferId());
		familyInfo.setStafferName(staff.getStafferName());
		return familyInfoDao.update(familyInfo);
	}
	//家庭成员删除
	public int familyInfoDel(int infoID)throws Exception {
		return familyInfoDao.del(infoID);
	}
	//获取家庭成员列表
	public List<FamilyInfo>getFamilyInfoList(int stafferId)throws Exception {
		List<FamilyInfo>list= familyInfoDao.getList(stafferId);
		for(int i=0;i<list.size();i++){
			FamilyInfo t=list.get(i);
			if(t.getFSex()==0)t.setFSex_str("男");
			else if(t.getFSex()==1)t.setFSex_str("女");
			//国籍
			String str=t.getFNationality();
			t.setFNationality_str(baseDataDao.getDictionaryByDictid(Integer.parseInt(str)).getFirstName());
		}
		return list;
	}
	//根据主键获取家庭成员
	public FamilyInfo getFamilyInfoByID(int infoID)throws Exception {
		FamilyInfo t=familyInfoDao.getFamilyInfoByID(infoID);
		return t;
	}
	//根据家庭成员主键获取
	public int getStaffIdByInfoID(int infoID)throws Exception {
		return familyInfoDao.getStaffIdByInfoID(infoID);
	}

	
	//人员信息--附件
	public int fileUpdate(Staff staff) throws Exception {
		try{
			String sfilePath = staff.getSFilePath();
		    String [] paths = sfilePath.split(",");
		    String tempPath = "";
		    for (String s : paths) {
				if(s.contains("temp")){
				    tempPath+=","+UploadFileUtil.moveUploadFile(s);
				}else{
					tempPath+=","+s;
				}
			}
		    staff.setSFilePath(tempPath.substring(1));
			return staffConfDao.fileUpdate(staff);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public Staff getStaffByStafferId(int stafferId)throws Exception  {
		Staff staff= staffConfDao.getStaffByStafferId(stafferId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(null!=staff.getBirthDay()){//基本资料
			//身份证解密
			staff.setIDNumber_decode(SymmetricEncoder.IDNumberDecode(staff.getIDNumber()));
			staff.setBirthDay_str(sdf.format(staff.getBirthDay()));
			staff.setPhone_a(staff.getPhone().substring(0,4));
			staff.setPhone_b(staff.getPhone().substring(5));
		}
		/*if(null!=staff.getSUnitPhone()){//单位信息
			staff.setSUnitPhone_a(staff.getSUnitPhone().substring(0,4));
			staff.setSUnitPhone_b(staff.getSUnitPhone().substring(5));
		}*/
		return staff;
	}
	public Staff getStaffByIDNumber(String IDNumber)throws Exception {
		String str=SymmetricEncoder.IDNumberEncode(IDNumber);
		return staffConfDao.getStaffByIDNumber(str);
	}
	public int importBaseAdd(Staff staff) throws Exception {
		String str=SymmetricEncoder.IDNumberEncode(staff.getIDNumber());
		staff.setIDNumber(str);
		System.out.println("zzy_import:"+staff.toString());
		return staffConfDao.importBaseAdd(staff);
	}

	public List<Staff> getStaffByDeptName(String deptName)throws Exception  {
		
		return staffConfDao.getStaffByDeptName(deptName);
	}


	
}
