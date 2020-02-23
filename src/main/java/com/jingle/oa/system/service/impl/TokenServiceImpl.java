package com.jingle.oa.system.service.impl;

import com.jingle.oa.system.entity.Token;
import com.jingle.oa.system.mapper.TokenMapper;
import com.jingle.oa.system.service.ITokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2019-12-08
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements ITokenService {

	@Override
	public boolean saveNewToken(Token newTokenObj) {
		// 删除该账号的旧token
		QueryWrapper<Token> wrapper = new QueryWrapper<Token>().eq("uid", newTokenObj.getUid());
		this.remove(wrapper);
		return this.save(newTokenObj);
	}

}
