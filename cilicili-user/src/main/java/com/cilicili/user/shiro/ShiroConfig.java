package com.cilicili.user.shiro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * Shiro的配置类
 * 
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfig {

	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设置安全管理
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 添加Shiro内置过滤器
		/**
		 * Shiro内置过滤器，可以实现权限相关的拦截 常用的过滤器： anon：无需认证（登录）可以访问, authc：必须认证才可访问
		 * user：如果用rememberMe（记住我）的功能可以直接访问 perms:该资源必须得到资源权限才可以访问 role：该资源必须得到角色权限才可以访问
		 */
		Map<String, String> filterMap = new LinkedHashMap<String, String>();

		// 认证过滤器
		filterMap.put("/user/add", "authc");
		filterMap.put("/user/update", "authc");
		filterMap.put("/user/testThymeleaf", "authc");
		filterMap.put("/user/login", "anon");
		filterMap.put("/user/toLogin", "anon");
		filterMap.put("/user/*", "anon");

		filterMap.put("/admin/add", "authc");
		filterMap.put("/admin/update", "authc");
		filterMap.put("/admin/testThymeleaf", "authc");

		// 授权过滤器,该角色需要拥有相关的权限才可以访问，这里会执行UserRealm那边的授权执行逻辑
		// 注意：当授权被拦截之后，会自动跳转到未授权页面
		// filterMap.put("/user/add", "perms[user:add]");
		// filterMap.put("/user/update", "perms[user:update]");

		/// 用户，需要角色权限 “user”
		// filterMap.put("/user/testThymeleaf", "roles[user]");
		// filterMap.put("/user/testThymeleaf", "roles[lhy]");

		// 管理员，需要角色权限 “admin”
		// filterMap.put("/admin/background", "roles[admin]");
		// filterMap.put("/admin/background", "roles[lhy]");

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		//filterChainDefinitionMap.put("/logout", "logout");

		/*
		 * 
		 * 这里需要给一个if判断，是管理员登的就跳管理员登录页 否则就是用户登录页
		 */
		// 修改调整的登陆页面
		/* shiroFilterFactoryBean.setLoginUrl("/user/toLogin"); */
		// 设置未授权页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/user/noAuth");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 创建DefaultWebSecurityManager @Qualifier("userRealm") UserRealm userRealm
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm,
			@Qualifier("adminRealm") AdminRealm adminRealm
			) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		List<Realm> realmsList = new ArrayList<Realm>();
		realmsList.add(adminRealm);
		realmsList.add(userRealm);
		
		// 关联realm
//		securityManager.setRealm(userRealm);
		securityManager.setRealms(realmsList);
		// 注入缓存管理器;
		//securityManager.setCacheManager(ehCacheManager());
		// 这个如果执行多次，也是同样的一个对象;

		return securityManager;
	}

	/*
	 * @Bean("authenticator") public JudgeRealmAuthenticator
	 * getCustomizedModularRealmAuthenticator() { JudgeRealmAuthenticator
	 * judgeRealmAuthenticator = new JudgeRealmAuthenticator();
	 * AllSuccessfulStrategy authenticationStrategy = new AllSuccessfulStrategy();
	 * judgeRealmAuthenticator.setAuthenticationStrategy(authenticationStrategy);
	 * return judgeRealmAuthenticator; }
	 */

	/*
	 * <!-- 配置使用自定义认证器，可以实现多Realm认证，并且可以指定特定Realm处理特定类型的验证 --> <bean
	 * id="authenticator"
	 * class="cn.com.security.filter.CustomizedModularRealmAuthenticator"> <!--
	 * 配置认证策略，只要有一个Realm认证成功即可，并且返回所有认证成功信息 --> <property
	 * name="authenticationStrategy"> <bean
	 * class="org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy"></bean>
	 * </property> </bean>
	 */

	/**
	 * shiro缓存管理器; 需要注入对应的其它的实体类中： 1、安全管理器：securityManager
	 * 可见securityManager是整个shiro的核心；
	 * 
	 * @return
	 */

	/*
	 * @Bean public SpringTemplateEngine templateEngine() { SpringTemplateEngine
	 * templateEngine = new SpringTemplateEngine();
	 * templateEngine.setTemplateResolver(templateResolver());
	 * 
	 * Set<IDialect> additionalDialects = new HashSet<IDialect>();
	 * 
	 * additionalDialects.add(new ShiroDialect());
	 * 
	 * templateEngine.setAdditionalDialects(additionalDialects);
	 * 
	 * return templateEngine; }
	 */

	/* thymeleaf 配置支持 shiro标签 */
	@Bean(name = "shiroDialect")
	public ShiroDialect shiroDialect() {

		return new ShiroDialect();

	}
	
	/*
	 * @Bean public EhCacheManager ehCacheManager(){
	 * System.out.println("ShiroConfiguration.getEhCacheManager()"); EhCacheManager
	 * cacheManager = new EhCacheManager();
	 * cacheManager.setCacheManagerConfigFile("classpath:config/shiro-ehcache.xml");
	 * return cacheManager;
	 * 
	 * }
	 */
	 

	/**
	 * 创建Realm
	 */
	@Bean(name = "userRealm")
	public UserRealm getUserRealm() {
		return new UserRealm();
	}

	@Bean(name = "adminRealm")
	public AdminRealm getAdminRealm() {
		return new AdminRealm();
	}

}
