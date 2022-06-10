package it.studenti.unisannio.gruppo7;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.MyAnnotationCallback;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.jdesktop.swingx.JXDatePicker;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

public class HomepageLoginClient extends JFrame implements ActionListener {

    private final JButton openReportButton = new JButton("Apri un report");
    private final JButton endAndExitButton = new JButton("Termina ed esci");
    private final JButton logOutButton = new JButton("Log out");

    private final JPanel rightPanelAssign = new JPanel();
    private final JPanel rightPanelCreateReport = new JPanel();
    private JPanel rightPanelOpenReport = new JPanel();
    private JPanel rightPanelExit = new JPanel();

    private JLabel title = new JLabel("Inserisci parametri per assegnare sacchetti");
    private JLabel nameLabel = new JLabel("Inserisci nome:");
    private JLabel surnameLabel = new JLabel("Inserisci cognome:");
    private JLabel fisCodLabel = new JLabel("Inserisci codice fiscale:");
    private JLabel trashType = new JLabel("Seleziona tipo sacchetto:");
    private JTextField nameTextField = new JTextField();
    private JTextField surnameTextField = new JTextField();
    private JTextField fisCodTextField = new JTextField();

    private JLabel title2 = new JLabel("Inserisci i parametri del report");
    private JLabel organic = new JLabel("Organico/Umido");
    private JLabel plastic = new JLabel("Plastica");
    private JLabel glass = new JLabel("Vetro");
    private JLabel paper = new JLabel("Carta");
    private JLabel indiff = new JLabel("Indifferenziata");
    private JLabel allum = new JLabel("Alluminio");
    private JLabel steel = new JLabel("Metallo");
    private JTextField organicNumber = new JTextField();
    private JTextField plasticNumber = new JTextField();
    private JTextField glassNumber = new JTextField();
    private JTextField paperNumber = new JTextField();
    private JTextField indiffNumber = new JTextField();
    private JTextField allumNumber = new JTextField();
    private JTextField steelNumber = new JTextField();

    private JButton confirmTrashBugButton = new JButton("Conferma");
    private JButton resetTrashBugButton = new JButton("Reset");

    private JFileChooser jfcReport = new JFileChooser();
    private JTabbedPane jtb = new JTabbedPane();
    private JPanel panelPDF = new JPanel();

    private ButtonGroup bg = new ButtonGroup();
    private ButtonGroup bg2 = new ButtonGroup();
    private JLabel type = new JLabel("Seleziona tipologia di report");
    private JCheckBox jcbQuality = new JCheckBox("Qualità");
    private JCheckBox jcbQuantity = new JCheckBox("Peso");
    private JLabel type2 = new JLabel("Seleziona la tipologia di spazzatura");
    private JCheckBox jcbIndiff = new JCheckBox("Indifferenziato");
    private JCheckBox jcbPlastc = new JCheckBox("Plastica e alluminio");
    private JCheckBox jcbOrganic = new JCheckBox("Organico");
    private JCheckBox jcbPaper = new JCheckBox("Carta e cartone");
    private JCheckBox jcbGlass = new JCheckBox("Vetro");
    private JCheckBox jcbAllum = new JCheckBox("Alluminio");
    private JCheckBox jcbSteel = new JCheckBox("Metallo");
    private JLabel type3 = new JLabel("Seleziona periodo temporale");
    private JButton confirmReport = new JButton("Conferma report");
    private JXDatePicker picker = new JXDatePicker();
    private JXDatePicker picker2 = new JXDatePicker();
    private JLabel date1 = new JLabel("Da");
    private JLabel date2 = new JLabel("A");

    private Font myFont = new Font("Serif",Font.BOLD,20);

    public HomepageLoginClient(Container container) {

        addComponentToContainer(container);
        setLocationAndSize();
        addActionEvent();
    }

    public void setLocationAndSize() {
        setAndAddLocationTrashbag();
        setAndAddLocationCreateReport();
        setAndAddLocationOpenReport();
        setExitAndLogOut();
        jtb.setBounds(50,10,1450,800);
        String space = "                                        ";
        jtb.setForeground(Color.decode("#046307"));
        jtb.addTab(space+"Asseggna sacchetti"+space, rightPanelAssign);
        jtb.addTab(space+"Apri report"+space, rightPanelOpenReport);
        jtb.addTab(space+"Crea report"+space, rightPanelCreateReport);
        jtb.addTab(space+"Termina ed esci"+space, rightPanelExit);
    }

    private void setExitAndLogOut() {
        rightPanelExit.setLayout(null);
        rightPanelExit.setBackground(Color.decode("#6B8E23"));
        rightPanelExit.add(endAndExitButton);
        rightPanelExit.add(logOutButton);
        endAndExitButton.setBounds(600,100,300,50);
        logOutButton.setBounds(600,200,300,50);
    }

