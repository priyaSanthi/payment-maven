package main.payment;

import java.io.IOException;
import com.google.gson.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class Application {

	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		Request request = requestMapping(mediaType);
		System.out.println(request);
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonElement je = JsonParser.parseString(response.body().string());
			String prettyJsonString = gson.toJson(je);
			System.out.println("Response::::::::\n" +prettyJsonString);
		}
	}

	private static Request requestMapping(MediaType mediaType) {
		RequestBody body = fetchReuestBody(mediaType);
		Request request = new Request.Builder().url("https://api.razorpay.com/v1/payment_links/").method("POST", body)
				.addHeader("Content-type", "application/json")
				.addHeader("Authorization", "Basic cnpwX3Rlc3RfaDJ6YU1JRExWTm1DQ0w6MWtMd1VLeXhtOWt6Z0VRV0J0VlhSTlk0")
				.build();
		return request;
	}

	private static RequestBody fetchReuestBody(MediaType mediaType) {
		// create your json here
		String bodyTxt = "{\r\n" + "  \"amount\": 1000,\r\n" + "  \"currency\": \"INR\",\r\n"
				+ "  \"accept_partial\": true,\r\n" + "  \"first_min_partial_amount\": 100,\r\n"
				+ "  \"expire_by\": 1691097057,\r\n" + "  \"reference_id\": \"TS1989\",\r\n"
				+ "  \"description\": \"Payment for policy no #23456\",\r\n" + "  \"customer\": {\r\n"
				+ "    \"name\": \"Gaurav Kumar\",\r\n" + "    \"contact\": \"+919999999999\",\r\n"
				+ "    \"email\": \"gaurav.kumar@example.com\"\r\n" + "  },\r\n" + "  \"notify\": {\r\n"
				+ "    \"sms\": true,\r\n" + "    \"email\": true\r\n" + "  },\r\n" + "  \"reminder_enable\": true,\r\n"
				+ "  \"notes\": {\r\n" + "    \"policy_name\": \"Jeevan Bima\"\r\n" + "  },\r\n"
				+ "  \"callback_url\": \"https://example-callback-url.com/\",\r\n"
				+ "  \"callback_method\": \"get\"\r\n" + "}";
		RequestBody body = RequestBody.create(mediaType, bodyTxt);
		return body;
	}

}
