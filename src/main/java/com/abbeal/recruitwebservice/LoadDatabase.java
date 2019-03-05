package com.abbeal.recruitwebservice;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abbeal.recruitwebservice.entities.Answer;
import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzContent;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.repositories.AnswerRepository;
import com.abbeal.recruitwebservice.repositories.QuestionRepository;
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
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;

	@Bean
	CommandLineRunner initDatabase() {
		
		
		User user1= new User("Vincent","Dhainaut","test@mail.com","06778899");
		User user2= new User("Chloée","Georgelin","chloee@mail.com","07112233");
		User candidate1 = new User("mail-candidat@mail.com");

		
		Quizz quizz1 = new Quizz("Premier quizz java expert",user1);
		Quizz quizz2 = new Quizz("test angular novice",user2);
		
		List<QuizzContent> quizzContents1 = new ArrayList<>();
		QuizzContent quizzContent1 = new QuizzContent(quizz1,"JAVA",Difficulty.ADVANCED,1);
		//QuizzContent quizzContent2 = new QuizzContent(quizz1,"JAVA",Difficulty.EXPERT,2);
		quizzContents1.add(quizzContent1);
		//quizzContents1.add(quizzContent2);
		
		List<QuizzContent> quizzContents2 = new ArrayList<>();
		QuizzContent quizzContent3 = new QuizzContent(quizz2,"ANGULAR",Difficulty.ADVANCED,2);
		QuizzContent quizzContent4 = new QuizzContent(quizz2,"ANGULAR",Difficulty.NOVICE,2);
		quizzContents2.add(quizzContent3);
		quizzContents2.add(quizzContent4);
		
		
		QuizzInstance quizzInstance1 = new QuizzInstance(quizz1, candidate1);
		QuizzInstance quizzInstance2 = new QuizzInstance(quizz2, candidate1);
		
		
		Question question1 = new Question("JAVA", Difficulty.NOVICE, "A quoi sert la classe StringBuilder ?");
		Set<Answer> answers1 = new HashSet<>();
		Answer answer1 = new Answer("Cette classe permet de concatener des chaines de caracteres",true,question1);
		Answer answer2 = new Answer("Cette classe permet d'obtenir des nombres aléatoires",false,question1);
		answers1.add(answer1);
		answers1.add(answer2);
		question1.setAnswers(answers1);
		
		
		Question question2 = new Question("JAVA", Difficulty.ADVANCED, "A quoi sert la classe Thread ?");
		Set<Answer> answers2 = new HashSet<>();
		Answer answer3 = new Answer("Travailler en parrallele",true,question2);
		Answer answer4 = new Answer("Gerer la couture",false,question2);
		Answer answer5 = new Answer("Gerer les peurs",false,question2);
		answers2.add(answer3);
		answers2.add(answer4);
		answers2.add(answer5);
		question2.setAnswers(answers2);
		

		Question question3 = new Question("JAVA", Difficulty.ADVANCED, "Quel avantage d'une interface sur l'heritage ?");
		Set<Answer> answers3 = new HashSet<>();
		Answer answer6 = new Answer("Une classe peut implementer plusieurs interfaces mais ne peut heriter que d'une seule classe mere",true,question3);
		Answer answer7 = new Answer("Les interfaces sont plus rapides que l'heritage",false,question3);
		Answer answer8 = new Answer("Les interfaces permettes de ne pas devoir surcharger toutes les methodes",false,question3);
		answers3.add(answer6);
		answers3.add(answer7);
		answers3.add(answer8);
		question3.setAnswers(answers3);



		
		return args -> {
			log.info("Preloading " + userRepository.save(user1));
			log.info("Preloading " + userRepository.save(user2));
			log.info("Preloading " + userRepository.save(candidate1));

			log.info("Preloading " + quizzRepository.save(quizz1));
			log.info("Preloading " + quizzRepository.save(quizz2));
			
			log.info("Preloading " + quizzContentRepository.save(quizzContent1));
			//log.info("Preloading " + quizzContentRepository.save(quizzContent2));
			log.info("Preloading " + quizzContentRepository.save(quizzContent3));
			log.info("Preloading " + quizzContentRepository.save(quizzContent4));
			
			log.info("Preloading " + quizzInstanceRepository.save(quizzInstance1));
			log.info("Preloading " + quizzInstanceRepository.save(quizzInstance2));
			
			log.info("Preloading " + questionRepository.save(question1));
			log.info("Preloading " + questionRepository.save(question2));
			log.info("Preloading " + questionRepository.save(question3));
			
			log.info("Preloading " + answerRepository.save(answer1));
			log.info("Preloading " + answerRepository.save(answer2));
			log.info("Preloading " + answerRepository.save(answer3));
			log.info("Preloading " + answerRepository.save(answer4));
			log.info("Preloading " + answerRepository.save(answer5));
			log.info("Preloading " + answerRepository.save(answer6));
			log.info("Preloading " + answerRepository.save(answer7));
			log.info("Preloading " + answerRepository.save(answer8));
			
		};
	}
}