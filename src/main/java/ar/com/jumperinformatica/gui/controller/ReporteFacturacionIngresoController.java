package ar.com.jumperinformatica.gui.controller;

import java.awt.Frame;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ar.com.jumperinformatica.core.enums.TipoFactura;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.core.transients.ReporteFacturacion;
import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.enums.Reportes;
import ar.com.jumperinformatica.gui.model.ParametroTipoFactura;
import ar.com.jumperinformatica.gui.principal.Facturacion;
import ar.com.jumperinformatica.impresion.GestorImpresion;

public class ReporteFacturacionIngresoController extends ReporteFacturacionController {
	
	public ReporteFacturacionIngresoController(Frame padre,
			FacturacionBean facturacionBean) {
		super(padre, facturacionBean);
	}

	
	public ReporteFacturacionIngresoController(JDialog padre,
			FacturacionBean facturacionBean) {
		super(padre, facturacionBean);
	}

	@Override
	protected void initModels() {
		super.initModels();
		ParametroTipoFactura[] locParametroTipoFactura = new ParametroTipoFactura[3];
		locParametroTipoFactura[0] = new ParametroTipoFactura("Factura A",TipoFactura.A);
		locParametroTipoFactura[1] = new ParametroTipoFactura("Factura C",TipoFactura.C);
		locParametroTipoFactura[2] = new ParametroTipoFactura("Factura A y C",TipoFactura.A, TipoFactura.C);
		this.getVista().getCboTipoFactura().setModel(new DefaultComboBoxModel(locParametroTipoFactura));
	}

	@Override
	public void aceptar() {
		try {
			this.actualizarModelo();
			this.validarModelo();
			Map<String,Object> locListaParametros = this.getListaParametros();
			GestorImpresion.imprimirConViewer(this.getVista(),Reportes.FACTURACION_INGRESO, locListaParametros);
		}
		catch(LogicaException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getVista(), e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getVista(), 
					"Ha ocurrido un error al intentar mostrar el reporte. Reinicie la aplicación e intente nuevamente",
					"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private Map<String, Object> getListaParametros() {
		try{
			Map<String, Object> locListaParametros = new HashMap<String, Object>();
			List<ReporteFacturacion> locListaReporte = this.getFacturacionBean().findListaFacturasIngreso(
									this.getModelo().getFechaDesde(),
									this.getModelo().getFechaHasta(),
									this.getModelo().getTiposFacturas().getListaTiposFacturas());
			
			for (ReporteFacturacion cadaReporte : locListaReporte){
				cadaReporte.setDtype("FacturaIngreso");
				cadaReporte.setFechaDesde(this.getModelo().getFechaDesde());
				cadaReporte.setFechaHasta(this.getModelo().getFechaHasta());
				cadaReporte.setPeriodo(this.getModelo().getPeriodo().toString());
				
				
				cadaReporte.setTipoFactura(this.getTipoFacturaContraria());
				
				locListaParametros.putAll(cadaReporte.getListaParametros());
			}
			return locListaParametros;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Obtiene el tipo de factura contraria para el reporte
	 * @return
	 */
	private TipoFactura getTipoFacturaContraria() {
		List<TipoFactura> locListaTiposFactura = this.getModelo().getTiposFacturas().getListaTiposFacturas();
		if (locListaTiposFactura.size()==1){
			if (locListaTiposFactura.get(0)==TipoFactura.A){
				return TipoFactura.C;
			}
			else{
				return TipoFactura.A;
			}
		}
		return null;
	}


	@Override
	public String toString() {
		return "Reporte Facturación Ingreso";
	}
	@Override
	protected void setParametrosVista() {
		this.getVista().setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.BARRA_ABM_REPORTES));
	}
}
