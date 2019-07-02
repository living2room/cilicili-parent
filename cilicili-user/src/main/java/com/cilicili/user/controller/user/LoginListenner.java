package com.cilicili.user.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.cilicili.domain.user.user.Users;

@WebListener
public class LoginListenner implements HttpSessionAttributeListener {
	/**
	 * 用于存放账号和session对应关系的map
    */
    private Map<String,HttpSession> map=new HashMap<>();
 
    /**
       * 当向session中放入数据触发
     */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		 //USER_SESSION为登录时存入session的名字
        if (name.equals("user")) {
            Users user = (Users) event.getValue();
            if (map.get(user.getUserName()) != null) {
                HttpSession session = map.get(user.getUserName());
                session.removeAttribute(user.getUserName());
                session.invalidate();
                System.out.println("哈哈哈哈1");
            }
            map.put(user.getUserName(), event.getSession());
            System.out.println("哈哈哈哈2");
        }
		
	}

	/**
     * 当向session中移除数据触发
     */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
        if (name.equals("loginuser")) {
            Users user = (Users) event.getValue();
            map.remove(user.getUserName());
            System.out.println("哈哈哈哈3");
        }	
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {

	}

	public Map<String, HttpSession> getMap() {
		return map;
	}

	public void setMap(Map<String, HttpSession> map) {
		this.map = map;
	}


}
