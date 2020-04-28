package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Nerc> Idcombo;

    @FXML
    private TextField txtAnni;

    @FXML
    private TextField txtOre;

    @FXML
    private Button btnAnalisi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doAnalisi(ActionEvent event) {
    	
    	
    	String StringaAnni = txtAnni.getText();
    	int anni = 0;
    	String StringaOre = txtOre.getText();
    	int ore = 0;
    	Nerc n = Idcombo.getValue();
    	
    	try {
    		anni = Integer.parseInt(StringaAnni);
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Eccezione! Formato non valido, devi inserire un numero!\n");
    	}
    			

    	try {
    		ore = Integer.parseInt(StringaOre);
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Eccezione! Formato non valido, devi inserire un numero!\n");
    	}
		
    	if(n==null) {
    		txtRisultato.setText("Devi selezionare un NERC!\n");
    		return;
    	}
    
    	if(StringaAnni.equals("")) {
    		txtRisultato.setText("Devi inserire un numero massimo di anni! \n");
    		return;
    	}
    	
    	if(StringaOre.equals("")) {
    		txtRisultato.setText("Devi inserire un numero massimo di ore! \n");
    		return;
    	}
    	
		List<PowerOutages> po = new ArrayList<>(model.getSoluzione(n, anni, ore));
    	
    	if(po.size()==0) {
    		txtRisultato.setText("Non ci sono powerOutages per i valori selezionati");
    		return;
    	}
    	
    	txtRisultato.clear();
    	txtRisultato.appendText("Il totale di customers_affected e': "+model.getBestSomma()+"\n");
    	txtRisultato.appendText("Il numero totale di ore e': "+model.sommaOre(po)+"\n");
    	for(PowerOutages pi : po) {
    		txtRisultato.appendText(pi.toString()+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert Idcombo != null : "fx:id=\"Idcombo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnni != null : "fx:id=\"txtAnni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOre != null : "fx:id=\"txtOre\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model = m ;
    	Idcombo.getItems().addAll(this.model.getNercList());
    	}
}
