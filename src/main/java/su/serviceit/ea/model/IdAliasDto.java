package su.serviceit.ea.model;

import java.util.Objects;

public class IdAliasDto {

    private Long id;

    private String alias;

    public Long getId() {
        return id;
    }

    public String getAlias() {
        if (Objects.isNull(alias) || alias.equals("")) {
            return "N/A";
        } else {
            return alias;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public IdAliasDto(Long id, String alias) {
        this.id = id;
        this.alias = alias;
    }
}
