package uy.edu.fing.tse.core.reservas.dao.impl;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import uy.edu.fing.tse.core.reservas.dto.ReservaDTO;
import uy.edu.fing.tse.core.reservas.dto.SolicitudDTO;

public class MongoConnection {

	private static final String HOST = "node1506-reservas07.web.elasticloud.uy";

	private static final String USER = "reservas";

	private static final String PASS = "r3s3rv45";

	private static final String DB_NAME = "solicitudes";

	private static final String SOLICITUDES_COLLECTION = "solicitudes";

	private static final String RESERVAS_COLLECTION = "reservas";


	private static MongoConnection instance;

	private MongoClient client;

	public static MongoConnection getInstance() {
		if(instance == null) {
			instance = new MongoConnection();
		}

		return instance;
	}


	private MongoConnection() {
		this.initClient();
	}


	private void initClient() {
		MongoCredential credential = MongoCredential.createCredential(USER, DB_NAME, PASS.toCharArray());
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);

		this.client = new MongoClient(new ServerAddress(HOST), credential,
				MongoClientOptions.builder()
				.sslEnabled(false)
				.codecRegistry(codecRegistry)
				.build());
	}

	private MongoDatabase getDatabase() {
		try {
			return this.client.getDatabase(DB_NAME);
		} catch(Exception ex) {
			this.initClient();
			throw ex;
		}
	}

	public MongoCollection<SolicitudDTO> getSolicitudesCollection(){
		try {
			return this.getDatabase().getCollection(SOLICITUDES_COLLECTION, SolicitudDTO.class);
		}catch(Exception ex) {
			this.initClient();
			throw ex;
		}
	}

	public MongoCollection<ReservaDTO> getReservasCollection(){
		try {
			return this.getDatabase().getCollection(RESERVAS_COLLECTION, ReservaDTO.class);
		}catch(Exception ex) {
			this.initClient();
			throw ex;
		}
	}


}
