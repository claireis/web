package tk.carlyle2k;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(value = {"/", "/hello"})
public class HelloWorldServlet extends HttpServlet {
    /**
     * Random instance
     */
    private static final Random RANDOM = new Random();

    private static final AtomicInteger CLICK = new AtomicInteger(0);

    private static final ClickService SERVICE = new ClickService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        writer.print(SERVICE.incrementAndGet());
        writer.print("<br>");
        writer.print(CLICK.incrementAndGet());
        writer.print("<br>");
        writer.print(RANDOM.nextInt());
        writer.print("<br>");
    }
}
