package com.cilicili.comment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.comment.domain.Comment;
import com.cilicili.comment.domain.Like;
import com.cilicili.comment.domain.Reply;
import com.cilicili.comment.service.CommentService;
import com.cilicili.comment.service.LikeService;
import com.cilicili.comment.service.ReplyService;


@Controller
@RequestMapping("/like")
public class LikeController {


	@Autowired
	private LikeService likeService;

	@Autowired
	private CommentService commentService;


	@Autowired
	private ReplyService replyService;
	
	/**
	 * 评论点赞
	 * @param like
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/CommentLike",produces="application/json")
	@ResponseBody
	public String CommentLike(@RequestBody Like like, Model model) {
		System.out.println("点赞模块："+like.toString());
		System.out.println("like.getUserId():   "+like.getUserId());
		//查询是否有该用户的点赞记录
		Like like1=likeService.findByUserId(like.getUserId());
		System.out.println("查询的 like1: "+like1);
		//根据commentId找到评论
		Comment comment=commentService.findById(like.getCommentId());
		System.out.println("comment:  "+comment);
		int res=0;
		if (like1!=null){
			System.out.println("有该记录");
			//如果找到这条记录,删除该记录,同时评论的点赞数减一（取消赞）
			//删除记录
			likeService.deleteById(like1.getLikeId());
			//根据commentId找到评论
			// Comment comment=commentService.findById(like1.getCommentId());
			//System.out.println("comment"+comment);
			//删除记录
			// likeService.deleteById(like1.getLikeId());
			//评论点赞数减一
			comment.setCommentLikeNum(comment.getCommentLikeNum()-1);
			res=comment.getCommentLikeNum();
			System.out.println("result:"+res);
			//更新评论点赞数
			commentService.updateById(comment);
		}else{
			//如果没有找到这条记录,则添加这条记录,同时评论点赞数加一
			//添加记录
			System.out.println("没有记录：");
			//likeService.insert(like);
			like1=new Like();
			like1.setCommentId(comment.getCommentId());
			like1.setUserId(comment.getUserId());
			likeService.insert(like1);
			System.out.println("添加记录后的 like1: "+like1);

			//评论点赞数加一
			comment.setCommentLikeNum(comment.getCommentLikeNum()+1);
			res=comment.getCommentLikeNum();
			System.out.println("result:"+res);
			//更新评论点赞数
			commentService.updateById(comment);
		}
		model.addAttribute("videoId", comment.getVideoId());
		// return ""+result;
		return "点赞数："+res;
	}
	
	
	
	@PostMapping(value = "/ReplyLike",produces="application/json")
	@ResponseBody
	public String ReplyLike(@RequestBody Like like, Model model) {
		System.out.println("点赞模块："+like.toString());
		System.out.println("like.getUserId():   "+like.getUserId());
		//查询是否有该用户的点赞记录
		Like like1=likeService.findByUserId(like.getUserId());
		System.out.println("查询的 like1: "+like1);
		
		//根据replyId找到回复
		Reply reply = replyService.findById(like.getReplyId());
		System.out.println("reply:  "+reply);
		
		int outcome=0;
		if (like1!=null){
			System.out.println("有该记录");
			//如果找到这条记录,删除该记录,同时评论的点赞数减一（取消赞）
			//删除记录
			likeService.deleteById(like1.getLikeId());
			
			//回复点赞数减一
			reply.setReplyLikeNum(reply.getReplyLikeNum()-1);
			outcome = reply.getReplyLikeNum();
			System.out.println("outcome:  "+outcome);
			
			//更新回复点赞数
			replyService.updateById(reply);
		}else{
			//如果没有找到这条记录,则添加这条记录,同时评论点赞数加一
			//添加记录
			System.out.println("没有记录：");
			//likeService.insert(like);
			like1=new Like();
			like1.setReplyId(reply.getReplyId());
			like1.setUserId(reply.getUserId());
			likeService.insert(like1);
			System.out.println("添加记录后的 like1: "+like1);

			//回复点赞数加一
			reply.setReplyLikeNum(reply.getReplyLikeNum()+1);
			outcome = reply.getReplyLikeNum();
			System.out.println("outcome:  "+outcome);
			
			//更新回复点赞数
			replyService.updateById(reply);
		}
		model.addAttribute("commentId", reply.getCommentId());
		// return ""+result;
		return "点赞数："+outcome;
	}
	
	
}
