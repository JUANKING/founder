package com.foundernest.controllers.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.foundernest.controllers.InvestorController;
import com.foundernest.domain.Investor;
import com.foundernest.repositories.InvestorRepository;
import com.foundernest.repositories.StartupRepository;

import lombok.SneakyThrows;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class InvestorControllerTest {
	public static final PodamFactory factory = new PodamFactoryImpl();

	private InvestorController investorController;

	@Mock
	InvestorRepository investorRepository;
	@Mock
	StartupRepository startupRepository;

	Investor investor;

	ResponseEntity<Page<Investor>> response;
	int page = 0;
	int limit = 10;
	Page<Investor> pageInvestor;


	@Before
	@SneakyThrows
	public void before() {
		this.investorController = new InvestorController(this.investorRepository, this.startupRepository);
		this.investor = factory.manufacturePojo(Investor.class);
		List<Investor> investors = new ArrayList<Investor>();
		investors.add(investor);
		pageInvestor = new PageImpl<Investor>(investors);
		this.response = new ResponseEntity<Page<Investor>>(pageInvestor, HttpStatus.OK);

		when(this.investorRepository.findAll(PageRequest.of(this.page, this.limit))).thenReturn(pageInvestor);
	}

	@Test
	public void testArticleContainsCodeControllerOk() {
		ResponseEntity<Page<Investor>> investorsResponse = this.investorController.findAllInvestors(this.page,
				this.limit);
		Assert.assertNotNull(investorsResponse);
		Assert.assertEquals(HttpStatus.OK, investorsResponse.getStatusCode());
		Assert.assertEquals(investorsResponse.getBody().getContent().size(), this.pageInvestor.getContent().size());

	}

}
