package com.example.assignment.Student;

import java.util.List;

public class MCQ {
    private int id;
    private String question;
    private List<String> options;
    private String correctAnswer;

    public MCQ(int id, String question, List<String> options, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
