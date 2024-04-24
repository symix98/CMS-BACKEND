package net.ccc.apps.core.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.ccc.apps.core.domain.Widget;
import net.ccc.apps.core.repository.WidgetRepository;
import net.ccc.apps.core.service.dto.WidgetDTO;
import net.ccc.apps.core.service.mapper.WidgetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Widget}.
 */
@Service
@Transactional
public class WidgetService {

    private final Logger log = LoggerFactory.getLogger(WidgetService.class);

    private final WidgetRepository widgetRepository;

    private final WidgetMapper widgetMapper;

    public WidgetService(WidgetRepository widgetRepository, WidgetMapper widgetMapper) {
        this.widgetRepository = widgetRepository;
        this.widgetMapper = widgetMapper;
    }

    /**
     * Save a widget.
     *
     * @param widgetDTO the entity to save.
     * @return the persisted entity.
     */
    public WidgetDTO save(WidgetDTO widgetDTO) {
        log.debug("Request to save Widget : {}", widgetDTO);
        Widget widget = widgetMapper.toEntity(widgetDTO);
        widget = widgetRepository.save(widget);
        return widgetMapper.toDto(widget);
    }

    /**
     * Get all the widgets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WidgetDTO> findAll() {
        log.debug("Request to get all Widgets");
        return widgetRepository.findAll().stream().map(widgetMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one widget by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WidgetDTO> findOne(Long id) {
        log.debug("Request to get Widget : {}", id);
        return widgetRepository.findById(id).map(widgetMapper::toDto);
    }

    /**
     * Delete the widget by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Widget : {}", id);
        widgetRepository.deleteById(id);
    }
}
