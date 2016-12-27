package Global;

import Model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Connectivity {
    private HttpClient client;
    private String address;
    private ObjectMapper mapper;

    public Connectivity(String address){
        this.client = HttpClientBuilder.create().build();
        this.address = address;
        this.mapper = new ObjectMapper();

    }

    public void refreshSingleNurse(NursesForTable nurse) throws IOException { //odświeża lokalną kopię pielęgniarki zapisaną w Commons
        if(nurse!=null){
            Nurse temp = null;
            HttpGet getRequest = new HttpGet(                                       //zwraca obiekt Login lub null
                    this.address + "/nurseLogin/" + nurse.getLogin() + "/" + nurse.getPassword());
            getRequest.addHeader("accept", "application/json");
            HttpResponse response = client.execute(getRequest);

            String json = null;
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            json = br.readLine();
            if (json != null) {
                temp = mapper.readValue(json, Nurse.class);
            }
            restart();
            nurse.setLastName(temp.getLastName());
            nurse.setFirstName(temp.getFirstName());
            nurse.setLogin(temp.getLogin());
            nurse.setPassword(temp.getPassword());
            nurse.setLiczbaPacjentow(temp.getPatients().size());
        }
    }

    public void refreshSingleUser(UsersForTable user) throws IOException { //odświeża lokalną kopię usera zapisaną w Commons
        if(user!=null) {
            User temp = null;
            HttpGet getRequest = new HttpGet(                                       //zwraca obiekt Login lub null
                    this.address + "/user/" + user.getId());
            getRequest.addHeader("accept", "application/json");
            HttpResponse response = client.execute(getRequest);

            String json = null;
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            json = br.readLine();
            if (json != null) {
                temp = mapper.readValue(json, User.class);
            }
            restart();
            user.setNextVisit(temp.getNextVisit());
            user.setConsumed(temp.getConsumed());
            user.setFirstName(temp.getFirstName());
            user.setLastName(temp.getLastName());
            user.setLimitPotassium(temp.getLimitPotassium());
            user.setLimitSodium(temp.getLimitSodium());
            user.setLimitWater(temp.getLimitWater());
            user.setWater(temp.getWater());
            user.setSodium(temp.getSodium());
            user.setPotassium(temp.getPotassium());
        }
    }

    public void newVisit(long id,Date date) throws IOException {
        String formattedDate = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate();
        HttpPost postRequest = new HttpPost(
                this.address+"/userVisit/"+id+"/"+formattedDate);
        client.execute(postRequest);
        restart();
    }
    public void addNurse(String imie,String nazwisko,String login,String haslo) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/nurse/"+imie+"/"+nazwisko+"/"+login+"/"+haslo);
        client.execute(postRequest);
        restart();
    }

    public void assignSingleUser(long nurseId, long userId) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/nurseAddPatient/"+nurseId+"/"+userId);
        client.execute(postRequest);
        restart();
    }

    public void deassignSingleUser(long nurseId, long userId) throws IOException {
        HttpPost postRequest = new HttpPost(
                this.address+"/nurseDelOnePatient/"+nurseId+"/"+userId);
        client.execute(postRequest);
        restart();
    }

    public void resetNurseConnections(long id) throws IOException { //teraz już useless, zrobiłem lepsze mechanizmy zarządzania
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
                temp.setLiczbaPacjentow(nurses.get(i).getPatients().size());
            }
            ret.add(temp);

        }
        ret.remove(0);
        restart();
        return ret;
    }

    public List<Product> getProducts() throws IOException {
        List<Product> products = null;
        HttpGet getRequest = new HttpGet(
                this.address+"/products");
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = client.execute(getRequest);
        String json = "";
        String tmp = null;
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while((tmp = br.readLine())!=null){
            json+=tmp;
        }
        products = mapper.readValue(json, new TypeReference<List<Product>>(){});
        restart();
        return products;
    }

    public List<UsersForTable> getPatients() throws IOException { //zwraca liste pacjentów
        List<User> patients = null;
        List<UsersForTable> ret = new ArrayList<UsersForTable>();
        HttpGet getRequest = new HttpGet(
                this.address+"/users");
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = client.execute(getRequest);

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
                    patients.get(i).getLastName(),patients.get(i).getLogin().getLogin(),patients.get(i).getLogin().getPasswd(),
                    patients.get(i).getPotassium(),patients.get(i).getWater(),patients.get(i).getSodium(),
                    patients.get(i).getLimitPotassium(),patients.get(i).getLimitWater(),patients.get(i).getLimitSodium(),
                    patients.get(i).getNextVisit(),patients.get(i).getConsumed());
            ret.add(temp);
            restart();
        }

        return ret;
    }

    public List<UsersForTable> getPatientsOfNurse(long nurseId) throws IOException { //zwraca liste pacjentów danej pielęgniarki
        List<User> patients = null;
        List<UsersForTable> ret = new ArrayList<UsersForTable>();
        HttpGet getRequest = new HttpGet(
                this.address+"/nursePatients/"+nurseId);
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = client.execute(getRequest);

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
                    patients.get(i).getLastName(),patients.get(i).getLogin().getLogin(),patients.get(i).getLogin().getPasswd(),
                    patients.get(i).getPotassium(),patients.get(i).getWater(),patients.get(i).getSodium(),
                    patients.get(i).getLimitPotassium(),patients.get(i).getLimitWater(),patients.get(i).getLimitSodium(),
                    patients.get(i).getNextVisit(),patients.get(i).getConsumed());
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

    public boolean checkUserAvailibility(String login, Long id) throws IOException {
        boolean result = true;
        List<UsersForTable> list = getPatients();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getLogin().equals(login) && list.get(i).getId()!=id){
                result = false;
                break;
            }
        }
        return result;
    }
    public boolean checkNurseAvailibility(String login, Long id) throws IOException {
        boolean result = true;
        List<NursesForTable> list = getNurses();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getLogin().equals(login) && list.get(i).getId()!=id){
                result = false;
                break;
            }
        }
        return result;
    }

    public void restart(){
        client.getConnectionManager().shutdown();
        client = HttpClientBuilder.create().build();
    }
}
