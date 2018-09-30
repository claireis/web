package tk.carlyle2k;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(value = {"/", "/hello"})
public class HelloWorldServlet extends HttpServlet {
    /**
     * Random instance
     */
    private static final Random RANDOM = new Random();

    /**
     * click counts
     */
    private static final AtomicInteger CLICK = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println(CLICK.incrementAndGet());
        resp.getWriter().print(RANDOM.nextInt());
    }
}
