package it.polito.tdp.meteo.db;

import java.util.List;

import it.polito.tdp.meteo.bean.Rilevamento;

public class TestDAO {

	public static void main(String[] args) {

		MeteoDAO dao = new MeteoDAO();

//		List<Rilevamento> list = dao.getAllRilevamenti();
//
//		// STAMPA: localita, giorno, mese, anno, umidita (%)
//		for (Rilevamento r : list) {
//			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
//		}
		List<Rilevamento> lost = dao.getAllRilevamentiLocalitaMese(05, "Genova");

		// STAMPA: localita, giorno, mese, anno, umidita (%)
		for (Rilevamento r : lost) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
		Double tmp= dao.getAvgRilevamentiLocalitaMese(03, "Genova");
		System.out.println(tmp);
//		System.out.println(dao.getAllRilevamentiLocalitaMese(1, "Genova"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(1, "Genova"));
//		
//		System.out.println(dao.getAllRilevamentiLocalitaMese(5, "Milano"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Milano"));
//		
//		System.out.println(dao.getAllRilevamentiLocalitaMese(5, "Torino"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Torino"));
		
	}

}
