package api.rithsagea.atelier;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

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
	private MongoCollection<User> userdata;
	
	private CodecProvider codecProvider;
	private CodecRegistry codecRegistry;
	
	public AtelierDatabase(String url) {
		codecProvider = PojoCodecProvider.builder().automatic(true).build();
		codecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(codecProvider));
		
		mongoClient = MongoClients.create(url);
		database = mongoClient.getDatabase("atelier").withCodecRegistry(codecRegistry);
		userdata = database.getCollection("userdata", User.class);
	}
	
	public User findUser(long id) {
		
		User user = userdata.find(Filters.eq("_id", id)).first();
		if(user == null) {
			user = new User(id);
			updateUser(user);
		}
		
		return user;
	}
	
	public void updateUser(User user) {
		userdata.replaceOne(Filters.eq("_id", user.getId()), 
				user, new ReplaceOptions().upsert(true));
	}
}
