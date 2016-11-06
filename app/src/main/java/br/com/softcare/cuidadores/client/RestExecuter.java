package br.com.softcare.cuidadores.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.softcare.cuidadores.enuns.API_URLS;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import br.com.softcare.cuidadores.exceptions.ExceptionError;

public final class RestExecuter {

	private RestTemplate restTemplate;

	private ObjectMapper objectMapper = new ObjectMapper();

	public RestExecuter() {
		restTemplate = new RestTemplate();
	}

	public <T> T post(API_URLS apiUser, Object body, Class<T> responseClass, Object... uri) throws BusinessException {
		try {
			HttpHeaders httpHeaders = getHeaders(null);
			String bodyJson = objectMapper.writeValueAsString(body);
			HttpEntity<String> entity = new HttpEntity<String>(bodyJson, httpHeaders);

			ResponseEntity<T> response = execute(apiUser.getUrl(), HttpMethod.POST, entity, responseClass, uri);
			return response.getBody();

		} catch (JsonProcessingException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	private HttpHeaders getHeaders(String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		if(token!=null)
			httpHeaders.add("token", token);
		
		return httpHeaders;
	}

	public<T> T put(String token,API_URLS apiUser, Object body, Class<T> responseClass,Object ... uri) throws BusinessException {
		try {

			HttpHeaders httpHeaders = getHeaders(token);
			String bodyJson = objectMapper.writeValueAsString(body);
			HttpEntity<String> entity = new HttpEntity<String>(bodyJson, httpHeaders);

			ResponseEntity<T> response = execute(apiUser.getUrl(), HttpMethod.PUT, entity, responseClass, uri);
			return response.getBody();

		} catch (JsonProcessingException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	private <T> ResponseEntity<T> execute(String url, HttpMethod method, HttpEntity<String> entity, Class<T> response,
			Object... uri) throws BusinessException {
		try {

			ResponseEntity<T> responseBody = restTemplate.exchange(url, method, entity, response, uri);

			return responseBody;
		} catch (HttpStatusCodeException e) {
			String responseBodyAsString = e.getResponseBodyAsString();
			String message = null;
			try {
				ExceptionError readValue = objectMapper.readValue(responseBodyAsString, ExceptionError.class);
				message = readValue.getMessage();
			} catch (Exception a) {
				message = "";
			}
			throw new BusinessException(message);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public <T> T get(String token,API_URLS apiUrl,Class<T> responseClass, Object... uri) throws BusinessException {
		
		HttpEntity<String> entity = new HttpEntity<>(getHeaders(token));
		ResponseEntity<T> response = execute(apiUrl.getUrl(),HttpMethod.GET,entity, responseClass, uri);
		
		return response.getBody();
	}

}
