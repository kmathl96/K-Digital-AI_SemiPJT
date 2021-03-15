package com.kd.classroom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kd.classroom.bean.Student;
import com.kd.classroom.dao.StudentDAO;

@Controller
public class StudentController {
	private StudentDAO studentDao;
	
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}

//	@RequestMapping(value="/join", method=RequestMethod.GET)
//	public String join(Model model) {
//		model.addAttribute("page", "join_form");
//		return "template";
//	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(HttpServletRequest request) {
//		ModelAndView modelAndView = new ModelAndView();
		Student stu = null;
		try {
			stu = studentDao.queryUser(request.getParameter("id"));
			if (stu!=null) {
				System.out.println("중복 메일");
//				modelAndView.addObject("err","아이디 중복");
//				modelAndView.addObject("page","err");
			} else {
				stu = new Student();
				stu.setId(request.getParameter("id"));
				stu.setName(request.getParameter("name"));
				stu.setPassword(request.getParameter("password"));
				stu.setProfile_img("");
				try {
					studentDao.insertUser(stu);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
//				modelAndView.addObject("page","login_form");
			}
		} catch (Exception e) {
			e.printStackTrace();
//			modelAndView.addObject("err","회원가입 오류");
//			modelAndView.addObject("page","err");
		}
		// 결과와 페이지를 한번에 넣어서 반환
//		modelAndView.setViewName("template");
//		return modelAndView;
		return "home";
	}
//	
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String login(Model model) {
//		model.addAttribute("page","login_form");
//		return "template";
//	}
//	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public ModelAndView login(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
//		ModelAndView modelAndView = new ModelAndView();
//		try {
//			Member mem = memberDao.queryMember(id);
//			if (mem==null) {
//				throw new Exception();
//			} else {
//				if (mem.getPassword().equals(password)) {
//					HttpSession session = request.getSession();
//					session.setAttribute("id", id);
//					modelAndView.addObject("page", "makeaccount_form");
//				} else throw new Exception();
//			}
//		} catch (Exception e) {
//			modelAndView.addObject("page", "login_form");
//		}
//		modelAndView.setViewName("template");
//		return modelAndView;
//	}
//	
//	@RequestMapping(value="/logout", method=RequestMethod.GET)
//	public String logout(HttpServletRequest request, Model model) {
//		HttpSession session = request.getSession();
//		session.removeAttribute("id");
//		model.addAttribute("page", "login_form");
//		return "template";
//	}
}