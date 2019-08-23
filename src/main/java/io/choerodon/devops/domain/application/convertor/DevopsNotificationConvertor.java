package io.choerodon.devops.domain.application.convertor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import io.choerodon.core.convertor.ConvertorI;
import io.choerodon.devops.api.dto.DevopsNotificationDTO;
import io.choerodon.devops.domain.application.entity.DevopsNotificationE;
import io.choerodon.devops.infra.dataobject.DevopsNotificationDO;

/**
 * Creator: ChangpingShi0213@gmail.com
 * Date:  19:23 2019/5/13
 * Description:
 */
@Component
public class DevopsNotificationConvertor implements ConvertorI<DevopsNotificationE, DevopsNotificationDO, DevopsNotificationDTO> {


    @Override
    public DevopsNotificationE dtoToEntity(DevopsNotificationDTO notificationDTO) {
        DevopsNotificationE notificationE = new DevopsNotificationE();
        BeanUtils.copyProperties(notificationDTO, notificationE);
        notificationE.setNotifyType(notificationDTO.getNotifyType().stream().collect(Collectors.joining(",")));
        notificationE.setNotifyTriggerEvent(notificationDTO.getNotifyTriggerEvent().stream().collect(Collectors.joining(",")));
        return notificationE;
    }

    @Override
    public DevopsNotificationDO entityToDo(DevopsNotificationE entity) {
        DevopsNotificationDO notificationDO = new DevopsNotificationDO();
        BeanUtils.copyProperties(entity, notificationDO);
        return notificationDO;
    }

    @Override
    public DevopsNotificationE doToEntity(DevopsNotificationDO notificationDO) {
        DevopsNotificationE notificationE = new DevopsNotificationE();
        BeanUtils.copyProperties(notificationDO, notificationE);
        return notificationE;
    }

    @Override
    public DevopsNotificationDTO entityToDto(DevopsNotificationE entity) {
        DevopsNotificationDTO notificationDTO = new DevopsNotificationDTO();
        BeanUtils.copyProperties(entity, notificationDTO);
        if (entity.getNotifyType() != null && !entity.getNotifyType().isEmpty()) {
            notificationDTO.setNotifyType(Arrays.asList(entity.getNotifyType().split(",")));
        } else {
            notificationDTO.setNotifyType(new ArrayList<>());
        }

        if (entity.getNotifyTriggerEvent() != null && !entity.getNotifyTriggerEvent().isEmpty()) {
            notificationDTO.setNotifyTriggerEvent(Arrays.asList(entity.getNotifyTriggerEvent().split(",")));
        } else {
            notificationDTO.setNotifyTriggerEvent(new ArrayList<>());
        }
        return notificationDTO;
    }
}