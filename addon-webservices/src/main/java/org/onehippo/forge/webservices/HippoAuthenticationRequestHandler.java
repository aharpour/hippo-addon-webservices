package org.onehippo.forge.webservices;

import javax.jcr.LoginException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.onehippo.forge.webservices.jaxrs.exception.UnauthorizedException;
import org.onehippo.forge.webservices.v1.jcr.util.RepositoryConnectionUtils;

@Provider
public class HippoAuthenticationRequestHandler implements RequestHandler {

    public Response handleRequest(Message m, ClassResourceInfo resourceClass) {
        AuthorizationPolicy policy = m.get(AuthorizationPolicy.class);
        if (policy != null) {
            String username = policy.getUserName();
            String password = policy.getPassword();
            Session session = null;
            try {
                session = RepositoryConnectionUtils.createSession(username, password);
                if (isAuthenticated(session)) {
                    HttpServletRequest request = (HttpServletRequest) m.get(AbstractHTTPDestination.HTTP_REQUEST);
                    request.setAttribute(AuthenticationConstants.HIPPO_CREDENTIALS, new SimpleCredentials(username, password.toCharArray()));
                    return null;
                } else {
                    // authentication failed, request the authentication, add the realm name if needed to the value of WWW-Authenticate
                    return Response.status(401).header("WWW-Authenticate", "Basic").build();
                }
            } catch (LoginException e) {
                throw new UnauthorizedException(e.getMessage(), "Hippo API Realm");
            } finally {
                RepositoryConnectionUtils.cleanupSession(session);
            }
        }
        return Response.status(401).header("WWW-Authenticate", "Basic").build();
    }

    private boolean isAuthenticated(Session session) {
        if (session != null) {
            return true;
        }
        return false;
    }
}