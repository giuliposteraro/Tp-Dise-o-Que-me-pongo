package scenes.Sugerencias;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.sugerencias.Sugerencia;

@SuppressWarnings("serial")
public class SugerenciasView extends Dialog<SugerenciasViewModel>{

	public SugerenciasView(WindowOwner owner) {
		super(owner, new SugerenciasViewModel());
	}


	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(form).setText("Sugerencias");
		Selector<Sugerencia> selectorSugerencia = new Selector<Sugerencia>(form).allowNull(true);
		selectorSugerencia.bindItemsToProperty("sugerencias");
		selectorSugerencia.bindValueToProperty("sugerenciaSeleccionada");
		
		new Label(form).setText("Sugerencia Seleccionada");
		new Label(form).setText("");
		
		this.setMinHeight(150);
		this.setMinWidth(200);
	}
	
	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Aceptar").onClick(this::accept).setAsDefault();
		new Button(actions).setCaption("Cancelar").onClick(this::cancel);
	}

	@Override
	protected void executeTask() {
//		System.out.println("Me aceptaron, yuppiiii!!!");
		super.executeTask();
	}

}
