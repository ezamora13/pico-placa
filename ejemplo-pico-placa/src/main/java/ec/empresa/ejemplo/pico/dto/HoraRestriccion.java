package ec.empresa.ejemplo.pico.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class HoraRestriccion {

	@Getter
	@Setter
	private Date fechaMananaFin;
	@Getter
	@Setter
	private Date fechaMananaInicio;
	@Getter
	@Setter
	private Date fechaTardeInicio;
	@Getter
	@Setter
	private Date fechaTardeFin;

}
