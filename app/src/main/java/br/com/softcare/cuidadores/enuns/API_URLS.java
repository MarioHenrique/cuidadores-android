package br.com.softcare.cuidadores.enuns;

public enum API_URLS {

	API_CAREGIVE("https://caregiver-api.herokuapp.com/api/caregiver?"
	+"zipcode={zipcode}&district={district}&city={city}&street={street}&state={state}&"
	+ "email={email}&contact={contact}&availability={availability}&period={period}&page={page}&pageSize={pageSize}"),
	
	API_CAREGIVE_ID("https://caregiver-api.herokuapp.com/api/caregiver/{id}"),
	API_CAREGIVE_SPECIALTY("https://caregiver-api.herokuapp.com/api/caregiver/specialty"),
	API_CAREGIVE_SPECIALTY_ID("https://caregiver-api.herokuapp.com/api/caregiver/specialty/{id}"),
	API_PATIENT("https://caregiver-api.herokuapp.com/api/patient/"),
	API_PATIENT_ID("https://caregiver-api.herokuapp.com/api/patient/{id}"),
	API_PATIENT_ID_TREATMENT("https://caregiver-api.herokuapp.com/api/patient/{id}/treatment/"),
	API_PATIENT_ID_TREATMENT_ID("https://caregiver-api.herokuapp.com/api/patient/{id}/treatment/{treatmentId}"),
	API_PROPOSAL("https://caregiver-api.herokuapp.com/api/proposal"),
	API_PROPOSAL_STATUS("https://caregiver-api.herokuapp.com/api/proposal/status"),
	API_PROPOSAL_ID("https://caregiver-api.herokuapp.com/api/proposal/{id}"),
	API_PROPOSAL_ID_CAREGIVER("https://caregiver-api.herokuapp.com/api/proposal/{id}/caregiver"),
	API_PROPOSAL_ID_RESPONSABLE("https://caregiver-api.herokuapp.com/api/proposal/{id}/caregiver"),
	API_PROPOSAL_ID_PROCEDURE("https://caregiver-api.herokuapp.com/api/proposal/{id}/procedure"),
	API_PROPOSAL_ID_PROCEDURE_ID("https://caregiver-api.herokuapp.com/api/proposal/{id}/procedure/{procedureId}"),
	API_PROPOSAL_ID_SYMPTOM("https://caregiver-api.herokuapp.com/api/proposal/{id}/symptom"),
	API_PROPOSAL_ID_SYMPTOM_ID("https://caregiver-api.herokuapp.com/api/proposal/{id}/symptom/{symptomId}"),
	API_USER("https://caregiver-api.herokuapp.com/api/user"),
	API_USER_LOGIN("https://caregiver-api.herokuapp.com/api/user/login");
	
	private String url;

	private API_URLS(String url){
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
}
