package com.compassouol.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.stereotype.Service;
@Service
public class SteamConnection {
	
	private URL url;
	private InputStream is;

	public BufferedReader getAllGames() throws IOException {
		this.url = new URL("https://api.steampowered.com/ISteamApps/GetAppList/v2/");
		this.is = url.openConnection().getInputStream();
		return new BufferedReader(new InputStreamReader(this.is));
	}
	
	public BufferedReader getThisGame(Integer appId) throws IOException {
		this.url = new URL("https://store.steampowered.com/api/appdetails/l=portuguese&?appids=" + appId);
		this.is = url.openConnection().getInputStream();
		return new BufferedReader(new InputStreamReader(this.is));
	}	
}