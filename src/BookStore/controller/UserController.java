package BookStore.controller;

import BookStore.domain.Order;
import BookStore.domain.OrderInfo;
import BookStore.service.UserService;
import BookStore.service.impl.BookStoreServiceImpl;
import BookStore.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.hsqldb.rights.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/changePassword")
	public ModelAndView changePassword(@RequestAttribute("oldPassword") String oldPassword,
	                                   @RequestAttribute("newPassword") String newPassword,
	                                   @RequestAttribute("checkPassword") String checkPassword,
	                                   HttpSession session,
	                                   HttpServletRequest request,
	                                   HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("usermanagement");
		try {
			UserServiceImpl usi = (UserServiceImpl) session.getAttribute("usi");
			if (!checkPassword.equals(newPassword)) {
				mv.addObject("message", "新密码两次输入不一致！请检查你的密码");
				return mv;
			}
			if (!oldPassword.equals(usi.getUser().getPassword())) {
				mv.addObject("message", "旧密码不正确！");
				return mv;
			}
			if(oldPassword.equals(newPassword)){
				mv.addObject("message", "旧密码不能与新密码一致！");
				return mv;
			}
			usi.changePassword(newPassword);
			mv.addObject("message","密码修改成功！");
			session.setAttribute("usi",usi);
			return mv;
		} catch (Exception e) {
			mv.addObject("message", e.toString());
			return mv;
		}
	}

	@RequestMapping("/printOrder")
	public ModelAndView printOrder(HttpSession session,
	                               HttpServletRequest request,
	                               HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("usermanagement");
		try{
			BookStoreServiceImpl bssi = (BookStoreServiceImpl)session.getAttribute("bssi");
			List<Order> orderList = bssi.getOrderList();
			String orderJSON = JSONObject.toJSONString(orderList);
			mv.addObject("json",orderJSON);
			return mv;
		}catch (Exception e){
			mv.addObject("message",e.toString());
			return mv;
		}
	}

	@RequestMapping("/printOrderInfo")
	public ModelAndView printOrderInfo(HttpSession session,
	                               HttpServletRequest request,
	                               HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("usermanagement");
		try{
			BookStoreServiceImpl bssi = (BookStoreServiceImpl)session.getAttribute("bssi");
			List<OrderInfo> orderInfoList = bssi.getOrderInfoList();
			String orderInfoJSON = JSONObject.toJSONString(orderInfoList);
			mv.addObject("json",orderInfoJSON);
			return mv;
		}catch (Exception e){
			mv.addObject("message",e.toString());
			return mv;
		}
	}

	@RequestMapping("/cancel")
	public ModelAndView cancelOrder(@RequestParam("orderID") int orderID,
									HttpSession session,
	                                HttpServletResponse response,
	                                HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("usermanagement");
		try{
			BookStoreServiceImpl bssi = (BookStoreServiceImpl)session.getAttribute("bssi");
			bssi.cancelOrder(orderID);
			mv.addObject("message","成功删除 "+orderID+"号订单!");
			return mv;
		}catch (Exception e){
			mv.addObject("message",e.toString());
			return mv;
		}
	}
}

