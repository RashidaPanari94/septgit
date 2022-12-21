package com.advproject.RestApiProject;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

	@Autowired
	SessionFactory factory;

	@GetMapping("question/{id}")
	public Question getQuestion(@PathVariable int id) {

		Session session = factory.openSession();

		Question question = session.load(Question.class, id);

		List<Answer> answers = question.getAnswers();

		System.out.println(question);

		return question;

	}

	@GetMapping("answer/{id}")
	public String getAnswer(@PathVariable int id) {

		Session session = factory.openSession();
		Answer answer = session.load(Answer.class, id);
		System.out.println(answer);
		NativeQuery<Object[]> query = session.createSQLQuery(
				"select answer_id , answer , question.question_id,question from question , answer where question.question_id=answer.question_id and answer_id="+id);
		List<Object[]> list = query.list();
		System.out.println(list.size());

		Object[] array = list.get(0);

		return Arrays.toString(array);

	}

}
