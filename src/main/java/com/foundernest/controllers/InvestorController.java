package com.foundernest.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foundernest.domain.Investor;
import com.foundernest.domain.Startup;
import com.foundernest.repositories.InvestorRepository;
import com.foundernest.repositories.StartupRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/investors")
@Api(tags = { "API Investors" })
@CrossOrigin
public class InvestorController {

	@Autowired
	InvestorRepository founderRepo;
	@Autowired
	StartupRepository startupRepo;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all founders")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error") })
	public ResponseEntity<Page<Investor>> findAllInvestors(
			@ApiParam(value = "Page") @RequestParam(value = "page", required = false, defaultValue = "0" ) int page,
			@ApiParam(value = "Size") @RequestParam(value = "size", required = false, defaultValue = "25") int size) {
		return new ResponseEntity<Page<Investor>>(this.founderRepo.findAll(PageRequest.of(page, size)), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find one founder by id")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error") })
	public ResponseEntity<Investor> findInvestorById(
			@ApiParam(value = "Investor Id", required = true) @PathVariable String id) {
		return new ResponseEntity<Investor>(this.founderRepo.findById(id).get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/startups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find one founder by id")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error") })
	public ResponseEntity<Page<Startup>> findAllStartupsByInvestor(
			@ApiParam(value = "Page") @RequestParam(value = "page", required = false, defaultValue = "0" ) int page,
			@ApiParam(value = "Size") @RequestParam(value = "size", required = false, defaultValue = "25") int size,
			@ApiParam(value = "Investor Id", required = true) @PathVariable String id) {
		return new ResponseEntity<Page<Startup>>(this.startupRepo.findAllByFounderId(id, PageRequest.of(page, size)), HttpStatus.OK);
	}

}
