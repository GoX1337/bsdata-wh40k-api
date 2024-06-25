package org.gox.bsdata.wh40k.model.catalogue;

import lombok.Data;

@Data
public class Constraint extends Entry {

    private Integer value;
    private String field;
    private String scope;
    private Boolean shared;
}
