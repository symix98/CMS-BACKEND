package net.ccc.materialrequisition.service;

import java.util.concurrent.atomic.AtomicReference;
import net.ccc.apps.core.domain.enumeration.IdConfigPropertyTypes;
import net.ccc.apps.core.repository.ProjectInfoRepository;
import net.ccc.apps.core.service.AppUserService;
import net.ccc.apps.core.service.custom.IidConfigResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaterialRequisitionIdConfigResolver implements IidConfigResolver {

    @Autowired
    private ProjectInfoRepository projectInfoRepository;

    @Autowired
    private AppUserService appUserService;

    public String resolveIdConfigFunction(IdConfigPropertyTypes propertyName) {
        AtomicReference<String> result = new AtomicReference<>();

        if (propertyName == IdConfigPropertyTypes.projectName) {
            projectInfoRepository.findAll().stream().findFirst().ifPresent(val -> result.set(val.getName()));
        } else if (propertyName == IdConfigPropertyTypes.userName) {
            //appUserService.findOne(UserService.currentLoggedInUser().getEmail()).ifPresent(val->result.set(val.getEmail()));
        }

        return result.get();
    }
}
