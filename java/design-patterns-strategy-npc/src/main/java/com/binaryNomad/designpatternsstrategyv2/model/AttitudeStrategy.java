package com.binaryNomad.designpatternsstrategyv2.model;

public interface AttitudeStrategy {

    String greetings();

    double buyItem(double itemPrice);

    double sellItem(double itemPrice);

    String gift();
}
