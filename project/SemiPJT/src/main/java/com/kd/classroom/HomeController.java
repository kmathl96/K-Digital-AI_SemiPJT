package com.kd.classroom;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kd.classroom.bean.Question;
import com.kd.classroom.bean.User;
import com.kd.classroom.dao.QuestionDAO;
import com.kd.classroom.dao.UserDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private UserDAO userDao;
	private QuestionDAO questionDao;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	public void setQuestionDao(QuestionDAO questionDao) {
		this.questionDao = questionDao;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String first(Locale locale, Model model) {
		return "first";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Question> ques = questionDao.queryQuestions();
		for (Question question : ques) {
			User writer = userDao.queryStudent(question.getW_id());
			question.setW_name(writer.getName());
			question.setCreated_at(question.getCreated_at().substring(0,10));
		}
		modelAndView.addObject("questions", ques);
		modelAndView.addObject("page","questionList");
		modelAndView.setViewName("home");
		return modelAndView;
	}
}
