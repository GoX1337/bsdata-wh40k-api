package org.gox.bsdata.wh40k.model.catalogue;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Catalogue extends Entry {

    private String gameSystemId;
    private String gameSystemRevision;
    private String revision;
    private String battleScribeVersion;
    private List<CategoryEntry> categoryEntries;
    private List<EntryLink> entryLinks;
    private List<SelectionEntry> sharedSelectionEntries;
    private List<Profile> sharedProfiles;
    private List<Rule> sharedRules;
    private List<SharedSelectionEntryGroup> sharedSelectionEntryGroups;
    private List<CatalogueLink> catalogueLinks;

    public List<EntryLink> getEntryLinks() {
        if (entryLinks == null) {
            entryLinks = new ArrayList<>();
        }
        return entryLinks;
    }

    public List<CategoryEntry> getCategoryEntries() {
        if (categoryEntries == null) {
            categoryEntries = new ArrayList<>();
        }
        return categoryEntries;
    }
}
