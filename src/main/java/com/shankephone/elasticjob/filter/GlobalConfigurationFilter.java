/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.shankephone.elasticjob.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dangdang.ddframe.job.lite.lifecycle.internal.reg.RegistryCenterFactory;
import com.dangdang.ddframe.job.reg.exception.RegException;
import com.google.common.base.Optional;
import com.shankephone.elasticjob.model.EventTraceDataSource;
import com.shankephone.elasticjob.model.EventTraceDataSourceFactory;
import com.shankephone.elasticjob.model.RegistryCenter;
import com.shankephone.elasticjob.service.EventTraceDataSourceConfigurationService;
import com.shankephone.elasticjob.service.RegistryCenterService;
import com.shankephone.elasticjob.service.impl.EventTraceDataSourceServiceImpl;
import com.shankephone.elasticjob.service.impl.RegistryCenterServiceImpl;
import com.shankephone.elasticjob.util.SessionEventTraceDataSourceConfiguration;
import com.shankephone.elasticjob.util.SessionRegistryCenterConfiguration;

import static com.shankephone.elasticjob.restful.config.EventTraceDataSourceRestfulApi.DATA_SOURCE_CONFIG_KEY;
import static com.shankephone.elasticjob.restful.config.RegistryCenterRestfulApi.REG_CENTER_CONFIG_KEY;


/**
 * 全局配置过滤器.
 *
 * @author caohao
 */
public final class GlobalConfigurationFilter implements Filter {
    
    private final RegistryCenterService regCenterService = new RegistryCenterServiceImpl();
    
    private final EventTraceDataSourceConfigurationService rdbService = new EventTraceDataSourceServiceImpl();
    
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpRequest.getSession();
        if (null == httpSession.getAttribute(REG_CENTER_CONFIG_KEY)) {
            loadActivatedRegCenter(httpSession);
        }
        if (null == httpSession.getAttribute(DATA_SOURCE_CONFIG_KEY)) {
            loadActivatedEventTraceDataSource(httpSession);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    private void loadActivatedRegCenter(final HttpSession httpSession) {
        Optional<RegistryCenter> config = regCenterService.loadActivated();
        if (config.isPresent()) {
            Long id = config.get().getId();
            boolean isConnected = setRegistryCenterNameToSession(config.get(), httpSession);
            if (isConnected) {
                regCenterService.load(id);
            }
        }
    }
    
    private boolean setRegistryCenterNameToSession(final RegistryCenter regCenterConfig, final HttpSession session) {
        session.setAttribute(REG_CENTER_CONFIG_KEY, regCenterConfig);
        try {
            RegistryCenterFactory.createCoordinatorRegistryCenter(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
            SessionRegistryCenterConfiguration.setRegistryCenterConfiguration((RegistryCenter) session.getAttribute(REG_CENTER_CONFIG_KEY));
        } catch (final RegException ex) {
            return false;
        }
        return true;
    }
    
    private void loadActivatedEventTraceDataSource(final HttpSession httpSession) {
        Optional<EventTraceDataSource> config = rdbService.loadActivated();
        if (config.isPresent()) {
            String configName = config.get().getName();
            boolean isConnected = setEventTraceDataSourceNameToSession(rdbService.find(configName, rdbService.loadAll()), httpSession);
            if (isConnected) {
                rdbService.load(configName);
            }
        }
    }
    
    private boolean setEventTraceDataSourceNameToSession(final EventTraceDataSource dataSourceConfig, final HttpSession session) {
        session.setAttribute(DATA_SOURCE_CONFIG_KEY, dataSourceConfig);
        try {
            EventTraceDataSourceFactory.createEventTraceDataSource(dataSourceConfig.getDriver(), dataSourceConfig.getUrl(),
                    dataSourceConfig.getUsername(), Optional.fromNullable(dataSourceConfig.getPassword()));
            SessionEventTraceDataSourceConfiguration.setDataSourceConfiguration((EventTraceDataSource) session.getAttribute(DATA_SOURCE_CONFIG_KEY));
            // CHECKSTYLE:OFF
        } catch (final Exception ex) {
            // CHECKSTYLE:ON
            return false;
        }
        return true;
    }
    
    @Override
    public void destroy() {
    }
}
