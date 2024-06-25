package org.gox.bsdata.wh40k.model.catalogue;

import lombok.Data;

@Data
public abstract class Entry {

    protected String id;
    protected String name;
    protected String type;
    protected String typeId;
    protected String targetId;
    protected String typeName;
}
