package ns.major.audit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ns.common.Page;
import ns.major.audit.query.SysExceptionQuery;



public interface SysExceptionService {
		Page getAll(SysExceptionQuery query)throws Exception;


		void delete(List ids)throws Exception;


		List<String> getInitEModuleName()throws Exception;




		void insertOneSysExcption(String model, String context,
				HttpServletRequest request)throws Exception;

}
