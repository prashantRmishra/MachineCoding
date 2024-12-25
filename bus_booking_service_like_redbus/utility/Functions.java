package bus_booking_service_like_redbus.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Functions {
    public static LocalDate getDate(String str){
        return LocalDate.parse(str,DateTimeFormatter.ofPattern("dd/MM/yyy"));
    }
}
