@import java.util.List

@import ru.c_energies.databases.entity.appeals.AppealRow
@import ru.c_energies.databases.entity.files.FileRow
@import ru.c_energies.web.pages.FilesForm
@import ru.c_energies.databases.entity.addresses.AddressRow
@import ru.c_energies.databases.entity.labels.LabelRow
@import ru.c_energies.databases.entity.themes.ThemeRow

@template.layout.main(content = @`
<div class="app-content-header"> <!--begin::Container-->
    <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
            <div class="col-sm-6">
                <h3 class="mb-0">Настройки</h3>
            </div>
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-end">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Настройки</li>
                </ol>
            </div>
        </div> <!--end::Row-->
    </div> <!--end::Container-->
</div> <!--end::App Content Header--> <!--begin::App Content-->
<div class="app-content"> <!--begin::Container-->
    <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
            <div class="col-md-6">
                <div class="card mb-4">
                    <div class="card-header">
                        енен
                    </div> <!-- /.card-header -->
                    <div class="card-body">
                        <nav>
                            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                <button class="nav-link active" id="nav-backup-tab" data-bs-toggle="tab" data-bs-target="#nav-backup" type="button" role="tab" aria-controls="nav-backup" aria-selected="true">Резервное копирование</button>
                                <button class="nav-link" id="nav-notification-tab" data-bs-toggle="tab" data-bs-target="#nav-notification" type="button" role="tab" aria-controls="nav-notification" aria-selected="false">Нотификации</button>
                            </div>
                        </nav>
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-backup" role="tabpanel" aria-labelledby="nav-backup-tab" tabindex="0">
                                <div class="card-body">
                                    <div class="card-header">Общее</div>
                                    <div class="card-body">
                                    <form class="row g-3">
                                        <div class="container" style="padding-left: 0px;margin-left: 0px;">
                                            <div class="row">
                                                <div class="col">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                                        <label class="form-check-label" for="flexCheckDefault">Включено резервное копирование</label>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                                        <label class="form-check-label" for="flexCheckChecked">Checked checkbox</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                    <label class="form-label" for="rotate">Глубина ротаций</label>
                                                    <select class="form-select" id="rotate" aria-label="Default select example">
                                                        <option value="5" selected>Пять дней</option>
                                                        <option value="10">Десять дней</option>
                                                        <option value="30">Тридацать дней</option>
                                                    </select>
                                                </div>
                                                <div class="col">
                                                    <label class="form-label" for="rotate">Вакантное место</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <button type="button" class="btn btn-primary" onclick="handleSubmit('editAppeal')">Сохранить</button>
                                        </div>
                                    </form>
                                    </div>
                                    <div class="card-header">Yandex Disk</div>
                                    <div class="card-body">
                                        <form class="row g-3">
                                            <div class="container" style="padding-left: 0px;margin-left: 0px;">
                                                <div class="row">
                                                    <div class="col">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                                            <label class="form-check-label" for="flexCheckDefault">Включено резервное копирование</label>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                                            <label class="form-check-label" for="flexCheckChecked">Checked checkbox</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col">
                                                        <label class="form-label" for="rotate">Глубина ротаций</label>
                                                        <select class="form-select" id="rotate" aria-label="Default select example">
                                                            <option value="5" selected>Пять дней</option>
                                                            <option value="10">Десять дней</option>
                                                            <option value="30">Тридацать дней</option>
                                                        </select>
                                                    </div>
                                                    <div class="col">
                                                        <label class="form-label" for="rotate">Вакантное место</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <button type="button" class="btn btn-primary" onclick="handleSubmit('editAppeal')">Сохранить</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="nav-notification" role="tabpanel" aria-labelledby="nav-notification-tab" tabindex="0">...</div>
                        </div>

                    </div> <!-- /.card-body -->
                </div> <!-- /.card -->

            </div> <!-- /.col -->
        </div> <!--end::Row-->
    </div> <!--end::Container-->
</div> <!--end::App Content-->
`)