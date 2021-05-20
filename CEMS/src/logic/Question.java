package logic;

import java.util.ArrayList;

public class Question {
	private String profession;
	private ArrayList<String> Course;
	private String instructions;
	private int ID;
	private int number;
	private String Author;
	private String[] Answers = new String[4];
	private char CorrectAnswer;
	
	public Question(String profession, ArrayList<String> course, String instructions, int iD, int number, String author,
			String[] answers, char correctAnswer) {
		super();
		this.profession = profession;
		Course = course;
		this.instructions = instructions;
		ID = iD;
		this.number = number;
		Author = author;
		Answers = answers;
		CorrectAnswer = correctAnswer;
	}
	
	
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	public ArrayList<String> getCourse() {
		return Course;
	}
	public void setCourse(ArrayList<String> course) {
		Course = course;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String[] getAnswers() {
		return Answers;
	}
	public void setAnswers(String[] answers) {
		Answers = answers;
	}
	public char getCorrectAnswer() {
		return CorrectAnswer;
	}
	public void setCorrectAnswer(char correctAnswer) {
		CorrectAnswer = correctAnswer;
	}

}
