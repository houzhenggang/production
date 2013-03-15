package com.rubik.support.security.listener;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureCredentialsExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureLockedEvent;

import org.springframework.security.authentication.event.AuthenticationFailureProviderNotFoundEvent;
import org.springframework.security.authentication.event.AuthenticationFailureProxyUntrustedEvent;
import org.springframework.security.authentication.event.AuthenticationFailureServiceExceptionEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.switchuser.AuthenticationSwitchUserEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.support.dao.TbGbLoginLogDao;
import com.rubik.support.entity.TbGbLoginLog;
import com.rubik.support.entity.TbSystemUser;
import com.rubik.support.security.entity.UserDetail;

@Service
@Transactional
public class LoginListener implements ApplicationListener<ApplicationEvent> {

	//日志对象
	private final Logger log = Logger.getLogger(LoginListener.class);
	
	@Autowired
	private TbGbLoginLogDao tbGbLoginLogDao;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (!(event instanceof AbstractAuthenticationEvent)){
			log.debug("=================================================非登陆请求");
			return;
		}
		this.log.debug(event.toString());
		AbstractAuthenticationEvent authEvent = (AbstractAuthenticationEvent) event;

		Object principal = authEvent.getAuthentication().getPrincipal();
		TbSystemUser user = null;

		String nickname = null;
		String loginName = null;
		
		if (principal instanceof UserDetail) {
			UserDetail userDetail = (UserDetail) principal;
			user = userDetail.getUser();
			nickname = user.getNickname();
			loginName = user.getUsername();
		} else if (principal instanceof String) {
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event
					.getSource();
			principal = token.getPrincipal();

			if (principal instanceof UserDetail) {
				UserDetail userDetail = (UserDetail) principal;
				user = userDetail.getUser();
				nickname = user.getNickname();
				loginName = user.getUsername();
			} else if (principal instanceof String) {
				loginName = (String) principal;
			}
		}

		TbGbLoginLog entity = new TbGbLoginLog();
		if(user != null){
			entity.setUserId(user.getId());
			entity.setNickname(nickname);
		}else{
			entity.setNickname("无");
		}
		entity.setLoginName(loginName);
		entity.setLoginTime(new Date());
		

		WebAuthenticationDetails webDetail = (WebAuthenticationDetails) authEvent
				.getAuthentication().getDetails();
		String loginIp = webDetail.getRemoteAddress();
		entity.setLoginIp(loginIp);

		this.log.debug("用户[" + loginName + "]登录IP[" + loginIp + "]登录日志");

		if (event instanceof AuthenticationSuccessEvent) {
			this.log.debug("验证成功");

			entity.setComments("验证成功");
			entity.setIsSuccess(Boolean.valueOf(true));
			//保存日志
			this.tbGbLoginLogDao.insert(entity);
		} else if (event instanceof AbstractAuthenticationFailureEvent) {
			this.log.debug("验证失败");
			String comments = "验证失败";
			if (event instanceof AuthenticationFailureCredentialsExpiredEvent) {
				this.log.debug("验证失败，凭证失效");
				comments = "验证失败，凭证失效";
			} else if (event instanceof AuthenticationFailureDisabledEvent) {
				this.log.debug("验证失败，禁用");
				comments = "验证失败，禁用";
			} else if (event instanceof AuthenticationFailureExpiredEvent) {
				this.log.debug("验证失败，失效");
				comments = "验证失败，失效";
			} else if (event instanceof AuthenticationFailureLockedEvent) {
				this.log.debug("验证失败，锁定");
				comments = "验证失败，锁定";
			} else if (event instanceof AuthenticationFailureProviderNotFoundEvent) {
				this.log.debug("验证失败，找不到provider");
				comments = "验证失败，找不到provider";
			} else if (event instanceof AuthenticationFailureProxyUntrustedEvent) {
				this.log.debug("验证失败，不可信任的代理");
				comments = "验证失败，不可信任的代理";
			} else if (event instanceof AuthenticationFailureServiceExceptionEvent) {
				this.log.debug("验证失败，服务异常");
				comments = "验证失败，服务异常";
			} else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
				this.log.debug("验证失败，账号或密码错误");
				comments = "验证失败，账号或密码错误";
			}
			entity.setComments(comments);
			entity.setIsSuccess(Boolean.valueOf(false));
			//保存日志
			this.tbGbLoginLogDao.insert(entity);
			
		} else if (event instanceof AuthenticationSwitchUserEvent) {
			this.log.debug("切换用户");
		} else {
			if (!(event instanceof InteractiveAuthenticationSuccessEvent))
				return;
			this.log.debug("内部验证成功");
		}
	}

}
