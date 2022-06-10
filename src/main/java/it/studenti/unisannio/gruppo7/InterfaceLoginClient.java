package it.studenti.unisannio.gruppo7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class InterfaceLoginClient extends JFrame implements ActionListener {

    //Variables that we use to create a JFrame and add component into it
    private Container container = getContentPane();
    private JLabel userLabel = new JLabel("Nome utente");
    private JLabel passLabel = new JLabel ("Password");
    private JTextField userTextField = new JTextField();
    private JPasswordField passTextField = new JPasswordField();
    private JButton loginButton = new JButton("Login");
    private JButton resetButton = new JButton("Reset");
    private JCheckBox showPassword = new JCheckBox("Mostra password");
    private ImageIcon icon = new ImageIcon("src/TARIP.png");
    private JLabel iconLabel = new JLabel(icon);

    //variables to use for homepage's municipal operator
    private JButton trashbagAssignButton = new JButton("Assegna sacchetti");
    private JButton createReportButton = new JButton("Crea un report");
    private JButton openReportButton = new JButton("Apri un report");
    private JButton endAndExitButton = new JButton("Termina ed esci");
    private JPanel panelTA = new JPanel();
    private JPanel panelCR = new JPanel();
    private JPanel panelOR = new JPanel();
    private JPanel panelEE = new JPanel();

    //Creating constructor of LoginFrame() class
    public InterfaceLoginClient() {
        //Calling setLayoutManager() method inside the constructor
        setLayoutManager();
        setLocationAndSize();
        addComponentToContainerLogin();
        addActionEventLogin();
    }

    public void setLayoutManager(){
        //Setting layout manager of Container to null
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        //login set
        container.setBackground(Color.decode("#f3f3df"));//e7ebda
        showPassword.setBackground(Color.decode("#f3f3df"));

        userLabel.setBounds(610, 390, 100, 30);
        passLabel.setBounds(610, 440, 100, 30);
        userTextField.setBounds(710, 390, 150, 30);
        passTextField.setBounds(710, 440, 150, 30);
        showPassword.setBounds(710, 480, 150, 30);
        loginButton.setBounds(610, 520, 100, 30);
        resetButton.setBounds(760, 520, 100, 30);
        iconLabel.setBounds(540, 30, 400, 400);

        //homepage set
    }

    public void addComponentToContainerLogin(){
        //Add each components to the container
        container.add(userLabel);
        container.add(passLabel);
        container.add(userTextField);
        container.add(passTextField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(iconLabel);
    }

    public void addActionEventLogin(){
        //this method let us add action listeners to components
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    public void refresh(){
        getContentPane().removeAll();
        repaint();
        printAll(getGraphics());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding action performed by button

        //verify login
        if(e.getSource() == loginButton){
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passTextField.getText();
            if(userText.equalsIgnoreCase("S.geroso") && pwdText.equalsIgnoreCase("123456")){
                homepagePane(container);

            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
                userTextField.setText("");
                passTextField.setText("");
            }

        }

        //reset button
        if(e.getSource()==resetButton){
            userTextField.setText("");
            passTextField.setText("");
        }

        if(e.getSource() == showPassword){
            if(showPassword.isSelected())
                passTextField.setEchoChar((char) 0);
            else
                passTextField.setEchoChar('*');

        }

    }

    public void loginPane(){

        InterfaceLoginClient ilcFrame = new InterfaceLoginClient();
        ilcFrame.setTitle("Interfaccia operatore comunale");
        ilcFrame.setVisible(true);
        ilcFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ilcFrame.setUndecorated(true);
        ilcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ilcFrame.setResizable(false);

    }

    public void homepagePane(Container container){
        refresh();
        HomepageLoginClient hlcFrame = new HomepageLoginClient(container);
    }


}



