package com.ruoyi.common.security.utils;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;

public class UserUtils {

    private static RemoteUserService remoteUserService = SpringUtils.getBean(RemoteUserService.class);

    public static SysUser getUserByUsername(String username) {
        R<LoginUser> userResult = remoteUserService.getUserInfo(username, SecurityConstants.INNER);
        if (R.FAIL == userResult.getCode()) {
            throw new ServiceException(userResult.getMsg());
        }
        return userResult.getData().getSysUser();
    }

}