    public void setAndAddLocationTrashbag() {
        rightPanelAssign.setLayout(null);
        rightPanelAssign.add(nameLabel);
        rightPanelAssign.add(surnameLabel);
        rightPanelAssign.add(nameTextField);
        rightPanelAssign.add(surnameTextField);
        rightPanelAssign.add(fisCodLabel);
        rightPanelAssign.add(fisCodTextField);
        rightPanelAssign.add(trashType);
        rightPanelAssign.add(organic);
        rightPanelAssign.add(plastic);
        rightPanelAssign.add(glass);
        rightPanelAssign.add(paper);
        rightPanelAssign.add(indiff);
        rightPanelAssign.add(organicNumber);
        rightPanelAssign.add(plasticNumber);
        rightPanelAssign.add(glassNumber);
        rightPanelAssign.add(paperNumber);
        rightPanelAssign.add(indiffNumber);
        rightPanelAssign.add(confirmTrashBugButton);
        rightPanelAssign.add(resetTrashBugButton);
        rightPanelAssign.add(title);
        rightPanelAssign.add(allum);
        rightPanelAssign.add(allumNumber);
        rightPanelAssign.add(steel);
        rightPanelAssign.add(steelNumber);

        //color panel
        rightPanelAssign.setBackground(Color.decode("#9ACD32"));
        organic.setBackground(Color.decode("#9ACD32"));
        plastic.setBackground(Color.decode("#9ACD32"));
        glass.setBackground((Color.decode("#9ACD32")));
        paper.setBackground(Color.decode("#9ACD32"));
        indiff.setBackground(Color.decode("#9ACD32"));

        //set bounds
        title.setFont(myFont);
        title.setForeground(Color.decode("#F5FFFA"));
        title.setBounds(600,10,500,100);
        nameLabel.setBounds(500, 100, 100, 30);
        surnameLabel.setBounds(480, 150, 150, 30);
        fisCodLabel.setBounds(455, 200, 150, 30);
        nameTextField.setBounds(610, 100, 250, 30);
        surnameTextField.setBounds(610, 150, 250, 30);
        fisCodTextField.setBounds(610, 200, 250, 30);
        trashType.setBounds(610,240,200,30);
        organic.setBounds(610, 270, 200, 30);
        plastic.setBounds(610, 310, 200, 30);
        glass.setBounds(610, 350, 200, 30);
        paper.setBounds(610, 390, 200, 30);
        indiff.setBounds(610, 430, 200, 30);
        steel.setBounds(610,470,200,30);
        allum.setBounds(610,510,200,30);
        organicNumber.setBounds(810, 275, 20, 20);
        plasticNumber.setBounds(810, 315, 20, 20);
        glassNumber.setBounds(810, 355, 20, 20);
        paperNumber.setBounds(810, 395, 20, 20);
        indiffNumber.setBounds(810, 435, 20, 20);
        steelNumber.setBounds(810,475,20,20);
        allumNumber.setBounds(810,515,20,20);
        confirmTrashBugButton.setBounds(750, 650, 100, 30);
        resetTrashBugButton.setBounds(600, 650, 100, 30);

        organicNumber.setText("0");
        plasticNumber.setText("0");
        indiffNumber.setText("0");
        glassNumber.setText("0");
        paperNumber.setText("0");
        steelNumber.setText("0");
        allumNumber.setText("0");
    }

