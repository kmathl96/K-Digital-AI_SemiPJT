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
import com.kd.classroom.bean.Scrap;
import com.kd.classroom.bean.User;
import com.kd.classroom.dao.AnswerDAO;
import com.kd.classroom.dao.CommentDAO;
import com.kd.classroom.dao.QuestionDAO;
import com.kd.classroom.dao.ScrapDAO;
import com.kd.classroom.dao.UserDAO;

@Controller
public class QuestionController {
	private UserDAO userDao;
	private QuestionDAO questionDao;
	private CommentDAO commentDao;
	private AnswerDAO answerDao;
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
	public void setAnswerDao(AnswerDAO answerDao) {
		this.answerDao = answerDao;
	}
	public void setScrapDao(ScrapDAO scrapDao) {
		this.scrapDao = scrapDao;
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
	
	@RequestMapping(value="/questionDetail/{id}", method=RequestMethod.GET)
	public ModelAndView questionDetail(HttpServletRequest request, @PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		int q_id = Integer.parseInt(id);
		try {
			Question que = questionDao.queryQuestion(q_id);
			questionDao.updateQuestionHits(que.getId());
			User writer = userDao.queryStudent(que.getW_id());
			List<Comment> coms = new ArrayList<>();
			try {
				coms = commentDao.queryComments(q_id);
				for (Comment comment : coms) {
					User c_writer = userDao.queryStudent(comment.getW_id());
					comment.setW_name(c_writer.getName());
					comment.setW_img(c_writer.getProfile_img());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("id");
			User request_user = userDao.queryUser(user_id);
			Answer ans = answerDao.queryAnswer(q_id);
			if (ans!=null) {
				ans.setW_name(userDao.queryUser(ans.getW_id()).getName());
				modelAndView.addObject("ans", ans);
			}
			modelAndView.addObject("writer", writer);
			modelAndView.addObject("request_user", request_user);
			modelAndView.addObject("que", que);
			modelAndView.addObject("comments", coms);
			modelAndView.addObject("page","questionDetail");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","질문 상세 조회 오류");
			modelAndView.addObject("page","err");
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}
	@RequestMapping(value="/questionDetail/{id}/scrapQuestion", method=RequestMethod.GET)
	public String scrapQuestion(HttpServletRequest request, Model model, @PathVariable String id) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");
		User request_user = userDao.queryUser(user_id);
		int q_id = Integer.parseInt(id);
		Scrap scr = new Scrap();
		scr.setU_id(user_id);
		scr.setQ_id(q_id);
		if (scrapDao.queryScrap(scr) != null) {
			// delete
			scrapDao.deleteScrap(scr);
		} else {
			// insert
			scrapDao.insertScrap(scr);
		}
		Answer ans = answerDao.queryAnswer(q_id);
		if (ans!=null) {
			ans.setW_name(userDao.queryUser(ans.getW_id()).getName());
		}
		Question que = questionDao.queryQuestion(q_id);
		questionDao.updateQuestionHits(que.getId());
		User writer = userDao.queryStudent(que.getW_id());
		List<Comment> coms = new ArrayList<>();
		try {
			coms = commentDao.queryComments(q_id);
			for (Comment comment : coms) {
				User c_writer = userDao.queryStudent(comment.getW_id());
				comment.setW_name(c_writer.getName());
				comment.setW_img(c_writer.getProfile_img());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("writer", writer);
		model.addAttribute("request_user", request_user);
		model.addAttribute("ans", ans);
		model.addAttribute("que", que);
		model.addAttribute("comments", coms);
		model.addAttribute("page", "questionDetail");
		return "home";
	}
}