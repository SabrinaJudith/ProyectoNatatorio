package com.natacion.sparta;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.natacion.sparta.vista.RegistroFrom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class SpartaApplication {

	public static void main(String[] args) {
		FlatMacDarkLaf.setup();
		//configuracion de la aplicacion
		//le decimos que no es una aplicacion web, le pasamos la clase pasamos el argumento y lo instanciamos
		ConfigurableApplicationContext contextoSpring =
				new SpringApplicationBuilder(SpartaApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE)
						.run(args);


		//Ejecutamos el codigo para cargar el formulario
		EventQueue.invokeLater(() -> {  //Metodo Lambda
			//Obtenemos el objeto from a traves del spring
			RegistroFrom registroFrom = contextoSpring.getBean(RegistroFrom.class);
			registroFrom.setVisible(true);
		});

	}

}
