package logic;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Test implements Serializable {
	private String ID;
	private String Profession;
	private String Course;
	private int Duration;
	private int number;
	private String Author;
	private String textForStudent;
	private String TextForTeacher;
	private ArrayList<Question> QArray;
	private ArrayList<String> Points;
	
	
	
	public Test(String iD, String profession, String course, int duration, int number, String author,
			String textForStudent, String textForTeacher, ArrayList<Question> qArray, ArrayList<String> points) {
		super();
		ID = iD;
		Profession = profession;
		Course = course;
		Duration = duration;
		this.number = number;
		Author = author;
		this.textForStudent = textForStudent;
		TextForTeacher = textForTeacher;
		QArray = qArray;
		Points = points;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getProfession() {
		return Profession;
	}
	public void setProfession(String profession) {
		Profession = profession;
	}
	public String getCourse() {
		return Course;
	}
	public void setCourse(String course) {
		Course = course;
	}
	public int getDuration() {
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
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
	public String getTextForStudent() {
		return textForStudent;
	}
	public void setTextForStudent(String textForStudent) {
		this.textForStudent = textForStudent;
	}
	public String getTextForTeacher() {
		return TextForTeacher;
	}
	public void setTextForTeacher(String textForTeacher) {
		TextForTeacher = textForTeacher;
	}
	public ArrayList<Question> getQArray() {
		return QArray;
	}
	public void setQArray(ArrayList<Question> qArray) {
		QArray = qArray;
	}
	public ArrayList<String> getPoints() {
		return Points;
	}
	public void setPoints(ArrayList<String> points) {
		Points = points;
	}
	

	
}
