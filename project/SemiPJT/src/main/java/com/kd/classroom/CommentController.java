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
public class CommentController {
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

	//	@RequestMapping(value="/createQuestion", method=RequestMethod.GET)
//	public String questionForm(Model model) {
////		model.addAttribute("page", "join_form");
//		return "questionForm";
//	}
//	
	@RequestMapping(value="questionDetail/{q_id}/createComment", method=RequestMethod.POST)
	public ModelAndView createComment(HttpServletRequest request, @PathVariable int q_id) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");
		User request_user = userDao.queryUser(user_id);
		Question que = questionDao.queryQuestion(q_id);
		User writer = userDao.queryStudent(que.getW_id());
		Comment com = null;
		try {
			com = new Comment();
			com.setId(commentDao.findNewId());
			com.setW_id(user_id);
			com.setW_name(request_user.getName());
			com.setQ_id(q_id);
			com.setContent(request.getParameter("content"));
			try {
				commentDao.insertComment(com);
				List<Comment> coms = commentDao.queryComments(q_id);
				for (Comment comment : coms) {
					User c_writer = userDao.queryStudent(comment.getW_id());
					comment.setW_name(c_writer.getName());
					comment.setW_img(c_writer.getProfile_img());
				}
				Answer ans = answerDao.queryAnswer(q_id);
				if (ans!=null) {
					ans.setW_name(userDao.queryTeacher(ans.getW_id()).getName());
				}
				modelAndView.addObject("writer", writer);
				modelAndView.addObject("request_user", request_user);
				modelAndView.addObject("ans", ans);
				modelAndView.addObject("que", que);
				modelAndView.addObject("comments", coms);
				modelAndView.addObject("page","questionDetail");
			} catch (Exception e2) {
				e2.printStackTrace();
				modelAndView.addObject("err","댓글 생성 오류");
				modelAndView.addObject("page","err");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("err","댓글 생성 오류");
			modelAndView.addObject("page","err");
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}
//	
//	@RequestMapping(value="/questionDetail/{q_id}", method=RequestMethod.GET)
//	public ModelAndView questionDetail(HttpServletRequest request, @PathVariable int q_id) {
//		ModelAndView modelAndView = new ModelAndView();
//		try {
//			Question que = questionDao.queryQuestion(q_id);
//			int hits = que.getHits();
//			que.setHits(hits+1);
//			Student writer = studentDao.queryUser(que.getW_id());
//			que.setW_name(writer.getName());
//			HttpSession session = request.getSession();
//			String user_id = (String) session.getAttribute("id");
//			boolean isWriter = (user_id.equals(que.getW_id()));
//			modelAndView.addObject("isWriter", isWriter);
//			modelAndView.addObject("que", que);
//			modelAndView.addObject("page","questionDetail");
//		} catch (Exception e) {
//			e.printStackTrace();
//			modelAndView.addObject("err","질문 상세 조회 오류");
//			modelAndView.addObject("page","err");
//		}
////		model.addAttribute("page", "join_form");
//		modelAndView.setViewName("home");
//		return modelAndView;
//	}
}