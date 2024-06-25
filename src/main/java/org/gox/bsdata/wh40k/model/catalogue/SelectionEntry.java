package org.gox.bsdata.wh40k.model.catalogue;

import lombok.Data;

import java.util.List;

@Data
public class SelectionEntry extends Entry {

    private List<Cost> costs;
    private List<SelectionEntry> selectionEntries;
    private List<CategoryLink> categoryLinks;
    private List<Profile> profiles;
    private List<InfoLink> infoLinks;
    private List<InfoGroup> infoGroups;
    private List<EntryLink> entryLinks;
}