    public void setAndAddLocationCreateReport() {
        rightPanelCreateReport.setLayout(null);

        title2.setFont(myFont);
        title2.setForeground(Color.decode("#F0BE48"));
        title2.setBounds(600,10,500,100);
        type.setBounds(500,100,600,30);
        type.setForeground(Color.decode("#F0BE48"));
        jcbQuality.setBounds(500,130,200,20);
        jcbQuantity.setBounds(500,160,200,20);
        type2.setBounds(500,200,600,20);
        type2.setForeground(Color.decode("#F0BE48"));
        jcbGlass.setBounds(500,230,200,20);
        jcbIndiff.setBounds(500,260,200,20);
        jcbOrganic.setBounds(500,290,200,20);
        jcbPaper.setBounds(500,320,200,20);
        jcbPlastc.setBounds(500,350,200,20);
        jcbAllum.setBounds(500,380,200,20);
        jcbSteel.setBounds(500,410,200,20);
        type3.setBounds(500,450,600,20);
        type3.setForeground(Color.decode("#F0BE48"));
        confirmReport.setBounds(750,600,200,50);
        date1.setBounds(500,480,30,30);
        date1.setForeground(Color.decode("#F0BE48"));
        date2.setBounds(500,530,30,30);
        date2.setForeground(Color.decode("#F0BE48"));
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        picker.setBounds(540,480,100,30);
        picker2.setDate(Calendar.getInstance().getTime());
        picker2.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        picker2.setBounds(540,530,100,30);

        jcbQuality.setBackground(Color.decode("#2E8B57"));
        jcbQuantity.setBackground(Color.decode("#2E8B57"));
        jcbPlastc.setBackground(Color.decode("#2E8B57"));
        jcbPaper.setBackground(Color.decode("#2E8B57"));
        jcbOrganic.setBackground(Color.decode("#2E8B57"));
        jcbIndiff.setBackground(Color.decode("#2E8B57"));
        jcbGlass.setBackground(Color.decode("#2E8B57"));
        jcbAllum.setBackground(Color.decode("#2E8B57"));
        jcbSteel.setBackground(Color.decode("#2E8B57"));

        bg.add(jcbGlass);
        bg.add(jcbIndiff);
        bg.add(jcbOrganic);
        bg.add(jcbPaper);
        bg.add(jcbPlastc);
        bg.add(jcbAllum);
        bg.add(jcbSteel);
        bg2.add(jcbQuality);
        bg2.add(jcbQuantity);
        rightPanelCreateReport.add(type);
        rightPanelCreateReport.add(jcbQuality);
        rightPanelCreateReport.add(jcbQuantity);
        rightPanelCreateReport.add(type2);
        rightPanelCreateReport.add(jcbGlass);
        rightPanelCreateReport.add(jcbIndiff);
        rightPanelCreateReport.add(jcbPaper);
        rightPanelCreateReport.add(jcbPlastc);
        rightPanelCreateReport.add(jcbOrganic);
        rightPanelCreateReport.add(jcbAllum);
        rightPanelCreateReport.add(jcbSteel);
        rightPanelCreateReport.add(type3);
        rightPanelCreateReport.add(confirmReport);
        rightPanelCreateReport.add(title2);
        rightPanelCreateReport.add(picker);
        rightPanelCreateReport.add(picker2);
        rightPanelCreateReport.add(date1);
        rightPanelCreateReport.add(date2);

        rightPanelCreateReport.setBackground(Color.decode("#2E8B57"));
        rightPanelCreateReport.setBounds(700, 50, 800, 700);
    }

