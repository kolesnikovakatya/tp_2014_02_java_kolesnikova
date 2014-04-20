package frontend; /**
 * Created by katya on 2/15/14.
 */

import templater.PageGenerator;
import logic.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


public class Frontend extends HttpServlet {

    private AtomicLong userIDGen = new AtomicLong();
    private Map<Long, String> loggedUsers = new HashMap<>();
    private Map<String, String> users = new HashMap<>();

    public static String getTime() {
        Date date = new Date();
        date.getTime();
        DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
        return formatter.format(date);
    }

    //timer variables
    private static Map<String, Object> timerVars (Long userId) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("refreshPeriod", "1000");
        pageVariables.put("time", getTime());
        pageVariables.put("userId", userId);
        return pageVariables;
    }

    private static Map<String, Object> simpleVars (String key, String value) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(key, value);
        return pageVariables;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<>();
        HttpSession session = request.getSession();

        if(request.getPathInfo().equals("/authform")) {
            Long userId = (Long)session.getAttribute("userId");
            if(userId == null) {
                pageVariables.put("error","you should be authorized");
                response.getWriter().println(PageGenerator.getPage("authform.tml", pageVariables));
            } else {
                pageVariables = timerVars(userId);
                response.getWriter().println(PageGenerator.getPage("userid.tml", pageVariables));
            }
        } else if(request.getPathInfo().equals("/regform")) {
            pageVariables = simpleVars("error", "");
            response.getWriter().println(PageGenerator.getPage("registration.tml", pageVariables));
        }
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Map<String, Object> pageVariables = new HashMap<>();
        User user;

        if (request.getPathInfo().equals("/authform")) {

            if (users != null && users.get(login).equals(password)) {
                HttpSession session = request.getSession();
                Long tmpUid = (Long)session.getAttribute("userId");
                if (tmpUid == null) {
                    tmpUid = userIDGen.getAndIncrement();
                } else if ((loggedUsers.containsKey(tmpUid)) && (!loggedUsers.get(tmpUid).equals(login))) {
                    loggedUsers.remove(tmpUid);
                    tmpUid = userIDGen.getAndIncrement();
                }
                loggedUsers.put(tmpUid, login);
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                session.setAttribute("userId", tmpUid);
                response.sendRedirect("/timer");
            } else {
                pageVariables.put("error","wrong pass");
                response.getWriter().println(PageGenerator.getPage("authform.tml", pageVariables));
            }
        } else if(request.getPathInfo().equals("/registration")) {

        }
     }
 }

