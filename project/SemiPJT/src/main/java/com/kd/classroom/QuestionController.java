package com.kd.classroom;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class QuestionController {
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
		String user_name = userDao.queryStudent(user_id).getName();
		Question que = null;
		try {
			que = new Question();
			que.setId(questionDao.findNewId());
			que.setW_id(user_id);
			que.setW_name(user_name);
			que.setTitle(request.getParameter("title"));
			que.setContent(request.getParameter("content"));
			que.setImg("");
			try {
				questionDao.insertQuestion(que);
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
				modelAndView.addObject("err","질문 생성 오류");
				modelAndView.addObject("page","err");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","질문 생성 오류");
			modelAndView.addObject("page","err");
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value="/questionDetail/{q_id}", method=RequestMethod.GET)
	public ModelAndView questionDetail(HttpServletRequest request, @PathVariable int q_id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Question que = questionDao.queryQuestion(q_id);
			questionDao.updateQuestionHits(que.getId());
			User writer = userDao.queryStudent(que.getW_id());
			que.setW_name(writer.getName());
			List<Comment> coms = new ArrayList<Comment>();
			try {
				coms = commentDao.queryComments(q_id);
				for (Comment comment : coms) {
					comment.setW_name(userDao.queryStudent(comment.getW_id()).getName());
				}
			} catch (Exception e) {
			}
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("id");
			User request_user = userDao.queryUser(user_id);
			Answer ans = answerDao.queryAnswer(q_id);
			if (ans!=null) {
				ans.setW_name(userDao.queryUser(ans.getW_id()).getName());
			}
			modelAndView.addObject("request_user", request_user);
			modelAndView.addObject("ans", ans);
			modelAndView.addObject("comments", coms);
			modelAndView.addObject("que", que);
			modelAndView.addObject("page","questionDetail");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","질문 상세 조회 오류");
			modelAndView.addObject("page","err");
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}
}