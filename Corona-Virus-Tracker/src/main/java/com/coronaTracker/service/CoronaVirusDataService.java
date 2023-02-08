package com.coronaTracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coronaTracker.model.LocationStates;

import jakarta.annotation.PostConstruct;

@Service
public class CoronaVirusDataService {

	private List<LocationStates> allStates = new ArrayList<LocationStates>();

	public List<LocationStates> getAllStates() {
		return allStates;
	}

	public void setAllStates(List<LocationStates> allStates) {
		this.allStates = allStates;
	}
	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	
	@PostConstruct
	@Scheduled(cron = "* * * 1 * *")
	public void fetchVirusData()throws IOException,InterruptedException
	{
		List<LocationStates> newstates=new ArrayList<LocationStates>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpResp = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvBodyreader=new StringReader(httpResp.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyreader);
		for (CSVRecord record : records) 
		{
			LocationStates losta=new LocationStates();
		    losta.setStates(record.get("Province/State"));
		    losta.setCountry(record.get("Country/Region"));
		    int latestCase=Integer.parseInt(record.get(record.size()-1));
		    int PrevCase=Integer.parseInt(record.get(record.size()-2));
		    losta.setTotalDeaths(latestCase);
		    losta.setChangesLastDay(latestCase-PrevCase);
		    System.out.println(losta);
		    
		    newstates.add(losta);
		    
		}
		this.allStates=newstates;
			
		
	}
}
