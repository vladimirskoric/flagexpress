package ph.devcon.flag.entrypoint.api.rest.filters;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;


/**
 * A simple filter to log the request and response.
 */
@Slf4j
@Provider
public final class LogFilter implements ContainerRequestFilter, ContainerResponseFilter {

    /**
     * Logs the incoming request with [METHOD] uri.
     *
     * @param containerRequestContext the request context
     */
    @Override
    public void filter(final ContainerRequestContext containerRequestContext) {
        final String method = containerRequestContext.getMethod();
        final UriInfo uriInfo = containerRequestContext.getUriInfo();
        final String path = uriInfo.getPath();
        log.info("FLAG API Request called: [{}] {}", method, path);
    }


    /**
     * Logs the outgoing response with [METHOD] uri (STATUS).
     *
     * @param containerRequestContext the request context
     * @param containerResponseContext the response context
     */
    @Override
    public void filter(final ContainerRequestContext containerRequestContext, final ContainerResponseContext containerResponseContext) {
        final String method = containerRequestContext.getMethod();
        final UriInfo uriInfo = containerRequestContext.getUriInfo();
        final String path = uriInfo.getPath();
        final int status = containerResponseContext.getStatus();
        log.info("FLAG API Request responded: [{}] {} ({})", method, path, status);
    }
}
