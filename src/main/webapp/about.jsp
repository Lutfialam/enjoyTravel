<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Enjoy when travel to indonesia">
    <meta name="keywords" content="explore, enjoy, travel, indonesia">

    <title>About - Enjoy travel</title>

    <link rel="apple-touch-icon" sizes="180x180" href="assets/images/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/images/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon-16x16.png">
    <link rel="manifest" href="assets/images/site.webmanifest">

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
      rel="stylesheet"
    />
    <script src="https://cdn.tailwindcss.com?plugins=forms"></script>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>
    <div id="app" class="hidden">
        <nav class="main_nav fixed w-full bg-transparent py-1 z-50">
            <div class="container mx-auto px-8 sm:px-14 py-4">
                <div class="flex justify-between">
                    <div class="flex content-center">
                        <a href="/" class="link_nav_detail text-white flex content-center space-x-2">
                            <h1 class="flex self-center text-white text-xl link_nav_logo">ENJOY TRAVEL</h1>
                        </a>
                    </div>
                    <div class="flex content-center items-center space-x-3 sm:space-x-10 self-center">
                        <a href="/destination/home" class="link_nav_detail text-white">Destination</a>
                        <a href="#" class="link_nav_detail text-white border-b-2 border-indigo-500">About</a>
                        <a href="/contact" class="link_nav_detail text-white">Contact</a>
                        <c:if test="${isLoggedIn and isAdmin}">
                            <a
                                href="/dashboard"
                                class="text-white py-2 px-4 rounded-md bg-indigo-500 hover:bg-indigo-700"
                            >
                            Dashboard
                            </a>
                        </c:if>
                        <c:if test="${isLoggedIn and !isAdmin}">
                            <form
                                action="login"
                                class="cursor-pointer"
                                method="post"
                                id="formLogout"
                            >
                                <input type="hidden" name="logout" id="logout" value="logout">
                                <h1 class="text-white py-2 px-4 rounded-md bg-red-500">
                                    Logout
                                </h1>
                            </form>
                        </c:if>
                        <c:if test="${!isLoggedIn}">
                            <a href="/login" class="link_nav_detail text-white">
                                Login
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </nav>

        <div style="background-image: url('images/bag\ raja\ ampat.jpg'); background-position: center; background-repeat: no-repeat; background-size: cover;">
            <div class="h-screen w-screen bg-black bg-opacity-50">
                <div class="flex container px-8 sm:px-14 mx-auto h-screen w-screen">
                    <div class="w-full sm:w-7/12 flex flex-col self-center">
                        <h1 class="text-6xl mb-5 text-white">The more you <span class="text-indigo-500">know</span> the more you <span class="text-indigo-500">understand!</h1>
                    </div>
                </div>
            </div>
        </div>

        <div class="flex flex-col container px-8 sm:px-14 mx-auto justify-center py-20">
            <div class="flex">
                <h1 class="text-3xl mb-4 border-b-2 border-indigo-500">About Us</h1>
            </div>
            <h2>We are Enjoy Travel that present about travel destination in Indonesia. We are willing to attract tourism to visit indonesia and we provide info about travel destination in Indonesia so tourism can Choose where is the suitable place for them to visit.</h2>
            <div class="flex">
                <a href="/contact" class="py-3 px-7 outline-none rounded-full my-6 bg-transparent border-2 border-indigo-500 text-indigo-500 focus:outline-none focus:bg-indigo-500 focus:text-white focus:ring ring-indigo-400">Contact us</a>
            </div>
        </div>

        <footer class="w-full bg-gray-800">
            <div class="flex content-center justify-center py-10">
                <h1 class="text-white">Copyright &copy; Group 11 - all right reversed </h1>
            </div>
        </footer>
    </div>

    <div id="loading" class="h-screen w-screen flex justify-center flex-col">
        <h1 class="text-7xl hidden sm:flex self-center"><span class="text-indigo-500">Wait</span>&nbsp;and enjoy..</h1>
        <div class="w-1/12 flex self-center">
            <img src="assets/images/Pulse-1.8s-177px.gif">
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="assets/script.js"></script>
    <script>
        document.getElementById('formLogout').addEventListener('click', (e) => {
        e.preventDefault();
        document.getElementById('formLogout').submit();
      });
    </script>
</body>
</html>
