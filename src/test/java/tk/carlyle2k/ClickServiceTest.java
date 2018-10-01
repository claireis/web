package tk.carlyle2k;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doThrow;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static tk.carlyle2k.ConnectionService.getConn;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionService.class)
public class ClickServiceTest {
    @Mock
    Connection conn;
    @Mock
    PreparedStatement psGet;
    @Mock
    PreparedStatement psUpdate;
    @Mock
    ResultSet rs;
    ClickService service;

    @Before
    public void setUp() {
        service = new ClickService();
    }

    @Test
    public void incrementAndGetTest() throws Exception {
        mockStatic(ConnectionService.class);

        when(getConn())
                .thenReturn(conn, conn);

        when(conn.prepareStatement(anyString()))
                .thenReturn(psGet, psUpdate, psGet, psUpdate);

        when(psGet.executeQuery())
                .thenReturn(rs, rs);

        when(rs.next())
                .thenReturn(true, false);

        when(rs.getInt(anyString()))
                .thenReturn(1, 100);

        when(psUpdate.executeUpdate())
                .thenReturn(1);

        assertEquals(101, service.incrementAndGet());
        assertEquals(-1, service.incrementAndGet());

        verify(psUpdate, times(2))
                .setInt(anyInt(), anyInt());
    }
}