<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.io.File" %>
<%
    List<File> files = (List<File>) request.getAttribute("files");
    String currentPath = (String) request.getAttribute("currentPath");
    String parentPath = (String) request.getAttribute("parentPath");
    String timestamp = request.getAttribute("timestamp").toString();
%>
<!DOCTYPE html>
<html lang="ru">
<head>

</head>
<body>
    <h2>Файловый менеджер</h2>
    <p>Дата: <%= timestamp %></p>
    <p>Текущий путь: <%= currentPath %></p>
    <% if (parentPath != null) { %>
        <p><a href="files?path=<%= parentPath %>">⬆Вверх⬆</a></p>
    <% } %>

    <table>
        <tr>
            <th>Имя</th>
            <th>Размер</th>
            <th>Дата изменения</th>
        </tr>
        <% for (File file : files) { %>
            <tr>
                <td>
                    <% if (file.isDirectory()) { %>
                        <a href="files?path=<%= file.getAbsolutePath() %>"><%= file.getName() %>/</a>
                    <% } else { %>
                        <a href="download?file=<%= file.getAbsolutePath() %>"><%= file.getName() %></a>
                    <% } %>
                </td>
                <td><%= file.isFile() ? file.length() + " B" : "-" %></td>
                <td><%= new java.util.Date(file.lastModified()) %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>
