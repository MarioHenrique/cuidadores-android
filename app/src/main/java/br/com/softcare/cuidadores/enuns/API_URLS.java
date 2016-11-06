package br.com.softcare.cuidadores.enuns;

public enum API_URLS {

	API_CAREGIVE("https://lucapi-appsforfree.rhcloud.com:443/cuidadores-api/api/caregiver?"
	+"zipcode={zipcode}&district={district}&city={city}&street={street}&state={state}&"
	+ "email={email}&contact={contact}&availability={availability}&period={period}&page={page}&pageSize={pageSize}"),
	
	API_CAREGIVE_ID("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/caregiver/{id}"),
	API_CAREGIVE_SPECIALTY("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/caregiver/specialty"),
	API_CAREGIVE_SPECIALTY_ID("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/caregiver/specialty/{id}"),
	API_PATIENT("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/patient/"),
	API_PATIENT_ID("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/patient/{id}"),
	API_PATIENT_ID_TREATMENT("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/patient/{id}/treatment/"),
	API_PATIENT_ID_TREATMENT_ID("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/patient/{id}/treatment/{treatmentId}"),
	API_PROPOSAL("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/proposal"),
	API_PROPOSAL_STATUS("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/proposal/status"),
	API_PROPOSAL_ID("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/proposal/{id}"),
	API_PROPOSAL_ID_CAREGIVER("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/proposal/{id}/caregiver"),
	API_PROPOSAL_ID_RESPONSABLE("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/proposal/{id}/caregiver"),
	API_PROPOSAL_ID_PROCEDURE("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/proposal/{id}/procedure"),
	API_PROPOSAL_ID_PROCEDURE_ID("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/proposal/{id}/procedure/{procedureId}"),
	API_PROPOSAL_ID_SYMPTOM("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/proposal/{id}/symptom"),
	API_PROPOSAL_ID_SYMPTOM_ID("https://lucapi-appsforfree.rhcloud.com/cuidadores-api/api/proposal/{id}/symptom/{symptomId}"),
	API_USER("https://lucapi-appsforfree.rhcloud.com:443/cuidadores-api/api/user"),
	API_USER_LOGIN("https://lucapi-appsforfree.rhcloud.com:443/cuidadores-api/api/user/login");
	
	private String url;

	private API_URLS(String url){
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
}
