package id.grw.com.model;


public class Question {

    public static final String QUESTION_TABLE = "question";
    public static final String QUESTION_ID = "id";
    public static final String QUESTION_TITLE = "title";
    public static final String QUESTION_ANSWER1 = "answer1";
    public static final String QUESTION_ANSWER2 = "answer2";
    public static final String QUESTION_ANSWER3 = "answer3";
    public static final String QUESTION_ANSWER4 = "answer4";
    public static final String QUESTION_CORRECT_ANSWER = "correctAnswer";
    public static final String QUESTION_TYPE = "type";
    public static final String QUESTION_IMAGE = "image";

    private int id;
    private String title;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
    private String type;
    private String image;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
