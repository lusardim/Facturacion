package ar.com.jumperinformatica.gui.enums;

public enum Recursos {
	LOGIN_LOGO("imagenes/login.PNG"),
	LOGIN_TITULO("imagenes/tituloLogin.PNG"),
	
	LOGO_JANO("imagenes/jano.png"),
	
	PRINCIPAL_LOGO("imagenes/logo.PNG"),
	PRINCIPAL_MENU_COMITENTES("imagenes/business_user.png"),
	PRINCIPAL_MENU_FACTURA_INGRESO("imagenes/folder_down.png"),
	PRINCIPAL_MENU_FACTURA_EGRESO("imagenes/folder_up.png"),
	PRINCIPAL_MENU_REPORTES("imagenes/page_info.png"),
	PRINCIPAL_MENU_REPORTES_FACTURA_COMITENTES("imagenes/business_user_info.png"),
	PRINCIPAL_MENU_REPORTES_FACTURA_INGRESO_INDIVIDUAL("imagenes/FactIngresoGral.png"),
	PRINCIPAL_MENU_REPORTES_FACTURACION_EGRESO_INDIVIDUAL("imagenes/notebook_search.png"),
	PRINCIPAL_MENU_REPORTES_FACTURACION_EGRESO_GENERAL("imagenes/notebook.png"),
	PRINCIPAL_MENU_UTILIDADES("imagenes/process.png"),
	PRINCIPAL_MENU_UTILIDADES_USUARIOS("imagenes/users.png"),
	PRINCIPAL_MENU_UTILIDADES_BACKUP("imagenes/database_process.png"),
	PRINCIPAL_MENU_UTILIDADES_AYUDA("imagenes/help.png"),
	PRINCIPAL_MENU_UTILIDADES_ACERCADE("imagenes/computers.png"),
	
	
	BUSQUEDA_ICONO_BUSCAR("imagenes/search_p.png"),
	
	
	BARRA_ABM_AGREGAR("imagenes/add.png"),
	BARRA_ABM_MODIFICAR("imagenes/edit.png"),
	BARRA_ABM_ELIMINAR("imagenes/rem.png"),
	
	BACKUP_HACER_BACKUP("imagenes/database_up.png"),
	BACKUP_RESTAURAR_BACKUP("imagenes/database_down.png"),
	
	ACERCADE_LOGO("imagenes/logoJano1.PNG"),
	ACERCADE_TITULO("imagenes/tituloacercade.PNG"),
	
	BOTON_ACEPTAR("imagenes/accept.png"),
	BOTON_CANCELAR("imagenes/delete.png"),
	
	FACTURA_TITULO("imagenes/fact.PNG"),
	FACTURA_BUSCAR_COMITENTE("imagenes/business_user_search.png"),	
	
	
	BARRA_ABM_IMPRIMIR("imagenes/printer.png"),
	BARRA_ABM_REPORTES("imagenes/windows.png")
	;
	
	private final String recurso;
	
	private Recursos(String pRecurso){
		this.recurso = pRecurso;
	}
	
	public String getUriRecurso(){
		return recurso;
	}
}
