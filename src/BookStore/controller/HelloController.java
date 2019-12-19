package BookStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping(value = "/welcome")
	public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("errorMessage"," ↓↓↓ 第一次登录网址请注册账号");
		return mv;
	}
}