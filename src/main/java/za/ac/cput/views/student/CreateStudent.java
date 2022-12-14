package za.ac.cput.views.student;

import za.ac.cput.client.StudentHttpClient;
import za.ac.cput.entity.Student;
import za.ac.cput.factory.StudentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import za.ac.cput.views.StudentMenu;


public class CreateStudent extends JFrame implements ActionListener
{

    //Attributes
    private JPanel northPanel, centerPanel, southPanel;
    private JLabel lblHeading, lblStudentId, lblStudentId1, lblFirstName, lblMiddleName, lblLastName,
            lblStudentEmail, lblCourseID;
    private  JTextField  txtFirstName, txtMiddleName, txtLastName, txtStudentEmail, txtCourseID;
    private JButton btnCreate, btnExit;
    private Font ftHeading, ftText, ftTextBold;
    private JLabel emptySpace1, emptySpace2, emptySpace3, emptySpace4, emptySpace5, emptySpace6, emptySpace7, emptySpace8, emptySpace9, emptySpace10, emptySpace11;

    public CreateStudent()
    {
        super("Create Student Screen version: 1.0 by Themba");

        northPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();


        lblHeading = new JLabel("Create Student", SwingConstants.CENTER);
        lblStudentId = new JLabel("Student ID: ", SwingConstants.RIGHT);
        lblFirstName = new JLabel("FirstName: ", SwingConstants.RIGHT);
        lblMiddleName = new JLabel("MiddleName: ", SwingConstants.RIGHT);
        lblLastName = new JLabel("Last Name: ", SwingConstants.RIGHT);
        lblStudentEmail = new JLabel("Student Email: ", SwingConstants.RIGHT);
        lblCourseID = new JLabel("Course ID: ", SwingConstants.RIGHT);

        lblStudentId1 = new JLabel("Auto Generated...");
        txtFirstName = new JTextField();
        txtMiddleName= new JTextField();
        txtLastName= new JTextField();
        txtStudentEmail = new JTextField();
        txtCourseID = new JTextField();

        btnCreate = new JButton("Create");
        btnExit = new JButton("Exit");

        ftHeading = new Font("Segoe UI Black", Font.PLAIN, 28);
        ftText = new Font("Arial", Font.PLAIN, 12);
        ftTextBold = new Font("Arial", Font.BOLD, 12);

        emptySpace1 = new JLabel();
        emptySpace2 = new JLabel();
        emptySpace3 = new JLabel();
        emptySpace4 = new JLabel();
        emptySpace5 = new JLabel();
        emptySpace6 = new JLabel();
        emptySpace7 = new JLabel();
        emptySpace8 = new JLabel();
        emptySpace9 = new JLabel();
        emptySpace10 = new JLabel();
        emptySpace11 = new JLabel();
    }

    public void setGui()
    {
        //Add Gridlayout to panels
        northPanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new GridLayout(8,3));
        southPanel.setLayout(new GridLayout(2,2));


        //Set font
        lblHeading.setFont(ftHeading);
        //lblHeading.setForeground(Color.decode("#FFFFFF"));

        lblStudentId.setFont(ftTextBold);
        lblFirstName.setFont(ftTextBold);
        lblMiddleName.setFont(ftTextBold);
        lblLastName.setFont(ftTextBold);
        lblStudentEmail.setFont(ftTextBold);
        lblCourseID.setFont(ftTextBold);

        btnCreate.setFont(ftTextBold);
        btnExit.setFont(ftTextBold);

        lblStudentId1.setFont(ftText);
        txtFirstName.setFont(ftText);
        txtMiddleName.setFont(ftText);
        txtLastName.setFont(ftText);
        txtStudentEmail.setFont(ftText);
        txtCourseID.setFont(ftText);

        //Add components to panels
        northPanel.add(lblHeading);
        //northPanel.setBackground(Color.decode("#4863A0"));

        centerPanel.add(lblStudentId);
        centerPanel.add(lblStudentId1);
        centerPanel.add(emptySpace1);

        centerPanel.add(lblFirstName);
        centerPanel.add(txtFirstName);
        centerPanel.add(emptySpace2);

        centerPanel.add(lblMiddleName);
        centerPanel.add(txtMiddleName);
        centerPanel.add(emptySpace3);

        centerPanel.add(lblLastName);
        centerPanel.add(txtLastName);
        centerPanel.add(emptySpace4);

        centerPanel.add(lblStudentEmail);
        centerPanel.add(txtStudentEmail);
        centerPanel.add(emptySpace5);

        centerPanel.add(lblCourseID);
        centerPanel.add(txtCourseID);
        centerPanel.add(emptySpace7);

        //centerPanel.setBackground(Color.decode("#CECECE"));

        southPanel.add(emptySpace10);
        southPanel.add(emptySpace11);
        southPanel.add(btnCreate);
        southPanel.add(btnExit);
        //southPanel.setBackground(Color.decode("#CECECE"));

        //Add panels to frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

        //Add action listener to buttons | mouse listener to hyperlink
        btnCreate.addActionListener(this);
        btnExit.addActionListener(this);

        //Frame
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setSize(640, 420);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Create")) {
            String firstName = txtFirstName.getText().trim().toString();
            String middleName = txtMiddleName.getText().trim().toString();
            String lastName = txtLastName.getText().trim().toString();
            String studentEmail = txtStudentEmail.getText().trim().toString();
            String courseID= (txtCourseID.getText().trim().toString());

            if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || studentEmail.isEmpty() ||
                    courseID.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please fill in all information to create a student profile.");
            }

            else {

                Student createStudent = StudentFactory.createStudent(firstName,middleName,lastName,studentEmail,courseID);

                Student result = StudentHttpClient.create(createStudent);

                if (result != null) {
                    JOptionPane.showMessageDialog(null, "You have successfully created your student profile!.");

                    txtFirstName.setText("");
                    txtMiddleName.setText("");
                    txtLastName.setText("");
                    txtStudentEmail.setText("");
                    txtCourseID.setText("");

                    txtFirstName.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "There was an error creating a student profile.");
                }
            }
        }

        else if(e.getActionCommand().equals("Exit"))
        {
            new StudentMenu().setGui();
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new CreateStudent().setGui();
    }
}
