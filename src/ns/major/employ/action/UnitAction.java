package ns.major.employ.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import ns.common.BaseAction;
import ns.common.Global;
import ns.major.employ.dao.domain.Unit;
import ns.major.employ.query.UnitQuery;
import ns.major.employ.service.UnitService;
import ns.util.SymmetricEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UnitAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private UnitService unitService;
	private Unit unit;
	private Unit unitTemp;
	private List<Unit> unitList;
	private UnitQuery query;
	private List<Map<Integer,String>> cityMap;
	private List<Map<Integer,String>> countryMap;
	private List<Map<Integer,String>> propertyMap;
	private String Pid;
	private String CountryTemp;
	private String unitName;//单位名称
	private String page1;//1:资料审核  2:资料查询
	

	/**
	 * 添加首页
	 * @return
	 */
	public String report()  throws Exception{
		try {
			if(getUserInfo().getUnitId() != 0){
				unit = unitService.report(getUserInfo());
				unit.setDealType(1);
				countryMap = unitService.getCountry(Integer.parseInt(unit.getUnitCity()));
			}else{
				unit =  new Unit();
			}
			cityMap = unitService.getCity();
			propertyMap = unitService.getProperty();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取区(县)
	 * @throws IOException
	 */
	public void getCountry() throws Exception {
		countryMap = unitService.getCountry(Integer.parseInt(this.getPid()));
		StringBuilder html = new StringBuilder();
		// 区县 html 代码
		html.append("<option value="+""+">-请选择-</option>");
		for(Map<Integer,String> map : countryMap){
			html.append("<option value="+'"'+map.get("Pid")+'"'+">"+map.get("Country")+"</option>");
		}
		this.getHttpResponse().getWriter().write(html.toString());
	}
	
	/**
	 * 添加
	 * @return
	 */
	public void save()  throws Exception{
		unit = this.getForm(Unit.class);
		try {
			if(unit.getUnitId() == 0 && unit.getId() == 0){
				unitService.save(unit);
				success("上报成功");
			}else{
				if(unitService.getOne(unit).getCheckStatus()!=1){
					if(unit.getDealType()==1){
						unitService.update(unit);
						success("编辑成功");
					}
				}else{
					error("正在审核中,请勿重复上报");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(unit.getDealType()==1){
				error("编辑失败");
			}else{
				error("上报失败");
			}
		}
		getHttpResponse().sendRedirect(getContextPath()+"/employ/report.do");
	}
	
	/**
	 * 单位列表(查询)
	 * @return
	 */
	public String list() throws Exception{
		query = this.getForm(UnitQuery.class);
 		if(page1.equals("2")){
 			if(getUserInfo().getUnitId() != 0 ){
 				query.setLevel(1);
 				query.setUnitId(getUserInfo().getUnitId());
 			}
		}
		setPage(unitService.list(query,page1));
		setQuery(query);
		cityMap = unitService.getCity();
		propertyMap = unitService.getProperty();
		if(page1.equals("1")){
			return "audit";
		}
		return "report";
	}
	
	/**
	 * 审核资料页面
	 * @return
	 */
	public String detail() throws Exception{
		Unit unitTemp = new Unit();
		unitTemp = this.getForm(Unit.class);
		if(unitTemp.getId()==0){
			unit = unitService.getOne(unitTemp);
		}else{
			unit = unitService.getOne(unitTemp);
		}
		unit.setCredentialsNum(SymmetricEncoder.AESDncode("AES", unit.getCredentialsNum()));
		if(unitTemp.getType()!=null){
			unit.setType("9");
		}
		return "success";
	}
	
	/**
	 * 批量审核
	 * @return
	 * @throws IOException 
	 */
	public void update() throws Exception{
		query = this.getForm(UnitQuery.class);
		try {
			if(query.getUnitIds() != null){
				unitService.update(query);
				success("审批成功");
				getHttpResponse().sendRedirect(getContextPath()+"/employ/list.do?page1=1");
			}else{
				query.setUnitIds(String.valueOf(query.getUnitId()));
				unitService.update(query);
				success("审批成功",true);
				getHttpResponse().sendRedirect(getContextPath()+"/toMessage.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
			error("审批失败",true);
			getHttpResponse().sendRedirect(getContextPath()+"/toMessage.do");
		}
	}
	
	/**
	 * 变更通知--列表
	 * @return
	 */
	public String photoList() throws Exception{
		query = this.getForm(UnitQuery.class);
		setPage(unitService.photoList(query));
		setQuery(query);
		return "success";
	}
	
	/**
	 * 变更对比
	 * @return
	 */
	public String getContrast() throws Exception{
		try {
			query = this.getForm(UnitQuery.class);
			unitList = unitService.getContrast(query);
			if(unitList.size() == 1){
				unit = unitList.get(0);
			}else{
				unitTemp = unitList.get(0);
				unit = unitList.get(1);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 验证 组织证书代码
	 * @throws IOException
	 */
	public void credCheck() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		try {
			String credTemp = getHttpRequest().getParameter("param");
			Boolean ifExit = unitService.credCheck(SymmetricEncoder.AESEncode("AES", credTemp));
			if(ifExit == true){
				map.put("info", "通过验证");
				map.put("status","y");
			}else{
				map.put("info", "组织结构代码不能重复");
				map.put("status","n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		JSONObject json=JSONObject.fromObject(map);
		getHttpResponse().setContentType("text/javascript;charset=UTF-8");
		getHttpResponse().getWriter().write(json.toString());
		getHttpResponse().getWriter().close();
	}
	
	/**
	 * 验证 单位名称
	 * @throws IOException
	 */
	public void queryName() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		try {
			String unitName = getHttpRequest().getParameter("param");
			Boolean ifExit = unitService.queryName(unitName);
			if(ifExit == true){
				map.put("info", "通过验证");
				map.put("status","y");
			}else{
				map.put("info", "单位名称不能重复");
				map.put("status","n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logSysExcption(Global.CONFIG_MODEL, e.toString());
		}
		JSONObject json=JSONObject.fromObject(map);
		getHttpResponse().setContentType("text/javascript;charset=UTF-8");
		getHttpResponse().getWriter().write(json.toString());
		getHttpResponse().getWriter().close();
	}
	
	
	// ----------------------

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public UnitQuery getQuery() {
		return query;
	}

	public void setQuery(UnitQuery query) {
		this.query = query;
	}


	public List<Map<Integer, String>> getCityMap() {
		return cityMap;
	}

	public void setCityMap(List<Map<Integer, String>> cityMap) {
		this.cityMap = cityMap;
	}

	public List<Map<Integer, String>> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(List<Map<Integer, String>> countryMap) {
		this.countryMap = countryMap;
	}

	public List<Map<Integer, String>> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(List<Map<Integer, String>> propertyMap) {
		this.propertyMap = propertyMap;
	}

	public String getPid() {
		return Pid;
	}

	public void setPid(String pid) {
		Pid = pid;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPage1() {
		return page1;
	}

	public void setPage1(String page1) {
		this.page1 = page1;
	}

	public void setUnitList(List<Unit> unitList) {
		this.unitList = unitList;
	}

	public Unit getUnitTemp() {
		return unitTemp;
	}

	public String getCountryTemp() {
		return CountryTemp;
	}

	public void setCountryTemp(String countryTemp) {
		CountryTemp = countryTemp;
	}
}
