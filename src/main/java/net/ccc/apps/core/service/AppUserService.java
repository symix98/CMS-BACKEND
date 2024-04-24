package net.ccc.apps.core.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.repository.AppUserRepository;
import net.ccc.apps.core.service.dto.AppUserDTO;
import net.ccc.apps.core.service.mapper.AppUserMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AppUser}.
 */
@Service
@Transactional
public class AppUserService {

    private final Logger log = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserMapper appUserMapper;

    @Value("${legacyServer.employee_url}")
    protected String employeeServer;

    @Autowired
    KeycloakService keycloakService;

    //    public AppUserService(KeycloakService keycloakService) {
    //        this.keycloakService = keycloakService;
    //    }
    //
    /**
     * Save a appUser.
     *
     * @param appUserDTO the entity to save.
     * @return the persisted entity.
     */

    public AppUserDTO save(AppUserDTO appUserDTO) {
        log.debug("Request to save AppUser : {}", appUserDTO);
        AppUser appUser = appUserMapper.toEntity(appUserDTO);
        appUser = appUserRepository.save(appUser);
        return appUserMapper.toDto(appUser);
    }

    /**
     * Get all the appUsers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AppUserDTO> findAll() {
        log.debug("Request to get all AppUsers");
        return appUserRepository.findAll().stream().map(appUserMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one appUserDTO by id.
     *
     * @param userId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AppUser> findOne(String userId) {
        log.debug("Request to get AppUser : {}", userId);
        return appUserRepository.findById(userId);
    }

    public String getUserName(String id) {
        log.debug("Request to get AppUser Caption : {}", id);
        if (id == null) {
            return "";
        }
        Optional<AppUser> byId = appUserRepository.findById(id);
        AppUser user = byId.orElse(null);
        if (user != null) {
            return user.getName();
        }
        return "";
    }

    /**
     * Delete the appUser by id.
     *
     * @param userId the id of the entity.
     */
    public void delete(String userId) {
        log.debug("Request to delete AppUser : {}", userId);
        appUserRepository.deleteById(userId);
    }

    public List<AppUserDTO> findAllUsersByRoleId(String roleId) {
        return appUserRepository
            .findAllByRoleId(roleId)
            .stream()
            .map(appUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public Map<String, Object> findEmployeeByGroupNo(String groupNo) {
        log.debug("find Employee from orgunit Server by groupNo : {}", groupNo);
        String timeSheetToken = keycloakService.getTimeSheetToken();
        Map<String, Object> result = new HashMap<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
            .url(employeeServer + "api/v1/entities/Employee?filter=" + groupNo)
            .method("GET", null)
            .addHeader("Authorization", "Bearer " + timeSheetToken)
            .build();
        try (Response response = client.newCall(request).execute()) {
            String empString = response.body().string().replaceAll("\\[", "").replaceAll("]", "");

            JsonElement empObj = new JsonParser().parse(empString);

            if (!empObj.isJsonNull()) {
                JsonObject empJson = empObj.getAsJsonObject();
                AppUserDTO userDto = new AppUserDTO();

                userDto.setUserId(!empJson.get("badgeNo").isJsonNull() ? empJson.get("badgeNo").getAsString() : null);
                userDto.setEmail(!empJson.get("email").isJsonNull() ? empJson.get("email").getAsString() : "");
                userDto.setName(empJson.get("firstName").getAsString() + " " + empJson.get("lastName").getAsString());
                userDto.setIsAdministrator(false);
                userDto.setInactive(false);
                result.put("appUser", userDto);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
