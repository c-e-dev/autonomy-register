@import java.util.List
@import ru.c_energies.databases.entity.themes.ThemeRow
@import ru.c_energies.databases.entity.appeals.AppealRow

@param List<AppealRow> listAppeals
@param ThemeRow theme
@param Integer decisionStatus

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
                        <button type="button" data-bs-toggle="modal" data-bs-target="#themeEdit" class="btn btn-create" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Редактировать</button>
                        <div class="modal fade" id="themeEdit" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" style="max-width:1000px">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="themeModalLabel2">Редактирование</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <form onsubmit="return false;" action="/document/themes/${theme.id()}" method="POST" enctype="application/x-www-form-urlencoded" id="editTheme">
                                        <div class="modal-body">
                                            <label class="form-label">Тема</label>
                                            <input type="text" class="form-control" id="inputTheme01" name="title" value="${theme.title()}">
                                            <label class="form-label" for="exampleFormControlTextarea2">Описание</label>
                                            <textarea class="form-control" id="exampleFormControlTextarea2" rows="5" name="description">${theme.description()}</textarea>
                                            <label class="form-label">Дата решения:</label>
                                            <input type="datetime-local" min="2020-01-01T00:00" class="form-control" id="inputText02" name="decisionDate" value="${theme.decisionDate()}">
                                            <label class="form-label" for="decisionStatus">Решено:</label>
                                            <select class="form-select" id="decisionStatus" required autocomplete="off" name="decisionStatus">
                                                @if(decisionStatus == 0)
                                                <option selected disabled value="">Выберите</option>@else <option disabled value="">Выберите</option> @endif
                                                @if(decisionStatus == 1)
                                                <option value="1" selected>Не решено</option>@else <option value="1">Не решено</option> @endif
                                                @if(decisionStatus == 2)
                                                <option value="2" selected>Решено</option>@else <option value="2">Решено</option> @endif
                                                @if(decisionStatus == 3)
                                                <option value="3" selected>Отложено</option>@else<option value="3">Отложено</option>@endif
                                                @if(decisionStatus == 4)
                                                <option value="4" selected>В работе</option>@else<option value="4">В работе</option>@endif
                                            </select>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" id="closeForm_editTheme" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                            <button type="button" class="btn btn-primary" onclick="handleSubmit('editTheme')">Сохранить</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div> <!-- /.card-header -->
                    <div class="card-body">
                        <div class="card-header">Сводная информация</div>
                        <div class="card-body">
                        <table class="table">
                            <tr>
                                <th style="width:15%">Описание:</th><td style="width:35%">${theme.description()}</td>
                                <th style="width:15%">Дата создания:</th><td style="width:35%">${theme.createDate()}</td>
                            </tr>
                            <tr>
                                <th>Статус:</th>
                                <td>${theme.decisionStatus()}</td>
                                <th>Дата решения:</th><td>${theme.decisionDate()}</td>
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
                                                <h1 class="modal-title fs-5" id="exampleModalLabel2">Создать обращение к теме "${theme.title()}"</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form onsubmit="return false;" action="/document/appeals" method="POST" enctype="application/x-www-form-urlencoded" id="createAppeal">
                                                <div class="modal-body">
                                                    <label class="form-label">Тема</label>
                                                    <input type="text" required class="form-control" id="themeId" name="themeId" value="${theme.id()}" readonly>
                                                    <label class="form-label">Тема обращения</label>
                                                    <input type="text" required class="form-control" id="titleAppeal" name="title" value="">
                                                    <label class="form-label">Кому</label>
                                                    <input type="text" class="form-control" id="inputRecipient" name="recipient" value="">
                                                    <label class="form-label">Куда</label>
                                                    <input type="text" class="form-control" id="inputAddress" name="address" value="">
                                                    <label class="form-label">Live Куда</label>
                                                    <select class="form-select" data-live-search="true">
                                                        <option >Hot Dog, Fries and a Soda</option>
                                                        <option>Burger, Shake and a Smile</option>
                                                        <option >Sugar, Spice and all things nice</option>
                                                    </select>

                                                    <label class="form-label">Номер отправления</label>
                                                    <input type="text" class="form-control" id="registerTrackNumber" name="registerTrackNumber" value="">
                                                    <label class="form-label">Срок исполнения</label>
                                                    <input type="datetime-local" min="2020-01-01T00:00" class="form-control" id="dueDate" name="dueDate" value="2020-01-01T00:00">
                                                    <label class="form-label" for="getAnsweredable">Получен ответ</label>
                                                    <select class="form-select" id="getAnsweredable" required name="getAnsweredable">
                                                        <option selected disabled value="">Выберите</option>
                                                        <option value="0">Нет</option>
                                                        <option value="1">Да</option>
                                                    </select>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" id="closeForm_createAppeal" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                                    <button type="button" class="btn btn-primary" onclick="handleSubmit('createAppeal')">Сохранить</button>
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