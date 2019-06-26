/*
 * package com.cilicili.user.controller.user;
 * 
 * import java.net.InetAddress;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * import org.apache.shiro.SecurityUtils; import
 * org.apache.shiro.subject.Subject; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.servlet.HandlerInterceptor; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import com.cilicili.user.service.user.RedisService;
 * 
 * import redis.clients.jedis.Jedis; import redis.clients.jedis.JedisPool;
 * 
 *//**
	 * //单点的设置的拦截器
	 *//*
		 * public class MyInterceptor implements HandlerInterceptor{
		 * 
		 * //private RedisService redisUtil = new RedisService();
		 * 
		 * @Autowired JedisPool jedisPool;
		 * 
		 * @Autowired private RedisService redisService ;
		 * 
		 * Jedis jedis = jedisPool.getResource();
		 * logger.info("set key-value info in redis..."); jedis.set("Key1","Value1");
		 * 
		 * 
		 * Jedis jedis = jedisPool.getResource();
		 * logger.info("get key-value info in redis..."); if(jedis.get("Key1") == null){
		 * logger.info("No key-value info in redis!"); }else{
		 * logger.info(jedis.get("Key1")); }
		 * 
		 * 
		 * //在请求处理之前进行调用（Controller方法调用之前
		 * 
		 * @Override public boolean preHandle(HttpServletRequest httpServletRequest,
		 * HttpServletResponse httpServletResponse, Object o) throws Exception {
		 * System.out.printf("preHandle被调用\n"); HttpSession session =
		 * httpServletRequest.getSession(); String userName = (String)
		 * session.getAttribute("userName"); //Users users =
		 * this.usersServiceImpl.findByUserName(userName); String userName1; String ip;
		 * try { //单点的设置 //Jedis jedis = jedisPool.getResource();
		 * //userName1=jedis.get(userName); //userName1 =
		 * redisUtil.get(userName).toString(); userName1 = (String)
		 * redisService.get(userName); System.out.println("aaaa"); InetAddress addr =
		 * InetAddress.getLocalHost(); ip = addr.getHostAddress().toString();
		 * if(userName1.equals(ip)) {
		 * 
		 * return true; }else { //退出登录 Subject currentUser = SecurityUtils.getSubject();
		 * currentUser.logout(); return true; } } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); return true; } }
		 * 
		 * //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
		 * 
		 * @Override public void postHandle(HttpServletRequest httpServletRequest,
		 * HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
		 * throws Exception { System.out.println("postHandle被调用\n"); }
		 * 
		 * //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
		 * 
		 * @Override public void afterCompletion(HttpServletRequest httpServletRequest,
		 * HttpServletResponse httpServletResponse, Object o, Exception e) throws
		 * Exception { System.out.println("afterCompletion被调用\n"); } }
		 */
