package clasesPrueba;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ttps.spring.clasesDAO.EventoDAO;
import ttps.spring.clasesDAO.FoodTruckDAO;
import ttps.spring.clasesDAO.FoodTruckerDAO;
import ttps.spring.clasesDAO.OrganizadorDAO;
import ttps.spring.clasesDAO.SolicitudDAO;
import ttps.spring.clasesDAO.ValoracionDAO;
import ttps.spring.clasesDAOImpJPA.FactoryDAO;
import ttps.spring.model.Evento;
import ttps.spring.model.FoodTruck;
import ttps.spring.model.FoodTrucker;
import ttps.spring.model.Organizador;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Valoracion;

public class PruebaSpring {
	public static void main (String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		ctx.register(ttps.spring.config.PersistenceConfig.class);
	
		
		ctx.scan("ttps.spring");
		ctx.refresh();
		
		Calendar c = Calendar.getInstance();
		Date fechaHoy= c.getTime();
		//Objetos de Prueba		
		
		System.out.println("--------------------------------- INICIO TEST GENERAL	---------------------------------");
		System.out.println("-----------------CREACION DE DAOS");
		OrganizadorDAO oDAO = ctx.getBean("organizadorDAOImpJPA",OrganizadorDAO.class);
//		FoodTruckerDAO ftDAO = ctx.getBean("foodTruckerDAOImpJPA",FoodTruckerDAO.class);
//		EventoDAO eDAO = ctx.getBean("eventoDAOImpJPA",EventoDAO.class);
//		FoodTruckDAO fDAO = ctx.getBean("foodTruckDAOImpJPA",FoodTruckDAO.class);
//		ValoracionDAO vDAO = ctx.getBean("valoracionDAOImpJPA",ValoracionDAO.class);
//		SolicitudDAO sDAO = ctx.getBean("solicitudDAOImpJPA",SolicitudDAO.class);
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
			String nombre="ImagenN°"+String.valueOf(i);
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
	
}
