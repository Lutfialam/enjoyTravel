<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="Enjoy when travel to indonesia" />
    <meta name="keywords" content="explore, enjoy, travel, indonesia" />

    <title>${data.name} - Enjoy travel</title>

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
      rel="stylesheet"
    />
    <script src="https://cdn.tailwindcss.com?plugins=forms"></script>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/style.css"
    />
  </head>
  <body>
    <div id="app" class="hidden">
      <nav class="main_nav fixed w-full bg-transparent py-1 z-50">
        <div class="container mx-auto px-14 py-4">
          <div class="flex justify-between">
            <div class="flex content-center">
              <a
                href="/"
                class="link_nav_detail text-white flex content-center space-x-2"
              >
                <h1 class="flex self-center text-white text-xl link_nav_logo">
                  ENJOY TRAVEL
                </h1>
              </a>
            </div>
            <div
              class="flex content-center items-center space-x-3 sm:space-x-10 self-center"
            >
              <a href="/destination/home" class="link_nav_detail text-white">Destination</a>
              <a
                href="/about"
                class="link_nav_detail text-white px-3"  
              >
                About
              </a>
              <a
                href="/contact"
                class="link_nav_detail text-white"
              >
                Contact
              </a>
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
                <a href="/login" class="link_nav_detail text-white"
                  >Login</a
                >
              </c:if>
            </div>
          </div>
        </div>
      </nav>

      <div class="h-screen w-screen">
        <img
          src="${pageContext.request.contextPath}/assets/images/${data.background}"
          class="w-full h-full object-cover object-left-top"
        />
      </div>

      <div class="container mx-auto px-14 min-h-screen py-20 flex flex-wrap">
        <div class="w-1/2 pr-4 flex flex-col self-center">
          <div class="flex">
            <h1 class="text-3xl mb-3 border-b-2 border-indigo-500">
              ${data.name}
            </h1>
          </div>
          <p>${data.detail}</p>
          <div class="flex">
            <a
              href="https://google.com/search?q=${data.search_key}"
              class="py-3 px-7 outline-none rounded-full mt-8 bg-transparent border-2 border-indigo-500 text-indigo-500 focus:outline-none focus:bg-indigo-500 focus:text-white focus:ring ring-indigo-400"
              target="_blank"
              >More detail on google</a
            >
          </div>
        </div>
        <div class="w-1/2 flex self-center">
          <img
            src="${pageContext.request.contextPath}/assets/images/${data.photo}"
            class="w-full rounded-md"
          />
        </div>

        <div class="w-full mt-20">
          <h1 class="text-3xl mb-3 pb-2 border-b-2 border-indigo-500">
            People review about this place
          </h1>
          <c:forEach items="${data.review}" var="u" varStatus="loop">
            <div class="w-11/12 mx-auto border-b-2 py-2 border-gray-200">
              <h1 class="text-indigo-500 mb-2 capitalize">${u.name}</h1>
              <p class="ml-3">${u.comment}</p>
            </div>
          </c:forEach>
          <c:if test="${isLoggedIn == true}">
            <div class="w-full mt-5 flex py-5">
              <form
                action="/review/create"
                method="post"
                class="flex w-full gap-2"
              >
                <input type="hidden" name="destination_id" value="${data.id}" />
                <!-- class="py-2 px-2 rounded border-2 border-gray-400 w-10/12" -->
                <input
                  type="text"
                  class="focus:ring focus:ring-opacity-50 rounded-md shadow-sm border-gray-300 focus:border-indigo-300 focus:ring-indigo-200 w-10/12"
                  placeholder="Review this place"
                  name="comment"
                />
                <button
                  type="submit"
                  class="w-2/12 rounded bg-indigo-500 text-white"
                >
                  Submit
                </button>
              </form>
            </div>
          </c:if>
        </div>
      </div>
    </div>
    <div id="loading" class="h-screen w-screen flex justify-center flex-col">
      <h1 class="text-7xl flex self-center">
        <span class="text-indigo-500">Wait</span>&nbsp;and enjoy..
      </h1>
      <div class="w-1/12 flex self-center">
        <img
          src="${pageContext.request.contextPath}/assets/images/Pulse-1.8s-177px.gif"
        />
      </div>
    </div>

    <footer class="w-full bg-gray-800 mt-10">
      <div class="flex content-center justify-center py-10">
        <h1 class="text-white">
          Copyright &copy; Group 11 - all right reversed
        </h1>
      </div>
    </footer>

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
