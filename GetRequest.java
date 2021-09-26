import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetRequest {
	static String sURL = "https://speller.yandex.net/services/spellservice";
	public static void main(String[] args) {
	    // TODO Auto-generated method stub
		CloseableHttpResponse resp1 = null;
		try {
		     CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(sURL + "/checkText?text=програмный+интерейс+прилложения");
			
			resp1 = httpClient.execute(httpGet);
			
			System.out.println(EntityUtils.toString (resp1.getEntity()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resp1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
}
