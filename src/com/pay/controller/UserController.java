package com.pay.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.controller.BasicController;
import com.pay.dao.UserDaoMapper;
import com.pay.entity.UserInfo;
import com.pay.util.SessionKey;

/**
 * 用户Controller层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BasicController {

	/**
	 * 获取后台主页列表数据
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Resource
	private UserDaoMapper userDao;
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		String username = request.getParameter("username") + "";
		String password = request.getParameter("password") + "";
		System.out.println("username"+username+"password"+password);
		if (username == null || password == null)
			throw new NullPointerException();
		UserInfo u=userDao.login(username,password);
		if (u==null)
		{
			writeInt(response, 1);
			return;
		}
		request.getSession().setAttribute(SessionKey.CHANNEL, u.getUserChannel());
		request.getSession().setAttribute(SessionKey.USERTYPE, u.getUserType());
		request.getSession().setAttribute(SessionKey.CHANNELTYPE, u.getChannelType());
		request.getSession().setAttribute(SessionKey.PAYTYTPE, u.getPayType());
		
		writeInt(response, 0);
	}
}