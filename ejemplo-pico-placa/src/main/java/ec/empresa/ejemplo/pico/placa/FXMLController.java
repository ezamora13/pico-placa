package ec.empresa.ejemplo.pico.placa;

import ec.empresa.ejemplo.pico.control.Logica;
import ec.empresa.ejemplo.pico.dto.DatosVehiculoEntrada;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {


    @FXML
    private TextField txtPlaca;
    @FXML
    private DatePicker txtFecha;
    @FXML
    private TextField txtHora;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            if (txtFecha.getValue() == null && txtHora.getText().isEmpty() && txtPlaca.getText().isEmpty()) {

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Información Dialogo");
                alert.setHeaderText(null);
                alert.setContentText("Complete la información !");
                alert.showAndWait();
            } else {
                DatosVehiculoEntrada entrada = new DatosVehiculoEntrada();
                Logica logica = new Logica();
                String salida="";
                entrada.setFechaCirculacion(txtFecha.getValue().toString());
                entrada.setHoraCirculacion(txtHora.getText());
                entrada.setPlacaVehiculo(txtPlaca.getText());
                salida = logica.obtenerCirculacion(entrada);
                  Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información Consulta");
                alert.setHeaderText(null);
                alert.setContentText("Usted !"+salida);
                alert.showAndWait();
                
            }

        } catch (Exception e) {
            System.out.println("Problemas..");
            
              Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Información Dialogo");
                alert.setHeaderText(null);
                alert.setContentText("Verifique la información!");
                alert.showAndWait();
        }

     }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
