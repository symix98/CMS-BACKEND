package net.ccc.apps.core.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.ResourceScopesResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.jose.jws.JWSInput;
import org.keycloak.jose.jws.JWSInputException;
import org.keycloak.representations.AccessToken;
import org.keycloak.util.JsonSerialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KeycloakService {

    private final Logger log = LoggerFactory.getLogger(KeycloakService.class);

    SecurityContext sc;
    private Keycloak keycloak;

    @Value("${spring.security.oauth2.client.registration.oidc.client-id}")
    protected String clientId;

    @Value("${kic.realm_name}")
    protected String realmName;

    @Value("${legacyServer.config}")
    protected String configUrl;

    @Autowired
    private Environment env;



    private String token;
    AuthzClient authzClient = AuthzClient.create(KeycloakService.class.getResourceAsStream(configUrl!=null?configUrl:"/config/keycloak.json"));


    public RealmResource getRealmResource(String realmName) {
        return this.keycloak.realms().realm(realmName);
    }

    public RealmResource getRealmResource() {
        return this.keycloak.realms().realm(realmName);
    }

    public String clientId(String realmName) {
        // Get client
        org.keycloak.representations.idm.ClientRepresentation app1Client = getRealmResource(realmName)
            .clients()
            .findByClientId(this.clientId)
            .get(0);
        return app1Client.getId();
    }

    public String clientId() {
        System.out.println(" client Id " + this.clientId);
        // Get client
        org.keycloak.representations.idm.ClientRepresentation app1Client = getRealmResource(realmName)
            .clients()
            .findByClientId(this.clientId)
            .get(0);
        return app1Client.getId();
    }

    public org.keycloak.representations.idm.UserRepresentation getCurrentUser() {
        return null;
        //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // KeycloakPrincipal userDetails = (KeycloakPrincipal) authentication.getPrincipal();
        // IDToken idToken = userDetails.getKeycloakSecurityContext().getIdToken();

        //    String userId =idToken.getId();

        //     System.out.println("userId::  "+ userId);

        //     return   this.getRealmResource(realmName).users().get( userId).toRepresentation();

    }

    public ResourceScopesResource clientScopes() {
        return getRealmResource().clients().get(clientId()).authorization().scopes();
    }

    public String getTimeSheetToken() {
        if (token == null) {
            token = authzClient.obtainAccessToken().getToken();
        }
        try {
            AccessToken accessToken = JsonSerialization.readValue(new JWSInput(token).getContent(), AccessToken.class);
            if (accessToken.isActive() && this.isTokenTimeToLiveSufficient(accessToken)) {
                return token;
            } else {
                token = authzClient.obtainAccessToken().getToken();
                log.debug("Access token is expired. recall Token");
            }
        } catch (IOException | JWSInputException e) {
            e.printStackTrace();
        }

        return token;
    }

    public boolean isTokenTimeToLiveSufficient(AccessToken token) {
        return (
            token != null &&
            (token.getExpiration() - authzClient.getConfiguration().getTokenMinimumTimeToLive()) > System.currentTimeMillis()
        );
    }
}
