package scenes.ListadoEventos;

import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.collections15.Transformer;
import org.uqbar.arena.bindings.DateTransformer;
import org.uqbar.arena.bindings.ValueTransformer;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.LabelProviderBuilder;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import domain.eventos.Evento;
import domain.sugerencias.Sugerencia;
import scenes.Sugerencias.SugerenciasView;

@SuppressWarnings("serial")
public class ListadoEventosView extends SimpleWindow<ListadoEventosViewModel>{

	public ListadoEventosView(WindowOwner parent) {
		super(parent, new ListadoEventosViewModel());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Sugerencias")
		.onClick(this::obtenerSugerencias);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Que Me Pongo");
		
		Panel panelSelectores = new Panel(mainPanel);
		this.crearPanelSelectoresFechasEn(panelSelectores);
		
		Table<Evento> tablaEventos = new Table<Evento>(mainPanel, Evento.class);
//		tablaEventos.bindItemsToProperty("eventos");
//		tablaEventos.bindValueToProperty("eventoSeleccionado");
		
		Column<Evento> columnaFecha = new Column<Evento>(tablaEventos);
		columnaFecha.setTitle("Fecha")
					.setFixedSize(150)
					.bindContentsToProperty("fecha").setTransformer(new Transformer<Evento, String>() {

						@Override
						public String transform(Evento evento) {
							return evento.getFecha().toString();
						}
					});
		
		Column<Evento> columnaTitulos = new Column<Evento>(tablaEventos);
		columnaTitulos.setTitle("Titulo")
					  .setFixedSize(150);
		
		Column<Evento> columnaSugerencias = new Column<Evento>(tablaEventos);
		columnaSugerencias.setTitle("Sugerencia")
						  .setFixedSize(150);
		
		tablaEventos.setHeight(300);
		tablaEventos.setWidth(300);
	}
	
	private void crearPanelSelectoresFechasEn(Panel panel) {
		panel.setLayout(new HorizontalLayout());
		
		new Label(panel).setText("Fecha desde ");
		Selector<String> selectorFechaDesde = new Selector<String>(panel).allowNull(true);
		
		new Label(panel).setText("Fecha hasta ");
		Selector<String> selectorFechaHasta = new Selector<String>(panel).allowNull(true);
	}
	

	public void obtenerSugerencias() {
		Dialog<?> dialog = new SugerenciasView(this);
		dialog.open();
	}

}
