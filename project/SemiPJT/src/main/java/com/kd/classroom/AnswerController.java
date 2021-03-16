package com.kd.classroom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kd.classroom.bean.Answer;
import com.kd.classroom.bean.Comment;
import com.kd.classroom.bean.Question;
import com.kd.classroom.bean.User;
import com.kd.classroom.dao.AnswerDAO;
import com.kd.classroom.dao.CommentDAO;
import com.kd.classroom.dao.QuestionDAO;
import com.kd.classroom.dao.UserDAO;

@Controller
public class AnswerController {
	private UserDAO userDao;
	private QuestionDAO questionDao;
	private CommentDAO commentDao;
	private AnswerDAO answerDao;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	public void setQuestionDao(QuestionDAO questionDao) {
		this.questionDao = questionDao;
	}
	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}
	public void setAnswerDao(AnswerDAO answerDao) {
		this.answerDao = answerDao;
	}

//	@RequestMapping(value="/createAnswer", method=RequestMethod.GET)
//	public ModelAndView answerForm(Model model) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("page", "answerForm");
//		modelAndView.setViewName("home");
//		return modelAndView;
//	}
	
	@RequestMapping(value="questionDetail/{q_id}/createAnswer", method=RequestMethod.POST)
	public ModelAndView createAnswer(HttpServletRequest request, @PathVariable int q_id) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String t_id = (String) session.getAttribute("id");
		User teacher = userDao.queryTeacher(t_id);
		Answer ans = null;
		try {
			ans = new Answer();
			ans.setId(answerDao.findNewId());
			ans.setW_id(t_id);
			ans.setQ_id(q_id);
			ans.setW_name(teacher.getName());
			ans.setContent(request.getParameter("content"));
			ans.setImg("");
			try {
				answerDao.insertAnswer(ans);
				Question que = questionDao.queryQuestion(q_id);
				User questionWriter = userDao.queryStudent(que.getW_id());
				que.setW_name(questionWriter.getName());
				List<Comment> coms = commentDao.queryComments(q_id);
				for (Comment comment : coms) {
					User writer = userDao.queryStudent(comment.getW_id());
					comment.setW_name(writer.getName());
				}
				modelAndView.addObject("ans", ans);
				modelAndView.addObject("request_user", teacher);
				modelAndView.addObject("que", que);
				modelAndView.addObject("comments", coms);
				modelAndView.addObject("page","questionDetail");
			} catch (Exception e2) {
				e2.printStackTrace();
				modelAndView.addObject("err","답변 생성 오류");
				modelAndView.addObject("page","err");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","답변 생성 오류");
			modelAndView.addObject("page","err");
		}
		// 결과와 페이지를 한번에 넣어서 반환
		modelAndView.setViewName("home");
		return modelAndView;
	}
}