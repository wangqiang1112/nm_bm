package ns.major.config.dao.mapper;

import ns.major.config.dao.domain.Privilege;
import ns.major.config.dao.domain.User;

public interface UserLoginMapper {

	User findUserByUserName(String userName);


	String getPermissions(int userId);

	Privilege getOnePermission(int parseInt);

	
}
