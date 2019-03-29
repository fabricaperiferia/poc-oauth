package com.aval.oauth.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aval.oauth.dto.ResponseObject;
import com.aval.oauth.service.TokenService;
import com.aval.oauth.util.Codes;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenControllerTest {
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private TokenService tokenService;
	
	private ResponseObject response;
	
	@Before
	public void setUp(){
		request = mock(HttpServletRequest.class);
		tokenService = mock(TokenService.class);
	}
	
	@Test
	public void whenNullRequest_thenErrorToken() {
		response = new ResponseObject();
		response.setCode(Codes.ERROR_TOKEN.getValue());
		response.setDetailedMessage(Codes.ERROR_TOKEN.getLabel());
		when(tokenService.validateToken(null)).thenReturn(response);
	}

	
}
