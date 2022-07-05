$(window).on('load', function () {
  $('#app').removeClass('hidden');
  $('#loading').addClass('hidden');
});

$(document).ready(function () {
  const nav = document.querySelector('.main_nav');
  const linked = document.querySelectorAll('.link_nav');
  const linked_logo = document.querySelector('.link_nav_logo');
  const link_nav_detail = document.querySelectorAll('.link_nav_detail');
  const available_email = [
    'example@email.com',
    'example@gmail.com',
    'email@example.com',
  ];

  window.addEventListener('scroll', () => {
    window.scrollY > 30
      ? (nav.classList.add('bg-white', 'shadow-xl'),
        nav.classList.remove('bg-transparent'),
        linked_logo.classList.remove('text-white'),
        linked_logo.classList.add('text-indigo-500'),
        link_nav_detail.forEach((item) => item.classList.add('text-black')),
        link_nav_detail.forEach((item) => item.classList.remove('text-white')))
      : (nav.classList.remove('bg-white', 'shadow-xl'),
        nav.classList.add('bg-transparent'),
        linked_logo.classList.add('text-white'),
        linked_logo.classList.remove('text-indigo-500'),
        link_nav_detail.forEach((item) => item.classList.add('text-white')),
        link_nav_detail.forEach((item) => item.classList.remove('text-black')));
  });

  const validateEmail = (email) => {
    var rules = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    return rules.test(email);
  };

  const subs_error = (message) => {
    $('#subscribe_mail').removeClass('ring-indigo-400');
    $('#subscribe_mail').addClass(
      'ring-red-500 border-2 border-red-700 placeholder-red-600 text-red-700'
    );
    $('#error_mail_subs').text(message);

    setTimeout(() => {
      $('#error_mail_subs').text('');
      $('#subscribe_mail').addClass('ring-indigo-400');
      $('#subscribe_mail').removeClass(
        'ring-red-500 border-2 border-red-700 placeholder-red-600 text-red-700'
      );
    }, 3000);
  };

  $('#subs_button').on('click', () => {
    if ($('#subscribe_mail').val().length <= 0) {
      subs_error('This field is required!');
    } else if (available_email.includes($('#subscribe_mail').val())) {
      subs_error('Email is already registered!');
    } else if (!validateEmail($('#subscribe_mail').val())) {
      subs_error('Email is not valid!');
    } else {
      $('#alert').removeClass('hidden');
      setTimeout(() => {
        $('#alert').addClass('hidden');
        $('#subscribe_mail').val('');
      }, 5000);
    }
  });

  $('#close_alert').on('click', () => {
    $('#alert').addClass('hidden');
  });

  $('#destination_search').on('search', () => {
    const value = $('#destination_search').val();

    $.ajax({
      url: '/DestinationSearch?keyword=' + value,
      method: 'GET',
      success: (result) => {
        let content = '';

        result.map((data, index) => {
          content += `<tr class="hover:bg-grey-lighter max-h-20">
                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${
                          index + 1
                        }</td>
                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${
                          data.name
                        }</td>
                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${
                          data.detail
                        }</td>
                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${
                          data.search_key
                        }</td>
                        <td class="py-4 px-6 border-b border-gray-200">
                            <img src="assets/images/${
                              data.photo
                            }" class="w-3/5">
                        </td>
                        <td class="py-4 px-6 border-b border-gray-200">
                            <img src="assets/images/${
                              data.background
                            }" class="w-3/5">
                        </td>
                        <td class="py-4 px-6 border-b border-gray-200">
                            <div class="space-x-2 flex">
                                <form action="/destination/detail" method="post">
                                    <input type="hidden" value="${
                                      data.id
                                    }" name="id">
                                    <input type="hidden" value="edit" name="type">
                                    <button class="flex self-center bg-indigo-500 px-5 py-1 rounded text-white">Edit</button>
                                </form>
                                <form action="/destination/detail" method="post">
                                    <input type="hidden" value="${
                                      data.id
                                    }" name="id">
                                    <input type="hidden" value="delete" name="type">
                                    <button class="flex self-center bg-red-400 px-5 py-1 rounded text-white">Delete</button>
                                </form>
                            </div>
                        </td>
                    </tr>`;
        });

        $('#destination_detail tbody').html(content);
      },
    });
  });

  $('#user_search').on('search', () => {
    const value = $('#user_search').val();
    $.ajax({
      url: '/UserSearch?keyword=' + value,
      method: 'GET',
      success: (result) => {
        console.log(result);
        let content = '';

        result.map((data, index) => {
          content += `<tr class="hover:bg-grey-lighter max-h-20">
                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${
                          index + 1
                        }</td>
                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${
                          data.name
                        }</td>
                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${
                          data.email
                        }</td>
                        <td class="py-4 px-6 border-b border-gray-200 text-xs">${
                          data.level
                        }</td>
                        <td class="py-4 px-6 border-b border-gray-200">
                            <div class="space-x-2 flex">
                                <form action="UserDetail" method="post">
                                    <input type="hidden" value="${
                                      data.id
                                    }" name="id">
                                    <input type="hidden" value="edit" name="type">
                                    <button class="flex self-center bg-indigo-500 px-5 py-1 rounded text-white">Edit</button>
                                </form>
                                <form action="UserDetail" method="post">
                                    <input type="hidden" value="${
                                      data.id
                                    }" name="id">
                                    <input type="hidden" value="delete" name="type">
                                    <button class="flex self-center bg-red-400 px-5 py-1 rounded text-white">Delete</button>
                                </form>
                            </div>
                        </td>
                    </tr>`;
        });

        $('#user_detail tbody').html(content);
      },
    });
  });

  document.querySelector('#menu').addEventListener('click', () => {
    document.querySelector('#sidebar').classList.toggle('-translate-x-full');
    setTimeout(() => {
      document.querySelector('#outer_sidebar').classList.toggle('hidden');
    }, 500);
  });

  document.querySelector('#outer_sidebar').addEventListener('click', () => {
    document.querySelector('#sidebar').classList.toggle('-translate-x-full');
    document.querySelector('#outer_sidebar').classList.toggle('hidden');
  });
});

document.getElementById('formLogout').addEventListener('click', (e) => {
  e.preventDefault();
  document.getElementById('formLogout').submit();
});

const sidebarContent = document.getElementById('sidebar-content');

const isActive = (location) => {
  const url = new URL(window.location.href);
  return url.pathname.includes(location);
};

const createSidebarItem = (text, link, icon, active) => {
  let item = `
        <li class="border-l-4 border-indigo-400 px-4 flex space-x-4 py-2">
            <span class="material-icons-outlined ${
              active ? 'text-indigo-400' : 'text-gray-400'
            }">
                ${icon}
            </span>
            <a href="${link}" class="${
    active ? 'text-indigo-400' : 'text-gray-400'
  } text-md font-bold">
                ${text}
            </a>
        </li>
    `;
  return item;
};

sidebarContent.appendChild(
  createSidebarItem(
    'Dashboard',
    '/dashboard',
    'dashboard',
    isActive('/dashboard')
  )
);
sidebarContent.appendChild(
  createSidebarItem(
    'Destination',
    '/destination',
    'flight_takeoff',
    isActive('/destination')
  )
);
sidebarContent.appendChild(
  createSidebarItem('User', '/UserServlet', 'people', isActive('/user'))
);
