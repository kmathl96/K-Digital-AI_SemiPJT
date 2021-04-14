package com.kd.classroom;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kd.classroom.bean.Comment;
import com.kd.classroom.bean.Question;
import com.kd.classroom.bean.Scrap;
import com.kd.classroom.bean.User;
import com.kd.classroom.dao.CommentDAO;
import com.kd.classroom.dao.QuestionDAO;
import com.kd.classroom.dao.ScrapDAO;
import com.kd.classroom.dao.UserDAO;

@Controller
public class UserController {
	private UserDAO userDao;
	private QuestionDAO questionDao;
	private CommentDAO commentDao;
	private ScrapDAO scrapDao;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	public void setQuestionDao(QuestionDAO questionDao) {
		this.questionDao = questionDao;
	}
	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}
	public void setScrapDao(ScrapDAO scrapDao) {
		this.scrapDao = scrapDao;
	}
	
//	@RequestMapping(value="/join", method=RequestMethod.GET)
//	public String join(Model model) {
//		model.addAttribute("page", "join_form");
//		return "template";
//	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ModelAndView joinStudent(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User stu = null;
		try {
			stu = userDao.queryStudent(request.getParameter("id"));
			if (stu!=null) {
				modelAndView.addObject("err","이메일 중복");
				modelAndView.addObject("page","err");
			} else {
				stu = new User();
				stu.setId(request.getParameter("id"));
				stu.setName(request.getParameter("name"));
				stu.setPassword(request.getParameter("password"));
				Random random = new Random();
				stu.setProfile_img("semiDefaultImg"+Integer.toString(random.nextInt(3))+".png");
				try {
					userDao.insertStudent(stu);
					HttpSession session = request.getSession();
					session.setAttribute("id", stu.getId());
					List<Question> ques = questionDao.queryQuestions();
					for (Question question : ques) {
						User writer = userDao.queryStudent(question.getW_id());
						question.setW_name(writer.getName());
						question.setCreated_at(question.getCreated_at().substring(0,10));
					}
					modelAndView.addObject("questions", ques);
					modelAndView.addObject("page","questionList");
				} catch (Exception e2) {
					e2.printStackTrace();
					modelAndView.addObject("err","회원가입 실패");
					modelAndView.addObject("page","err");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","회원가입 실패");
			modelAndView.addObject("page","err");
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}
//	
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String login(Model model) {
//		model.addAttribute("page","login_form");
//		return "template";
//	}
//	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		ModelAndView modelAndView = new ModelAndView();
		try {
			User user = userDao.queryUser(id);
			if (user!=null && user.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				List<Question> ques = questionDao.queryQuestions();
				for (Question question : ques) {
					User writer = userDao.queryStudent(question.getW_id());
					question.setW_name(writer.getName());
					question.setCreated_at(question.getCreated_at().substring(0,10));
				}
				modelAndView.addObject("questions", ques);
				modelAndView.addObject("page", "questionList");
			} else {
				PrintWriter out = response.getWriter();
		        out.println("<script>alert('이메일 또는 비밀번호가 틀린 것 같아요!');</script> ");
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
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public ModelAndView mypage(HttpServletRequest request) throws IllegalStateException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");
		try {
			User request_user = userDao.queryUser(user_id);
			List<Question> ques = questionDao.queryQuestions(user_id);
			for (Question question : ques) {
				question.setCreated_at(question.getCreated_at().substring(0,10));
			}
			modelAndView.addObject("questions", ques);
			List<Comment> coms = commentDao.queryComments(user_id);
			modelAndView.addObject("comments", coms);
			List<Scrap> scr_list = scrapDao.queryScraps(user_id);
			List<Question> scrs = new ArrayList<>();
			for (Scrap scrap : scr_list) {
				Question que = questionDao.queryQuestion(scrap.getQ_id());
				que.setCreated_at(que.getCreated_at().substring(0,10));
				scrs.add(que);
			}
			modelAndView.addObject("scraps", scrs);
			modelAndView.addObject("request_user", request_user);
			modelAndView.addObject("page","myPage");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","My page 조회 실패");
			modelAndView.addObject("page","err");
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value="/changeProfile", method=RequestMethod.GET)
	public ModelAndView changeProfile(HttpServletRequest request) throws IllegalStateException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");
		try {
			User request_user = userDao.queryUser(user_id);
			modelAndView.addObject("request_user", request_user);
			modelAndView.addObject("page","changeProfile");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","실패");
			modelAndView.addObject("page","err");
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value="/changeProfile", method=RequestMethod.POST)
	public ModelAndView changeProfile(HttpServletRequest request, @RequestParam("uploadFile") MultipartFile file) throws IllegalStateException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		String PROFILE_IMAGE_PATH = request.getSession().getServletContext().getRealPath("resource/profileUpload/");
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");
		System.out.println(user_id);
		try {
			User request_user = userDao.queryUser(user_id);
			if (!file.getOriginalFilename().isEmpty()) {
				file.transferTo(new File(PROFILE_IMAGE_PATH, request_user.getId()));
				System.out.println("empty");
			}
			request_user.setProfile_img(PROFILE_IMAGE_PATH+"/"+request_user.getId());
			System.out.println(request_user);
			userDao.changeProfileImg(request_user);
			modelAndView.addObject("request_user", request_user);
			modelAndView.addObject("page","myPage");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","실패");
			modelAndView.addObject("page","err");
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
//	@RequestMapping(value="/signup", method=RequestMethod.POST)
//	public ModelAndView joinStudent(HttpServletRequest request) {
//		ModelAndView modelAndView = new ModelAndView();
//		User stu = null;
//		try {
//			stu = userDao.queryStudent(request.getParameter("id"));
//			if (stu!=null) {
//				modelAndView.addObject("err","이메일 중복");
//				modelAndView.addObject("page","err");
//			} else {
//				stu = new User();
//				stu.setId(request.getParameter("id"));
//				stu.setName(request.getParameter("name"));
//				stu.setPassword(request.getParameter("password"));
//				Random random = new Random();
//				stu.setProfile_img("semiDefaultImg"+Integer.toString(random.nextInt(3))+".png");
//				try {
//					userDao.insertStudent(stu);
//					HttpSession session = request.getSession();
//					session.setAttribute("id", stu.getId());
//					List<Question> ques = questionDao.queryQuestions();
//					for (Question question : ques) {
//						User writer = userDao.queryStudent(question.getW_id());
//						question.setW_name(writer.getName());
//						question.setCreated_at(question.getCreated_at().substring(0,10));
//					}
//					modelAndView.addObject("questions", ques);
//					modelAndView.addObject("page","questionList");
//				} catch (Exception e2) {
//					e2.printStackTrace();
//					modelAndView.addObject("err","회원가입 실패");
//					modelAndView.addObject("page","err");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			modelAndView.addObject("err","회원가입 실패");
//			modelAndView.addObject("page","err");
//		}
//		modelAndView.setViewName("home");
//		return modelAndView;
//	}
}