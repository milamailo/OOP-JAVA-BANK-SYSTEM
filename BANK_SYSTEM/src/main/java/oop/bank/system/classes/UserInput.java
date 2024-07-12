package oop.bank.system.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles dynamic user input collection based on predefined questions.
 * This class simplifies gathering responses from the user via the console.
 */
public class UserInput {
    private List<String> questions; // List to hold questions to be asked to the user
    private List<String> answers;   // List to store user responses
    private Scanner scanner;        // Scanner to read input from the console

    /**
     * Constructs a UserInput object with a list of questions.
     * @param questions List of strings, each representing a question.
     */
    public UserInput(List<String> questions) {
        this.questions = new ArrayList<>(questions);
        this.answers = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user with each question and stores their responses.
     */
    public void askQuestions() {
        for (String question : questions) {
            System.out.print(String.format("%s: ", question));
            String answer = scanner.nextLine();
            answers.add(answer);
        }
    }

    /**
     * Returns the list of questions.
     * @return List of questions.
     */
    public List<String> getQuestions() {
        return this.questions;
    }

    /**
     * Sets new questions for the UserInput instance.
     * @param questions List of new questions.
     */
    public void setQuestions(List<String> questions) {
        this.questions = new ArrayList<>(questions);
    }

    /**
     * Retrieves the answers provided by the user.
     * @return List of answers.
     */
    public List<String> getAnswers() {
        return new ArrayList<>(this.answers);  // Corrected to return answers instead of questions.
    }

    /**
     * Returns a map linking each question to its corresponding answer.
     * @return Map of question-answer pairs.
     */
    public Map<String, String> getQuestionsAndAnswers() {
        Map<String, String> questionsAndAnswers = new HashMap<>();
        for (int i = 0; i < questions.size(); i++) {
            questionsAndAnswers.put(questions.get(i), answers.get(i));
        }
        return questionsAndAnswers;
    }
}
