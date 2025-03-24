@import gg.jte.Content
@param Content content

<!DOCTYPE html>
<html lang="en"> <!--begin::Head-->

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Autonomy register</title><!--begin::Primary Meta Tags-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="title" content="Autonomy register">
  <meta name="author" content="ColorlibHQ">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fontsource/source-sans-3@5.0.12/index.css" integrity="sha256-tXJfXfp6Ewt1ilPzLDtQnJV4hclT9XuaZUKyUvmyr+Q=" crossorigin="anonymous"><!--end::Fonts--><!--begin::Third Party Plugin(OverlayScrollbars)-->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/overlayscrollbars@2.3.0/styles/overlayscrollbars.min.css" integrity="sha256-dSokZseQNT08wYEWiz5iLI8QPlKxG+TswNRD8k35cpg=" crossorigin="anonymous"><!--end::Third Party Plugin(OverlayScrollbars)--><!--begin::Third Party Plugin(Bootstrap Icons)-->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.min.css" integrity="sha256-Qsx5lrStHZyR9REqhUF8iQt73X06c8LGIUPzpOhwRrI=" crossorigin="anonymous"><!--end::Third Party Plugin(Bootstrap Icons)--><!--begin::Required Plugin(AdminLTE)-->
  <link rel="stylesheet" href="/css/adminlte.css"><!--end::Required Plugin(AdminLTE)--><!-- apexcharts -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/apexcharts@3.37.1/dist/apexcharts.css" integrity="sha256-4MX+61mt9NVvvuPjUWdUdyfZfxSB1/Rf9WtqRHgG5S0=" crossorigin="anonymous">
  <link rel="stylesheet" href="/css/font-awesome.min.css" >
</head>

