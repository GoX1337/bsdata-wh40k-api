package org.gox.bsdata.wh40k.model.catalogue;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
public class Characteristic extends Entry {

    @JacksonXmlText
    private String value;
}
