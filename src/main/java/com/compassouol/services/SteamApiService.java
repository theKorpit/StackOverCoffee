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

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SteamApiService {

	private Integer appIdSteam;
	private String nomeJogo, desenvolvedor,distribuidora;
	private String dataLancamento, categoria,descricao;
	private Double valorDeVenda;
	private JSONParser parser = new JSONParser();
	SteamConnection steamCon = new SteamConnection();

	public SteamApiService(String nomeJogo) throws IOException, ParseException {
		this.nomeJogo = nomeJogo;
		this.appIdSteam = this.jogoPorNome(nomeJogo);
		this.getInfo();
	}

	public SteamApiService(int appIdSteam) throws IOException, ParseException {
		this.appIdSteam = appIdSteam;
		this.nomeJogo = this.jogoPorId(appIdSteam);
		this.getInfo();
	}

	public Jogo retornaJogo() {
		Jogo jogo = new Jogo(this.appIdSteam, this.nomeJogo, this.desenvolvedor, this.distribuidora,this.dataLancamento, this.categoria, this.valorDeVenda, this.descricao);
		return jogo;
	}

	public String jogoPorId(int appId) throws IOException, ParseException {

		JSONArray jsonEntries = getAppsField();

		for (int i = 0; i < jsonEntries.size(); i++) {
			JSONObject o = (JSONObject) jsonEntries.get(i);
			Long idApp = (Long) o.get("appid");

			if (idApp == appId) {
				String saida = (String) o.get("name");
				return saida;
			}
		}

		throw new JogoInvalidoException("ID invalido", this.appIdSteam);
	}

	public Integer jogoPorNome(String jogoNome) throws IOException, ParseException {

		JSONArray jsonEntries = getAppsField();

		for (int i = 0; i < jsonEntries.size(); i++) {
			JSONObject o = (JSONObject) jsonEntries.get(i);
			String nomeJogo = (String) o.get("name");

			if (nomeJogo.equalsIgnoreCase(jogoNome)) {
				String oh = o.get("appid").toString();
				int id = Integer.parseInt(oh);
				return id;
			}
		}

		throw new JogoInvalidoException("Nome de jogo invalido", this.nomeJogo);
	}

	private void getInfo() throws IOException, ParseException {

		Object obj = parser.parse(steamCon.getThisGame(this.appIdSteam));
		JSONObject Job = (JSONObject) obj;
		JSONObject Job2 = (JSONObject) Job.get(Long.toString(this.appIdSteam));
		Job = (JSONObject) Job2.get("data");

		if (Job == null)
			throw new JogoInvalidoException("Entrada invalida, isso e um jogo de testes da steam", this.appIdSteam);

		String saida = Job.get("type").toString();
		if (saida.equals("game")) {
			this.descricao = (String) Job.get("short_description");
			JSONArray array = (JSONArray) Job.get("categories");

			if (array == null) {
				throw new JogoInvalidoException("isso e um software e nao um jogo", this.appIdSteam);
			}

			String cat = array.get(0).toString();
			cat = cat.substring((cat.indexOf(":\"") + 2), (cat.indexOf("\",")));
			this.categoria = cat + ",";
			array = (JSONArray) Job.get("genres");
			for (int i = 0; i < array.size(); i++) {
				Job = (JSONObject) array.get(i);
				if (i == array.size() - 1)
					this.categoria += ((String) Job.get("description"));
				else
					this.categoria += ((String) Job.get("description") + ",");
			}

			Job = (JSONObject) Job2.get("data");
			this.desenvolvedor = limparLixo(Job.get("developers").toString());
			this.distribuidora = limparLixo(Job.get("publishers").toString());

			String data = Job.get("release_date").toString();
			this.dataLancamento = limparLixo(data.substring((data.indexOf("date")) + 7, (data.indexOf("date")) + 19).replace(",", ""));
			
			try {
				this.valorDeVenda = Double.valueOf(gettingGameValue(Job.get("price_overview").toString()));

			} catch (Exception e) {
				this.valorDeVenda = 0.0;
			}

		} else {
			throw new JogoInvalidoException("isso nao e um jogo e um/uma " + saida, this.appIdSteam);
		}
	}

	private String gettingGameValue(String val) {
		return val.substring((val.indexOf(":") + 2) + 3, (val.indexOf("\","))).replace(",", ".");
	}

	private String limparLixo(String string) {
		return string.replace("\"", "").replace("[", "").replace("]", "");
	}

	private JSONArray getAppsField() throws IOException, ParseException {
		Object obj = parser.parse(steamCon.getAllGames());
		JSONObject jsonObject = (JSONObject) obj;

		jsonObject = (JSONObject) jsonObject.get("applist");
		JSONArray jsonEntries = (JSONArray) jsonObject.get("apps");
		return jsonEntries;
	}

}