<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Document</title>

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
  <body>
    <div id="app" class="flex min-h-screen">
      <div
        class="transform md:translate-x-0 -translate-x-full flex w-full fixed md:relative md:w-1/5 min-h-full transition duration-500 ease-in-out"
        id="sidebar"
      >
        <aside
          class="flex flex-col w-2/3 md:w-full bg-white h-full shadow-2xl justify-between"
        >
          <div class="flex flex-col h-full">
            <div class="flex justify-center w-full h-16">
              <h1 class="text-center flex self-center">ENJOYT</h1>
            </div>
            <div class="flex w-full mt-5">
              <ul class="space-y-2">
                <li class="text-gray-400 text-sm pl-3 mb-2">Dashboard</li>
                <li
                  class="border-l-4 border-indigo-400 px-4 flex space-x-4 py-2"
                >
                  <span class="material-icons-outlined text-indigo-400"
                    >dashboard</span
                  >
                  <a href="" class="text-md text-indigo-400 font-bold"
                    >Dashboard</a
                  >
                </li>
                <li
                  class="border-l-4 border-transparent px-4 flex space-x-4 py-2"
                >
                  <span class="material-icons-outlined text-gray-400"
                    >flight_takeoff</span
                  >
                  <a href="/destination" class="text-md text-gray-400 font-bold"
                    >Destination</a
                  >
                </li>
                <li
                  class="border-l-4 border-transparent px-4 flex space-x-4 py-2"
                >
                  <span class="material-icons-outlined text-gray-400"
                    >people</span
                  >
                  <a href="/UserServlet" class="text-md text-gray-400 font-bold"
                    >User</a
                  >
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
        <div
          class="hidden w-1/3 h-full bg-black bg-opacity-50"
          id="outer_sidebar"
        ></div>
      </div>
      <div class="flex flex-col w-full md:w-4/5 min-h-full bg-gray-200">
        <nav
          class="w-full bg-indigo-500 py-2 flex justify-between px-4 md:px-10 mx-auto text-white"
        >
          <div class="flex self-center">
            <span class="material-icons-outlined md:hidden" id="menu"
              >menu</span
            >
          </div>
          <div class="flex self-center">
            <ul>
              <li class="flex content-center space-x-4">
                <div class="w-10 rounded-full overflow-hidden">
                  <img src="assets/images/avatar-1.png" class="w-full" />
                </div>
                <div class="flex self-center">
                  <div class="dropdown flex justify-center">
                    <a
                      class="dropbtn no-underline text__navigation flex content-center"
                    >
                      <span class="mr-1 hidden md:flex">Hi, ${name}</span>
                      <svg
                        class="fill-current h-4 w-4 flex self-center"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 20 20"
                      >
                        <path
                          d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"
                        />
                      </svg>
                    </a>

                    <div
                      class="dropdown-content w-2/6 md:w-1/6 rounded-lg mr-12 mt-5 right-3"
                    >
                      <a
                        href="/Profile"
                        class="hover:bg-indigo-500 hover:text-white pl-6 font-light text-xs font-semibold"
                      >
                        <div
                          class="flex flex-row content-center w-full h-full text-gray-500 hover:text-white py-3"
                        >
                          <span class="material-icons-outlined">people</span>
                          <h1 class="flex self-center ml-4">Profile</h1>
                        </div>
                      </a>
                      <a
                        href="#"
                        class="hover:bg-indigo-500 hover:text-white pl-6 font-light text-xs font-semibold"
                      >
                        <div
                          class="flex flex-row content-center w-full h-full text-gray-500 hover:text-white py-3"
                        >
                          <span class="material-icons-outlined">people</span>
                          <h1 class="flex self-center ml-4">Setting</h1>
                        </div>
                      </a>
                      <form
                        action="login"
                        class="hover:bg-red-400 hover:text-white pl-6 font-light text-xs font-semibold"
                        method="post"
                        id="formLogout"
                      >
                        <div
                          class="flex flex-row content-center w-full h-full text-red-400 hover:text-white py-3"
                        >
                          <span class="material-icons-outlined">people</span>
                          <input
                            type="hidden"
                            name="logout"
                            id="logout"
                            value="logout"
                          />
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
            <h1 class="text-2xl font-semibold">Dashboard</h1>
          </div>
          <div class="container px-4 md:px-10 mx-auto mt-5">
            <h1>Hello world!</h1>
          </div>
        </main>

        <footer
          class="flex justify-between items-center h-14 px-10 py-50 mx-auto w-full bg-white"
        >
          <div class="text-gray-400">Copyright &copy; 2022</div>
          <div class="">
            <input type="radio" name="" id="" />
          </div>
        </footer>
      </div>
    </div>

    <script>
      document.getElementById('formLogout').addEventListener('click', (e) => {
        console.log('kepence6 cuy');
        e.preventDefault();
        document.getElementById('formLogout').submit();
      });
      document.querySelector('#menu').addEventListener('click', () => {
        document
          .querySelector('#sidebar')
          .classList.toggle('-translate-x-full');
        setTimeout(() => {
          document.querySelector('#outer_sidebar').classList.toggle('hidden');
        }, 500);
      });

      document.querySelector('#outer_sidebar').addEventListener('click', () => {
        document
          .querySelector('#sidebar')
          .classList.toggle('-translate-x-full');
        document.querySelector('#outer_sidebar').classList.toggle('hidden');
      });
    </script>
  </body>
</html>
