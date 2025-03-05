package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.KnotsData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class knotsService {

    public List<KnotsData> getAllPublicKnots() {
        List<KnotsData> knots = new ArrayList<KnotsData>();

        return knots;
    }

    public KnotsData getPublicKnotById(Long knotId) {
        KnotsData knot = new KnotsData();

        return knot;
    }
}
