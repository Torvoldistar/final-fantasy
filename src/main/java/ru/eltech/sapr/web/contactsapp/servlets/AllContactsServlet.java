package ru.eltech.sapr.web.contactsapp.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import ru.eltech.sapr.web.contactsapp.service.ContactService;
import ru.eltech.sapr.web.contactsapp.thymeleaf.TemplateEngineUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class AllContactsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ContactService service = (ContactService) getServletContext().getAttribute(ContactService.SERVICE_NAME);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("contacts", service.getAll());
        engine.process("contacts.html", context, resp.getWriter());
    }
}
