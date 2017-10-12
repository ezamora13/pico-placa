package ec.empresa.ejemplo.pico.control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ec.empresa.ejemplo.pico.dto.DatosVehiculoEntrada;
import ec.empresa.ejemplo.pico.dto.HoraRestriccion;
import ec.empresa.ejemplo.pico.dto.PlacaPermitida;

public class Logica {

    private HoraRestriccion horaRestriccion = new HoraRestriccion();

    public String obtenerCirculacion(DatosVehiculoEntrada entrada) throws Exception {
        String resultado = "";
        Date fechaConsulta = new Date();
        Calendar fechaCalendario = Calendar.getInstance();
        iniciarHoras();

        fechaConsulta = transformarFechaHora((entrada.getFechaCirculacion() + " " + entrada.getHoraCirculacion()).replace("-", "/"));
        fechaCalendario.setTime(fechaConsulta);
        if (fechaCalendario.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || fechaCalendario.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return "Libre Circulacion";
            
        } else {
            int numeroPlaca = Integer
                    .parseInt(entrada.getPlacaVehiculo().substring(entrada.getPlacaVehiculo().length() - 1));

            System.out.println(obtenerDia(fechaCalendario.get(Calendar.DAY_OF_WEEK), fechaConsulta, numeroPlaca).getMensaje());
          return  obtenerDia(fechaCalendario.get(Calendar.DAY_OF_WEEK), fechaConsulta, numeroPlaca).getMensaje();

        }

        
    }

    private String validarHorario(Date fechaComparar) {
        Integer minMananaInicial = horaRestriccion.getFechaMananaInicio().getHours() * 60
                + horaRestriccion.getFechaMananaInicio().getMinutes();
        Integer minMananaFinal = horaRestriccion.getFechaMananaFin().getHours() * 60
                + horaRestriccion.getFechaMananaFin().getMinutes();

        Integer minTardeInicial = horaRestriccion.getFechaTardeInicio().getHours() * 60
                + horaRestriccion.getFechaTardeInicio().getMinutes();
        Integer minTardeFinal = horaRestriccion.getFechaTardeFin().getHours() * 60
                + horaRestriccion.getFechaTardeFin().getMinutes();

        Integer minComparar = fechaComparar.getHours() * 60 + fechaComparar.getMinutes();

        if (minComparar >= minMananaInicial && minComparar <= minMananaFinal) {
            return "No puede circular";

        } else if (minComparar >= minTardeInicial && minComparar <= minTardeFinal) {
            return "No puede circular";
        } else {
            return "Si puede circular";
        }

    }

    @SuppressWarnings("unused")
    private void obtenerFecha(String fecha, String hora) throws Exception {
        DateFormat sourceFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = sourceFormat.parse(fecha + " " + hora);
        System.out.println(date.getTime());

    }

    @SuppressWarnings("unused")
    private void iniciarHoras() throws ParseException {
        horaRestriccion.setFechaMananaInicio(transformarFechaHora("2017/01/01 07:00:00"));
        horaRestriccion.setFechaMananaFin(transformarFechaHora("2017/01/01 09:30:00"));
        horaRestriccion.setFechaTardeInicio(transformarFechaHora("2017/01/01 16:00:00"));
        horaRestriccion.setFechaTardeFin(transformarFechaHora("2017/01/01 19:30:00"));
    }

    private Date transformarFechaHora(String fecha) throws ParseException {
        DateFormat sourceFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = sourceFormat.parse(fecha);
        return date;

    }

    private PlacaPermitida obtenerDia(int diaSemana, Date fechaComparar, int placa) {
        PlacaPermitida permitida = new PlacaPermitida();
        String dia = "";
        if (diaSemana == 1) {
            permitida.setDia("Domingo");
            dia = "Domingo";
        } else if (diaSemana == 2) {
            permitida.setPlaca1(1);
            permitida.setPlaca2(2);
            permitida.setDia("Lunes");
            if (permitida.getPlaca1() == placa || permitida.getPlaca2() == placa) {
                permitida.setMensaje(validarHorario(fechaComparar));
            } else {
                permitida.setMensaje("Si puede circular");
            }
        } else if (diaSemana == 3) {
            permitida.setPlaca1(3);
            permitida.setPlaca2(4);
            permitida.setDia("Martes");
            if (permitida.getPlaca1() == placa || permitida.getPlaca2() == placa) {
               permitida.setMensaje(validarHorario(fechaComparar));
            } else {
                permitida.setMensaje("Si puede circular");
            }
        } else if (diaSemana == 4) {
            permitida.setPlaca1(5);
            permitida.setPlaca2(6);
            permitida.setDia("Miercoles");
            if (permitida.getPlaca1() == placa || permitida.getPlaca2() == placa) {
                  permitida.setMensaje(validarHorario(fechaComparar));
            } else {
                permitida.setMensaje("Si puede circular");
            }
        } else if (diaSemana == 5) {
            permitida.setPlaca1(7);
            permitida.setPlaca2(8);
            permitida.setDia("Jueves");
            if (permitida.getPlaca1() == placa || permitida.getPlaca2() == placa) {
                   permitida.setMensaje(validarHorario(fechaComparar));
            } else {
                permitida.setMensaje("Si puede circular");
            }
        } else if (diaSemana == 6) {
            permitida.setPlaca1(9);
            permitida.setPlaca2(0);
            permitida.setDia("Viernes");
            if (permitida.getPlaca1() == placa || permitida.getPlaca2() == placa) {
                permitida.setMensaje(validarHorario(fechaComparar));
            } else {
                permitida.setMensaje("Si puede circular");
            }
        } else if (diaSemana == 7) {
            permitida.setDia("Sabado");

        }
        return permitida;
    }

}
