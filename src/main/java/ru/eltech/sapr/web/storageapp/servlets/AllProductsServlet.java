package ru.eltech.sapr.web.storageapp.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import ru.eltech.sapr.web.storageapp.service.IProductService;
import ru.eltech.sapr.web.storageapp.thymeleaf.TemplateEngineUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class AllProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IProductService service = (IProductService) getServletContext().getAttribute(IProductService.SERVICE_NAME);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("products", service.getAll());
        engine.process("products.html", context, resp.getWriter());
    }
}
