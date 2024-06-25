package org.gox.bsdata.wh40k.model.catalogue;

import lombok.Data;

import java.util.List;

@Data
public class InfoGroup extends Entry {

    private List<InfoLink> infoLinks;
    private List<Profile> profiles;
}
