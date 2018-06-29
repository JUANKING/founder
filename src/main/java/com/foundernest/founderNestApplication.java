package com.foundernest;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.foundernest.domain.Investor;
import com.foundernest.domain.Startup;
import com.foundernest.repositories.InvestorRepository;
import com.foundernest.repositories.StartupRepository;

@EnableSpringDataWebSupport
@SpringBootApplication
public class founderNestApplication {
	public static void main(String[] args) {
		SpringApplication.run(founderNestApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(InvestorRepository investorRepo, StartupRepository startupRepo) {
		return args -> {
			Investor investor = Investor.builder().name("Example name").build();
			investorRepo.save(investor);
			System.out.println("CommandLineRunner running in the UnsplashApplication class...");

			Startup lugia = Startup.builder().name("Lugia").founderId(investor.getId()).href("StartupZubat").build();
			Startup zubat = Startup.builder().name("Zubat").founderId(investor.getId()).href("StartupZubat").build();

			startupRepo.saveAll(Arrays.asList(lugia, zubat));
		};
	}

}
