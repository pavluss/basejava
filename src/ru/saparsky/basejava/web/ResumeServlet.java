package ru.saparsky.basejava.web;

import ru.saparsky.basejava.Config;
import ru.saparsky.basejava.model.ContactType;
import ru.saparsky.basejava.model.Resume;
import ru.saparsky.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getSqlStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Resume> resumes = storage.getAllSorted();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer w = response.getWriter();
        w.write("""
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <link rel="stylesheet" href="css/style.css">
                    <title>Список всех резюме</title>
                </head>
                <body>
                <section>
                <table border="1" cellpadding="8" cellspacing="0">
                    <tr>
                        <th>ID</th>
                        <th>Имя</th>
                        <th>Email</th>
                    </tr>
                """);

        for (Resume r : resumes) {
            w.write("<tr>\n" +
                    "<td><a href=\"resume?uuid=" + r.getUuid() + "\">" + r.getUuid() + "</a></td>\n" +
                    "<td>" + r.getFullName() + "</td>\n" +
                    "<td>" + r.getContact(ContactType.EMAIL) + "</td>\n" +
                    "</tr>\n");
        }
        w.write("""
                </table>
                </section>
                </body>
                </html>""");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
