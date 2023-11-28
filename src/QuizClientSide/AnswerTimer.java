package QuizClientSide;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
public class AnswerTimer {
    Timer timer;

    AnswerTimer(QuizController quizController) {
            ActionListener listener = new ActionListener() {
                int counter = quizController.pGUI.progressBar.getMaximum();
                int r=0,g=255,b=0;
                public void actionPerformed(ActionEvent ae) {
                    counter--;
                    r = 255 + (int)(-255 * ((float)counter / quizController.pGUI.progressBar.getMaximum()));
                    g = (int) (255 * ((float)counter / quizController.pGUI.progressBar.getMaximum()));
                    quizController.pGUI.progressBar.setValue(counter);
                    quizController.pGUI.progressBar.setForeground(new Color(r,g,b));
                    if (counter<1) {
                        try {
                            if(quizController.pGUI.answerButtons[0].isEnabled()) {
                                quizController.networkProtocolClient.sendAnswer(quizController.getClient().getOutputStream(), quizController.pGUI.answerButtons[0].getText(), 0);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        timer.stop();
                    }

                    /*
                    try {
                        Thread.sleep(20*1000/quizController.pGUI.progressBar.getMaximum());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                     */
                }
            };
            timer = new Timer(0, listener);
            timer.start();
    }

    Timer getTimer() { return timer; }
}
