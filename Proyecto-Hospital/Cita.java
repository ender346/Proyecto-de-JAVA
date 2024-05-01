public class Cita {
    private String fecha;
    private String hora;
    private Medico medico;
    private Paciente paciente;

    public Cita(String fecha, String hora, Medico medico, Paciente paciente) {
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
