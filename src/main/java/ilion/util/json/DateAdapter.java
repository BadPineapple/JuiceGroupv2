package ilion.util.json;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
	
	List<DateFormat> dateFormats;
	
    public DateAdapter() {
        dateFormats = new ArrayList<DateFormat>();
        dateFormats.add( new SimpleDateFormat("dd/MM/yyyy") );
        dateFormats.add( new SimpleDateFormat("MM/dd/yy hh:mm:ss a"));
        dateFormats.add( new SimpleDateFormat("MM/dd/yy") );
        dateFormats.add( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'") );
        dateFormats.add( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ss'Z'") );
        dateFormats.add( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssz") );
        dateFormats.add( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ssz") );
        dateFormats.add( DateFormat.getDateTimeInstance() );
        dateFormats.add(DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ) );
        dateFormats.add(DateFormat.getDateTimeInstance( DateFormat.MEDIUM, DateFormat.MEDIUM ) );
        dateFormats.add(DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.SHORT ) );
        dateFormats.add( new SimpleDateFormat("EEE MMM d hh:mm:ss a z yyyy") );
        dateFormats.add( new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy") );
    }
	
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		if(src == null) {
			return JsonNull.INSTANCE;
		}
		return new JsonPrimitive(src.getTime());
	}
	
	public Date deserialize(JsonElement json, Type typeOfT,	JsonDeserializationContext context) throws JsonParseException {
		if(json.isJsonNull()) {
			return null;
		}
		
		if( ! json.isJsonPrimitive() ) {
			throw new JsonParseException("Invalid type for Date!");
		}
		
		try {
			return new Date(json.getAsLong());
		} catch (Exception e) {
		}
		
		for( DateFormat format : dateFormats ) {
            try {
                return format.parse( json.getAsString() );
            } catch (ParseException e) {
                // try next format
            }
        }
		
		throw new JsonParseException("Invalid type for Date! "+json.getAsString());
	}

//	public static void main(String[] args) {
//		
//		Gson gson = new GsonBuilder()
//		.registerTypeAdapter(Date.class, new DateAdapter())
//        .create();
//		//String json = gson.toJson(new Date());
//		//System.out.println(json);
//		
//		List<String> jsons = Arrays.asList(
//				"1411593074081",
//				"2014-10-10T08:10:11.293Z"
//				);
//		
//		for (String json : jsons) {
//			try {
//				System.out.println(gson.fromJson(json, Date.class));
//			} catch (Exception e) {
//				System.out.println("ERRO: "+json+" "+e.getMessage());
//			}
//		}
//		
//		//System.out.println(gson.fromJson("2014-10-10T08:10:11.293Z", Date.class));
//		
//	}
}