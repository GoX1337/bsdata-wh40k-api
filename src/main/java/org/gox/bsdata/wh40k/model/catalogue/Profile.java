package org.gox.bsdata.wh40k.model.catalogue;

import lombok.Data;

import java.util.List;

@Data
public class Profile extends Entry {

    private List<Characteristic> characteristics;
}