<body class="layout-fixed sidebar-expand-lg bg-body-tertiary"> <!--begin::App Wrapper-->
<div class="app-wrapper"> <!--begin::Header-->
  <nav class="app-header navbar navbar-expand bg-body"> <!--begin::Container-->
    <div class="container-fluid"> <!--begin::Start Navbar Links-->
      <ul class="navbar-nav">
        <li class="nav-item"> <a class="nav-link" data-lte-toggle="sidebar" href="#" role="button"> <i class="bi bi-list"></i> </a> </li>
        <li class="nav-item d-none d-md-block"> <a href="/" class="nav-link">Главная</a> </li>
        <li class="nav-item d-none d-md-block"> <a href="/contact" class="nav-link">Контакты</a> </li>
        <li class="nav-item d-none d-md-block"> <a href="/agreement" class="nav-link">Соглашение</a> </li>
      </ul> <!--end::Start Navbar Links--> <!--begin::End Navbar Links-->
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <div id="version"></div>
        </li>
      </ul>
      <ul class="navbar-nav ms-auto"> <!--begin::Navbar Search-->
        <li class="nav-item"> <a class="nav-link" data-widget="navbar-search" href="#" role="button"> <i class="bi bi-search"></i> </a> </li> <!--end::Navbar Search--> <!--begin::Messages Dropdown Menu-->
        <!--<li class="nav-item dropdown"> <a class="nav-link" data-bs-toggle="dropdown" href="#"> <i class="bi bi-chat-text"></i> <span class="navbar-badge badge text-bg-danger">3</span> </a>
          <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end"> <a href="#" class="dropdown-item">
            <div class="d-flex">
              <div class="flex-shrink-0"> <img src="/assets/img/user1-128x128.jpg" alt="User Avatar" class="img-size-50 rounded-circle me-3"> </div>
              <div class="flex-grow-1">
                <h3 class="dropdown-item-title">
                  Brad Diesel
                  <span class="float-end fs-7 text-danger"><i class="bi bi-star-fill"></i></span>
                </h3>
                <p class="fs-7">Call me whenever you can...</p>
                <p class="fs-7 text-secondary"> <i class="bi bi-clock-fill me-1"></i> 4 Hours Ago
                </p>
              </div>
            </div>
          </a>
            <div class="dropdown-divider"></div> <a href="#" class="dropdown-item">
              <div class="d-flex">
                <div class="flex-shrink-0"> <img src="/assets/img/user8-128x128.jpg" alt="User Avatar" class="img-size-50 rounded-circle me-3"> </div>
                <div class="flex-grow-1">
                  <h3 class="dropdown-item-title">
                    John Pierce
                    <span class="float-end fs-7 text-secondary"> <i class="bi bi-star-fill"></i> </span>
                  </h3>
                  <p class="fs-7">I got your message bro</p>
                  <p class="fs-7 text-secondary"> <i class="bi bi-clock-fill me-1"></i> 4 Hours Ago
                  </p>
                </div>
              </div>
            </a>
            <div class="dropdown-divider"></div> <a href="#" class="dropdown-item">
              <div class="d-flex">
                <div class="flex-shrink-0"> <img src="/assets/img/user3-128x128.jpg" alt="User Avatar" class="img-size-50 rounded-circle me-3"> </div>
                <div class="flex-grow-1">
                  <h3 class="dropdown-item-title">
                    Nora Silvester
                    <span class="float-end fs-7 text-warning"> <i class="bi bi-star-fill"></i> </span>
                  </h3>
                  <p class="fs-7">The subject goes here</p>
                  <p class="fs-7 text-secondary"> <i class="bi bi-clock-fill me-1"></i> 4 Hours Ago
                  </p>
                </div>
              </div>
            </a>
            <div class="dropdown-divider"></div> <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
          </div>
        </li> --> <!--end::Messages Dropdown Menu--> <!--begin::Notifications Dropdown Menu-->
        <li class="nav-item dropdown"> <a class="nav-link" data-bs-toggle="dropdown" href="#"> <i class="bi bi-bell-fill"></i> <span class="navbar-badge badge text-bg-warning">15</span> </a>
          <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end"> <span class="dropdown-item dropdown-header">15 Notifications</span>
            <!--<div class="dropdown-divider"></div> <a href="#" class="dropdown-item"> <i class="bi bi-envelope me-2"></i> 4 new messages
              <span class="float-end text-secondary fs-7">3 mins</span> </a>
            <div class="dropdown-divider"></div> <a href="#" class="dropdown-item"> <i class="bi bi-people-fill me-2"></i> 8 friend requests
              <span class="float-end text-secondary fs-7">12 hours</span> </a>-->
            <div class="dropdown-divider"></div> <a href="#" class="dropdown-item"> <i class="bi bi-file-earmark-fill me-2"></i> 3 new reports
              <span class="float-end text-secondary fs-7">2 days</span> </a>
            <div class="dropdown-divider"></div> <a href="#" class="dropdown-item dropdown-footer">
              See All Notifications
            </a>
          </div>
        </li> <!--end::Notifications Dropdown Menu--> <!--begin::Fullscreen Toggle-->
        <li class="nav-item"> <a class="nav-link" href="#" data-lte-toggle="fullscreen"> <i data-lte-icon="maximize" class="bi bi-arrows-fullscreen"></i> <i data-lte-icon="minimize" class="bi bi-fullscreen-exit" style="display: none;"></i> </a> </li> <!--end::Fullscreen Toggle--> <!--begin::User Menu Dropdown-->
      </ul> <!--end::End Navbar Links-->
    </div> <!--end::Container-->
  </nav> <!--end::Header--> <!--begin::Sidebar-->
  <aside class="app-sidebar bg-body-secondary shadow" data-bs-theme="dark"> <!--begin::Sidebar Brand-->
    <div class="sidebar-brand"> <!--begin::Brand Link--> <a href="/" class="brand-link"> <!--begin::Brand Image--> <img src="/assets/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image opacity-75 shadow"> <!--end::Brand Image--> <!--begin::Brand Text--> <span class="brand-text fw-light">Autonomy register</span> <!--end::Brand Text--> </a> <!--end::Brand Link--> </div> <!--end::Sidebar Brand--> <!--begin::Sidebar Wrapper-->
    <div class="sidebar-wrapper">
      <nav class="mt-2"> <!--begin::Sidebar Menu-->
        <ul class="nav sidebar-menu flex-column" data-lte-toggle="treeview" role="menu" data-accordion="false">
          <li class="nav-item"> <a href="/" class="nav-link"> <i class="nav-icon bi bi-palette"></i>
            <p>Главная</p>
          </a> </li>
          <li class="nav-item"> <a href="#documents" class="nav-link"> <i class="nav-icon bi bi-speedometer"></i>
            <p>Документы<i class="nav-arrow bi bi-chevron-right"></i></p>
          </a>
            <ul class="nav nav-treeview">
              <li class="nav-item" > <a id="theme" href="/document/themes" class="nav-link " aria-current="true" aria-selected="true"> <i class="nav-icon bi bi-circle"></i>
                <p>Темы</p>
              </a> </li>
              <li class="nav-item"> <a href="/document/appeals" class="nav-link active" > <i class="nav-icon bi bi-circle"></i>
                <p>Обращения</p>
              </a> </li>
              <li class="nav-item"> <a href="/document/secured-party" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
                <p>Обеспеченный интерес</p>
              </a> </li>
            </ul>
          </li>
          <li class="nav-item"> <a href="#notifications" class="nav-link"> <i class="nav-icon bi bi-clipboard-fill"></i>
            <p>
              Оповещения (?)
              <!--<span class="nav-badge badge text-bg-secondary me-3">6</span> <i class="nav-arrow bi bi-chevron-right"></i>-->
            </p>
          </a>
            <ul class="nav nav-treeview">
              <li class="nav-item"> <a href="layout/unfixed-sidebar.html" class="nav-link"> <i class="nav-icon bi bi-circle"></i>
                <p>Default Sidebar</p>
              </a> </li>
            </ul>
          </li>
        </ul> <!--end::Sidebar Menu-->
        <ul class="nav nav-sidebar sidebar-bottom">
          <li class="nav-header">Администрирование</li>
          <li class="nav-item"> <a href="/settings" class="nav-link" style="padding-bottom: 0px;padding-top: 0px;"> <i style="height:25px" class="nav-icon bi bi-palette"></i>
            <p>Настройки</p>
          </a> </li>
        </ul>
      </nav>
    </div> <!--end::Sidebar Wrapper-->
  </aside> <!--end::Sidebar--> <!--begin::App Main-->

  <main class="app-main"> <!--begin::App Content Header-->
    ${content}
  </main> <!--end::App Main--> <!--begin::Footer-->
  <footer class="app-footer"> <!--begin::To the end-->
    <div class="float-end d-none d-sm-inline">Anything you want</div> <!--end::To the end--> <!--begin::Copyright--> <strong>
      Copyright &copy; 2014-2024&nbsp;
      <a href="https://adminlte.io" class="text-decoration-none">AdminLTE.io</a>.
    </strong>
    All rights reserved.
    <strong>
      / &copy;: Andrey Nikolaevich Bezrukikh, 2025 Copyright is protected by the Berne Copyright Convention of 1886.
    </strong>
    <!--end::Copyright-->
  </footer> <!--end::Footer-->
