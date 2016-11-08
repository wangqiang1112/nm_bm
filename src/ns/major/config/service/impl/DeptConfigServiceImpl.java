package ns.major.config.service.impl;

import java.util.List;

import ns.common.BaseService;
import ns.common.Page;
import ns.major.config.dao.domain.Department;
import ns.major.config.dao.domain.Staff;
import ns.major.config.dao.mapper.DeptConfigMapper;
import ns.major.config.query.DeptQuery;
import ns.major.config.service.DeptConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptConfigServiceImpl extends BaseService implements DeptConfigService {
    @Autowired
	private DeptConfigMapper deptConfDao;

	public Page getAll(DeptQuery queryForm) {
		return pageQuery(DeptConfigMapper.class, "query", queryForm);
	}

	public int delete(int deptId)throws Exception  {
		return deptConfDao.delete(deptId);
	}

	public List<Staff> getAllStaff() throws Exception {
		return deptConfDao.getAllStaff();
	}

	public int add(Department dept) throws Exception {
		return deptConfDao.add(dept);
	}

	public int update(Department dept)throws Exception  {
		return deptConfDao.update(dept);
	}

	public Department getDeptById(int deptId) throws Exception {
		return deptConfDao.getDeptById(deptId);
	}

	public List<Staff> getStaffByDept(int departId)throws Exception  {
		return deptConfDao.getStaffByDept(departId);
	}

	public Boolean deptNameCheck(String departName)throws Exception  {
		Boolean ifExit=deptConfDao. deptNameCheck(departName)==0?true:false;
		return ifExit;
	}
}
