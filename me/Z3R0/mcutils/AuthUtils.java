package me.Z3R0.mcutils;

import java.net.Proxy;
import java.util.UUID;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import net.minecraft.util.Session;

public class AuthUtils {
    public static Session authenticate(String username, String password) {
        YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
        UserAuthentication userAuthentication = yggdrasilAuthenticationService.createUserAuthentication(Agent.MINECRAFT);
        userAuthentication.setUsername(username);
        userAuthentication.setPassword(password);
        try {
            userAuthentication.logIn();
            return new Session(userAuthentication.getSelectedProfile().getName(), userAuthentication.getSelectedProfile().getId().toString(), userAuthentication.getAuthenticatedToken(), username.contains("@") ? "mojang" : "legacy");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Session authenticateOffline(String username) throws AuthenticationException {
    	
    	//YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "-").createUserAuthentication(Agent.MINECRAFT);
    	YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "-");
    	UserAuthentication userAuthentication = yggdrasilAuthenticationService.createUserAuthentication(Agent.MINECRAFT);
    	
    	
    	return new Session(username, "", "-", "legacy");
    	
    	
    	
    }

}
