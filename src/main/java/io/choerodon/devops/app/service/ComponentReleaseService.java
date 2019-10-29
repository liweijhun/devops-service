package io.choerodon.devops.app.service;

import io.choerodon.devops.infra.dto.AppServiceInstanceDTO;
import io.choerodon.devops.infra.dto.DevopsPrometheusDTO;

/**
 * 为集群组件创建对应的Release
 *
 * @author zmf
 * @since 10/29/19
 */
public interface ComponentReleaseService {
    /**
     * 为Prometheus组件创建相应的release
     *
     * @param devopsPrometheusDTO 组件信息
     * @return 组件对应的实例纪录
     */
    AppServiceInstanceDTO createReleaseForPrometheus(DevopsPrometheusDTO devopsPrometheusDTO);
}