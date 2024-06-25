package org.gox.bsdata.wh40k.model.catalogue;

import lombok.Data;

import java.util.List;

@Data
public class EntryLink extends Entry {

    private List<Constraint> constraints;
}
