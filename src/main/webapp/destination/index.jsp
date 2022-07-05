<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Destination list</title>

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined"
      rel="stylesheet"
    />
    <script src="https://cdn.tailwindcss.com?plugins=forms"></script>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>
    
    <div id="app" class="flex min-h-screen">
        <div class="transform md:translate-x-0 -translate-x-full flex w-full fixed md:relative md:w-1/5 min-h-full transition duration-500 ease-in-out" id="sidebar">
            <aside class="flex flex-col w-2/3 md:w-full bg-white min-h-full shadow-2xl justify-between">
                <div class="flex flex-col h-full">
                    <div class="flex justify-center w-full h-16">
                        <h1 class="text-center flex self-center">ENJOYT</h1>
                    </div>
                    <div class="flex w-full mt-5">
                        <ul class="space-y-2">
                            <li class="text-gray-400 text-sm pl-3 mb-2">Dashboard</li>
                            <li class="border-l-4 border-transparent px-4 flex space-x-4 py-2">
                                <span class="material-icons-outlined text-gray-400">dashboard</span>
                                <a href="/dashboard" class="text-md text-gray-400 font-bold">Dashboard</a>
                            </li>
                            <li class="border-l-4 border-indigo-400 px-4 flex space-x-4 py-2">
                                <span class="material-icons-outlined text-indigo-400">flight_takeoff</span>
                                <a href="" class="text-md text-indigo-400 font-bold">Destination</a>
                            </li>
                            <li class="border-l-4 border-transparent px-4 flex space-x-4 py-2">
                                <span class="material-icons-outlined text-gray-400">people</span>
                                <a href="/UserServlet" class="text-md text-gray-400 font-bold">User</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="w-full p-5 flex">
                    <a
                      href="/"
                      class="bg-indigo-500 px-5 py-2 rounded text-white w-full text-center flex items-center justify-center space-x-4"
                    >
                      Go to home
                    </a>
                </div>
            </aside>
            <div class="hidden w-1/3 h-full bg-black bg-opacity-50" id="outer_sidebar"></div>
        </div>
        <div class="flex flex-col w-full md:w-4/5 min-h-full bg-gray-200">
            <nav class="w-full bg-indigo-500 py-2 flex justify-between px-4 md:px-10 mx-auto text-white h-16">
                <div class="flex self-center">
                    <span class="material-icons-outlined md:hidden" id="menu">menu</span>
                    
                </div>
                <div class="flex self-center">
                    <ul>
                        <li class="flex content-center space-x-4">
                            <div class="w-10 rounded-full overflow-hidden">
                                <img src="assets/images/avatar-1.png" class="w-full">
                            </div>
                            <div class="flex self-center">
                                <div class="dropdown flex justify-center">
                                    <a class="dropbtn no-underline text__navigation flex content-center">
                                        <span class="mr-1 hidden md:flex text-white">Hi, ${name}!</span>
                                        <svg class="fill-current h-4 w-4 flex self-center" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/> </svg>
                                    </a>
                
                                    <div class="dropdown-content w-2/6 md:w-1/6 rounded-lg mr-12 mt-5 right-3">
                                        <a href="/Profile" class="hover:bg-indigo-500 hover:text-white pl-6 font-light text-xs font-semibold">
                                            <div class="flex flex-row content-center w-full h-full text-gray-500 hover:text-white py-3">
                                                <span class="material-icons-outlined">people</span>
                                                <h1 class="flex self-center ml-4">Profile</h1>
                                            </div>
                                        </a>
                                        <a href="#" class="hover:bg-indigo-500 hover:text-white pl-6 font-light text-xs font-semibold">
                                            <div class="flex flex-row content-center w-full h-full text-gray-500 hover:text-white py-3">
                                                <span class="material-icons-outlined">people</span>
                                                <h1 class="flex self-center ml-4">Setting</h1>
                                            </div>
                                        </a>
                                        <form action="login" class="hover:bg-red-400 hover:text-white pl-6 font-light text-xs font-semibold" method="post" id="formLogout">
                                            <div class="flex flex-row content-center w-full h-full text-red-400 hover:text-white py-3">
                                                <span class="material-icons-outlined">people</span>
                                                <input type="hidden" name="logout" id="logout" value="logout">
                                                <h1 class="flex self-center ml-4">Logout</h1>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>

            <main class="flex flex-col bg-gray-100 flex-grow" id="body">
                <div class="bg-white py-5 px-4 md:px-10">
                    <h1 class="text-2xl font-semibold">Destination</h1>
                </div>
                <div class="container mt-5 px-4 md:px-10 mx-auto">
                    <div class="flex justify-between mt-4">
                        <a href="/destination/create" class="content-center bg-indigo-500 px-5 py-2 rounded text-white">Add data</a>
                        <div class="flex hidden">
                            <h1 class="self-center">Search</h1>
                            <input type="search" class="border-2 border-gray-400 ml-2 focus:outline-none focus:ring ring-blue-400 rounded-md" id="destination_search">
                        </div>
                    </div>
                    <div class="bg-white shadow-xl rounded-md my-4">
                        <table class="text-left w-full" id="destination_detail">
                            <thead>
                                <tr>
                                    <th class="py-4 px-6 bg-gray-200 font-bold text-sm border-b border-gray-200">#</th>
                                    <th class="py-4 px-6 bg-gray-200 font-bold text-sm border-b border-gray-200">Name</th>
                                    <th class="py-4 px-6 bg-gray-200 font-bold text-sm border-b border-gray-200">Detail</th>
                                    <th class="py-4 px-6 bg-gray-200 font-bold text-sm border-b border-gray-200">Search key</th>
                                    <th class="py-4 px-6 bg-gray-200 font-bold text-sm border-b border-gray-200">Photo</th>
                                    <th class="py-4 px-6 bg-gray-200 font-bold text-sm border-b border-gray-200">Background</th>
                                    <th class="py-4 px-6 bg-gray-200 font-bold text-sm border-b border-gray-200">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${data}" var="u" varStatus="loop">
                                    <tr class="hover:bg-grey-lighter max-h-20">
                                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${loop.index}</td>
                                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${u.name}</td>
                                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${u.detail}</td>
                                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${u.search_key}</td>
                                        <td class="py-4 px-6 border-b border-gray-200">
                                            <img src="assets/images/${u.photo}" class="w-3/5">
                                        </td>
                                        <td class="py-4 px-6 border-b border-gray-200">
                                            <img src="assets/images/${u.background}" class="w-3/5">
                                        </td>
                                        <td class="py-4 px-6 border-b border-gray-200">
                                            <div class="space-x-2 flex">
                                                <a href="/destination/detail?id=${u.id}" class="flex self-center bg-indigo-500 px-5 py-1 rounded text-white">
                                                    Edit
                                                </a>
                                                <!-- <form action="/destination/detail" method="post">
                                                    <input type="hidden" value="${u.id}" name="id" id="id">
                                                    <input type="hidden" value="edit" name="type" id="type">
                                                    <button class="flex self-center bg-indigo-500 px-5 py-1 rounded text-white">Edit</button>
                                                </form> -->
                                                <form action="/destination/detail" method="post">
                                                    <input type="hidden" value="${u.id}" name="id">
                                                    <input type="hidden" value="delete" name="type">
                                                    <button class="flex self-center bg-red-400 px-5 py-1 rounded text-white">Delete</button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>

            <footer class="flex justify-between items-center h-14 px-10 py-50 mx-auto w-full bg-white">
                <div class="text-gray-400">Copyright &copy; 2022</div>
                <div class="">
                    <input type="radio" name="" id="">
                </div>
            </footer>
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
