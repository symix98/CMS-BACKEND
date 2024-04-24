package net.ccc.apps.core.service.custom;

import net.ccc.apps.core.domain.enumeration.IdConfigPropertyTypes;

public interface IidConfigResolver {
    String resolveIdConfigFunction(IdConfigPropertyTypes propertyName);
}
