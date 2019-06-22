package com.cilicili.user.shiro.ultra;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

public class JudgeRealmAuthenticator extends ModularRealmAuthenticator {

	@Override
	public AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {

		System.out.println("realm的名字2222");

		// 判断getRealms()是否返回为空
		assertRealmsConfigured();
		// 强制转换回自定义的CustomizedToken
		JudgeUsernamePasswordToken customizedToken = (JudgeUsernamePasswordToken) authenticationToken;
		// 登录类型
		String loginType = customizedToken.getLoginType();
		// 所有Realm
		System.out.println("realm的名字22" + loginType);
		Collection<Realm> realms = getRealms();
		// 登录类型对应的所有Realm
		System.out.println("realm的名字2" + realms);
		Collection<Realm> typeRealms = new ArrayList<Realm>();
		for (Realm realm : realms) {

			System.out.println("realm的名字" + realm.getName());

			// Java String.contains()方法，当且仅当此字符串包含指定的char值序列时，返回true。
			if (realm.getName().contains(loginType)) {
				typeRealms.add(realm);
				System.out.println("进了");
			}
		}

		System.out.println("11111");

		// 判断是单Realm还是多Realm
		if (typeRealms.size() == 1) {

			return doSingleRealmAuthentication(typeRealms.iterator().next(), customizedToken);
		} else {
			return doMultiRealmAuthentication(typeRealms, customizedToken);
		}

	}

}
