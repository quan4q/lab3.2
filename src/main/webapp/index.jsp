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
        * {
            box-sizing: border-box;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 40px 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
            color: #333;
        }

        .container {
            background-color: white;
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 900px;
            animation: fadeIn 1s ease;
        }

        h2 {
            margin-bottom: 20px;
            font-size: 28px;
            text-align: center;
        }

        p {
            margin: 10px 0;
            font-size: 16px;
        }

        a {
            color: #667eea;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        .logout-link {
            position: absolute;
            top: 20px;
            right: 30px;
            background-color: #e53935;
            color: white;
            padding: 10px 18px;
            border-radius: 10px;
            text-decoration: none;
            font-weight: bold;
            transition: background 0.3s;
        }

        .logout-link:hover {
            background-color: #c62828;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 14px 16px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f4f4f4;
            font-size: 16px;
        }

        tr:hover {
            background-color: #f0f0ff;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        @media (max-width: 600px) {
            th, td {
                padding: 10px;
                font-size: 14px;
            }

            .logout-link {
                padding: 8px 12px;
                font-size: 14px;
            }
        }

        .timestamp-widget {
            display: flex;
            align-items: center;
            background: linear-gradient(90deg, #e0e7ff, #f3e8ff);
            padding: 12px 16px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(102, 126, 234, 0.2);
            margin: 15px 0;
            font-size: 16px;
            color: #333;
            gap: 10px;
            animation: fadeIn 1s ease;
        }

        .clock-icon {
            font-size: 20px;
            color: #667eea;
        }

        /* Hover effect for timestamp widget */
        .timestamp-widget:hover {
            box-shadow: 0 8px 20px rgba(102, 126, 234, 0.4);
            transform: translateY(-5px);
            transition: box-shadow 0.3s ease, transform 0.3s ease;
        }

        /* Add a slight animation to table rows on hover */
        tr:hover {
            background-color: #f0f0ff;
            transform: scale(1.02);
            transition: transform 0.2s ease;
        }

        .current-path {
            background: linear-gradient(90deg, #e0e7ff, #f3e8ff);
            padding: 14px 18px;
            border-radius: 12px;
            margin: 15px 0;
            color: #333;
            font-size: 16px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            box-shadow: 0 4px 12px rgba(102, 126, 234, 0.2);
            animation: fadeIn 1s ease;
        }

        .current-path span {
            font-weight: bold;
            color: #667eea;
        }

    </style>
</head>
<body>
<a href="logout" class="logout-link">Выйти</a>

<div class="container">
    <h2>Файловый менеджер</h2>
    <div class="timestamp-widget">
        <span class="clock-icon">⏰</span>
        <span class="timestamp-text">Дата и время: <%= timestamp %></span>
    </div>

    <!-- Стиль для текущего пути -->
    <div class="current-path">
        <span>Текущий путь:</span>
        <span><%= currentPath %></span>
    </div>

    <% if (parentPath != null && !currentPath.equals(userPathRoot)) { %>
        <p><a href="files?path=<%= parentPath.replace('\\', '/') %>">⬆ Вверх</a></p>
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
</div>
</body>
</html>
