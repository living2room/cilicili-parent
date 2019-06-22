package com.cilicili.user.controller.user;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * 获取application域，没有用到
 */

public class MyContextListener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext application = arg0.getServletContext();
		application.setAttribute("a", "aaa");
		application.setAttribute("b", "bbb");
		System.out.println(application.getAttribute("a"));
		System.out.println(application.getAttribute("b"));
	}
}
