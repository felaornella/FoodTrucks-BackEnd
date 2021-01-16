package clasesPrueba;

import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

import ttps.spring.clasesDAO.*;
import ttps.spring.clasesDAOImpJPA.FactoryDAO;
import ttps.spring.model.*;
public class Pruebas {

	public static void main(String[] args) {
		Scanner check = new Scanner(System.in);
		check.nextLine();
		testAlta();
//		check.nextLine();
//		testRecuperar();
//		check.nextLine();
//		testModificar();
//		check.nextLine();
//		testBorrar();
//		check.nextLine();
		check.close();
	}
	
	private static void testRecuperar(){
		Calendar c = Calendar.getInstance();
		Date fechaHoy= c.getTime();
		//Objetos de Prueba		
		
		System.out.println("--------------------------------- INICIO TEST GENERAL	---------------------------------");
		System.out.println("-----------------CREACION DE DAOS");
		OrganizadorDAO oDAO = FactoryDAO.getOrganizadorDAO();
		FoodTruckerDAO ftDAO = FactoryDAO.getFoodTruckerDAO();
		EventoDAO eDAO = FactoryDAO.getEventoDAO();
		FoodTruckDAO fDAO = FactoryDAO.getFoodTruckDAO();
		ValoracionDAO vDAO = FactoryDAO.getValoracionDAO();
		SolicitudDAO sDAO = FactoryDAO.getSolicitudDAO();
		System.out.println("-----------------DAOS CREADOS. Inicia Recuperacion");
		System.out.println("-----------------Organizadores");
		List<Organizador> os= oDAO.recuperarTodos();
		System.out.println("-----------------FoodTruckers");
		List<FoodTrucker> fts= ftDAO.recuperarTodos();
		System.out.println("-----------------Eventos");
		List<Evento> es= eDAO.recuperarTodos();
		System.out.println("-----------------FoodTrucks");
		List<FoodTruck> fs = fDAO.recuperarTodos();
		System.out.println("-----------------Valoraciones");
		List<Valoracion> vs= vDAO.recuperarTodos();
		System.out.println("-----------------Solicitudes");
		List<Solicitud> ss= sDAO.recuperarTodos();
		System.out.println("-----------------DATOS RECUPERADOS. Inicia IMPRESION");
		System.out.println("-----------------Organizadores");
		for (Organizador o : os) {System.out.println(o.toString());}
		System.out.println("-----------------FoodTruckers");
		for (FoodTrucker ft: fts) {System.out.println(ft.toString());}
		System.out.println("-----------------Eventos");
		for (Evento e : es) {System.out.println(e.toString());}
		System.out.println("-----------------FoodTrucks");
		for (FoodTruck f : fs) {System.out.println(f.toString());}
		System.out.println("-----------------Valoraciones");
		for (Valoracion v : vs) {System.out.println(v.toString());}
		System.out.println("-----------------Solicitudes");
		for (Solicitud s : ss) {System.out.println(s.toString());}
		System.out.println("--------------------------------- FIN TEST GENERAL	---------------------------------");
	}
	private static void testAlta(){
		Calendar c = Calendar.getInstance();
		Date fechaHoy= c.getTime();
		//Objetos de Prueba		
		
		System.out.println("--------------------------------- INICIO TEST GENERAL	---------------------------------");
		System.out.println("-----------------CREACION DE DAOS");
		OrganizadorDAO oDAO = FactoryDAO.getOrganizadorDAO();
		FoodTruckerDAO ftDAO = FactoryDAO.getFoodTruckerDAO();
		EventoDAO eDAO = FactoryDAO.getEventoDAO();
		FoodTruckDAO fDAO = FactoryDAO.getFoodTruckDAO();
		ValoracionDAO vDAO = FactoryDAO.getValoracionDAO();
		SolicitudDAO sDAO = FactoryDAO.getSolicitudDAO();
		System.out.println("-----------------DAOS CREADOS. Inicia Persistir");
		System.out.println("-----------------DEFINICION: Organizador");
		Organizador o = new Organizador("Juan","Perez","juanperez","password","juanperez@hotmail.com");
		System.out.println("-----------------DEFINICION: FoodTrucker");
		FoodTrucker ft = new FoodTrucker("Pedro","Garcia","elpepe","admin","pepegarcia@gmail.com");
		System.out.println("-----------------DEFINICION: Evento");
		Evento e = new Evento("Picurba","Camino Centenario 1854",1900,"Buenos Aires",fechaHoy,"picurba@hotmail.com","2214712222","Somos un picnic urbano que reune los mejores emprendimientos de comida del area","Markets Callejeros","Asistentes");
		System.out.println("-----------------DEFINICION: FoodTruck");
		FoodTruck f = new FoodTruck("Sale Con Pan","Sandwiches","Hacemos los mejores sandwiches de bondiola de la ciudad!","www.saleconpan.com","saleconpan","2215062190","saleconpan",0);
		for (int i=1;i<10;i++) {
			String nombre="ImagenNn"+String.valueOf(i);
			f.agregarImagen(nombre);
		}
		System.out.println("-----------------DEFINICION: Valoracion");
		Valoracion v = new Valoracion(5,3,4,5,2);
		System.out.println("-----------------DEFINICION: Solicitud");
		Solicitud s = new Solicitud(e,f);
		//Solicitud s = new Solicitud();
		System.out.println("-----------------DEFINICION: Fin Definicion. Pasa a Asignaciones");
		
		
		System.out.println("-----------------ASIGNACION: Evento a Organizador");
		o.agregarEvento(e);
		System.out.println("-----------------ASIGNACION: Solicitud a Organizador");
		o.agregarSolicitud(s);
		System.out.println("-----------------ASIGNACION: FoodTruck a FoodTrucker");
		ft.agregarFoodTruck(f);
		System.out.println("-----------------ASIGNACION: Solicitud a FoodTrucker");
		ft.agregarSolicitud(s);
		System.out.println("-----------------ASIGNACION: Valoracion a Solicitud");
		s.agregarValoracion(v);
		System.out.println("-----------------ASIGNACION: Fin Asignacion. Pasa a Persistir");
		
		
		System.out.println("-----------------PERSISTIENDO.");
		oDAO.persistir(o);
		System.out.println("-----------------PERSISTIENDO. Fin Persistencia");
		System.out.println("--------------------------------- FIN TEST GENERAL	---------------------------------");
	}
	private static void testBorrar(){
		//Objetos de Prueba		
		System.out.println("--------------------------------- INICIO TEST GENERAL	---------------------------------");
		System.out.println("-----------------CREACION DE DAOS");
		OrganizadorDAO oDAO = FactoryDAO.getOrganizadorDAO();
		FoodTruckerDAO ftDAO = FactoryDAO.getFoodTruckerDAO();
		EventoDAO eDAO = FactoryDAO.getEventoDAO();
		FoodTruckDAO fDAO = FactoryDAO.getFoodTruckDAO();
		ValoracionDAO vDAO = FactoryDAO.getValoracionDAO();
		SolicitudDAO sDAO = FactoryDAO.getSolicitudDAO();
		System.out.println("-----------------DAOS CREADOS. Inicia Eliminacion");
		
		System.out.println("-----------------Organizador");
		oDAO.borrar(Long.valueOf(1));
		System.out.println("-----------------FoodTrucker");
		ftDAO.borrar(Long.valueOf(2));
		System.out.println("-----------------Eventos");
		eDAO.borrar(Long.valueOf(1));
		System.out.println("-----------------FoodTrucks");
		fDAO.borrar(Long.valueOf(1));
		System.out.println("-----------------Valoracion");
		vDAO.borrar(Long.valueOf(1));
		System.out.println("-----------------Solicitud");
		sDAO.borrar(Long.valueOf(1));
		System.out.println("-----------------DATOS ELIMINADOS");
		
		System.out.println("--------------------------------- FIN TEST GENERAL	---------------------------------");
	}
	private static void testModificar(){
		//Objetos de Prueba		
		System.out.println("--------------------------------- INICIO TEST GENERAL	---------------------------------");
		System.out.println("-----------------CREACION DE DAOS");
		OrganizadorDAO oDAO = FactoryDAO.getOrganizadorDAO();
		FoodTruckerDAO ftDAO = FactoryDAO.getFoodTruckerDAO();
		EventoDAO eDAO = FactoryDAO.getEventoDAO();
		FoodTruckDAO fDAO = FactoryDAO.getFoodTruckDAO();
		ValoracionDAO vDAO = FactoryDAO.getValoracionDAO();
		SolicitudDAO sDAO = FactoryDAO.getSolicitudDAO();
		System.out.println("-----------------DAOS CREADOS. Inicia Recuperacion");
		
		System.out.println("-----------------Organizador");
		Organizador o= oDAO.recuperarPorId(1);
		System.out.println("-----------------FoodTrucker");
		FoodTrucker ft = ftDAO.recuperarPorId(2);
		System.out.println("-----------------Eventos");
		Evento e = eDAO.recuperarPorId(1);
		System.out.println("-----------------FoodTrucks");
		FoodTruck f = fDAO.recuperarPorId(1);
		System.out.println("-----------------Valoracion");
		Valoracion v = vDAO.recuperarPorId(1);
		System.out.println("-----------------Solicitud");
		Solicitud s = sDAO.recuperarPorId(1);
		System.out.println("-----------------DATOS RECUPERADOS. Inicia MODIFICACION");
		
		System.out.println("-----------------Organizador: Cambia Nombre");
		o.setNombre("NombreActualizado");
		System.out.println("-----------------FoodTrucker: Cambia Nombre");
		ft.setNombre("NombreActualizado");
		System.out.println("-----------------Evento: Cambia Nombre");
		e.setNombre("NombreActualizado");
		System.out.println("-----------------FoodTruck: Cambia Nombre");
		f.setNombre("NombreActualizado");
		System.out.println("-----------------Valoracion: Cambian Valores a todos 1");
		v.setCalidad_precio(1);
		v.setDiseno(1);
		v.setLimpieza(1);
		v.setSabor(1);
		v.setSimpatia(1);
		System.out.println("-----------------Solicitud: Cambia Estado a Aceptada");
		s.setAceptada();
		System.out.println("-----------------DATOS Modificados. Inicia Actualizacion");
		
		
		System.out.println("-----------------Organizador");
		oDAO.actualizar(o);
		System.out.println("-----------------FoodTrucker");
		ftDAO.actualizar(ft);
		System.out.println("-----------------Eventos");
		eDAO.actualizar(e);
		System.out.println("-----------------FoodTrucks");
		fDAO.actualizar(f);
		System.out.println("-----------------Valoracion");
		vDAO.actualizar(v);
		System.out.println("-----------------Solicitud");
		sDAO.actualizar(s);
		System.out.println("-----------------DATOS Actualizados.");
		
		
		System.out.println("--------------------------------- FIN TEST GENERAL	---------------------------------");
	}
}
