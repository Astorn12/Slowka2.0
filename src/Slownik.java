import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by osiza on 02.09.2018.
 */
public class Slownik {
    public String nazwaSlownika;
    public String jezyk1;
    public String jezyk2;
    public List<Word> slowka;

    Slownik() {
        jezyk1 = "";
        jezyk2 = "";
        slowka = new LinkedList();
    }

    public Vector<String> getAnswer(String question) {
        Vector<String> answers = new Vector<>();

        for (Word w : slowka) {
            if (w.slowo1.equals(question)) {
                answers.add(w.slowo2);
            }
        }
        if (answers.size() == 0) {
            for (Word w : slowka) {
                if (w.slowo2.equals(question)) {
                    answers.add(w.slowo1);
                }
            }
        }
        return answers;
    }

    public JTextArea getAnswerPanel(String question) {

        Vector<String> answers = this.getAnswer(question);
        JTextArea jTextArea = new JTextArea();
        if (answers.size() == 0) {
            jTextArea.setText("Brad odpowiedzi w słowniku");
        } else if (answers.size() == 1) {
            jTextArea.setText(question + "-" + answers.get(0));
        } else {
            jTextArea.setText(question + "\n");
            for (String s : answers)

            {
                jTextArea.append("-" + s + "\n");
            }

        }
        return jTextArea;
    }

    public void answerJTextService(String question, JTextPane jta) {

        Vector<String> answers = this.getAnswer(question);
        JTextPane jTextArea = jta;
        if (answers.size() == 0) {
            jTextArea.setText("Brad odpowiedzi w słowniku");
        } else if (answers.size() == 1) {
            jTextArea.setText(question + "-" + answers.get(0));
        } else {
            jTextArea.setText(question + "\n");
            String sc=jTextArea.getText();
            for (String s : answers)

            {
                sc+="-" + s + "\n";
                //jTextArea.setText("-" + s + "\n");
                //jTextArea.append("-" + s + "\n");
            }
            jTextArea.setText(sc);

        }

    }


}

