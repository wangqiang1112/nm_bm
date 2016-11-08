package ns.major.employ.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;


import ns.common.BaseService;
import ns.common.Page;
import ns.major.config.dao.domain.Role;
import ns.major.config.dao.domain.User;
import ns.major.config.service.UserConfigService;
import ns.major.employ.dao.domain.Unit;
import ns.major.employ.dao.mapper.UnitMapper;
import ns.major.employ.query.UnitQuery;
import ns.major.employ.service.UnitService;
import ns.util.SymmetricEncoder;
import ns.util.UploadFileUtil;

public class UnitServiceImpl extends BaseService implements UnitService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UnitMapper unitMapper;
	@Autowired
	private UserConfigService userservice;
	
	private List<Unit> unitList;

	public List<Map<Integer,String>> getCity() throws Exception{
		return unitMapper.getCity();
	}

	public List<Map<Integer,String>> getCountry(int Pid) throws Exception{
		return unitMapper.getCountry(Pid);
	}

	public List<Map<Integer,String>> getProperty() throws Exception{
		return unitMapper.getProperty();
	}

	/**
	 * 保存
	 */
	public void save(Unit unit)  throws Exception{
		try {
			unit.setCredentialsNum(SymmetricEncoder.AESEncode("AES", unit.getCredentialsNum()));
			//获得UUID作为索引
			UUID uuid = UUID.randomUUID();
			unit.setUuid(uuid.toString());
			
			if(unit.getFileTemp() != null && unit.getFileTemp() != ""){
				//移动附件到正式目录 并得到路径 
				String filePath;
				try {
					String path = unit.getFileTemp();
					if(path.indexOf(",") != -1){
						StringBuilder sb = new  StringBuilder();
						String[] temp = path.split(",");
						for(int i = 0 ; i < temp.length ; i++ ){
							sb.append(UploadFileUtil.moveUploadFile(temp[i]));
							if(i != temp.length-1){
								sb.append(",");
							}
						}
						filePath = sb.toString();
					}else{
						filePath = UploadFileUtil.moveUploadFile(unit.getFileTemp());
					}
					//设置新的路径
					unit.setFilePath(filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//数据入库
			this.unitMapper.addUnit(unit);
			//添加单位变更记录(快照)
			
			Unit unitTemp = this.getUnit_uuid(uuid.toString());
			this.savePhoto(unitTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存快照表
	 */
	public void savePhoto(Unit unit)  throws Exception{
		this.unitMapper.addUnitPhoto(unit);
	}

	public Boolean queryName(String unitName) throws Exception {
		Boolean ifExit = unitMapper.queryName(unitName)==0?true:false;
		return ifExit;
	}

	public Page list(UnitQuery query,String page1) throws Exception {
		if(page1.equals("1")){
			query.setClas(1);
		}else{
			query.setClas(2);
		}
		return this.pageQuery(UnitMapper.class, "getUnitList", query);
	}

	public Unit getOne(Unit unit) throws Exception {
		return this.unitMapper.getOne(unit);
	}

	public void update(UnitQuery query) throws Exception {
		query.setCheckStatus(Integer.parseInt(query.getType()));
		// 获取id
		String[] unitIds = query.getUnitIds().split(",");
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0;i<unitIds.length;i++){
			ids.add(Integer.parseInt(unitIds[i]));
			if( userservice.getUser(Integer.parseInt(unitIds[i])) == null ){
				// 获取权限
				Role role = userservice.getRoles();
				// 添加帐号
				Unit u = new Unit();
				u.setUnitId(Integer.parseInt(unitIds[i]));
				Unit unitTemp = this.getOne(u);
				int math = (int)(Math.random()*100);
				StringBuilder sb = new StringBuilder();
				sb.append(unitTemp.getPricipalPhone());
				sb.append(String.valueOf(math));
				User user = new User();
				user.setUserName(sb.toString());
				user.setUpassword(SymmetricEncoder.AESEncode("AES", sb.toString()));
				user.setStafferName(unitTemp.getPrincipal());
				user.setUserType(2);
				user.setDepartId(0);
				user.setUserPhone(unitTemp.getPricipalPhone());
				user.setOfferPhone(unitTemp.getUnitTelePhone());
				user.setUnitId(unitTemp.getUnitId());
				user.setUnitName(unitTemp.getUnitName());
				user.setRoleId(role.getRoleId());
				userservice.add(user);
			}
		}
		query.setList(ids);
		this.unitMapper.updateUnit(query);
		this.unitMapper.updateUnitPhoto(query);
	}

	public Unit getUnit_uuid(String uuid)  throws Exception{
		return this.unitMapper.getUnit_uuid(uuid);
	}

	public Page photoList(UnitQuery query) throws Exception {
		return this.pageQuery(UnitMapper.class, "getUnitPhoto", query);
	}

	/**
	 * 变更对比
	 */
	public List<Unit> getContrast(UnitQuery query) throws Exception {
		try {
			unitList = unitMapper.getContrast(query);
			if(unitList.size()==1){
				// 解密
				unitList.get(0).setCredentialsNum(SymmetricEncoder.AESDncode("AES", unitList.get(0).getCredentialsNum()));
			}else if(unitList.size()==2){
				unitList.get(0).setCredentialsNum(SymmetricEncoder.AESDncode("AES", unitList.get(0).getCredentialsNum()));
				unitList.get(1).setCredentialsNum(SymmetricEncoder.AESDncode("AES", unitList.get(1).getCredentialsNum()));
			}
			return unitList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 单位编辑
	 */
	public Unit report(User user) throws Exception {
		int depart = user.getUnitId();
		Unit unitTemp = new Unit();
		unitTemp.setUnitId(depart);
		Unit unit = this.getOne(unitTemp);
		unit.setCredentialsNum(SymmetricEncoder.AESDncode("AES", unit.getCredentialsNum()));
		return unit;
	}

	public void update(Unit unit) throws Exception {
		unit.setCredentialsNum(SymmetricEncoder.AESEncode("AES", unit.getCredentialsNum()));
		
		if(unit.getFileTemp() != null && unit.getFileTemp() != ""){
			//移动附件到正式目录 并得到路径 
			String filePath;
			try {
				String path = unit.getFileTemp();
				if(path.indexOf(",") != -1){
					StringBuilder sb = new  StringBuilder();
					String[] temp = path.split(",");
					for(int i = 0 ; i < temp.length ; i++ ){
						sb.append(UploadFileUtil.moveUploadFile(temp[i]));
						if(i != temp.length-1){
							sb.append(",");
						}
					}
					filePath = sb.toString();
				}else{
					filePath = UploadFileUtil.moveUploadFile(unit.getFileTemp());
				}
				//设置新的路径
				unit.setFilePath(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		unit.setCheckStatus(1);
		unitMapper.update(unit);
		
		//添加单位变更记录(快照)
		
		Unit unitTemp = this.getOne(unit);
		this.savePhoto(unitTemp);
	}

	public List<String> getAllFilePath(Unit unit)  throws Exception{
		return unitMapper.getAllFilePath(unit);
	}

	public Boolean credCheck(String cred)  throws Exception{
		Boolean ifExit = unitMapper.credCheck(cred)==0?true:false;
		return ifExit;
	}

}
