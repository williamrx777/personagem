package com.will.personagem;

import com.will.personagem.Entities.Atores;
import com.will.personagem.Entities.Filmes;
import com.will.personagem.Repositories.AtoresRepository;
import com.will.personagem.Repositories.FilmesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class PersonagemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonagemApplication.class, args);
	}
	@Bean
	public CommandLineRunner mappingTest(FilmesRepository filmesRepository, AtoresRepository atoresRepository){
		return args -> {
			Filmes filmes1 = new Filmes();
			filmes1.setName("Equals");
			filmes1.setDescription("No futuro, existe uma nova raça de seres humanos - os Equals, indivíduos pacíficos, justos e que não possuem mais emoções. Até que uma doença passa a ameaçar todos, ativando sentimentos em suas vítimas, que são excluídas do resto da sociedade. Silas é infectado, mas percebe que Nia também possui sentimentos, sendo capaz de escondê-los. Ao sentirem pela primeira vez algum tipo de intimidade em suas vidas, eles decidem fugir.");
			filmes1.setImage("https://cdn.fstatic.com/media/movies/photos/2016/03/equals_t86078.jpg");

			Filmes filmes2 = new Filmes();
			filmes2.setName("Eu Robo");
			filmes2.setDescription("Em 2035, é comum robôs serem usados como empregados e assistentes dos humanos. Para manter a ordem , esses robôs possuem um código de programação que impede a violência contra humanos, a Lei dos Robóticos. Quando Dr. Miles aparece morto e o principal suspeito é justamente um robô, acredita-se na possibilidade de que os robôs tenham encontrado um meio de desativar a Lei dos Robóticos.");
			filmes2.setImage("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSwltqEu4PlYVCzV_HnWiqWMdU59NAH3bZPgdi9rIV8PDOfc4oK");
			filmesRepository.saveAll(Arrays.asList(filmes1,filmes2));

			Atores atores1 = new Atores();
			atores1.setName("Nicholas Hoult");
			atores1.setImage("https://t2.gstatic.com/licensed-image?q=tbn:ANd9GcRkAtmQsb6LRqmvv2O1O-WaKG4NrBuJgStHnwMU2rI1OLlj-BkjtY03EjwxFnKDxBj4");

			Atores atores2 = new Atores();
			atores2.setName("Kristen Stewart");
			atores2.setImage("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQUZL6tFyI51zRT_g07_88vR6Ni51kInf9nX1tA9_302AhJPSit");

			Atores atores3 = new Atores();
			atores3.setName("Will Smith");
			atores3.setImage("https://imagem.natelinha.uol.com.br/tudo-sobre/will-smith_4963.jpeg");
			atoresRepository.saveAll(Arrays.asList(atores1,atores2,atores3));

			filmes1.getAtores().addAll(Arrays.asList(atores1,atores2));

			filmes2.getAtores().add(atores3);

			filmesRepository.saveAll(Arrays.asList(filmes1,filmes2));


		};
	}
}
