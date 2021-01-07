package com.compassouol.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;

public class SteamAPI {

	private int appIdSteam;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private String dataLancamento;
	private String categoria;
	private double valorDeVenda;
	private String descricao;
	private URL url;
	private JSONParser parser;
	private InputStream is;

	public SteamAPI(String nomeJogo) throws IOException, ParseException {
		this.nomeJogo = nomeJogo;
		this.appIdSteam = this.IdPorNomeJogo(nomeJogo);
		System.out.println(this.appIdSteam);
		this.getInfo();
	}

	public SteamAPI(int appIdSteam) throws IOException, ParseException {
		this.appIdSteam = appIdSteam;
		this.nomeJogo = this.JogoPorAppid(appIdSteam);
		this.getInfo();
	}

	public Jogo retornaJogo() {
		Jogo jogo = new Jogo(this.nomeJogo,this.desenvolvedor,this.distribuidora,this.dataLancamento,
			this.categoria,this.valorDeVenda,this.appIdSteam,this.descricao);	
		return jogo;
	}
	

	public String JogoPorAppid(int appId) throws IOException, ParseException {

		parser = new JSONParser();
		url = new URL("https://api.steampowered.com/ISteamApps/GetAppList/v2/");
		is = url.openConnection().getInputStream();

		Object obj = parser.parse(new BufferedReader(new InputStreamReader(is)));
		JSONObject jsonObject = (JSONObject) obj;
		Object obj2 = jsonObject.get("applist");
		JSONObject jsonObject2 = (JSONObject) obj2;

		JSONArray jsonEntries = (JSONArray) jsonObject2.get("apps");

		for (int i = 0; i < jsonEntries.size(); i++) {
			JSONObject o = (JSONObject) jsonEntries.get(i);
			Long idApp = (Long) o.get("appid");
			if (idApp == appId) {
				String saida = (String) o.get("name");
				return saida;

			}
		}
		throw new JogoInvalidoException("ID invalido");

	}

	public Integer IdPorNomeJogo(String jogoNome) throws IOException, ParseException {
		parser = new JSONParser();
		url = new URL("https://api.steampowered.com/ISteamApps/GetAppList/v2/");
		is = url.openConnection().getInputStream();

		Object obj = parser.parse(new BufferedReader(new InputStreamReader(is)));
		JSONObject jsonObject = (JSONObject) obj;
		Object obj2 = jsonObject.get("applist");
		JSONObject jsonObject2 = (JSONObject) obj2;

		JSONArray jsonEntries = (JSONArray) jsonObject2.get("apps");

		for (int i = 0; i < jsonEntries.size(); i++) {
			JSONObject o = (JSONObject) jsonEntries.get(i);
			String nomeJogo = (String) o.get("name");
			if (nomeJogo.equalsIgnoreCase(jogoNome)) {
				String oh = o.get("appid").toString();
				int id = Integer.parseInt(oh);
				return id;

			}
		}
		throw new JogoInvalidoException("Nome de jogo invalido");
	}

	private void getInfo() throws IOException, ParseException {
		parser = new JSONParser();
		url = new URL("https://store.steampowered.com/api/appdetails/l=portuguese&?appids=" + this.appIdSteam);
		is = url.openConnection().getInputStream();

		Object obj = parser.parse(new BufferedReader(new InputStreamReader(is)));
		JSONObject Job = (JSONObject) obj;
		JSONObject Job2 = (JSONObject) Job.get(Long.toString(this.appIdSteam));
		Job = (JSONObject) Job2.get("data");
		this.descricao = (String) Job.get("short_description");
		JSONArray array = (JSONArray) Job.get("categories");
		String cat = array.get(0).toString();
		int o1 = cat.indexOf(":\"") + 2;
		int o = cat.indexOf("\",");
		cat = cat.substring(o1, o);
		this.categoria = cat + ",";
		JSONArray array2 = (JSONArray) Job.get("genres");
		for (int i = 0; i < array2.size(); i++) {
			Job = (JSONObject) array2.get(i);
			if (i == array2.size() - 1)
				this.categoria += ((String) Job.get("description"));
			else
				this.categoria += ((String) Job.get("description") + ",");
		}
		Job = (JSONObject) Job2.get("data");
		this.desenvolvedor = limparLixo(Job.get("developers").toString());
		this.distribuidora = limparLixo(Job.get("publishers").toString());
		String data = Job.get("release_date").toString();
		o = data.indexOf("date");
		data = data.substring(o + 7, o + 19);
		data = data.replace(",", "");
		this.dataLancamento = data;
		try {
			String val = Job.get("price_overview").toString();
			o = val.indexOf(":") + 2;
			o1 = val.indexOf("\",");
			val = val.substring(o + 3, o1);
			val = val.replace(",", ".");
			this.valorDeVenda = Double.valueOf(val);
		} catch (Exception e) {
			this.valorDeVenda = 0;
		}

	}

	private String limparLixo(String string) {
		string = string.replace("\"", "");
		string = string.replace("[", "");
		string = string.replace("]", "");
		return string;
	}

	@Override
	public String toString() {
		return "App id =" + this.appIdSteam + "\nNome do jogo = " + this.nomeJogo + "\nDesenvolvedora = "
				+ this.desenvolvedor + "\n" + "Distribuidora = " + this.distribuidora + "\nData de Lancamento = "
				+ this.dataLancamento + "\nCategorias = " + this.categoria + "" + "\nDescricao= " + this.descricao
				+ "\nValor de venda = " + this.valorDeVenda;

	}

	public void getValorDeVenda() {
		System.out.println(this.valorDeVenda + "é o valor do jogo com o id " + this.appIdSteam);
	}

}
