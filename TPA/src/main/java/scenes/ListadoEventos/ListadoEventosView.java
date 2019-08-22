package scenes.ListadoEventos;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.collections15.Transformer;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import domain.eventos.Evento;
import scenes.Sugerencias.SugerenciasView;

@SuppressWarnings("serial")
public class ListadoEventosView extends SimpleWindow<ListadoEventosViewModel>{

	public ListadoEventosView(WindowOwner parent) {
		super(parent, new ListadoEventosViewModel());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Buscar")
		.onClick(()-> this.getModelObject().buscarEventos());
		//.onClick(this::buscarEventos());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Que Me Pongo");
		
		Panel panelSelectores = new Panel(mainPanel);
		panelSelectores.setLayout(new VerticalLayout());
		this.crearPanelSelectorFechasDesdeEn(panelSelectores);
		this.crearPanelSelectorFechasHastaEn(panelSelectores);
		
		Table<Evento> tablaEventos = new Table<Evento>(mainPanel, Evento.class);
		tablaEventos.setHeight(300);
		tablaEventos.bindItemsToProperty("eventos");
		tablaEventos.bindValueToProperty("eventoSeleccionado");
		
		Column<Evento> columnaFecha = new Column<Evento>(tablaEventos);
		columnaFecha.setTitle("Fecha")
					.setFixedSize(100)
					.bindContentsToProperty("fecha")
					.setTransformer(new Transformer<Date, String>() {

						@Override
						public String transform(Date fecha) {
							return DateFormat.getInstance().format(fecha);
						}
					});
		
		Column<Evento> columnaTitulos = new Column<Evento>(tablaEventos);
		columnaTitulos.setTitle("Titulo")
					  .setFixedSize(500)
					  .bindContentsToProperty("motivo");
		
//		Column<Evento> columnaSugerencias = new Column<Evento>(tablaEventos);
//		columnaSugerencias.setTitle("Sugerencia")
//						  .setFixedSize(150);
		
		tablaEventos.setWidth(800);
		tablaEventos.setHeight(600);
	}
	
	private void crearPanelSelectorFechasDesdeEn(Panel panel) {
		Panel panelFecha = new Panel(panel);
		panelFecha.setLayout(new HorizontalLayout());
		
		
		new Label(panelFecha).setText("Fecha desde: ");
		
		Selector<Integer> selectorDia = new Selector<Integer>(panelFecha);
		selectorDia.bindValueToProperty("diaDesde");
		selectorDia.bindItemsToProperty("dias");
		
		Selector <Integer> selectorMes = new Selector<Integer>(panelFecha);
		selectorMes.bindValueToProperty("mesDesde");
		selectorMes.bindItemsToProperty("meses");
		
		Selector <Integer> selectorAnio= new Selector<Integer>(panelFecha);
		selectorAnio.bindValueToProperty("anioDesde");	
		selectorAnio.bindItemsToProperty("anios");
		
		
	}
	
	private void crearPanelSelectorFechasHastaEn(Panel panel) {
		Panel panelFecha = new Panel(panel);
		panelFecha.setLayout(new HorizontalLayout());
		
		new Label(panelFecha).setText("Fecha hasta: ");
		
		Selector<Integer> selectorDia = new Selector<Integer>(panelFecha);
		selectorDia.bindValueToProperty("diaHasta");
		selectorDia.bindItemsToProperty("dias");
		
		Selector <Integer> selectorMes = new Selector<Integer>(panelFecha);
		selectorMes.bindValueToProperty("mesHasta");
		selectorMes.bindItemsToProperty("meses");
		
		Selector <Integer> selectorAnio= new Selector<Integer>(panelFecha);
		selectorAnio.bindValueToProperty("anioHasta");	
		selectorAnio.bindItemsToProperty("anios");
	}

}
