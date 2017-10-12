package ec.empresa.ejemplo.pico.dto;

import lombok.Getter;
import lombok.Setter;

public class DatosVehiculoEntrada {

	@Getter
	@Setter
	private String placaVehiculo;
	@Getter
	@Setter
	private String fechaCirculacion;
	@Getter
	@Setter
	private String horaCirculacion;

}
