package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 50;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	private MeteoDAO dao= new MeteoDAO();
	
	public Model() {

	}

	public String getUmiditaMedia(int mese) {
		String result="";
		double avg=0.0;
		ArrayList<String> rilPerMese = new ArrayList<String>();
		rilPerMese=(ArrayList<String>)dao.getCitta(mese);
		for(String citta : rilPerMese){
			avg=dao.getAvgRilevamentiLocalitaMese(mese, citta);
			result+="L'umidità media nella città di "+citta+" il mese "+mese+" è di "+avg+"\n";
		}
		return result;
	}

	public String trovaSequenza(int mese) {

		return "TODO!";
	}

	private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {

		
		double score = 0.0;
		return score;
	}

	private boolean controllaParziale(List<SimpleCity> parziale) {

		return true;
	}

}
