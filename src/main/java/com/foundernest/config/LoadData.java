package com.foundernest.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foundernest.domain.Investor;
import com.foundernest.domain.Startup;
import com.foundernest.repositories.InvestorRepository;
import com.foundernest.repositories.StartupRepository;
import com.google.gson.Gson;

@Component
@Order(1)
public class LoadData {
	private final Logger logger = LoggerFactory.getLogger(LoadData.class);

	@Bean
	CommandLineRunner runner(InvestorRepository investorRepo, StartupRepository startupRepo) {
		return args -> {
			Investor investor = Investor.builder().name("Example name").build();
			investorRepo.save(investor);
			List<Startup> startups = getJson(investor.getId());
			logger.info("CommandLineRunner -- Load data.");
			startupRepo.saveAll(startups);
		};
	}

	@SuppressWarnings("unchecked")
	public List<Startup> getJson(String founderId) {
		JSONParser parser = new JSONParser();
		List<Startup> startups = new ArrayList<Startup>();
		try {
			Object obj = parser.parse(new FileReader(getClass().getResource("/response.json").getPath()));
			JSONArray jsonArray = (JSONArray) obj;
			jsonArray.forEach(item -> {
				JSONObject JSONobj = (JSONObject) item;
				System.out.println("JSONobj: " + JSONobj);
				Startup startup = new Gson().fromJson(JSONobj.toString(), Startup.class);
				startup.setFounderId(founderId);
				startups.add(startup);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startups;
	}

}
