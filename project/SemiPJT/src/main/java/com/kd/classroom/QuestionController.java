package com.kd.classroom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kd.classroom.bean.Question;
import com.kd.classroom.dao.QuestionDAO;

@Controller
public class QuestionController {
	private QuestionDAO questionDao;
	
	public void setQuestionDao(QuestionDAO questionDao) {
		this.questionDao = questionDao;
	}

	@RequestMapping(value="/createQuestion", method=RequestMethod.GET)
	public String questionForm(Model model) {
//		model.addAttribute("page", "join_form");
		return "questionForm";
	}
	
	@RequestMapping(value="/createQuestion", method=RequestMethod.POST)
	public String createQuestion(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
//		ModelAndView modelAndView = new ModelAndView();
		Question que = null;
		try {
			int new_id = Question.getNum();
			que = new Question();
			que.setId(new_id);
			que.setW_id("");
			que.setTitle(request.getParameter("title"));
			que.setContent(request.getParameter("content"));
			que.setImg("");
			try {
				questionDao.insertQuestion(que);
				Question.setNum(new_id+1);
			} catch (Exception e2) {
				e2.printStackTrace();
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
}