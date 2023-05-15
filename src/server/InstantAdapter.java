package server;

import java.io.IOException;
import java.time.Instant;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Instant adapter.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class InstantAdapter extends TypeAdapter<Instant> {
//	DateTimeFormatter fmt = DateTimeFormatter.ISO_INSTANT;

	@Override
	public void write(JsonWriter jsonWriter, Instant instant) throws IOException {
		if (instant == null) {
			jsonWriter.value("null");
			return;
		}
		jsonWriter.value(instant.toString());
	}

	@Override
	public Instant read(JsonReader jsonReader) throws IOException {
		final String text = jsonReader.nextString();
		if (text.equals("null")) {
			return null;
		}
		return Instant.parse(text);
	}
}
