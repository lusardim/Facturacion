package ar.com.jumperinformatica.gui.enums;

public enum Recursos {
	LOGIN_LOGO("resources/login.PNG"),
	LOGIN_TITULO("resources/tituloLogin.PNG"),
	
	LOGO_JANO("resources/jano.png"),
	
	PRINCIPAL_LOGO("resources/logo.PNG"),
	PRINCIPAL_MENU_COMITENTES("resources/business_user.png"),
	PRINCIPAL_MENU_FACTURA_INGRESO("resources/folder_down.png"),
	PRINCIPAL_MENU_FACTURA_EGRESO("resources/folder_up.png"),
	PRINCIPAL_MENU_REPORTES("resources/page_info.png"),
	PRINCIPAL_MENU_REPORTES_FACTURA_COMITENTES("resources/business_user_info.png"),
	PRINCIPAL_MENU_REPORTES_FACTURA_INGRESO_INDIVIDUAL("resources/FactIngresoGral.png"),
	PRINCIPAL_MENU_REPORTES_FACTURACION_EGRESO_INDIVIDUAL("resources/notebook_search.png"),
	PRINCIPAL_MENU_REPORTES_FACTURACION_EGRESO_GENERAL("resources/notebook.png"),
	PRINCIPAL_MENU_UTILIDADES("resources/process.png"),
	PRINCIPAL_MENU_UTILIDADES_USUARIOS("resources/users.png"),
	PRINCIPAL_MENU_UTILIDADES_BACKUP("resources/database_process.png"),
	PRINCIPAL_MENU_UTILIDADES_AYUDA("resources/help.png"),
	PRINCIPAL_MENU_UTILIDADES_ACERCADE("resources/computers.png"),
	
	
	BUSQUEDA_ICONO_BUSCAR("resources/search_p.png"),
	
	
	BARRA_ABM_AGREGAR("resources/add.png"),
	BARRA_ABM_MODIFICAR("resources/edit.png"),
	BARRA_ABM_ELIMINAR("resources/rem.png"),
	
	BACKUP_HACER_BACKUP("resources/database_up.png"),
	BACKUP_RESTAURAR_BACKUP("resources/database_down.png"),
	
	ACERCADE_LOGO("resources/logoJano1.PNG"),
	ACERCADE_TITULO("resources/tituloacercade.PNG"),
	
	BOTON_ACEPTAR("resources/accept.png"),
	BOTON_CANCELAR("resources/delete.png"),
	
	FACTURA_TITULO("resources/fact.PNG"),
	FACTURA_BUSCAR_COMITENTE("resources/business_user_search.png"),	
	
	
	BARRA_ABM_IMPRIMIR("resources/printer.png"),
	BARRA_ABM_REPORTES("resources/windows.png")
	;
	
	private final String recurso;
	
	private Recursos(String pRecurso){
		this.recurso = pRecurso;
	}
	
	public String getUriRecurso(){
		return recurso;
	}
}
