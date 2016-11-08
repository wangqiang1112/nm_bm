package ns.major.analysis.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ns.common.BaseService;
import ns.major.analysis.dao.domain.DepartmentAnalyse;
import ns.major.analysis.dao.domain.EmployAnalyse;
import ns.major.analysis.dao.domain.WarnAnalyse;
import ns.major.analysis.dao.mapper.TaskAnalysisMapper;
import ns.major.analysis.query.TaskAnalysisQuery;
import ns.major.analysis.service.TaskAnalysisService;
import ns.major.config.dao.domain.Department;
import ns.major.employ.dao.domain.Unit;

import org.apache.poi.hssf.record.DSFRecord;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskAnalysisServiceImpl extends BaseService implements
		TaskAnalysisService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	TaskAnalysisMapper dao;
	
	public List<DepartmentAnalyse> getDepart(TaskAnalysisQuery query) throws Exception {
		dateFmt2(query);
		List<Department>depList=dao.getAllDep();
		List<DepartmentAnalyse>departList=new ArrayList<DepartmentAnalyse>();
		for(int i=0;i<depList.size();i++){
			Department dep=depList.get(i);
			DepartmentAnalyse t=new DepartmentAnalyse();
			t.setDepartId(dep.getDepartID());
			t.setDepartName(dep.getDepartName());
			
			query.setDepartId(t.getDepartId());
			
			query.setStatus(1);//已接收
			t.setDeptAmount(dao.getDepart(query));
			query.setStatus(5);//已关闭
			t.setCloseAmount(dao.getDepart(query));
			
			if(t.getDeptAmount()==0){
				t.setPercent(0.0);
			}else{
				Double a=Double.parseDouble(t.getDeptAmount().toString());
				Double b=Double.parseDouble(t.getCloseAmount().toString());
				Double y=100.0*b/a;
				DecimalFormat df=new DecimalFormat("#.00");
				y=Double.parseDouble(df.format(y));
				t.setPercent(y);
			}
			t.setNum(i+1);
			departList.add(t);
		}
		//根据下发量进行排序
		Collections.sort(departList,new Comparator<DepartmentAnalyse>(){
			public int compare(DepartmentAnalyse a,DepartmentAnalyse b){
				return b.getDeptAmount()-a.getDeptAmount();
			}
		});
		for(int i=0;i<departList.size();i++){
			departList.get(i).setNum(i+1);
		}
		return departList;
	}

	public List<EmployAnalyse> getEmploy(TaskAnalysisQuery query)  throws Exception{
		dateFmt2(query);
		List<Unit>unitList=dao.getAllUnit();
		List<EmployAnalyse>employList=new ArrayList<EmployAnalyse>();
		for(int i=0;i<unitList.size();i++){
			Unit unit=unitList.get(i);
			EmployAnalyse t=new EmployAnalyse();
			t.setUnitId(unit.getUnitId());
			t.setUnitName(unit.getUnitName());
			query.setUnitId(t.getUnitId());
			
			query.setType(1);//下发
			t.setUnitAmount(dao.getEmploy(query));
			query.setType(2);//未报
			t.setwAmount(dao.getEmploy(query));
			query.setType(3);//已报
			t.setyAmount(dao.getEmploy(query));
			query.setType(4);//关闭
			t.setCloseAmount(dao.getEmploy(query));
			
			if(t.getUnitAmount()==0){
				t.setPercent(0.0);
			}else{
				Double a=Double.parseDouble(t.getUnitAmount().toString());
				Double b=Double.parseDouble(t.getCloseAmount().toString());
				Double y=100.0*b/a;
				DecimalFormat df=new DecimalFormat("#.00");
				y=Double.parseDouble(df.format(y));
				t.setPercent(y);
			}
			t.setNum(i+1);
			employList.add(t);
		}
		//根据下发量进行排序
		Collections.sort(employList,new Comparator<EmployAnalyse>(){
			public int compare(EmployAnalyse a,EmployAnalyse b){
				return b.getUnitAmount()-a.getUnitAmount();
			}
		});
		for(int i=0;i<employList.size();i++){
			employList.get(i).setNum(i+1);
		}
		return employList;
	}
	public List<WarnAnalyse> getWarn(TaskAnalysisQuery query) throws Exception {
		dateFmt2(query);
		List<Unit>unitList=dao.getAllUnit();
		List<WarnAnalyse>analySeList=new ArrayList<WarnAnalyse>();
		for(int i=0;i<unitList.size();i++){
			Unit unit=unitList.get(i);
			WarnAnalyse t=new WarnAnalyse();
			t.setUnitId(unit.getUnitId());
			t.setUnitName(unit.getUnitName());
			query.setUnitId(t.getUnitId());
			
			query.setType(1);//下发
			t.setUnitAmount(dao.getEmploy(query));
			//预警
			t.setYjAmount(dao.getWarn(query));
			
			if(t.getUnitAmount()==0){
				t.setPercent(0.0);
			}else{
				Double a=Double.parseDouble(t.getUnitAmount().toString());
				Double b=Double.parseDouble(t.getYjAmount().toString());
				Double y=100.0*b/a;
				DecimalFormat df=new DecimalFormat("#.00");
				y=Double.parseDouble(df.format(y));
				t.setPercent(y);
			}
			t.setNum(i+1);
			analySeList.add(t);
		}
		Collections.sort(analySeList,new Comparator<WarnAnalyse>(){
			public int compare(WarnAnalyse a,WarnAnalyse b){
				return b.getUnitAmount()-a.getUnitAmount();
			}
		});
		for(int i=0;i<analySeList.size();i++){
			analySeList.get(i).setNum(i+1);
		}
		return analySeList;
	}
	private void dateFmt2(TaskAnalysisQuery query) throws Exception{
	try{
		if(query.getTimeUnit()==1){//年
			String startTimeSec=query.getStartTime_str()+"-01-01 00:00:00";
			query.setStartTime_test(startTimeSec);
			
			String endTimeSec=query.getEndTime_str()+"-12-31 23:59:59";
			query.setEndTime_test(endTimeSec);
		}else if(query.getTimeUnit()==3){//月
			String startTimeSec=query.getStartTime_str()+"-01 00:00:00";
			query.setStartTime_test(startTimeSec);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			Calendar c=Calendar.getInstance();
			c.setTime(sdf.parse(query.getEndTime_str()));
			String endTimeSec=query.getEndTime_str()+"-"
					+c.getActualMaximum(Calendar.DAY_OF_MONTH)+" 23:59:59";
			query.setEndTime_test(endTimeSec);
		}else if(query.getTimeUnit()==2){//季度
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM季度");
			Calendar c=Calendar.getInstance();
			//开始
			c.setTime(sdf.parse(query.getStartTime_str()));
			if(c.get(Calendar.MONTH)==0){//第一季度
				c.set(c.get(Calendar.YEAR),1-1,1);
			}else if(c.get(Calendar.MONTH)==1){//第二季度
				c.set(c.get(Calendar.YEAR),4-1,1);
			}else if(c.get(Calendar.MONTH)==2){//第三季度
				c.set(c.get(Calendar.YEAR),7-1,1);
			}else if(c.get(Calendar.MONTH)==3){//第四季度
				c.set(c.get(Calendar.YEAR),10-1,1);
			}
			SimpleDateFormat dateSdf=new SimpleDateFormat("yyyy-MM-dd");
			String startTimeSec=dateSdf.format(c.getTime())+" 00:00:00";
			query.setStartTime_test(startTimeSec);
			
			//结束
			c.setTime(sdf.parse(query.getEndTime_str()));
			if(c.get(Calendar.MONTH)==0){//第一季度
				c.set(c.get(Calendar.YEAR),3-1,31);
			}else if(c.get(Calendar.MONTH)==1){//第二季度
				c.set(c.get(Calendar.YEAR),6-1,30);
			}else if(c.get(Calendar.MONTH)==2){//第三季度
				c.set(c.get(Calendar.YEAR),9-1,30);
			}else if(c.get(Calendar.MONTH)==3){//第四季度
				c.set(c.get(Calendar.YEAR),12-1,31);
			}
			String endTimeSec=dateSdf.format(c.getTime())+" 23:59:59";
			query.setEndTime_test(endTimeSec);
		}
		SimpleDateFormat secondSdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		query.setStartTime(secondSdf.parse(query.getStartTime_test()));
		query.setEndTime(secondSdf.parse(query.getEndTime_test()));
		
		//System.out.println("dateFmt:"+query.toString());
	}catch (Exception e) {}
	}
}
