package test;

import static org.junit.Assert.*;

import org.junit.Test;

import vista.Logeado;
import vista.RestaurarContrasena;
import vista.VentanaEspacios;
import vista.VentanaEstaciones;
import vista.VentanaTop;

public class TestsVentanas {

	//Tests ventanas
	@org.junit.Test
	public void testVentanaRestaurar() {
		RestaurarContrasena ventana = new RestaurarContrasena();
		ventana.getBtnEnviarContrasena();
		ventana.getBtnEnviarRespuesta();
		ventana.getBtnEnviarUsuario();
		ventana.getBtnVolver();
		ventana.getFrame();
		ventana.getLblContrasena();
		ventana.getLblIntroduzcaUsuario();
		ventana.getLblPregunta();
		ventana.getLblRepetirContrasena();
		ventana.getLblResponda();
		ventana.getTextFieldContrasena();
		ventana.getTextFieldRepetirContrasena();
		ventana.getTextFieldRespuesta();
		ventana.getTextFieldUsuario();
		assertEquals(true, ventana.metodoPrueba());
	}
	
	@org.junit.Test
	public void testVentanaEstaciones() {
		VentanaEstaciones ventana = new VentanaEstaciones();
		ventana.setTable(ventana.getTable());
		ventana.setModel(ventana.getModel());
		ventana.setComboBox(ventana.getComboBox());
		assertEquals(true, ventana.metodoPrueba());
	}
	
	@Test
	public void testVentanaTops()
	{
		VentanaTop ventana = new VentanaTop();
		ventana.setScrollPaneBizkaia(ventana.getScrollPaneBizkaia());
		ventana.setScrollPaneAraba(ventana.getScrollPaneAraba());
		ventana.setScrollPaneGuipuzkoa(ventana.getScrollPaneGuipuzkoa());
		ventana.setModelBizkaia(ventana.getModelBizkaia());
		ventana.setTableBizkaia(ventana.getTableBizkaia());
		ventana.setModelAraba(ventana.getModelAraba());
		ventana.setTableAraba(ventana.getTableAraba());
		ventana.setModelGuipu(ventana.getModelGuipu());
		ventana.setTableGuipu(ventana.getTableGuipu());
		ventana.setModelEspacios(ventana.getModelEspacios());
		ventana.setTableEspacios(ventana.getTableEspacios());
		ventana.setModelMunicipios(ventana.getModelMunicipios());
		ventana.setTableMunicipios(ventana.getTableMunicipios());
		ventana.setColumnasEspacios(ventana.getColumnasEspacios());
		ventana.setColumnasMunicipios(ventana.getColumnasMunicipios());
		ventana.setScrollPaneEstaciones(ventana.getScrollPaneEstaciones());
		ventana.setScrollPaneMunicipios(ventana.getScrollPaneMunicipios());
		assertEquals(true, ventana.metodoPrueba());
	}
	
	@Test
	public void testVentanaEspacios()
	{
		VentanaEspacios ventana = new VentanaEspacios();
		ventana.setEspacios(ventana.getEspacios());
		ventana.setVolver(ventana.getVolver());
		ventana.getModel();
		assertEquals(true, ventana.metodoPrueba());
	}
	
	@Test
	public void testVentanaLogeado()
	{
		Logeado ventana = new Logeado();
		ventana.setModel(ventana.getModel());
		ventana.setTable(ventana.getTable());
		ventana.getBotonBizkaia();
		ventana.getBotonAraba();
		ventana.getBotonGipuzkoa();
		ventana.getFrame();
		ventana.getBotonTops();
		ventana.getBotonEspaciosNaturales();
		assertEquals(true, ventana.probarClase());
	}
}
