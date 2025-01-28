@import java.util.List
@import ru.c_energies.web.models.themes.ThemeRow
@import ru.c_energies.databases.entity.appeals.AppealRow

@param List<AppealRow> listAppeals
@param ThemeRow theme

@template.layout.main(content = @`
<div class="app-content-header"> <!--begin::Container-->
    <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
            <div class="col-sm-6">
                <h3 class="mb-0">${theme.title()}</h3>
            </div>
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-end">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                        Simple Tables
                    </li>
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
                        <button type="button" class="btn btn-create" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Редактировать</button>
                    </div> <!-- /.card-header -->
                    <div class="card-body">
                        <div class="card-header">Сводная информация</div>
                        <div class="card-body">
                        <table class="table">
                            <tr>
                                <th style="width:15%">Описание:</th><td style="width:35%">Вот посвящено тому и тому. ПУсть всем сделают хорошо, особенно вон тем</td>
                                <th style="width:15%">Дата создания:</th><td style="width:35%">2024-05-23 12:05</td>
                            </tr>
                            <tr>
                                <th>Решено:</th>
                                <td>
                                <!--<select class="form-select form-select mb-3" aria-label="Large select example">
                                    <option selected>Выберите</option>
                                    <option value="1">Не решено</option>
                                    <option value="2">Решено</option>
                                    <option value="3">Отложено</option>
                                </select>-->
                                </td>
                                <th>Дата решения:</th><td>2024-07-23 09:05</td>
                            </tr>
                        </table>
                        <div class="card mb-4">
                            <div class="card-header">Связанные отправления:</div>
                            <div class="card-body" style="padding: 10px">
                                <button type="button" class="btn btn-create" data-bs-toggle="modal" data-bs-target="#createAppealByTheme" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Создать</button>
                                <div class="modal fade" id="createAppealByTheme" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" style="max-width:1000px">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel2">Создать обращение к теме</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form onsubmit="return false;" action="/document/appeals" method="POST" enctype="application/x-www-form-urlencoded" id="createAppeal">
                                                <div class="modal-body">
                                                    <label class="form-label">Тема</label>
                                                    <input type="text" required class="form-control" id="themeId" name="themeId" value="${theme.id()}" readonly>
                                                    <label class="form-label">Тема обращения</label>
                                                    <input type="text" required class="form-control" id="titleAppeal" name="title" value="">
                                                    <label class="form-label">Номер отправления</label>
                                                    <input type="text" class="form-control" id="registerTrackNumber" name="registerTrackNumber" value="">
                                                    <label class="form-label">Срок исполнения</label>
                                                    <input type="datetime-local" class="form-control" id="dueDate" name="dueDate" value="">
                                                    <label class="form-label" for="getAnsweredable">Получен ответ</label>
                                                    <select class="form-select" id="getAnsweredable" required name="getAnsweredable">
                                                        <option selected disabled value="">Выберите</option>
                                                        <option value="0">Нет</option>
                                                        <option value="1">Да</option>
                                                    </select>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" id="closeFormCreateAppeal" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                                    <button type="button" class="btn btn-primary" onclick="handleSubmitCreateAppeal()">Сохранить</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th style="width: 200px">Номер отправления</th>
                                            <th>Название</th>
                                            <th style="width: 150px">Дата создания</th>
                                            <th style="width: 150px">Срок исполнения</th>
                                            <th style="width: 40px">Ответили?</th>
                                            <th>Отправленные документы</th>
                                            <th>Полученные документы</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        @for(AppealRow lar : listAppeals)
                                        <tr class="align-middle">
                                            <td><a href="/document/appeals/${lar.id()}">${lar.id()}.</a></td>
                                            <td><a href="/document/appeals/${lar.id()}">${lar.registerTrackNumber()}</a></td>
                                            <td>${lar.title()}</td>
                                            <td>${lar.createDate()}</td>
                                            <td>${lar.dueDate()}</td>
                                            <td class="text-bg-info">${lar.answered()}</td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        @endfor
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div> <!-- /.card-body -->
                </div> <!-- /.card -->

            </div> <!-- /.col -->
        </div> <!--end::Row-->
    </div> <!--end::Container-->
</div> <!--end::App Content-->
`)