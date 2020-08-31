package com.ck.springbootdemo.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ck.springbootdemo.modules.account.entity.User;
import com.ck.springbootdemo.modules.account.service.UserService;

@Component
public class MyRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;

	//资源授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
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
