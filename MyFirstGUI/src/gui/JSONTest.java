package gui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import data.Person;

public class JSONTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Writer writer = new FileWriter("C:\\temp\\output.json");
			Gson gson = new GsonBuilder().serializeNulls().create();
			gson.toJson("Hallo", writer);
			gson.toJson(12345, writer);
			//
			Person p = new Person();
			p.setVorname("Max");
			p.setNachname("Mustermann");
			//
			gson.toJson(p, writer);
			//
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
