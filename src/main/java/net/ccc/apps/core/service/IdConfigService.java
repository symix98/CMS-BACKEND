package net.ccc.apps.core.service;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import net.ccc.apps.core.domain.IdConfig;
import net.ccc.apps.core.domain.enumeration.IdConfigPropertyTypes;
import net.ccc.apps.core.repository.IdConfigRepository;
import net.ccc.apps.core.service.dto.IdConfigDTO;
import net.ccc.apps.core.service.mapper.IdConfigMapper;
import net.ccc.materialrequisition.service.MaterialRequisitionIdConfigResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IdConfigService {

    private final Logger log = LoggerFactory.getLogger(IdConfigService.class);

    private final IdConfigRepository idConfigRepository;

    private final IdConfigMapper idConfigMapper;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ApplicationContext applicationContext;

    public IdConfigService(IdConfigRepository idConfigRepository, IdConfigMapper idConfigMapper) {
        this.idConfigRepository = idConfigRepository;
        this.idConfigMapper = idConfigMapper;
    }

    /**
     * Save a idConfig.
     *
     * @param idConfigDTO the entity to save.
     * @return the persisted entity.
     */
    public IdConfigDTO save(IdConfigDTO idConfigDTO) {
        log.debug("Request to save IdConfig : {}", idConfigDTO);
        IdConfig idConfig = idConfigMapper.toEntity(idConfigDTO);
        idConfig = idConfigRepository.save(idConfig);
        return idConfigMapper.toDto(idConfig);
    }

    /**
     * Get all the idConfigs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IdConfigDTO> findAll() {
        log.debug("Request to get all IdConfigs");
        return idConfigRepository.findAll().stream().map(idConfigMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one idConfig by id.
     *
     * @param name the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IdConfigDTO> findOne(String name) {
        log.debug("Request to get IdConfig : {}", name);
        return idConfigRepository.findById(name).map(idConfigMapper::toDto);
    }

    @Transactional(readOnly = true)
    private String resolvePrefixPostfix(String paramName) throws Exception {
        String result = "";
        try {
            Class<?> mClass = Class.forName("net.ccc.materialrequisition.service.MaterialRequisitionIdConfigResolver");
            Object obj = applicationContext.getBean(mClass);
            Method method = mClass.getDeclaredMethod("resolveIdConfigFunction", IdConfigPropertyTypes.class);
            String param = paramName.substring(1, paramName.length() - 1);
            result = (String) method.invoke(obj, IdConfigPropertyTypes.valueOf(param));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return result;
    }

    @Transactional(readOnly = true)
    public String getCurrentId(String idConfigName) {
        String generatedIdConfig = null;
        String prefix = "";
        String postfix = "";

        log.debug("Request to get current document id : {}", idConfigName);
        try {
            IdConfigDTO idConfigDTO = idConfigMapper.toDto(
                entityManager.find(IdConfig.class, idConfigName, LockModeType.PESSIMISTIC_WRITE)
            );
            if (idConfigDTO != null) {
                Integer numberOfZeroesPadding = idConfigDTO.getCounterDigits();
                Boolean prefixIndex = idConfigDTO.getPrefix() != null
                    ? (idConfigDTO.getPrefix().contains("{") || idConfigDTO.getPrefix().contains("}"))
                    : false;
                Boolean postfixIndex = idConfigDTO.getPostfix() != null
                    ? (idConfigDTO.getPostfix().contains("{") || idConfigDTO.getPostfix().contains("}"))
                    : false;

                prefix = idConfigDTO.getPrefix();
                postfix = idConfigDTO.getPostfix();

                if (prefixIndex) {
                    prefix = resolvePrefixPostfix(idConfigDTO.getPrefix());
                    prefix = (prefix.contains("@")) ? prefix.substring(0, prefix.indexOf("@")) : prefix;
                }

                if (postfixIndex) {
                    postfix = resolvePrefixPostfix(idConfigDTO.getPostfix());
                    postfix = (postfix.contains("@")) ? postfix.substring(0, postfix.indexOf("@")) : postfix;
                }

                Integer currentCount = idConfigDTO.getCurrentCounterValue() == 0
                    ? idConfigDTO.getCounterStart()
                    : idConfigDTO.getCurrentCounterValue() + 1;
                String currentCountZeroPadded = String.format("%0" + numberOfZeroesPadding + "d", currentCount);
                generatedIdConfig = (prefix != null ? prefix : "") + (postfix != null ? postfix : "") + currentCountZeroPadded;
                idConfigDTO.setCurrentCounterValue(currentCount);

                this.save(idConfigDTO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return generatedIdConfig;
    }

    /**
     * Delete the idConfig by id.
     *
     * @param name the id of the entity.
     */
    public void delete(String name) {
        log.debug("Request to delete IdConfig : {}", name);
        idConfigRepository.deleteById(name);
    }
}
