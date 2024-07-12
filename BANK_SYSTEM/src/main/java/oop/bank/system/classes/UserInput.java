package oop.bank.system.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInput {
    private List<String> questions;
    private List<String> answers;
    private Scanner scanner;

    public UserInput(List<String> questions) {
        this.questions = new ArrayList<>(questions);
        this.answers = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void askQuestions() {
        for (String question : questions) {
            System.out.print(String.format("%s: ", question));
            String answer = scanner.nextLine();
            answers.add(answer);
        }
    }

    public List<String> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = new ArrayList<>(questions);
    }

    public List<String> getAnswers() {
        List answers = new ArrayList<String>();

        return this.questions;
    }
    public Map<String, String> getQuestionsAndAnswers() {
        Map<String, String> questionsAndAnswers = new HashMap<>();
        for (int i = 0; i < questions.size(); i++) {
            questionsAndAnswers.put(questions.get(i), answers.get(i));
        }
        return questionsAndAnswers;
    }
}
