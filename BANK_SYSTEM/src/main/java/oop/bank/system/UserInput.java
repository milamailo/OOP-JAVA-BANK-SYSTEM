package oop.bank.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInput {
    private List<String> questions;
    private Map<String, String> answers;
    private Scanner scanner;

    public UserInput(List<String> questions) {
        this.questions = new ArrayList<>(questions);
        this.answers = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void askQuestions() {
        for (String question : questions) {
            System.out.print(String.format("%s: ", question));
            String answer = scanner.nextLine();
            answers.put(question, answer);
        }
    }

    public List<String> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = new ArrayList<>(questions);
    }

    public Map<String, String> getAnswers() {
        return this.answers;
    }
}
