import java.sql.*;
import java.util.Scanner;

class sql2{

    public static void main(String args[]){

        //Input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe uzytkownika");
        String user = scanner.nextLine();
        System.out.println("Podaj haslo");
        String password = scanner.nextLine();
        System.out.println("Podaj polecenie");
        String polecenie = scanner.nextLine();

        //Używamy bloku try i catch do sprawdzenia czy połączyliśmy się z bazą
        try{

            //Definijemy Driver, żeby połączyć się z bazą mysql
            Class.forName("com.mysql.jdbc.Driver");

            //Łączymy się z bazą
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://mysql.wmi.amu.edu.pl/s444551_test",user ,password);

            //Tworzymy komunikat, który będzie służył do wykonywania poleceń w bazie danych
            Statement stmt=con.createStatement();

            //Wykonujemy polecenie
            ResultSet rs=stmt.executeQuery(polecenie);

            //Obliczamy liczbę kolumn, żeby wypisywać popranwie dane
            int liczbaKolumn = rs.getMetaData().getColumnCount();

            String wynik = "";
            int i ;

            //Wypisywanie nazw kolumn
            for (i = 1; i <= liczbaKolumn; i++) {
                wynik += rs.getMetaData().getColumnName(i) + " ";
            }
            System.out.println(wynik);

            //Wypisywanie wyniku zapytania
            while(rs.next()) {
                wynik = "";
                for (i = 1; i <= liczbaKolumn; i++) {
                    wynik += rs.getString(i) + " ";
                }
                System.out.println(wynik);
            }

            //Zamykanie połączenia
            con.close();

        }catch(Exception e){ System.out.println(e);} //Łapanie wyjątku, gdyby nastąpił błąd z połączeniem do bazy
    }
}
