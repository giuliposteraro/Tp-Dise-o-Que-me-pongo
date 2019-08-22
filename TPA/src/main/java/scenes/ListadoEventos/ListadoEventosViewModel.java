package scenes.ListadoEventos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.model.annotations.Observable;

import domain.Config;
import domain.eventos.Evento;
import domain.guardarropa.Guardarropa;
import domain.guardarropa.GuardarropaIlimitado;
import domain.usuario.Usuario;
import domain.usuario.UsuarioPremium;
import exceptions.FechasInvalidas;

@Observable
public class ListadoEventosViewModel {
	private Set<Evento> eventos = Config.instance().getRepositorioEventos().eventos();
	private Evento eventoSeleccionado;
	private Integer diaDesde;
	private Integer mesDesde;
	private Integer anioDesde;
	private Integer diaHasta;
	private Integer mesHasta;
	private Integer anioHasta;
	private List <Integer> dias;
	private List <Integer> meses;
	private List <Integer> anios;
	
	public ListadoEventosViewModel() {
		Usuario usuario = this.nuevoUsuario();
		Guardarropa guardarropa = usuario.crearGuardarropa();
		dias = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
		meses = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
		anios = Arrays.asList(2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
		
		Evento unEvento1 = new Evento(usuario, guardarropa, LocalDate.now(), "", "A");
		Evento unEvento2 = new Evento(usuario, guardarropa, LocalDate.now(), "", "B");
		Evento unEvento3 = new Evento(usuario, guardarropa, LocalDate.now(), "", "C");
		Config.instance().getRepositorioEventos().agregarEvento(unEvento1);
		Config.instance().getRepositorioEventos().agregarEvento(unEvento2);
		Config.instance().getRepositorioEventos().agregarEvento(unEvento3);
	}
	
	public Usuario nuevoUsuario() {
		return new Usuario(new UsuarioPremium());
	}
	
	public Guardarropa nuevoGuardarropa() {
		return new Guardarropa(new GuardarropaIlimitado());
	}
	
	public Set<Evento> getEventos() {
		return eventos;
	}
	
	public void setEventos(List<Evento> eventos) {
	}
	
	public Evento getEventoSeleccionado() {
		return eventoSeleccionado;
	}

	public void setEventoSeleccionado(Evento eventoSeleccionado) {
		this.eventoSeleccionado = eventoSeleccionado;
	}

	public List<Integer> getDias() {
		return dias;
	}

	public void setDias(List<Integer> dias) {
		this.dias = dias;
	}

	public List<Integer> getMeses() {
		return meses;
	}

	public void setMeses(List<Integer> meses) {
		this.meses = meses;
	}

	public List<Integer> getAnios() {
		return anios;
	}

	public void setAnios(List<Integer> anios) {
		this.anios = anios;
	}

	public Integer getDiaDesde() {
		return diaDesde;
	}

	public void setDiaDesde(Integer diaDesde) {
		this.diaDesde = diaDesde;
	}

	public Integer getMesDesde() {
		return mesDesde;
	}

	public void setMesDesde(Integer mesDesde) {
		this.mesDesde = mesDesde;
	}

	public Integer getAnioDesde() {
		return anioDesde;
	}

	public void setAnioDesde(Integer anioDesde) {
		this.anioDesde = anioDesde;
	}

	public Integer getDiaHasta() {
		return diaHasta;
	}

	public void setDiaHasta(Integer diaHasta) {
		this.diaHasta = diaHasta;
	}

	public Integer getMesHasta() {
		return mesHasta;
	}

	public void setMesHasta(Integer mesHasta) {
		this.mesHasta = mesHasta;
	}

	public Integer getAnioHasta() {
		return anioHasta;
	}

	public void setAnioHasta(Integer anioHasta) {
		this.anioHasta = anioHasta;
	}
	
	public Set<Evento> buscarEventos(){
		LocalDate fechaDesde = LocalDate.of(anioDesde, mesDesde, diaDesde);
		LocalDate fechaHasta = LocalDate.of(anioHasta, mesHasta, diaHasta);
		if(fechaDesde.isAfter(fechaHasta)) {
			throw new FechasInvalidas("La fecha desde no puede ser mayor a la fecha hasta");
		}
		return this.getEventos().stream().filter(evento -> evento.entre(fechaDesde,fechaHasta)).collect(Collectors.toSet());
	}
	
	

	
}
