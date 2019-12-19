package BookStore.controller;

import BookStore.domain.Book;
import BookStore.service.impl.BookStoreServiceImpl;
import BookStore.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/bookstore")
public class BookStoreController {

	@RequestMapping("/showBooks")
	public ModelAndView showBooks(HttpSession session,
	                              HttpServletRequest request,
	                              HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		try {
			BookStoreServiceImpl bssi = (BookStoreServiceImpl) session.getAttribute("bssi");
			List<Book> bookList = bssi.getBookList();
			System.out.println(bookList);
			String jsonString = JSONObject.toJSONString(bookList);
			mv.setViewName("bookstore");
			mv.addObject("json", jsonString);
			session.setAttribute("bssi", bssi);
			return mv;
		} catch (Exception e) {
			mv.setViewName("error");
			mv.addObject("message", e.toString());
			return mv;
		}
	}

	@RequestMapping("/buyBooks")
	public ModelAndView buyBooks(HttpSession session,
	                             @RequestAttribute("bookID") int bookID,
	                             @RequestAttribute("qty") int qty,
	                             HttpServletRequest request,
	                             HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		try {
			BookStoreServiceImpl bssi = (BookStoreServiceImpl) session.getAttribute("bssi");
			bssi.buyBook(bookID, qty);
			mv.setViewName("bookstore");
			mv.addObject("message", "购买" + qty + "本书成功！");
			session.setAttribute("bssi", bssi);
			return mv;
		} catch (Exception e) {
			mv.setViewName("bookstore");
			mv.addObject("message", e.toString());
			return mv;
		}
	}

	@RequestMapping("/toUserManagement")
	public ModelAndView toUserManagement(HttpSession session,
	                                     HttpServletRequest request,
	                                     HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		try {
			UserServiceImpl usi = new UserServiceImpl((BookStoreServiceImpl) session.getAttribute("bssi"));
			session.setAttribute("usi", usi);
			mv.setViewName("usermanagement");
			return mv;
		} catch (Exception e) {
			mv.setViewName("error");
			mv.addObject("message", e.toString());
			return mv;
		}
	}
}






