/*package aiss.model.resources;

import static org.junit.Assert.*;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;


import aiss.api.consume.*;


public class PilotoResourceTest {

	static Piloto piloto1, piloto2, piloto3;
	static PilotoResource sr = new PilotoResource();
	
	@BeforeClass
	public static void setup() throws Exception {
		
		// Test Piloto 1
		piloto1 = sr.addPiloto(new Piloto("Test escuderia","Test nombre",45,45, 3));
		
		// Test Piloto 2
		piloto2 = sr.addPiloto(new Piloto("Test escuderia 2","Test nombre 2",2,653, 62));
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		sr.deletePiloto(piloto1.getId());
		sr.deletePiloto(piloto3.getId());
	}
	
	@Test
	public void testGetAll() {
		Collection<Piloto> pilotos = sr.getAll();
		
		assertNotNull("The collection of pilotos is null", pilotos);
		
		// Show result
		System.out.println("Listing all pilotos:");
		int i=1;
		for (Piloto s : pilotos) {
			System.out.println("Piloto" + i++ + " : " + s.getNombre() + " (ID=" + s.getId() + ")");
		}
	}

	@Test
	public void testGetPiloto() {
		Piloto piloto = sr.getPiloto("p0");
		
		assertNotNull(piloto);
		
		System.out.println(piloto.getEscuderia());
		System.out.println(piloto.getNombre());
		System.out.println(piloto.getEdad());
		System.out.println(piloto.getDorsal());
		System.out.println(piloto.getVictorias());

		
	}
	

	@Test
	public void testAddPiloto() {
		
		Piloto piloto = new Piloto("Test escuderia","Test nombre",45,45, 3);
		Piloto added = sr.addPiloto(piloto);
		
		assertNotNull(added);
		assertEquals(piloto.getEscuderia(), added.getEscuderia());
		assertEquals(piloto.getNombre(), added.getNombre());
		assertEquals(piloto.getEdad(), added.getEdad());
		assertEquals(piloto.getDorsal(), added.getDorsal());
		assertEquals(piloto.getVictorias(), added.getVictorias());

	}

	@Test
	public void testUpdatePiloto() {
		
		String pilotoEscuderia = "Update piloto test escuderia";
		String pilotoNombre = "Update piloto test nombre";
		Integer pilotoEdad = 25;
		Integer pilotoDorsal = 164;
		Integer pilotoVictorias = 199;
		
		// Update piloto
		piloto1.setEscuderia(pilotoEscuderia);
		piloto1.setNombre(pilotoNombre);
		piloto1.setEdad(pilotoEdad);
		piloto1.setDorsal(pilotoDorsal);
		piloto1.setVictorias(pilotoVictorias);
		
		boolean success = sr.updatePiloto(piloto1);
		
		assertTrue("Error when updating the piloto", success);
		
		Piloto piloto  = sr.getPiloto(piloto1.getId());
		assertEquals("La escuderia del piloto no ha sido actualizada correctamente", pilotoEscuderia, piloto.getEscuderia());
		assertEquals("El nombre del piloto no ha sido actualizado correctamente", pilotoNombre, piloto.getNombre());
		assertEquals("La edad del piloto no ha sido actualizada correctamente", pilotoEdad, piloto.getEdad());
		assertEquals("El dorsal del piloto no ha sido actualizado correctamente", pilotoDorsal, piloto.getDorsal());
		assertEquals("Las victorias del piloto no han sido actualizadas correctamente", pilotoVictorias, piloto.getVictorias());
	}

	@Test(expected = ResourceException.class)
	public void testDeletePiloto() {
		
		// Delete pilotos
		boolean success = sr.deletePiloto(piloto2.getId());
		
		assertTrue("Error when deleting the piloto", success);
		
		Piloto piloto  = sr.getPiloto(piloto2.getId());
		assertNull("The piloto has not been deleted correctly", piloto);
	}

}*/
