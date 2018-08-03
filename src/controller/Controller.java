package controller;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class Controller {

    private Config cfg;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label userLbl;

    @FXML
    private TextField ipTxt;

    @FXML
    private AnchorPane anchorPne;

    @FXML
    private Label ipLbl;

    @FXML
    private Button searchBtn;

    @FXML
    private Button userBtn;

    @FXML
    private TextField userTxt;

    @FXML
    private TextField ipDomainTxt;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button toolsBtn1;

    @FXML
    private Button toolsBtn2;

    @FXML
    private Button toolsBtn3;

    @FXML
    private Button toolsBtn4;

    @FXML
    private Button toolsBtn5;

    @FXML
    private Button toolsBtn6;

    @FXML
    private Button toolsBtn7;

    @FXML
    private Button toolsBtn8;

    @FXML
    private Button toolsBtn9;


    @FXML
    void userBtnAction(ActionEvent event) {
        userTxt.setText(System.getProperty("user.name"));
    }

    @FXML
    void searchBtnAction(ActionEvent event) throws UnknownHostException {
//        try {
//            Process p = Runtime.getRuntime().exec("cmd /c start cmd /k systeminfo");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String resposta= "";
        String comando = "systeminfo /FO CSV /NH";

        try {

            Scanner S = new Scanner( Runtime.getRuntime().exec(comando).getInputStream());
            //int cont = 0;
            while(S.hasNextLine()) {
                resposta += S.nextLine() + "\n";
//                String temp = S.nextLine() + "\n";
//                if(cont == 1) {
//                    resposta += temp;
//                    cont--;
//                }else {
//                    cont++;
//                }
            }

        } catch (IOException ex) {

            ex.printStackTrace();

        }
            resposta = resposta.replace("\"", "");
            resposta = resposta.replace("   ", "");
            String[] out = resposta.split(",");;
            System.out.println("Hostname: " + out[0] + " System: " + out[1] + " Build: " + out[2] + " Installation Date: " + out[9] + " Last Boot: " + out[11] + " " + out[12] + " eth" + out[50] + " System RAM: " + out[26]);

        InetAddress ip = InetAddress.getByName(ipTxt.getText());
        byte[] bytes = ip.getAddress();
        InetAddress address = InetAddress.getByAddress(bytes);
        String ipAdress = ip.getHostAddress();
        String DomainAdress = address.getCanonicalHostName();
        if(ipTxt.getText() != ipAdress) {
            ipDomainTxt.setText(DomainAdress);
        } else if(ipTxt.getText() == DomainAdress) {
            ipDomainTxt.setText(ipAdress);
        } else {
            ipDomainTxt.setText("Não Encontrado");
        }
    }

    private Tooltip createToolTip(String msg) {
        Tooltip tooltip = new Tooltip(msg);
        return tooltip;
    }

    @FXML
    void initialize() {
        toolsBtn1.setTooltip(createToolTip("Remote Support"));
        toolsBtn2.setTooltip(createToolTip("Remote Access"));
        toolsBtn3.setTooltip(createToolTip("Mesh Commander Tools"));
        toolsBtn4.setTooltip(createToolTip("Remote System-Info"));
        toolsBtn5.setTooltip(createToolTip("Remote Support"));
        toolsBtn6.setTooltip(createToolTip("Remote Support"));
        cfg = new Config();
        System.out.println(cfg.getProperty("mDbUser"));
        System.out.println(cfg.getProperty("mDbPwd"));
        System.out.println(cfg.getProperty("mDbDomain"));
        System.out.println(cfg.getProperty("mDbName"));
    }
}
