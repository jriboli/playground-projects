package org.example.assignments.entity;

public class FlightInfo {
    public String airlineName;
    public String airlineCode;
    public String departure;
    public String arrival;
    public String duration;
    public String terminal;

    @Override
    public String toString() {
        return "FlightInfo{" +
                "airlineName='" + airlineName + '\'' +
                ", airlineCode='" + airlineCode + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", duration='" + duration + '\'' +
                ", terminal='" + terminal + '\'' +
                '}';
    }
}
