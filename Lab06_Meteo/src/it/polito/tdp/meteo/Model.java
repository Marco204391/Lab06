package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
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
		ArrayList<SimpleCity> parziale = new ArrayList<SimpleCity>();
		ArrayList<SimpleCity> best = new ArrayList<SimpleCity>();
		int visitata[]= new int[dao.getCitta(mese).size()];
		
		recursive(parziale, best, 0, mese, visitata);
		return "TODO!";
	}

	public void recursive(ArrayList<SimpleCity> parziale, ArrayList<SimpleCity> best, int step, int mese, int visitata[]){
		
		if(step==15){
			if(punteggioSoluzione(parziale)<punteggioSoluzione(best))
				best.clear();
				best.addAll(parziale);
		}
		if(parziale.isEmpty())
		{
			for( int i=0; i<dao.getCitta(mese).size(); i++){
				parziale.add(new SimpleCity(dao.getCitta(mese).get(i)));
				visitata[i]=1;
				recursive(parziale, best, step+1, mese, visitata);
				parziale.clear();
				visitata[i]=0;
			}
		}
		else{
			SimpleCity c= parziale.get(parziale.size()-1);
			if(c.getPermanenza()<3){
				c.incrementaPermanenza();
				recursive(parziale, best, step+1, mese, visitata);
			}
			else if(c.getPermanenza()<6){
				c.incrementaPermanenza();
				recursive(parziale, best, step+1, mese, visitata);
				c.decrementaPermanenza();
				for(int i=0; i<visitata.length; i++){
					if(visitata[i]==0){
						SimpleCity nuovaCity = new SimpleCity(dao.getCitta(mese).get(i)); 
						parziale.add(nuovaCity);
						visitata[i]=1;
						recursive(parziale, best, step+1, mese, visitata);
						parziale.remove(nuovaCity);
						visitata[i]=0;
					}
				}
			}
		}
	}
	private Double punteggioSoluzione(List<SimpleCity> parziale) {
		double score = 0.0;
		
		for( int i=0; i<parziale.size(); i++){
			score+=parziale.get(i).getCosto();
			if(parziale.get(i+1).getNome().compareTo(parziale.get(i).getNome())!=0)
			{
				score+=100;
			}
		}
		return score;
	}

//	private boolean controllaParziale(List<SimpleCity> parziale) {
//
//		return true;
//	}

}
