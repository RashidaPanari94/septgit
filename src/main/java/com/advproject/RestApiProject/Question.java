package com.advproject.RestApiProject;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	int question_id;
	String question;
	@OneToMany(targetEntity = Answer.class,cascade=CascadeType.ALL)
	@JoinColumn(name="question_id")
	List<Answer> answers;
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(int question_id, String question, List<Answer> answers) {
		super();
		this.question_id = question_id;
		this.question = question;
		this.answers = answers;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	@Override
	public String toString() {
		return "Question [question_id=" + question_id + ", question=" + question + ", answers=" + answers + "]";
	}
	

}
