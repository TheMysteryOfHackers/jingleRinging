package com.jingle.oa.system.service;

import com.jingle.oa.system.entity.Token;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mowen
 * @since 2019-12-08
 */
public interface ITokenService extends IService<Token> {

	boolean saveNewToken(Token newTokenObj);

}
