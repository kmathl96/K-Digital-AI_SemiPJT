package com.kd.classroom;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kd.classroom.bean.Question;
import com.kd.classroom.bean.Student;
import com.kd.classroom.dao.QuestionDAO;
import com.kd.classroom.dao.StudentDAO;

@Controller
public class StudentController {
	private StudentDAO studentDao;
	private QuestionDAO questionDao;
	
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	public void setQuestionDao(QuestionDAO questionDao) {
		this.questionDao = questionDao;
	}

//	@RequestMapping(value="/join", method=RequestMethod.GET)
//	public String join(Model model) {
//		model.addAttribute("page", "join_form");
//		return "template";
//	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ModelAndView join(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Student stu = null;
		try {
			stu = studentDao.queryUser(request.getParameter("id"));
			if (stu!=null) {
				System.out.println("중복 메일");
				modelAndView.addObject("err","아이디 중복");
				modelAndView.addObject("page","err");
			} else {
				stu = new Student();
				stu.setId(request.getParameter("id"));
				stu.setName(request.getParameter("name"));
				stu.setPassword(request.getParameter("password"));
				stu.setProfile_img("");
				try {
					studentDao.insertUser(stu);
					List<Question> ques = questionDao.queryQuestions();
					for (Question question : ques) {
						Student writer = studentDao.queryUser(question.getW_id());
						question.setW_name(writer.getName());
						question.setCreated_at(question.getCreated_at().substring(0,10));
					}
					modelAndView.addObject("questions", ques);
					modelAndView.addObject("page","questionList");
				} catch (Exception e2) {
					e2.printStackTrace();
					modelAndView.addObject("err","회원가입 오류");
					modelAndView.addObject("page","err");
				}
//				modelAndView.addObject("page","login_form");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","회원가입 오류");
			modelAndView.addObject("page","err");
		}
		// 결과와 페이지를 한번에 넣어서 반환
		modelAndView.setViewName("home");
		return modelAndView;
//		return "home";
	}
//	
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String login(Model model) {
//		model.addAttribute("page","login_form");
//		return "template";
//	}
//	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		ModelAndView modelAndView = new ModelAndView();
		try {
			Student stu = studentDao.queryUser(id);
			if (stu!=null && stu.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				List<Question> ques = questionDao.queryQuestions();
				for (Question question : ques) {
					Student writer = studentDao.queryUser(question.getW_id());
					question.setW_name(writer.getName());
					question.setCreated_at(question.getCreated_at().substring(0,10));
				}
				modelAndView.addObject("questions", ques);
				modelAndView.addObject("page", "questionList");
			} else {
				PrintWriter out = response.getWriter();
		        out.println("<script>alert('이메일이나 비밀번호가 틀린 것 같아요!');</script> ");
		        out.flush();
				modelAndView.setViewName("first");
				return modelAndView;
			}
		} catch (Exception e) {
			modelAndView.setViewName("first");
			return modelAndView;
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		modelAndView.setViewName("first");
		return modelAndView;
	}
}