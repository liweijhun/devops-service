package io.choerodon.devops.api.controller.v1;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.core.annotation.Permission;
import io.choerodon.core.notify.NoticeSendDTO;
import io.choerodon.devops.app.service.DevopsCheckLogService;

@RestController
@RequestMapping(value = "/v1/upgrade")
public class  DevopsCheckController {

    private final Logger logger = LoggerFactory.getLogger(DevopsCheckController.class);
    private Gson gson = new Gson();

    @Autowired
    private DevopsCheckLogService devopsCheckLogService;

    /**
     * 平滑升级
     *
     * @param version 版本
     */
    @Permission(permissionLogin = true)
    @ApiOperation(value = "用于平滑升级(迁移数据等操作,可以多次调用)")
    @GetMapping
    public ResponseEntity<String> checkLog(
            @ApiParam(value = "version")
            @RequestParam(value = "version") String version) {
        devopsCheckLogService.checkLog(version);
        return new ResponseEntity<>(System.currentTimeMillis() + "", HttpStatus.OK);
    }
}
