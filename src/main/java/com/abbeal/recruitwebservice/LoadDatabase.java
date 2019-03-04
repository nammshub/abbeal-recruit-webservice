package com.abbeal.recruitwebservice;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzContent;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.repositories.QuizzContentRepository;
import com.abbeal.recruitwebservice.repositories.QuizzInstanceRepository;
import com.abbeal.recruitwebservice.repositories.QuizzRepository;
import com.abbeal.recruitwebservice.repositories.UserRepository;

@Configuration
@Slf4j
class LoadDatabase {
	
	Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Autowired
	QuizzRepository quizzRepository;
	@Autowired
	QuizzContentRepository quizzContentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	QuizzInstanceRepository quizzInstanceRepository;

	@Bean
	CommandLineRunner initDatabase() {
		
		
		User user1= new User("Vincent","Dhainaut","test@mail.com","06778899");
		User user2= new User("Chloée","Georgelin","chloee@mail.com","07112233");
		User candidate1 = new User("mail-candidat@mail.com");

		
		Quizz quizz1 = new Quizz("Premier quizz java expert",user1);
		Quizz quizz2 = new Quizz("test angular novice",user2);
		
		Set<QuizzContent> quizzContents1 = new HashSet<>();
		QuizzContent quizzContent1 = new QuizzContent(quizz1,"JAVA",Difficulty.ADVANCED,2);
		QuizzContent quizzContent2 = new QuizzContent(quizz1,"JAVA",Difficulty.EXPERT,2);
		quizzContents1.add(quizzContent1);
		quizzContents1.add(quizzContent2);
		
		Set<QuizzContent> quizzContents2 = new HashSet<>();
		QuizzContent quizzContent3 = new QuizzContent(quizz2,"ANGULAR",Difficulty.ADVANCED,2);
		QuizzContent quizzContent4 = new QuizzContent(quizz2,"ANGULAR",Difficulty.NOVICE,2);
		quizzContents2.add(quizzContent3);
		quizzContents2.add(quizzContent4);
		
		
		QuizzInstance quizzInstance1 = new QuizzInstance(quizz1, candidate1);
		QuizzInstance quizzInstance2 = new QuizzInstance(quizz2, candidate1);


		
		return args -> {
			log.info("Preloading " + userRepository.save(user1));
			log.info("Preloading " + userRepository.save(user2));
			log.info("Preloading " + userRepository.save(candidate1));

			log.info("Preloading " + quizzRepository.save(quizz1));
			log.info("Preloading " + quizzRepository.save(quizz2));
			
			log.info("Preloading " + quizzContentRepository.save(quizzContent1));
			log.info("Preloading " + quizzContentRepository.save(quizzContent2));
			log.info("Preloading " + quizzContentRepository.save(quizzContent3));
			log.info("Preloading " + quizzContentRepository.save(quizzContent4));
			
			log.info("Preloading " + quizzInstanceRepository.save(quizzInstance1));
			log.info("Preloading " + quizzInstanceRepository.save(quizzInstance2));
			
		};
	}
}