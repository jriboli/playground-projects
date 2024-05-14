package com.binaryNomad.emailFiltering.model;

import java.util.List;

public interface FilterStrategy {

    List<EmailData> applyFilter(List<EmailData> emails);
 }
