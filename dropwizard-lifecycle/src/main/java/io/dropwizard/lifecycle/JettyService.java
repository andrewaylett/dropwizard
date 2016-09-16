package io.dropwizard.lifecycle;

import com.google.common.util.concurrent.Service;
import org.eclipse.jetty.util.component.AbstractLifeCycle;

/**
 * A wrapper for {@link Service} instances which ties them to a Jetty {@link
 * org.eclipse.jetty.util.component.LifeCycle}.
 */
public class JettyService extends AbstractLifeCycle implements Managed {
    private final Service service;

    /**
     * Creates a new JettyManaged wrapping {@code managed}.
     *
     * @param service a {@link Managed} instance to be wrapped
     */
    public JettyService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    @Override
    protected void doStart() throws Exception {
        service.startAsync().awaitRunning();
    }

    @Override
    protected void doStop() throws Exception {
        service.stopAsync().awaitTerminated();
    }

    @Override
    public String toString() {
        return service.toString();
    }
}
