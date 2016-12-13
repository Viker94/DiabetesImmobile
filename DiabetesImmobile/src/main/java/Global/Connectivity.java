package Global;

import Model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fishe on 26.11.2016.
 */
public class Connectivity {
    private HttpClient client;
    private String address;
    private ObjectMapper mapper;

    public Connectivity(String address){
        this.client = HttpClientBuilder.create().build();
        this.address = address;
        this.mapper = new ObjectMapper();

    }


    public List<Product> getProducts() throws IOException {
        //TO NIE ZADZIAłA, SPRAWDŹ JAK DEKODOWANE JEST getNurses(), tak działa
        List<Product> products = null;
        Product tmp = null;
        HttpGet getRequest = new HttpGet(
                this.address+"/products");
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = client.execute(getRequest);
        String json = null;
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while((json = br.readLine())!=null){
            tmp = mapper.readValue(json,Product.class);
            products.add(tmp);
        }
        restart();
        return products;
    }

    public void addNurse(String imie,String nazwisko,String login,String haslo) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/nurse/"+imie+"/"+nazwisko+"/"+login+"/"+haslo);
        client.execute(postRequest);
        restart();
    }

    public void resetPolaczenPiguly(long id) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/nurseDelPatients/"+id);
        client.execute(postRequest);
        restart();
    }

    public void addUser(String imie,String nazwisko,String login,String haslo) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/userAdd/"+login+"/"+haslo+"/"+imie+"/"+nazwisko);
        client.execute(postRequest);
        restart();
    }
    public void editUser(Long id,String imie,String nazwisko,String login,String haslo) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/userEdit/"+id+"/"+imie+"/"+nazwisko+"/"+login+"/"+haslo);
        client.execute(postRequest);
        restart();
    }
    public void editNurse(Long id,String imie,String nazwisko,String login,String haslo) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/nurseEdit/"+id+"/"+imie+"/"+nazwisko+"/"+login+"/"+haslo);
        client.execute(postRequest);
        restart();
    }

    public void deleteUser(long id) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/userDel/"+id);
        client.execute(postRequest);
        restart();
    }
    public void nurseDel(long id) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/nurseDel/"+id);
        client.execute(postRequest);
        restart();
    }

    public List<NursesForTable> getNurses() throws IOException {
        List<Nurse> nurses = null;
        List<NursesForTable> ret = new ArrayList<NursesForTable>();
        HttpGet getRequest = new HttpGet(
                this.address+"/nurse");
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = client.execute(getRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }
        String json = "";
        String tmp = null;
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while((tmp=br.readLine())!=null){
            json+=tmp;
        }
        nurses = mapper.readValue(json,new TypeReference<List<Nurse>>(){});
        NursesForTable temp = null;
        for(int i=0;i<nurses.size();i++){
            if(nurses.get(i).getAdmin()==false){
                temp = new NursesForTable(nurses.get(i).getId(),nurses.get(i).getFirstName(),nurses.get(i).getLastName(),
                nurses.get(i).getLogin(),nurses.get(i).getPassword(),0);
            }
            ret.add(temp);

        }
        ret.remove(0);
        //Przypisywanie liczby pacjentów
        long id;
        restart();
        for(int i=0;i<ret.size();i++){
            id = ret.get(i).getId();
            getRequest = new HttpGet(
                    this.address+"/nurseNumPatients/"+id);
            getRequest.addHeader("accept", "application/json");
            response = client.execute(getRequest);
            br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            Size num = mapper.readValue(br.readLine(),Size.class);
            ret.get(i).setLiczbaPacjentow(num.getSize());
            restart();
        }

        /////////////////////////////////
        restart();
        return ret;
    }

    public List<UsersForTable> getPatients() throws IOException { //zwraca liste pacjentów
        List<User> patients = null;
        List<UsersForTable> ret = new ArrayList<UsersForTable>();
        HttpGet getRequest = new HttpGet(
                this.address+"/users");
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = client.execute(getRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        String json = "";
        String tmp = null;
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while((tmp=br.readLine())!=null){
            json+=tmp;
        }

        patients = mapper.readValue(json,new TypeReference<List<User>>(){});
        restart();
        UsersForTable temp = null;
        for(int i=0;i<patients.size();i++){
            temp = new UsersForTable(patients.get(i).getId(),patients.get(i).getFirstName(),
                    patients.get(i).getLastName(),patients.get(i).getLogin().getLogin(),patients.get(i).getLogin().getPasswd());
            ret.add(temp);
            restart();
        }

        return ret;
    }

    public Nurse checkLogin(String login, String passwd) throws IOException { //sprawdza istnienie konta
        HttpGet getRequest = new HttpGet(                                       //zwraca obiekt Login lub null
                this.address+"/nurseLogin/"+login+"/"+passwd);
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

        Nurse wynik = null;
        if(json!=null){
            wynik = mapper.readValue(json,Nurse.class);
        }
        restart();
        return wynik;
    }

    public void restart(){
        client.getConnectionManager().shutdown();
        client = HttpClientBuilder.create().build();
    }
}