    public void setAndAddLocationOpenReport(){
        panelPDF.setBounds(70,80,1300,700);
        panelPDF.setBackground(Color.decode("#3CB371"));
        rightPanelOpenReport.setLayout(null);
        rightPanelOpenReport.add(panelPDF);
        rightPanelOpenReport.add(openReportButton);
        rightPanelOpenReport.setBackground(Color.decode("#3CB371"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF FILES","pdf","pdf");
        jfcReport.setFileFilter(filter);
        openReportButton.setBounds(600,50,150,30);
        jfcReport.setCurrentDirectory(new File(System.getProperty("user.home")));

    }

    public void addComponentToContainer(Container container) {
        //component to add for homepage
        container.add(jtb);
    }

    public void addActionEvent() {
        openReportButton.addActionListener(this);
        endAndExitButton.addActionListener(this);
        logOutButton.addActionListener(this);
        resetTrashBugButton.addActionListener(this);
        confirmTrashBugButton.addActionListener(this);
        jfcReport.addActionListener(this);
        confirmReport.addActionListener(this);

    }

    //method that let us perform action event
    @Override
    public void actionPerformed(ActionEvent e) {
        String x=null; //quality or quantity
        String y=null; //plastic,indiff,organic,paper,glass

        if (e.getSource() == openReportButton) {

            setAndAddLocationOpenReport();
            int n = jfcReport.showOpenDialog(this);
            if(n==JFileChooser.APPROVE_OPTION){
                File f = jfcReport.getSelectedFile();
                String path = f.getPath();
                Desktop desk = Desktop.getDesktop();
                SwingController controller = new SwingController();
                SwingViewBuilder builder = new SwingViewBuilder(controller);
                JPanel panelViewer = builder.buildViewerPanel();
                ComponentKeyBinding.install(controller,panelViewer);
               // controller.getDocumentViewController().setAnnotationCallback(
               //         new org.icepdf.ri.common.MyAnnotationCallback(
               //                 controller.getDocumentViewController()));
                panelPDF.add(panelViewer);
                panelPDF.setVisible(true);
                controller.openDocument(path);
                if(f.exists()) {
                    try {
                        desk.open(f);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
            }
        }
        if (e.getSource() == endAndExitButton) {
            System.exit(0);
        }
        if (e.getSource() == logOutButton){
            setLayout(null);
            jtb.setVisible(false);
            InterfaceLoginClient ilc = new InterfaceLoginClient();
            ilc.loginPane();
        }

        if (e.getSource() == resetTrashBugButton) {
            nameTextField.setText("");
            surnameTextField.setText("");
            fisCodTextField.setText("");
            organicNumber.setText("0");
            plasticNumber.setText("0");
            indiffNumber.setText("0");
            glassNumber.setText("0");
            paperNumber.setText("0");
            steelNumber.setText("0");
            allumNumber.setText("0");
        }

        if (e.getSource() == confirmTrashBugButton) {

            String nameText = null;
            try{
                nameText = nameTextField.getText();
                if(nameText.length()==0) throw new InputNotValidException("Inserisci il nome del cittadino!");
            }catch(InputNotValidException erN){
                JOptionPane.showMessageDialog(this,erN.getMessage());
                return;
            }

            String surnameText = null;
            try{
                surnameText = surnameTextField.getText();
                if(surnameText.length() == 0) throw new InputNotValidException("Inserisci il cognome del cittadino!");
            }catch(InputNotValidException erS){
                JOptionPane.showMessageDialog(this,erS.getMessage());
                return;
            }
            String fisCodeText = null;
            try{
                fisCodeText = fisCodTextField.getText();
                if(fisCodeText.length() != 16) throw new InputNotValidException("Il codice fiscale deve contenere 16 caratteri!");
            }catch(InputNotValidException erF){
                JOptionPane.showMessageDialog(this,erF.getMessage());
                return;
            }

            String typeOrganic = organic.getText();
            String typePlastic = plastic.getText();
            String typePaper = paper.getText();
            String typeIndiff = indiff.getText();
            String typeGlass = glass.getText();
            String typeSteel = steel.getText();
            String typeAllum = allum.getText();

            String org = organicNumber.getText();
            int oNumb = Integer.parseInt(org);

            String pla = plasticNumber.getText();
            int pNumb = Integer.parseInt(pla);

            String pap = paperNumber.getText();
            int paNumb = Integer.parseInt(pap);

            String ind = indiffNumber.getText();
            int iNumb = Integer.parseInt(ind);

            String gla = glassNumber.getText();
            int gNumb = Integer.parseInt(gla);

            String ste = steelNumber.getText();
            int sNumb = Integer.parseInt(ste);

            String all = allumNumber.getText();
            int aNumb = Integer.parseInt(all);

            String output = "Output:" + nameText + "\n" + surnameText + "\n" + fisCodeText + "\n" +
                    typeOrganic + ": " + oNumb + "\n" +
                    typePaper + ": " + paNumb + "\n" + typeGlass + ": " + gNumb + "\n" + typePlastic + ": " + pNumb + "\n"
                    + typeIndiff + ": " + iNumb + "\n" + typeAllum +": "+aNumb + "\n"+ typeSteel +": "+sNumb;

            JOptionPane.showMessageDialog(this, output);

            output = nameText + "\n" + surnameText + "\n" + fisCodeText + "\n" +
                    typeOrganic + ": " + oNumb + "\n" +
                    typePaper + ": " + paNumb + "\n" + typeGlass + ": " + gNumb + "\n" + typePlastic + ": " + pNumb + "\n"
                    + typeIndiff + ": " + iNumb + "\n" + typeAllum +": "+aNumb + "\n"+ typeSteel +": "+sNumb;
            System.out.println("L'output e'\n"+output);

            HashMap<String,Integer> sac = new HashMap<>();
            Set<String> keys = sac.keySet();
            for(String key: keys){
                for(int i = 0; i < sac.get(key); i++){
                    //crea sacchetta
                    //FisCod e tipo
                }
            }


        }

        if(jcbQuantity.isSelected()){
            x = "Peso";
        }

        if(jcbQuality.isSelected()){
            x = "Qualita";
        }

        if(jcbOrganic.isSelected()){
            y = "Organico";
        }

        if(jcbGlass.isSelected()){
            y = "Vetro";
        }

        if(jcbIndiff.isSelected()){
            y = "Indifferenziata";
        }

        if(jcbPlastc.isSelected()){
            y = "Plastica";
        }

        if(jcbPaper.isSelected()) {
            y = "Carta";
        }

        if(jcbSteel.isSelected()){
            y = "Carta";
        }

        if(jcbAllum.isSelected()){
            y = "Alluminio";
        }

        if(e.getSource() == confirmReport) {

            try {
                PDFTypeMaker pdft = new PDFTypeMaker(picker.getDate(),picker2.getDate(),y,x);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            String output2 = "Output:" + x + "\n" + y +"\n"+ picker.getDate() +"\n" +picker2.getDate();
            JOptionPane.showMessageDialog(this, output2);
            System.out.println("L'output e'"+output2);
        }
    }
}

