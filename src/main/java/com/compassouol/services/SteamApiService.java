package com.compassouol.services;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compassouol.connection.SteamConnection;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;

@Service 
public class SteamApiService {

	@Autowired
	private SteamConnection steamCon;
	
	Jogo jogo = new Jogo();

	public Jogo retornaJogo(Integer appIdSteam,String nomeJogo) throws IOException, ParseException {
		if(appIdSteam==null) {
			this.jogo.setNomeJogo(nomeJogo);
			this.jogo.setAppIdSteam(this.jogoPorNome(nomeJogo));
			this.getInfo();
		}else {
			this.jogo.setAppIdSteam(appIdSteam);
			this.jogo.setNomeJogo(jogoPorId(appIdSteam));
			this.getInfo();
		}
		return jogo;
	}

	public String jogoPorId(int appId) throws IOException, ParseException {

		JSONArray jsonEntries = getAppsField();

		for (int i = 0; i < jsonEntries.size(); i++) {
			JSONObject o = (JSONObject) jsonEntries.get(i);
			Long idApp = (Long) o.get("appid");

			if (idApp == appId) 
				return (String) o.get("name");
			
		}

		throw new JogoInvalidoException(appId);
	}

	public Integer jogoPorNome(String jogoNome) throws IOException, ParseException {

		JSONArray jsonEntries = getAppsField();

		for (int i = 0; i < jsonEntries.size(); i++) {
			JSONObject o = (JSONObject) jsonEntries.get(i);
			String nomeJogo = (String) o.get("name");

			if (nomeJogo.equalsIgnoreCase(jogoNome)) 
				return Integer.parseInt(o.get("appid").toString());
			
		}

		throw new JogoInvalidoException(jogoNome);
	}

	private void getInfo() throws IOException, ParseException {

		Object obj = new JSONParser().parse(steamCon.getThisGame(jogo.getAppIdSteam()));
		JSONObject Job = (JSONObject) obj;
		JSONObject Job2 = (JSONObject) Job.get(Long.toString(jogo.getAppIdSteam()));
		Job = (JSONObject) Job2.get("data");

		if (Job == null)
			throw new JogoInvalidoException(jogo.getAppIdSteam());

		String saida = Job.get("type").toString();
		if (saida.equals("game")) {
			jogo.setDescricao((String) Job.get("short_description"));
			JSONArray array = (JSONArray) Job.get("categories");

			if (array == null) {
				throw new JogoInvalidoException( jogo.getAppIdSteam());
			}

			String cat = array.get(0).toString();
			cat = cat.substring((cat.indexOf(":\"") + 2), (cat.indexOf("\",")));
			jogo.setCategoria(cat + ",");
			array = (JSONArray) Job.get("genres");
			for (int i = 0; i < array.size(); i++) {
				Job = (JSONObject) array.get(i);
				if (i == array.size() - 1)
					jogo.setCategoria(jogo.getCategoria() + (String) Job.get("description"));
				else
					jogo.setCategoria(jogo.getCategoria() + (String) Job.get("description") + ",");
			}

			Job = (JSONObject) Job2.get("data");
			jogo.setDesenvolvedor(limparLixo(Job.get("developers").toString()));
			jogo.setDistribuidora(limparLixo(Job.get("publishers").toString()));

			String data = Job.get("release_date").toString();
			jogo.setDataLancamento(limparLixo(data.substring((data.indexOf("date")) + 7, (data.indexOf("date")) + 19).replace(",", "")));
			try {
				jogo.setValorDeVenda(Double.valueOf(gettingGameValue(Job.get("price_overview").toString()))); 

			} catch (Exception e) {
				jogo.setValorDeVenda(0.0);
			}

		} else {
			throw new JogoInvalidoException( jogo.getAppIdSteam());
		}
	}

	private String gettingGameValue(String val) {
		return val.substring((val.indexOf(":") + 2) + 3, (val.indexOf("\","))).replace(",", ".");
	}

	private String limparLixo(String string) {
		return string.replace("\"", "").replace("[", "").replace("]", "");
	}

	private JSONArray getAppsField() throws IOException, ParseException {
		Object obj = new JSONParser().parse(steamCon.getAllGames());
		JSONObject jsonObject = (JSONObject) obj;

		jsonObject = (JSONObject) jsonObject.get("applist");
		JSONArray jsonEntries = (JSONArray) jsonObject.get("apps");
		return jsonEntries;
	}

}