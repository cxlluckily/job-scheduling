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

package com.shankephone.elasticjob.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 注册中心配置.
 *
 * @author zhangliang
 */
@Getter
@Setter
@NoArgsConstructor
public final class RegistryCenter implements Serializable {
    
    private static final long serialVersionUID = -5996257770767863699L;
    
    private Long id;
    
    private String name;
    
    private String zklist;
    
    private String namespace;
    
    private String digest;
    
    private boolean activated;
    
    private Date createTime;
    
    private Date updateTime;
}
