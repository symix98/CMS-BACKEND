package net.ccc.apps.config;

import java.time.Duration;

import net.ccc.apps.core.repository.UserRepository;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, "oAuth2Authentication");
            createCache(cm, net.ccc.apps.core.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, net.ccc.apps.core.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, net.ccc.apps.core.domain.User.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Authority.class.getName());
            createCache(cm, net.ccc.apps.core.domain.User.class.getName() + ".authorities");
            createCache(cm, net.ccc.apps.core.domain.ProjectInfo.class.getName());
            createCache(cm, net.ccc.apps.core.domain.ProjectInfo.class.getName() + ".attachements");
            createCache(cm, net.ccc.apps.core.domain.ProjectSettings.class.getName());
            createCache(cm, net.ccc.apps.core.domain.DataType.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Reference.class.getName());
            createCache(cm, net.ccc.apps.core.domain.CustomView.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Report.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Report.class.getName() + ".attachements");
            createCache(cm, net.ccc.apps.core.domain.Report.class.getName() + ".reportDetails");
            createCache(cm, net.ccc.apps.core.domain.ReportDetails.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Attachement.class.getName());
            createCache(cm, net.ccc.apps.core.domain.AppUser.class.getName());
            createCache(cm, net.ccc.apps.core.domain.AppUser.class.getName() + ".roles");
            createCache(cm, net.ccc.apps.core.domain.Role.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Role.class.getName() + ".appUsers");
            createCache(cm, net.ccc.apps.core.domain.SecurityObject.class.getName());
            createCache(cm, net.ccc.apps.core.domain.SecurityPermission.class.getName());
            createCache(cm, net.ccc.apps.core.domain.MessageTemplate.class.getName());
            createCache(cm, net.ccc.apps.core.domain.WorkflowTemplate.class.getName());
            createCache(cm, net.ccc.apps.core.domain.WorkflowProcess.class.getName());
            createCache(cm, net.ccc.apps.core.domain.WorkflowStep.class.getName());
            createCache(cm, net.ccc.apps.core.domain.WorkflowActionUser.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Inbox.class.getName());
            createCache(cm, net.ccc.apps.core.domain.WorkflowStepMessageConfig.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Widget.class.getName());
            createCache(cm, net.ccc.apps.core.domain.DashboardLayout.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Dashboard.class.getName());
            createCache(cm, net.ccc.apps.core.domain.Dashboard.class.getName() + ".dashboardDetails");
            createCache(cm, net.ccc.apps.core.domain.DashboardDetails.class.getName());
            // jhipster-needle-ehcache-add-entry

//            createCache(cm, "oAuth2Authentication");
//            createCache(cm, UserRepository.USERS_BY_LOGIN_CACHE);
//            createCache(cm, UserRepository.USERS_BY_EMAIL_CACHE);

        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
