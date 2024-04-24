package net.ccc.apps.core.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import net.ccc.apps.core.domain.Role;
import net.ccc.apps.core.repository.RoleRepository;
import net.ccc.apps.core.service.dto.RoleDTO;
import net.ccc.apps.core.service.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Role}.
 */
@Service
@Transactional
public class RoleService {

    private final Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * Save a role.
     *
     * @param roleDTO the entity to save.
     * @return the persisted entity.
     */
    public RoleDTO save(RoleDTO roleDTO) {
        log.debug("Request to save Role : {}", roleDTO);
        Role role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    /**
     * Get all the roles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        log.debug("Request to get all Roles");
        return roleRepository.findAll().stream().map(roleMapper :: toDto).collect(Collectors.toCollection(LinkedList:: new));
    }

    /**
     * Get all the roles where AppUser is {@code null}.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RoleDTO> findAllWhereAppUserIsNull() {
        log.debug("Request to get all roles where AppUser is null");
        return roleRepository.findAll().stream().filter(role -> role.getAppUsers() == null).map(roleMapper :: toDto).collect(Collectors.toCollection(LinkedList :: new));
    }

    /**
     * Get one role by roleId.
     *
     * @param roleId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RoleDTO> findOne(String roleId) {
        log.debug("Request to get Role : {}", roleId);
        return roleRepository.findById(roleId).map(roleMapper :: toDto);
    }

    /**
     * Delete the role by roleId.
     *
     * @param roleId the id of the entity.
     */
    public void delete(String roleId) {
        log.debug("Request to delete Role : {}", roleId);
        roleRepository.deleteById(roleId);
    }
}
