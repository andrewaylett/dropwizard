package io.dropwizard.lifecycle;

import com.google.common.util.concurrent.Service;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JettyServiceTest {
    private final Service managed = mock(Service.class);
    private final JettyService jettyManaged = new JettyService(managed);

    @Test
    public void startsAndStops() throws Exception {
        when(managed.startAsync()).thenReturn(managed);
        when(managed.stopAsync()).thenReturn(managed);

        jettyManaged.start();
        jettyManaged.stop();

        final InOrder inOrder = inOrder(managed);
        inOrder.verify(managed).startAsync();
        inOrder.verify(managed).awaitRunning();
        inOrder.verify(managed).stopAsync();
        inOrder.verify(managed).awaitTerminated();
    }
}
