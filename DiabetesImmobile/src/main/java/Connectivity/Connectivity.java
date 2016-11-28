package Connectivity;

import Model.Login;
import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by fishe on 26.11.2016.
 */
public class Connectivity {
    private HttpClient client;
    private String address;

    public Connectivity(String address){
        this.client = HttpClientBuilder.create().build();
        this.address = address;


    }
    public List<User> getPatients() throws IOException { //zwraca użytkowników ze statusem pacjenta,
        List<User> patients = null;                     //żeby wypełnic liste wyboru do podglądu dla piguły
        HttpGet getRequest = new HttpGet(
                this.address+"/login/");
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = client.execute(getRequest);

        HttpGet userRequest;
        HttpResponse userResponse;

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }
        String json = null;
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        BufferedReader br2;
        Login tmpLogin = null;
        User tmpUser = null;
        ObjectMapper mapper = new ObjectMapper();
        while((json = br.readLine()) != null){
            tmpLogin = mapper.readValue(json,Login.class);
            if(tmpLogin.getType()==1){
                userRequest = new HttpGet(
                        this.address+"/user/"+tmpLogin.getLogin());
                userRequest.addHeader("accept", "application/json");
                userResponse = client.execute(userRequest);
                br2 = new BufferedReader(
                        new InputStreamReader((userResponse.getEntity().getContent())));
                json = br2.readLine();
                tmpUser = mapper.readValue(json,User.class);
                patients.add(tmpUser);
            }
        }
        return patients;
    }
    public Login checkLogin(String login, String passwd) throws IOException { //sprawdza istnienie konta
        HttpGet getRequest = new HttpGet(                                       //zwraca obiekt Login lub null
                this.address+"/login/"+login+"/"+passwd);
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = client.execute(getRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }
        String json = null;
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        json = br.readLine();

        Login wynik = null;
        ObjectMapper mapper = new ObjectMapper();
        if(json!=null){
            wynik = mapper.readValue(json,Login.class);
        }
        return wynik;
    }

    public void close(){
        client.getConnectionManager().shutdown();
    }
}
