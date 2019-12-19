package BookStore.controller;

import BookStore.service.impl.BookStoreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username") String username,
	                          @RequestParam("password") String password,
	                          HttpSession session,
	                          HttpServletRequest request,
	                          HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		try {
			BookStoreServiceImpl service = new BookStoreServiceImpl(username, password);
			mv.setViewName("bookstore");

			//Session传值
			session.setAttribute("bssi",service);
			return mv;
		} catch (Exception e) {
			mv.setViewName("index");
			mv.addObject("errorMessage","登录错误！<br>"+ e.toString());
		}
		return mv;
	}
}



