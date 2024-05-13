package com.binaryNomad.designpatternsstrategyemailfilter.model;

import java.util.List;

public interface FilterStrategy {

    List<EmailData> applyFilter(List<EmailData> emails);
 }
