package ar.com.jumperinformatica.gui.model;

import java.util.ArrayList;
import java.util.List;

import ar.com.jumperinformatica.core.enums.TipoFactura;

public class ParametroTipoFactura {
	private List<TipoFactura> listaTiposFacturas;
	private String parametro;
	
	public ParametroTipoFactura(String pNombre, TipoFactura... pTipoFacturas){
		this.parametro = pNombre;
		this.listaTiposFacturas = new ArrayList<TipoFactura>();
		for (TipoFactura cadaTipoFactura : pTipoFacturas){
			listaTiposFacturas.add(cadaTipoFactura);
		}
	}
	
	public List<TipoFactura> getListaTiposFacturas() {
		return listaTiposFacturas;
	}
	public void setListaTiposFacturas(List<TipoFactura> listaTiposFacturas) {
		this.listaTiposFacturas = listaTiposFacturas;
	}

	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	@Override
	public String toString() {
		return this.parametro;
	}
	
}
