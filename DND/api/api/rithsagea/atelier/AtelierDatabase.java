package api.rithsagea.atelier;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodecProvider;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;

public class AtelierDatabase {
	
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<User> userData;
	private MongoCollection<CharacterSheet> sheetData;
	
	private CodecProvider codecProvider;
	private CodecRegistry codecRegistry;
	
	private static final ReplaceOptions UPSERT_OPTION = new ReplaceOptions().upsert(true);
	
	public AtelierDatabase(String url) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		
		codecProvider = PojoCodecProvider.builder().automatic(true).build();
		codecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(
						codecProvider,
						new UuidCodecProvider(UuidRepresentation.STANDARD)));
		
		mongoClient = MongoClients.create(url);
		database = mongoClient.getDatabase("atelier").withCodecRegistry(codecRegistry);
		userData = database.getCollection("userdata", User.class);
		sheetData = database.getCollection("sheetdata", CharacterSheet.class);
	}
	
	public User findUser(long id) {
		User user = userData.find(Filters.eq("_id", id)).first();
		if(user == null) {
			user = new User(id);
			updateUser(user);
		}
		
		return user;
	}
	
	public void updateUser(User user) {
		userData.replaceOne(Filters.eq("_id", user.getId()), 
				user, UPSERT_OPTION);
	}
	
	public Set<CharacterSheet> getSheets() {
		Set<CharacterSheet> sheets = new HashSet<>();
		sheetData.find().forEach((CharacterSheet sheet) -> sheets.add(sheet));
		
		return sheets;
	}
	
	public CharacterSheet findSheet(ObjectId id) {
		return sheetData.find(Filters.eq("_id", id)).first();
	}
	
	public void updateSheet(CharacterSheet sheet) {
		sheetData.replaceOne(Filters.eq("_id", sheet.getId()),
				sheet, UPSERT_OPTION);
	}
}
