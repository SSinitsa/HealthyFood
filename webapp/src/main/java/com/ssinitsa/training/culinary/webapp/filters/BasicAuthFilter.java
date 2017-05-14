package com.ssinitsa.training.culinary.webapp.filters;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.services.IRecipeService;
import com.ssinitsa.training.culinary.services.IUserService;
import com.ssinitsa.training.culinary.services.impl.UserAuthStorage;

public class BasicAuthFilter implements Filter {

    private IUserService userService;
    
    private IRecipeService recipeService;
    
    private ApplicationContext appContext;
    
    private UserAuthStorage userDataStorage;


    @Override
    public void init(FilterConfig config) throws ServletException {

        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config
                .getServletContext());
        userService = context.getBean(IUserService.class);
        //recipeService = context.getBean(IRecipeService.class);
        appContext = context;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (!isAuthRequired(req)) {
            chain.doFilter(request, response);
            return;
        }
        if (!getUserData (req)){
        	res.sendError(401);
        	return;
        }
        if (isAccessDenied(req)){
        	res.sendError(401);
        	return;
        }
        
        chain.doFilter(request, response);

    }

    

	private boolean getUserData(HttpServletRequest req) {
		userDataStorage = appContext.getBean(UserAuthStorage.class);

        String[] credentials = resolveCredentials(req);

        boolean isCredentialsResolved = credentials != null && credentials.length == 2;
        if (!isCredentialsResolved) {
            
            return false;
        }

        String username = credentials[0];
        String password = credentials[1];

        Integer userId = userService.getByLogin(username);
        if (validateUserPassword(userId, password)) {

            userDataStorage.setId(userId);
            userDataStorage.setRole(userService.get(userId).getRole().name());
            return true;
        } else {
            return false;
        }
		
	}

	private boolean isAuthRequired(HttpServletRequest req) {
        if (req.getMethod().toUpperCase().equals("GET")) {
            return false;
        }
        return true;
    }
	
	private boolean isAccessDenied(HttpServletRequest req){
		if (req.getMethod().toUpperCase().equals("POST")&&req.getRequestURI().startsWith("/ingredient")&&!userDataStorage.getRole().equals("admin")){
			return true;
		}
		
		return false;
		
	}
    
    private boolean userIsAuthor(String requestURI, Integer id) {
  	  Integer indexFrom = requestURI.lastIndexOf("/")+1;
  	  String indexOfObject = requestURI.substring(indexFrom);
  	  System.out.println(indexOfObject+" "+id);
  		return true;
  	}

   
	private boolean validateUserPassword(Integer userId, String password) {
        if (userId == null) {
            return false;
        }
        User user = userService.get(userId);
        if (user.getPassword().equals(password)){
        	return true;
        }else{
        	return false;
        }
        

    }

    private String[] resolveCredentials(HttpServletRequest req) {
        try {
            Enumeration<String> headers = req.getHeaders("Authorization");
            String nextElement = headers.nextElement();
            String base64Credentials = nextElement.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
            return credentials.split(":", 2);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void destroy() {
    }

}