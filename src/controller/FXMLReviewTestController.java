/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Test;
import project.Project;

/**
 * FXML Controller class
 *
 * @author ConorLaptop
 */
public class FXMLReviewTestController implements Initializable {
  @FXML
  private Label lbQuestionText,
      lbChoiceA,
      lbChoiceB,
      lbChoiceC,
      lbChoiceD,
      lbQuestionNum,
      lbExplanationA,
      lbExplanationB,
      lbExplanationC,
      lbExplanationD;
  @FXML private Button btFinishTest, btNext, btPrevious;
  private Integer questionNumber = 0; // track which question user is currently on
  private Test test;

  /**
   * Switches to next question, and changes color of the answer the user selects
   *
   * @param event indicates that the Next button has been pressed
   */
  @FXML
  public void handleNext(ActionEvent event) {
    goToQuestion("next");
    getInfoToShow();
    displayUserAnswer();
    displayCorrectAnswer();
  }

  /**
   * Switches to previous question, and changes color of the answer the user selects
   *
   * @param event indicates that the Previous button has been pressed
   */
  @FXML
  public void handlePrevious(ActionEvent event) {
    goToQuestion("previous");
    getInfoToShow();
    displayUserAnswer();
    displayCorrectAnswer();
  }

  /**
   * Displays user score and end screen
   *
   * @param event indicates that the Finish button has been pressed
   * @throws IOException thrown when I/O error occurs
   */
  @FXML
  public void handleFinishReview(ActionEvent event) throws IOException, Exception {
    showEndScreen(event);
  }

  /** Gets information for the question and answers */
  public void getInfoToShow() {
    lbQuestionText.setText(test.getQuestion(questionNumber).getQuest());
    lbChoiceA.setText((String) test.getQuestion(questionNumber).getChoices().get(0));
    lbExplanationA.setText((String) test.getQuestion(questionNumber).getHints().get(0));
    lbChoiceB.setText((String) test.getQuestion(questionNumber).getChoices().get(1));
    lbExplanationB.setText((String) test.getQuestion(questionNumber).getHints().get(1));
    lbChoiceC.setText((String) test.getQuestion(questionNumber).getChoices().get(2));
    lbExplanationC.setText((String) test.getQuestion(questionNumber).getHints().get(2));
    lbChoiceD.setText((String) test.getQuestion(questionNumber).getChoices().get(3));
    lbExplanationD.setText((String) test.getQuestion(questionNumber).getHints().get(3));
    lbQuestionNum.setText("Question " + (questionNumber + 1) + "/" + test.getNumberOfQuestions());
  }

  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    test = Project.getTest();
    getInfoToShow();
    displayUserAnswer();
    displayCorrectAnswer();
  }

  /** Sets the selected answer to have orange text to indicate it was selected by the user */
  public void displayUserAnswer() {
    initializeQuestionChoices();

    if (null != test.getQuestion(questionNumber).getUserAnswer())
      switch (test.getQuestion(questionNumber).getUserAnswer()) {
        case -1:
          initializeQuestionChoices();
          break;
        case 0:
          lbChoiceA.setTextFill(Color.ORANGE);
          lbExplanationA.setTextFill(Color.ORANGE);
          break;
        case 1:
          lbChoiceB.setTextFill(Color.ORANGE);
          lbExplanationB.setTextFill(Color.ORANGE);
          break;
        case 2:
          lbChoiceC.setTextFill(Color.ORANGE);
          lbExplanationC.setTextFill(Color.ORANGE);
          break;
        case 3:
          lbChoiceD.setTextFill(Color.ORANGE);
          lbExplanationD.setTextFill(Color.ORANGE);
          break;
      }
  }

  /** Shows the correct answer by changing its color to green and making the test bold */
  public void displayCorrectAnswer() {

    if (null != test.getQuestion(questionNumber).getAnswer())
      switch (test.getQuestion(questionNumber).getAnswer()) {
        case 0:
          lbChoiceA.setTextFill(Color.GREEN);
          lbChoiceA.setStyle("-fx-font-weight: bold");
          lbExplanationA.setTextFill(Color.GREEN);
          break;
        case 1:
          lbChoiceB.setTextFill(Color.GREEN);
          lbChoiceB.setStyle("-fx-font-weight: bold");
          lbExplanationB.setTextFill(Color.GREEN);
          break;
        case 2:
          lbChoiceC.setTextFill(Color.GREEN);
          lbChoiceC.setStyle("-fx-font-weight: bold");
          lbExplanationC.setTextFill(Color.GREEN);
          break;
        case 3:
          lbChoiceD.setTextFill(Color.GREEN);
          lbChoiceD.setStyle("-fx-font-weight: bold");
          lbExplanationD.setTextFill(Color.GREEN);
          break;
      }
  }

  /** Sets all text to their default styles */
  public void initializeQuestionChoices() {
    lbChoiceA.setTextFill(Color.BLACK);
    lbChoiceB.setTextFill(Color.BLACK);
    lbChoiceC.setTextFill(Color.BLACK);
    lbChoiceD.setTextFill(Color.BLACK);
    lbExplanationA.setTextFill(Color.BLACK);
    lbExplanationB.setTextFill(Color.BLACK);
    lbExplanationC.setTextFill(Color.BLACK);
    lbExplanationD.setTextFill(Color.BLACK);
    lbChoiceA.setStyle("-fx-font-weight: regular");
    lbChoiceB.setStyle("-fx-font-weight: regular");
    lbChoiceC.setStyle("-fx-font-weight: regular");
    lbChoiceD.setStyle("-fx-font-weight: regular");
  }

  /**
   * Goes to either the next or previous question
   *
   * @param direction indicates if the program should go to the next or previous question
   */
  public void goToQuestion(String direction) {
    if (direction == "next") {
      if (questionNumber < test.getNumberOfQuestions() - 1) {
        questionNumber++;
      }
    } else if (direction == "previous") {
      if (questionNumber > 0) {
        questionNumber--;
      }
    }

    lbChoiceA.setTextFill(Color.BLACK);
    lbChoiceB.setTextFill(Color.BLACK);
    lbChoiceC.setTextFill(Color.BLACK);
    lbChoiceD.setTextFill(Color.BLACK);
    switch (test.getQuestion(questionNumber).getUserAnswer()) {
      case (0):
        displayUserAnswer();
        displayCorrectAnswer();
        break;
      case (1):
        displayUserAnswer();
        displayCorrectAnswer();
        break;
      case (2):
        displayUserAnswer();
        displayCorrectAnswer();
        break;
      case (3):
        displayUserAnswer();
        displayCorrectAnswer();
    }
  }

  /**
   * Shows the end screen
   *
   * @param event indicates that a button has been pressed
   * @throws IOException thrown when I/O error occurs
   */
  public void showEndScreen(ActionEvent event) throws IOException {
    Project.setTest(test);
    Parent endPageParent =
        FXMLLoader.load(getClass().getResource("/project/FXMLUserAccountPage.fxml"));
    Scene endPageScene = new Scene(endPageParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(endPageScene);
    window.setTitle("User Account");
    window.show();
  }
}
