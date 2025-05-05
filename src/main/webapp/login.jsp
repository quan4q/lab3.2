<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login/Register</title>
    <style>
        :root {
            --bg-light: linear-gradient(135deg, #667eea, #764ba2);
            --bg-dark: linear-gradient(135deg, #1e1e2f, #2c2c3c);
            --text-light: #fff;
            --text-dark: #333;
        }

        body {
            margin: 0;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background: var(--bg-light);
            transition: background 0.5s;
        }

        body.dark {
            background: var(--bg-dark);
        }

        .container {
            max-width: 400px;
            margin: 80px auto;
            padding: 30px;
            background-color: white;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            animation: fadeIn 1s ease;
        }

        body.dark .container {
            background-color: #2c2c3c;
            color: white;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
        }

        .form-group {
            position: relative;
            margin-bottom: 20px;
        }

        .form-group input {
            width: 100%;
            padding: 12px 40px 12px 40px;
            border: 1px solid #ccc;
            border-radius: 12px;
            font-size: 16px;
            transition: 0.3s;
        }

        .form-group input:focus {
            border-color: #667eea;
            box-shadow: 0 0 8px rgba(102, 126, 234, 0.4);
        }

        .form-group input:invalid {
            border-color: #e57373;
        }

        .form-group label {
            position: absolute;
            top: 12px;
            left: 40px;
            pointer-events: none;
            color: #999;
            transition: 0.2s ease;
        }

        .form-group input:focus + label,
        .form-group input:not(:placeholder-shown) + label {
            top: -8px;
            left: 30px;
            font-size: 12px;
            color: #667eea;
            background-color: white;
            padding: 0 4px;
        }

        .form-group .icon {
            position: absolute;
            left: 12px;
            top: 50%;
            transform: translateY(-50%);
            font-size: 18px;
            color: #667eea;
        }

        .btn {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 12px;
            color: white;
            font-weight: bold;
            font-size: 16px;
            background: linear-gradient(to right, #667eea, #764ba2);
            cursor: pointer;
            transition: 0.3s;
            margin-top: 10px;
        }

        .btn:hover {
            opacity: 0.9;
            transform: scale(1.02);
        }

        .theme-toggle {
            position: absolute;
            top: 20px;
            right: 30px;
            background: transparent;
            border: 2px solid white;
            color: white;
            padding: 5px 10px;
            border-radius: 8px;
            cursor: pointer;
            font-size: 14px;
            transition: 0.3s;
        }

        .alert {
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            font-size: 14px;
        }

        .alert-success {
            background-color: #c8e6c9;
            color: #2e7d32;
        }

        .alert-error {
            background-color: #ffcdd2;
            color: #c62828;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body>
<button class="theme-toggle" onclick="toggleTheme()">🌗 Тема</button>

<div class="container">
    <h2>Login or Register</h2>

    <form method="post" action="">
        <div class="form-group">
            <span class="icon">👤</span>
            <input type="text" name="login" placeholder=" " required>
            <label>Login</label>
        </div>
        <div class="form-group">
            <span class="icon">🔒</span>
            <input type="password" name="password" placeholder=" " required>
            <label>Password</label>
        </div>
        <div class="form-group">
            <span class="icon">✉️</span>
            <input type="email" name="email" placeholder=" ">
            <label>Email (для регистрации)</label>
        </div>
        <button class="btn" type="submit" name="action" value="login">Войти</button>
        <button class="btn" type="submit" name="action" value="register">Зарегистрироваться</button>
    </form>
</div>

<script>
    function toggleTheme() {
        document.body.classList.toggle("dark");
    }
</script>
</body>
</html>
