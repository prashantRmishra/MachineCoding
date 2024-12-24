package bus_booking_service_like_redbus.service;

import java.util.Objects;

public class SourceDestination{
    String source;
    String destination;
    public SourceDestination(String s, String d){
        this.source = s;
        this.destination =d;
    }
    @Override
    public boolean equals(Object sourceDestination){
        if(this == sourceDestination) return true;
        if(sourceDestination ==null || sourceDestination.getClass()!= this.getClass()) return false;
        SourceDestination sourceDest = (SourceDestination)sourceDestination;
        if(Objects.equals(sourceDest.source, this.source) && Objects.equals(sourceDest.destination, this.destination)) return true;
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(source,destination);
    }
}