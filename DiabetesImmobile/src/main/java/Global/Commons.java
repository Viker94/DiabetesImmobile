package Global;

import Model.NursesForTable;
import Model.UsersForTable;

public class Commons {
    private static String imie = "";
    private static String nazwisko = "";
    private static String login = "";

    private static UsersForTable selectedUser;
    private static NursesForTable selectedNurse;
    private static int userOrNurse = 0; //1 - user, 2 - nurse

    //Gettery i settery
    // !!! zrobione ręcznie, bo @Data lomboka z automatu pomija wszystkie static fields !!!

    public static String getImie() {
        return imie;
    }

    public static void setImie(String imie) {
        Commons.imie = imie;
    }

    public static String getNazwisko() {
        return nazwisko;
    }

    public static void setNazwisko(String nazwisko) {
        Commons.nazwisko = nazwisko;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        Commons.login = login;
    }

    public static UsersForTable getSelectedUser() {
        return selectedUser;
    }

    public static void setSelectedUser(UsersForTable selectedUser) {
        Commons.selectedUser = selectedUser;
    }

    public static NursesForTable getSelectedNurse() {
        return selectedNurse;
    }

    public static void setSelectedNurse(NursesForTable selectedNurse) {
        Commons.selectedNurse = selectedNurse;
    }

    public static int getUserOrNurse() {
        return userOrNurse;
    }

    public static void setUserOrNurse(int userOrNurse) {
        Commons.userOrNurse = userOrNurse;
    }

    //koniec getterów i setterów


    public static Connectivity conn = new Connectivity("http://dialisys.azurewebsites.net");
    public static WindowControls windowControls = new WindowControls();

}
