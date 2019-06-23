//package com.cilicili.user.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class ErrorHandleController {
//
//	  
//	private static final Logger log = LoggerFactory.getLogger(ErrorHandleController.class);
//	@ExceptionHandler({RuntimeException.class})
//	@ResponseStatus(HttpStatus.OK)
//	public ModelAndView processException(RuntimeException exception){
//		log.info("自定义异常处理-RuntimeException");
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("myException", exception.getMessage());
//		mav.setViewName("/404.html");
//		return mav;
//	}
//	
//	public ModelAndView processException(Exception exception){
//		log.info("统一异常处理-Exception");
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("myException", exception.getMessage());
//		mav.setViewName("/404.html");
//		return mav;
//	}
//
//}
