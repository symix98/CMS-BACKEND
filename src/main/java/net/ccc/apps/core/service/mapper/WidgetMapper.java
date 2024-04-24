package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.Widget;
import net.ccc.apps.core.service.dto.WidgetDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Widget} and its DTO {@link WidgetDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WidgetMapper extends EntityMapper<WidgetDTO, Widget> {
    default Widget fromId(Long id) {
        if (id == null) {
            return null;
        }
        Widget widget = new Widget();
        widget.setId(id);
        return widget;
    }
}
