@param String username

@template.layout.main(content = @`
<div class="app-content-header"> <!--begin::Container-->
    <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
            <div class="col-sm-6">
                <h3 class="mb-0">Добро пожаловать, ${username}!</h3>
            </div>
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-end">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        Главная
                    </li>
                </ol>
            </div>
        </div> <!--end::Row-->
    </div> <!--end::Container-->
</div>
<div class="app-content"> <!--begin::Container-->
    <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
            <div class="col-lg-6">
                <div class="card mb-4">
                    This is your dashboard. Here you can view your recent activity and manage your account.
                </div> <!-- /.card -->
                <div class="card mb-4">
                    This is your dashboard. Here you can view your recent activity and manage your account.
                </div> <!-- /.card -->
            </div> <!-- /.col-md-6 -->
        </div> <!--end::Row-->
    </div> <!--end::Container-->
</div> <!--end::App Content-->
`)