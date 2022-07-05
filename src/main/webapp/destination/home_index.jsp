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

    <title>Destination - Enjoy travel</title>

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
      rel="stylesheet"
    />
    <script src="https://cdn.tailwindcss.com?plugins=forms"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
    <div id="app" class="hidden">
        <nav class="main_nav fixed w-full bg-transparent py-1 z-50">
            <div class="container mx-auto px-10 sm:px-14 py-4">
                <div class="flex justify-between">
                    <div class="flex content-center">
                        <a href="/" class="link_nav_detail text-white flex content-center space-x-2">
                            <h1 class="flex self-center text-white text-xl link_nav_logo">ENJOY TRAVEL</h1>
                        </a>
                    </div>
                    <div class="flex content-center items-center space-x-3 sm:space-x-10 self-center">
                        <a href="#" class="link_nav_detail text-white border-b-2 border-indigo-500">Destination</a>
                        <a href="/about" class="link_nav_detail text-white">About</a>
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
                            <a href="/login" class="link_nav_detail text-white">Login</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </nav>

        <div style="background-image: url('${pageContext.request.contextPath}/assets/images/bag\ bromo.jpg'); background-position: center; background-repeat: no-repeat; background-size: cover;" class="flex h-screen w-full ">
            <div class="flex h-screen w-full bg-black bg-opacity-50 ">
                <div class="flex content-center container mx-auto px-10 sm:px-14">
                    <div class="w-full sm:w-1/2 flex self-center flex-col">
                        <h1 class="text-6xl mb-5 text-white">The <span class="text-indigo-500">destination</span> you are looking for is here!</h1>
                        <h1 class="text-white">Feel The Exotic Indonesia, VISIT INDONESIA</h1>
                        <div class="flex">
                            <a href="/" class="py-3 px-7 outline-none rounded-full mt-8 bg-indigo-500 text-white focus:outline-none focus:ring ring-indigo-400">Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container px-10 sm:px-14 mb-20 mx-auto w-full h-full">
            <div class="flex justify-center my-12">
                <h1 class="text-4xl">All destination</h1>
            </div>
            <div class="flex flex-wrap mx-auto gap-2">
                <c:forEach items="${data}" var="u" varStatus="loop">
                    <div class="percent_24 min-h-44 h-44 max-h-44 shadow-2xl rounded-lg overflow-hidden">
                        <a href="/destination/home/detail?id=${u.id}">
                            <img src="${pageContext.request.contextPath}/assets/images/${u.photo}" class="w-full h-full z-10 object-cover">
                            <div class="-mt-12 h-12 w-full flex content-center justify-center z-20">
                                <div class="flex flex-col self-center bg-gray-200 bg-opacity-50 w-full h-full">
                                    <h1 class="text-center my-3">${u.name}</h1>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
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
            <img src="${pageContext.request.contextPath}/assets/images/Pulse-1.8s-177px.gif">
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/script.js"></script>
    <script>
        document.getElementById('formLogout').addEventListener('click', (e) => {
        e.preventDefault();
        document.getElementById('formLogout').submit();
      });
    </script>
</body>
</html>
