//IMPORTANT: YOU MUST MAKE SURE THAT THERE ARE NO EMPTY LINES IN THE TEXT FILE EXCEPT FOR THE FIRST ONE.

package es.duggl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Main implements ActionListener{

    public void setTally(String won) {

        frame.getContentPane().removeAll();
        frame.repaint();
        JLabel tally = new JLabel(won + " Won the Vote!");
        tally.setBounds(165, 80, 300, 25);
        frame.add(tally);
        tally.setVisible(true);

    }


    JFrame frame;
    JTextField textField;
    JLabel nameEnter;
    JComboBox whoVote;
    JButton submit;
    JButton tally;
    String candidateList[] = new String[] {"In and out", "Burger King", "Carl's Jr", "MCDonalds", "Wendy's"};
    int candidateListInt[] = new int[] {0, 0, 0, 0, 0};

    public Main() {

        frame = new JFrame("Vote Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,290);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(90, 70, 300, 25);

        nameEnter = new JLabel("Please Enter Your Name:");
        nameEnter.setBounds(90, 50, 300, 25);

        whoVote = new JComboBox(candidateList);
        whoVote.setBounds(90, 150, 100, 25);

        submit = new JButton("Submit Vote");
        submit.setBounds(280, 150, 110, 25);

        tally = new JButton("Tally");
        tally.setBounds(280, 180, 110, 25);
        tally.addActionListener(this);

        frame.add(tally);
        tally.setVisible(true);
        frame.add(submit);
        submit.setVisible(true);
        submit.addActionListener(this);
        frame.add(whoVote);
        frame.setVisible(true);
        frame.add(nameEnter);
        nameEnter.setVisible(true);
        frame.add(textField);
        textField.setVisible(true);

        frame.setVisible(true);
        frame.setResizable(false);

    }

    public static void main(String[] args) {new Main();}

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == submit) {

            String myName = textField.getText();
            String myVote = whoVote.getSelectedItem().toString();

            boolean found = false;
            String tempName1;
            String tempVote;

            try {

                Scanner x;
                x = new Scanner(new File("names.txt"));
                x.useDelimiter("[,\n]");

                while(x.hasNext() && !found) {

                    tempName1 = x.next();
                    tempVote = x.next();

                    for(int i = 0; i < candidateList.length; i++) {

                        if(tempVote.equals(candidateList[i])) {

                            candidateListInt[i] += 1;

                        }

                    }

                    if(tempName1.trim().equals(myName.trim())) {

                        found = true;

                    }

                }
                x.close();

                if(!found) {

                    System.out.println("Congrats! Your vote has been Added");
                    File file = new File("names.txt");
                    FileWriter fw = null;

                    try {

                        fw = new FileWriter(file, true);

                    } catch (IOException ioException) {

                        ioException.printStackTrace();
                    }

                    PrintWriter pw = new PrintWriter(fw);

                    pw.println(myName + "," + myVote);

                    pw.close();
                    textField.setText("");
                }

            } catch (FileNotFoundException d) {
                d.printStackTrace();
            }

        }

        if(e.getSource() == tally) {

            int max = candidateListInt[0];

            for(int i = 1; i < candidateList.length; i++) {

                if(candidateListInt[i] > max) {

                    max = i;

                }

            }

            setTally(candidateList[max]);

        }

    }
}
