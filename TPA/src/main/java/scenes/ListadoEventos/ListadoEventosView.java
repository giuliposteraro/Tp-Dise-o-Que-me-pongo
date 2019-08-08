package scenes.ListadoEventos;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.collections15.Transformer;
import org.uqbar.arena.layout.HorizontalLayout;
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
		.setCaption("Sugerencias")
		.onClick(this::obtenerSugerencias);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Que Me Pongo");
		
		Panel panelSelectores = new Panel(mainPanel);
		this.crearPanelSelectoresFechasEn(panelSelectores);
		
		Table<Evento> tablaEventos = new Table<Evento>(mainPanel, Evento.class);
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
	
	private void crearPanelSelectoresFechasEn(Panel panel) {
		panel.setLayout(new HorizontalLayout());
		
		new Label(panel).setText("Fecha desde ");
		new TextBox(panel).setWidth(300)
			.bindValueToProperty("fechaDesde");
//		Selector<String> selectorFechaDesde = new Selector<String>(panel).allowNull(true);
		
//		new Label(panel).setText("Fecha hasta ");
//		Selector<String> selectorFechaHasta = new Selector<String>(panel).allowNull(true);
	}
	

	public void obtenerSugerencias() {
		Dialog<?> dialog = new SugerenciasView(this);
		dialog.open();
	}

}
