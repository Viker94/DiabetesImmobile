package Globality;

import Connectivity.Connectivity;
import Model.NursesForTable;
import Model.UsersForTable;
import org.apache.http.ConnectionClosedException;

/**
 * Created by fishe on 29.11.2016.
 */
public class LoginData {
    public static String imie = "";
    public static String nazwisko = "";
    public static String login = "";

    public static UsersForTable selectedUser;
    public static NursesForTable selectedNurse;
    public static int coDoEdycji = 0;

    public static Connectivity conn = new Connectivity("http://dialisys.azurewebsites.net");
}
