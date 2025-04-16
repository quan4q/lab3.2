<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.io.File" %>
<%
    List<File> files = (List<File>) request.getAttribute("files");
    String currentPath = (String) request.getAttribute("currentPath");
    String parentPath = (String) request.getAttribute("parentPath");
    String timestamp = request.getAttribute("timestamp").toString();
    String userPathRoot = request.getAttribute("userPathRoot").toString();
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Файловый менеджер</title>
    <style>
        .logout-link {
            position: absolute;
            top: 10px;
            right: 10px;
            text-decoration: none;
            background-color: #f44336;
            color: white;
            padding: 8px 12px;
            border-radius: 5px;
            font-weight: bold;
            font-family: sans-serif;
        }

        .logout-link:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <a href="logout" class="logout-link">Выйти</a>

    <h2>Файловый менеджер</h2>
    <p>Дата: <%= timestamp %></p>
    <p>Текущий путь: <%= currentPath %></p>
    <% if (parentPath != null && !currentPath.equals(userPathRoot)) { %>
        <p><a href="files?path=<%= parentPath.replace('\\', '/') %>">⬆Вверх⬆</a></p>
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
                        <a href="files?path=<%= file.getAbsolutePath().replace('\\', '/') %>"><%= file.getName() %>/</a>
                    <% } else { %>
                        <a href="download?file=<%= file.getAbsolutePath().replace('\\', '/') %>"><%= file.getName() %></a>
                    <% } %>
                </td>
                <td><%= file.isFile() ? file.length() + " B" : "-" %></td>
                <td><%= new java.util.Date(file.lastModified()) %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>
