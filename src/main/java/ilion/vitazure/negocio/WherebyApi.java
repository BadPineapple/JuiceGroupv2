package ilion.vitazure.negocio;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.Profissional;

@Service
public class WherebyApi {
	
	 @Autowired
	 private PropNegocio propNegocio;

	public void gerarLinkAtendimentoOnline(Profissional profissional , Agenda agenda) throws JSONException {
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		String API_KEY = propNegocio.findValueById(PropEnum.API_KEY_WHEREBY);
		String URL_API = propNegocio.findValueById(PropEnum.URL_API_WHEREBY);

		SimpleDateFormat sdfData = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");

		//Formatação padrão da data yyyy-MM-ddThh-mm-ss.000Z
		String startDate = sdfData.format(new Date(agenda.getDataHoraAgendamento().getTime()));
		String startHour = sdfHora.format(new Date(agenda.getDataHoraAgendamento().getTime()));

		String endDate = sdfData.format(new Date(agenda.getDataHoraAgendamento().getTime()));
		String endHour = sdfHora.format(new Date(agenda.getDataHoraAgendamento().getTime() + (Integer.parseInt(profissional.getDuracaoAtendimento().getNome()) * 60 * 1000)));

		try {
			HttpPost requestJson = new HttpPost(URL_API);
			
			StringEntity params = new StringEntity("{\"isLocked\": true,\"roomNamePattern\": \"human-short\", \"startDate\": \"" + startDate + "T" + startHour + ".000Z" + "\", \"endDate\": " +
					" \"" + endDate + "T" + endHour + ".000Z" + "\"," +
					" \"fields\": [\"hostRoomUrl\"]}");
			
			requestJson.addHeader("authorization", "Bearer " + API_KEY);
			requestJson.addHeader("content-type", "application/json");
			requestJson.setEntity(params);

			HttpResponse responseJson = httpClient.execute(requestJson); //Possíveis retornos "200" OK "401" Autenticação incorreta

			String responseString = EntityUtils.toString(responseJson.getEntity());
			
			if (responseJson.getStatusLine().getStatusCode() == 201) {
				JSONObject json = new JSONObject(responseString);
				String configuracaoSalaAtendimento = "?embed&logo=on&?chat=on&?people=off&?leaveButton=on&?lang=pt&?floatSelf";
				String roomUrl = json.get("roomUrl").toString();
				String roomHostUrl = json.get("hostRoomUrl").toString();
				agenda.setUrlAtendimentoOnline(roomUrl.concat(configuracaoSalaAtendimento));
				agenda.setHostUrlAtendimentoOnline(roomHostUrl.concat(configuracaoSalaAtendimento));
				agenda.setMeetingId(json.get("meetingId").toString());
				agenda.setHoraFinalAtendimento(endHour);
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
}
