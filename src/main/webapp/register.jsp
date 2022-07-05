<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Enjoy travel register</title>

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
    <link rel="stylesheet" href="assets/style.css" />
  </head>

  <body class="overflow-hidden">
    <nav class="main_nav fixed w-full bg-transparent py-1 z-40">
      <div class="container mx-auto px-8 sm:px-14">
        <div class="flex justify-between">
          <div class="flex content-center">
            <a
              href="/"
              class="link_nav_detail text-white flex content-center mt-2 space-x-2"
            >
              <h1 class="flex self-center text-white text-xl link_nav_logo">
                ENJOY TRAVEL
              </h1>
            </a>
          </div>
        </div>
      </div>
    </nav>

    <div id="app" class="flex justify-end">
      <div
        class="flex w-2/5 fixed overflow-hidden h-full bg-white bg-opacity-30 backdrop-filter backdrop-blur content-center justify-center rounded-tl-2xl rounded-bl-2xl z-20"
      >
        <div class="w-11/12 bg-white rounded-2xl self-center shadow-2xl p-8">
          <h1 class="text-center text-3xl font-bold">Register</h1>
          <form action="/register" method="post">
            <div class="w-full flex flex-col my-3">
              <label for="name">Name</label>
              <input
                class="focus:ring focus:ring-opacity-50 rounded-md shadow-sm border-gray-300 focus:border-indigo-300 focus:ring-indigo-200 w-11/12"
                type="text"
                name="name"
                id="name"
              />
            </div>
            <div class="w-full flex flex-col my-3">
              <label for="email">E-mail</label>
              <input
                class="focus:ring focus:ring-opacity-50 rounded-md shadow-sm border-gray-300 focus:border-indigo-300 focus:ring-indigo-200 w-11/12"
                type="email"
                name="email"
                id="email"
              />
            </div>
            <div class="w-full flex flex-col my-3">
              <label for="password">Password</label>
              <input
                class="focus:ring focus:ring-opacity-50 rounded-md shadow-sm border-gray-300 focus:border-indigo-300 focus:ring-indigo-200 w-11/12"
                type="password"
                name="password"
                id="password"
              />
            </div>
            <div class="w-full flex flex-col my-3">
              <label for="password_confirmation">Password Confirmation</label>
              <input
                class="focus:ring focus:ring-opacity-50 rounded-md shadow-sm border-gray-300 focus:border-indigo-300 focus:ring-indigo-200 w-11/12"
                type="password"
                name="password_confirmation"
                id="password_confirmation"
              />
            </div>
            <p>
              Do you have account? login at
              <a href="/login" class="link_nav_detail text-indigo-400">here!</a>
            </p>

            <button
              type="submit"
              class="mt-3 bg-indigo-500 px-5 py-2 rounded text-white"
            >
              Register
            </button>
          </form>
        </div>
      </div>
      <div class="w-full h-full fixed bg-black bg-opacity-50"></div>
      <div class="w-full">
        <img
          src="assets/images/bali.jpeg"
          alt=""
          class="w-full h-full bg-cover"
        />
      </div>
    </div>
  </body>
</html>
