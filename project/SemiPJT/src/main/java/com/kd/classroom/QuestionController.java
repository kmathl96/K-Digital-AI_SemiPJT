package com.kd.classroom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kd.classroom.bean.Comment;
import com.kd.classroom.bean.Question;
import com.kd.classroom.bean.Student;
import com.kd.classroom.dao.CommentDAO;
import com.kd.classroom.dao.QuestionDAO;
import com.kd.classroom.dao.StudentDAO;

@Controller
public class QuestionController {
	private StudentDAO studentDao;
	private QuestionDAO questionDao;
	private CommentDAO commentDao;
	
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	public void setQuestionDao(QuestionDAO questionDao) {
		this.questionDao = questionDao;
	}
	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}

	@RequestMapping(value="/createQuestion", method=RequestMethod.GET)
	public ModelAndView questionForm(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page", "questionForm");
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value="/createQuestion", method=RequestMethod.POST)
	public ModelAndView createQuestion(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");
		String user_name = studentDao.queryUser(user_id).getName();
		Question que = null;
		try {
			int new_id = Question.getNum();
			que = new Question();
			que.setId(new_id);
			que.setW_id(user_id);
			que.setW_name(user_name);
			que.setTitle(request.getParameter("title"));
			que.setContent(request.getParameter("content"));
			que.setImg("");
			try {
				questionDao.insertQuestion(que);
				Question.setNum(new_id+1);
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
				modelAndView.addObject("err","질문 생성 오류");
				modelAndView.addObject("page","err");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","질문 생성 오류");
			modelAndView.addObject("page","err");
		}
		// 결과와 페이지를 한번에 넣어서 반환
		modelAndView.setViewName("home");
		return modelAndView;
//		return "home";
	}
	
	@RequestMapping(value="/questionDetail/{q_id}", method=RequestMethod.GET)
	public ModelAndView questionDetail(HttpServletRequest request, @PathVariable int q_id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Question que = questionDao.queryQuestion(q_id);
			questionDao.updateQuestionHits(que.getId());
			Student writer = studentDao.queryUser(que.getW_id());
			que.setW_name(writer.getName());
			List<Comment> coms = commentDao.queryComments(q_id);
			for (Comment comment : coms) {
				comment.setW_name(studentDao.queryUser(comment.getW_id()).getName());
			}
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("id");
			Student request_user = studentDao.queryUser(user_id);
			modelAndView.addObject("request_user", request_user);
			modelAndView.addObject("comments", coms);
			modelAndView.addObject("que", que);
			modelAndView.addObject("page","questionDetail");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","질문 상세 조회 오류");
			modelAndView.addObject("page","err");
		}
//		model.addAttribute("page", "join_form");
		modelAndView.setViewName("home");
		return modelAndView;
	}
}