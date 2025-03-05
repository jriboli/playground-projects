package com.helldivers.model.stratagems;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StratagemResponse {

    private int num_of_stratagems;
    private List<StratagemData> stratagems;

    public StratagemResponse(List<StratagemData> data) {
        num_of_stratagems = data.size();
        stratagems = data;
    }

    public StratagemResponse(StratagemData data) {
        List<StratagemData> stratagemList = new ArrayList<>();
        stratagemList.add(data);

        num_of_stratagems = stratagemList.size();
        stratagems = stratagemList;
    }
}
