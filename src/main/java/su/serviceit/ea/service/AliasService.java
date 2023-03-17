package su.serviceit.ea.service;

import su.serviceit.ea.model.IdAliasDto;
import su.serviceit.ea.repository.EnterpriseArchitectRepository;

import java.util.Objects;

public class AliasService {
    public AliasService() {
    }

    private final EnterpriseArchitectRepository enterpriseArchitectRepository = new EnterpriseArchitectRepository();

    public IdAliasDto getAlias(String guidStr) {
        return enterpriseArchitectRepository.getAliasByGuid(guidStr);
    }

    public boolean deleteAlias(IdAliasDto dto) {
        if (Objects.nonNull(dto)) {
            return enterpriseArchitectRepository.deleteAlias(dto.getId());
        }
        return false;
    }
}