</div> <!--end::App Wrapper--> <!--begin::Script--> <!--begin::Third Party Plugin(OverlayScrollbars)-->
<script src="https://cdn.jsdelivr.net/npm/overlayscrollbars@2.3.0/browser/overlayscrollbars.browser.es6.min.js" integrity="sha256-H2VM7BKda+v2Z4+DRy69uknwxjyDRhszjXFhsL4gD3w=" crossorigin="anonymous"></script> <!--end::Third Party Plugin(OverlayScrollbars)--><!--begin::Required Plugin(popperjs for Bootstrap 5)-->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha256-whL0tQWoY1Ku1iskqPFvmZ+CHsvmRWx/PIoEvIeWh4I=" crossorigin="anonymous"></script> <!--end::Required Plugin(popperjs for Bootstrap 5)--><!--begin::Required Plugin(Bootstrap 5)-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha256-YMa+wAM6QkVyz999odX7lPRxkoYAan8suedu4k2Zur8=" crossorigin="anonymous"></script> <!--end::Required Plugin(Bootstrap 5)--><!--begin::Required Plugin(AdminLTE)-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha256-YMa+wAM6QkVyz999odX7lPRxkoYAan8suedu4k2Zur8=" crossorigin="anonymous"></script>
<script src="/js/tinymce/tinymce.min.js" referrerpolicy="origin"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.2/umd/popper.min.js" integrity="sha512-2rNj2KJ+D8s1ceNasTIex6z4HWyOnEYLVC3FigGOmyQCZc2eBXKgOxQmo3oKLHyfcj53uz4QMsRCWNbLd32Q1g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="/js/adminlte.js"></script> <!--end::Required Plugin(AdminLTE)--><!--begin::OverlayScrollbars Configure-->
<script src="/js/jquery-3.7.1.min.js"></script>
<script src="/js/webCustom.js"></script>
<script src="/js/notes.js"></script>
<script src="/js/notifications.js"></script>
<script type="module">
  import Tags from "https://cdn.jsdelivr.net/gh/lekoala/bootstrap5-tags@master/tags.js";
  Tags.init("select");
