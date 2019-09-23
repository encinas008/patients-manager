package org.encinas.business.parsers;

public interface Parser<E, D> {
    E parseDtoToEntity(D dto);
    D parseEntityToDto(E entity);
}
