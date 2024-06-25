package org.gox.bsdata.wh40k.model.catalogue;

import lombok.Data;

import java.util.List;

@Data
public class SharedSelectionEntryGroup extends Entry {

    private List<EntryLink> entryLinks;
}
