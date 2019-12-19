package BookStore.controller;

import BookStore.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@Controller
@RequestMapping("/regs")
public class RegisterController {
	@RequestMapping("/toregs")
	public String toRegs(HttpServletResponse response, HttpServletRequest request) throws IOException {
		return "regs";
	}

	@RequestMapping("/doregs")
	public ModelAndView doRegs(@RequestParam("username") String username,
	                           @RequestParam("password") String password,
	                           @RequestParam("sex") String Sex,
	                           @RequestParam("birthday") String birthday,
	                           HttpServletRequest request,
	                           HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		try {
			UserServiceImpl userService = new UserServiceImpl();
			userService.register(username, password, Sex, Date.valueOf(birthday), null);
			mv.setViewName("index");
			mv.addObject("errorMessage","注册成功！现在可以登录啦\uD83D\uDE01");
			return mv;
		} catch (Exception e) {
			mv.setViewName("regs");
			mv.addObject("regsErrorMessage",("注册错误！<br>"+ e.toString()));
			return mv;
		}
	}
}





