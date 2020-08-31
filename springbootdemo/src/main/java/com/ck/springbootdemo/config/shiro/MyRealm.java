package com.ck.springbootdemo.config.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ck.springbootdemo.modules.account.entity.Resource;
import com.ck.springbootdemo.modules.account.entity.Role;
import com.ck.springbootdemo.modules.account.entity.User;
import com.ck.springbootdemo.modules.account.service.ResourceService;
import com.ck.springbootdemo.modules.account.service.RoleService;
import com.ck.springbootdemo.modules.account.service.UserService;

@Component
public class MyRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;

	//资源授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		String userName = (String)principals.getPrimaryPrincipal();
		User user = userService.getUserByUserName(userName);
		if(user == null) {
			return null;
		}
		
		List<Role> roles = roleService.getRoleByUserId(user.getUserId());
		for(Role role : roles) {
			authorizationInfo.addRole(role.getRoleName());;
			List<Resource> resources = resourceService.getResourcesByRoleId(role.getRoleId());
			
			for(Resource resource : resources) {
				authorizationInfo.addStringPermission(resource.getPermission());
			}
		}
		
		return authorizationInfo;
		
	}

	//身份验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		User user = userService.getUserByUserName(userName);
		if(user == null) {
			throw new UnknownAccountException("This user name do not exist");
		}
		/**
		 * principal：当前用户信息，可以是user对象，也可以是用户名
		 * credentials：当前用户凭证，传密码
		 * realmName：当前realm对象的唯一名字，调用父类的getName()方法
		 */
		return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
	}
	
}
