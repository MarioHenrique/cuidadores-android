package br.com.softcare.cuidadores.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import br.com.softcare.cuidadores.enuns.API_URLS;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import br.com.softcare.cuidadores.exceptions.ExceptionError;

public final class RestExecuter {

	private RestTemplate restTemplate;

	private ObjectMapper objectMapper = new ObjectMapper();

	public RestExecuter() {
		restTemplate = new RestTemplate();
	}

	public void delete(String token,API_URLS apiUser,Object ... params) throws BusinessException {
		final HttpHeaders headers = getHeaders(token);
		final HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		execute(apiUser.getUrl(),HttpMethod.DELETE,httpEntity,String.class,params);
	}

	public <T> T post(String token,API_URLS apiUser, Object body, Class<T> responseClass, Object... uri) throws BusinessException {
		try {
			HttpHeaders httpHeaders = getHeaders(token);
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
		httpHeaders.add("Content-Type","application/json;charset=utf-8");
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



	public <T> List<LinkedHashMap> getComplexList(String token, API_URLS apiUrls, Class<T> responseClass,Object ... param) throws BusinessException {
		final HttpHeaders headers = getHeaders(token);
		final HttpEntity<String> entity = new HttpEntity<>(headers);
		try{
			final ResponseEntity<String> result = restTemplate.exchange(apiUrls.getUrl(), HttpMethod.GET, entity,String.class,param);
			final List<LinkedHashMap> resultListMap = objectMapper.readValue(result.getBody(), new TypeReference<List<T>>() {
			});
			return resultListMap;
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

	public <T> List<T> getList(String token, API_URLS apiUrls, Class<T> responseClass,Object ... param) throws BusinessException {
		final HttpHeaders headers = getHeaders(token);
		final HttpEntity<String> entity = new HttpEntity<>(headers);
		try{
			final ResponseEntity<String> result = restTemplate.exchange(apiUrls.getUrl(), HttpMethod.GET, entity,String.class,param);
			final List<LinkedHashMap> resultListMap = objectMapper.readValue(result.getBody(), new TypeReference<List<T>>() {
			});
			return convertToList(resultListMap,responseClass);
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

	private <T> List<T> convertToList(List<LinkedHashMap> resultListMap, Class<T> responseClass) throws IllegalAccessException, InstantiationException {
		List<T> resultList = new ArrayList<>();
		for(LinkedHashMap item:resultListMap){
			final T t = responseClass.newInstance();
			final Field[] fields = responseClass.getDeclaredFields();
			for(int i = 0; i<fields.length;i++){
				fields[i].setAccessible(true);
				final JsonProperty annotation = fields[i].getAnnotation(JsonProperty.class);
				String nomeCampo = fields[i].getName();
				if(annotation!=null){
					nomeCampo = annotation.value();
				}
				if(!item.containsKey(nomeCampo))
					continue;
				String className =  fields[i].getType().getSimpleName();
				if(className.equals("Long")){
					fields[i].set(t,((Integer)item.get(nomeCampo)).longValue());
				}else if(className.equals("LinkedHashMap")) {
					final Class<? extends Field> aClass = fields[i].getClass();
				}else{
					fields[i].set(t, item.get(nomeCampo));
				}
			}
			resultList.add(t);
		}
		return resultList;
	}

	public <T> T get(String token,API_URLS apiUrl,Class<T> responseClass, Object... uri) throws BusinessException {
		
		HttpEntity<String> entity = new HttpEntity<>(getHeaders(token));
		ResponseEntity<T> response = execute(apiUrl.getUrl(),HttpMethod.GET,entity, responseClass, uri);
		
		return response.getBody();
	}

}
