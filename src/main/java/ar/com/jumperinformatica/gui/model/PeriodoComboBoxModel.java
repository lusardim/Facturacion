package ar.com.jumperinformatica.gui.model;

import javax.swing.DefaultComboBoxModel;

import ar.com.jumperinformatica.gui.enums.Periodo;

public class PeriodoComboBoxModel extends DefaultComboBoxModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 848174156765557147L;

	public PeriodoComboBoxModel(){
		super(Periodo.values());
	}
}
