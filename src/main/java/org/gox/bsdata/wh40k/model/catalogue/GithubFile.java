package org.gox.bsdata.wh40k.model.catalogue;

import lombok.Data;

@Data
public class GithubFile {

    private long id;
    private String name;
    private String type;
    private String download_url;
}
