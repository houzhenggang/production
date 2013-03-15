package com.rubik.support.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;

import com.rubik.support.dao.TbSystemResourceDao;
import com.rubik.support.entity.TbSystemResource;

//1 加载资源与权限的对应关系
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private static final Logger log = Logger.getLogger(MySecurityMetadataSource.class);
	
	private TbSystemResourceDao resourceDao;
	private AntPathRequestMatcher pathMatcher;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	//由spring调用
	public MySecurityMetadataSource(TbSystemResourceDao resourceDao) {
		this.resourceDao = resourceDao;
		loadResourceDefine();
	}

	public TbSystemResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(TbSystemResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	//加载所有资源与权限的关系
	private void loadResourceDefine() {
		if(resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<TbSystemResource> resources = this.resourceDao.getResourceUrlAuthority();
			for (TbSystemResource resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                //以权限名封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig(resource.getAuthority());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getUrl(), configAttributes);
			}
		}
		
//		Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();
//		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();
		
	}
	//返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		HttpServletRequest request = ((FilterInvocation) object).getRequest();   
		log.debug("请求资源的URL："+request.getRequestURL());
        if (resourceMap == null) {   
        	loadResourceDefine();
        }   
        Iterator<String> it = resourceMap.keySet().iterator();   
           
        while (it.hasNext()) {   
            String resURL = it.next(); 
            log.debug("正在匹配的URL："+request.getRequestURL());
            pathMatcher = new AntPathRequestMatcher(resURL);
            if (pathMatcher.matches(request)) {
            	log.debug("匹配URL的结果："+pathMatcher.matches(request));
                Collection<ConfigAttribute> returnCollection = resourceMap.get(resURL);   
                return returnCollection;   
            }else{
            	log.debug("匹配URL的结果："+pathMatcher.matches(request));
            }
        }   
        return null;
	}

}