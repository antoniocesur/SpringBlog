package com.vedoque.notas;

import com.vedoque.notas.modelo.Entrada;
import com.vedoque.notas.servicios.ServicioEntradas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.time.LocalDate;

@SpringBootApplication
public class NotasApplication {
	@Autowired
	ServicioEntradas servicio;

	public static void main(String[] args) {
		try{
			Process process = Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqld.exe");
		}catch (IOException e){
			e.printStackTrace();
		}
		SpringApplication.run(NotasApplication.class, args);
	}

	@Bean
	CommandLineRunner baseDeDatos(){
		return args -> {
			Entrada entrada1=new Entrada();
			entrada1.setTitulo("La noticia del día en DAW");
			entrada1.setContenido("La clase ha estado totalmente en silencio durante 3 segundos y 4 décimas, récord del presente año");
			entrada1.setFecha(LocalDate.now());
			servicio.save(entrada1);

			Entrada entrada2=new Entrada();
			entrada2.setTitulo("La noticia del año en DAM");
			entrada2.setContenido("Hoy le ha funcionado un método a la primera a toda la clase");
			entrada2.setFecha(LocalDate.now().minusDays(1));
			servicio.save(entrada2);

			for(int i=0; i<50; i++){
				Entrada entrada=new Entrada();
				entrada.setTitulo("La noticia " + (i+3));
				entrada.setContenido("Esta es la noticia número " + (i+3));
				entrada.setFecha(LocalDate.now().minusDays(i));
				servicio.save(entrada);
			}

		};
	}

}