</script>
<script>
  tinymce.init({
    selector: 'textarea#editor',
    license_key: 'gpl',
  });
</script>

<script>
  const SELECTOR_SIDEBAR_WRAPPER = ".sidebar-wrapper";
  const Default = {
      scrollbarTheme: "os-theme-light",
      scrollbarAutoHide: "leave",
      scrollbarClickScroll: true,
  };
  document.addEventListener("DOMContentLoaded", function() {
      const sidebarWrapper = document.querySelector(SELECTOR_SIDEBAR_WRAPPER);
      if (
          sidebarWrapper &&
          typeof OverlayScrollbarsGlobal?.OverlayScrollbars !== "undefined"
      ) {
          OverlayScrollbarsGlobal.OverlayScrollbars(sidebarWrapper, {
              scrollbars: {
                  theme: Default.scrollbarTheme,
                  autoHide: Default.scrollbarAutoHide,
                  clickScroll: Default.scrollbarClickScroll,
              },
          });
      }
  });

</script> <!--end::OverlayScrollbars Configure--> <!-- OPTIONAL SCRIPTS --> <!-- apexcharts -->
<script src="https://cdn.jsdelivr.net/npm/apexcharts@3.37.1/dist/apexcharts.min.js" integrity="sha256-+vh8GkaU7C9/wbSLIcwq82tQ2wTf44aOHA8HlBMwRI8=" crossorigin="anonymous"></script>
<script>
  // NOTICE!! DO NOT USE ANY OF THIS JAVASCRIPT
  // IT'S ALL JUST JUNK FOR DEMO
  // ++++++++++++++++++++++++++++++++++++++++++

  const visitors_chart_options = {
      series: [{
              name: "High - 2023",
              data: [100, 120, 170, 167, 180, 177, 160],
          },
          {
              name: "Low - 2023",
              data: [60, 80, 70, 67, 80, 77, 100],
          },
      ],
      chart: {
          height: 200,
          type: "line",
          toolbar: {
              show: false,
          },
      },
      colors: ["#0d6efd", "#adb5bd"],
      stroke: {
          curve: "smooth",
      },
      grid: {
          borderColor: "#e7e7e7",
          row: {
              colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
              opacity: 0.5,
          },
      },
      legend: {
          show: false,
      },
      markers: {
          size: 1,
      },
      xaxis: {
          categories: ["22th", "23th", "24th", "25th", "26th", "27th", "28th"],
      },
  };

  const visitors_chart = new ApexCharts(
      document.querySelector("#visitors-chart"),
      visitors_chart_options
  );
  visitors_chart.render();

  const sales_chart_options = {
      series: [{
              name: "Net Profit",
              data: [44, 55, 57, 56, 61, 58, 63, 60, 66],
          },
          {
              name: "Revenue",
              data: [76, 85, 101, 98, 87, 105, 91, 114, 94],
          },
          {
              name: "Free Cash Flow",
              data: [35, 41, 36, 26, 45, 48, 52, 53, 41],
          },
      ],
      chart: {
          type: "bar",
          height: 200,
      },
      plotOptions: {
          bar: {
              horizontal: false,
              columnWidth: "55%",
              endingShape: "rounded",
          },
      },
      legend: {
          show: false,
      },
      colors: ["#0d6efd", "#20c997", "#ffc107"],
      dataLabels: {
          enabled: false,
      },
      stroke: {
          show: true,
          width: 2,
          colors: ["transparent"],
      },
      xaxis: {
          categories: [
              "Feb",
              "Mar",
              "Apr",
              "May",
              "Jun",
              "Jul",
              "Aug",
              "Sep",
              "Oct",
          ],
      },
      fill: {
          opacity: 1,
      },
      tooltip: {
          y: {
              formatter: function(val) {
                  return "$ " + val + " thousands";
              },
          },
      },
  };

  const sales_chart = new ApexCharts(
      document.querySelector("#sales-chart"),
      sales_chart_options
  );
  sales_chart.render();
</script> <!--end::Script-->
</body><!--end::Body-->

</html>