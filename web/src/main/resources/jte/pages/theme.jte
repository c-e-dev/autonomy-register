@import java.util.List
@import java.util.Map
@import ru.c_energies.databases.entity.themes.ThemeRow
@import ru.c_energies.databases.entity.appeals.AppealRow
@import ru.c_energies.databases.entity.addresses.AddressRow
@import ru.c_energies.databases.entity.notes.NoteRow

@param List<AppealRow> listAppeals
@param ThemeRow theme
@param Integer decisionStatus
@param Map<Integer, AddressRow> addressRowMap
@param List<NoteRow> noteRows
@param Map<Integer, List<AppealRow>> mapSubAppeal

@template.layout.main(content = @`
<div class="app-content-header"> <!--begin::Container-->
    <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
            <div class="col-sm-6">
                <h3 class="mb-0">${theme.title()}</h3>
            </div>
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-end">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item"><a href="/document/themes">Темы обращений</a></li>
                    <li class="breadcrumb-item active" aria-current="page"><a href="">Тема</a></li>
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
                                            <input type="datetime-local" min="2025-01-01T00:00" class="form-control" id="inputText02" name="decisionDate" value="${theme.decisionDate()}">
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
                            <div class="card-header">Связанные отправления<button type="button" class="btn btn-create" data-bs-toggle="modal" data-bs-target="#createAppealByTheme" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Создать</button></div>
                            <div class="card-body" style="padding: 10px">
                                <div class="modal fade" id="createAppealByTheme" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" style="max-width:1000px">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel2">Создать обращение к теме "${theme.title()}"</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form onsubmit="return false;" action="/document/appeals" method="POST" enctype="application/x-www-form-urlencoded" id="createAppeal">
                                                <div class="modal-body">
                                                    <input type="text" required class="form-control" id="themeId" name="themeId" value="${theme.id()}" readonly hidden="true">
                                                    <label class="form-label">Тема обращения</label>
                                                    <input type="text" required class="form-control" id="titleAppeal" name="title" value="">
                                                    <label class="form-label">Кому</label>
                                                    <input type="text" class="form-control" id="inputRecipient" name="recipient" value="">
                                                    <label class="form-label">Куда</label>
                                                    <input type="text" class="form-control" id="inputAddress" name="address" value="">
                                                    <!--<label class="form-label">Live Куда</label>
                                                    <select class="form-select" data-live-search="true">
                                                        <option >Hot Dog, Fries and a Soda</option>
                                                        <option>Burger, Shake and a Smile</option>
                                                        <option >Sugar, Spice and all things nice</option>
                                                    </select>-->

                                                    <label class="form-label">Номер отправления</label>
                                                    <input type="text" class="form-control" id="registerTrackNumber" name="registerTrackNumber" value="">
                                                    <label class="form-label">Внутренний номер</label>
                                                    <input type="text" class="form-control" id="inputInternalNumber" name="internalNumber" value="">
                                                    <label class="form-label">Срок исполнения</label>
                                                    <input type="datetime-local" min="2025-01-01T00:00" class="form-control" id="dueDate" name="dueDate" value="2020-01-01T00:00">
                                                    <label class="form-label" for="getAnsweredable">Получен ответ</label>
                                                    <select class="form-select" id="getAnsweredable" required name="getAnsweredable">
                                                        <option selected disabled value="">Выберите</option>
                                                        <option value="0">Нет</option>
                                                        <option value="1">Да</option>
                                                    </select>
                                                    <label class="form-label" for="getType">Тип обращения</label>
                                                    <select class="form-select" id="getType" required autocomplete="off" name="type">
                                                        <option selected disabled value="none">Не выбрано</option>
                                                        <option selected value="inbound">Входящее</option>
                                                        <option selected value="outbound">Исходящее</option>
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
                                            <tr style="width: 100%">
                                                <th style="width: 1%">#</th>
                                                <th>Номер отправления</th>
                                                <th>Внутренний номер</th>
                                                <th>Тип обращения</th>
                                                <th>Название</th>
                                                <th>Кому</th>
                                                <th>Дата создания</th>
                                                <th>Срок исполнения</th>
                                                <th>Ответили?</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        @for(AppealRow lar : listAppeals)
                                        <tr>
                                            <!--<td><a href="/document/appeals/${lar.id()}">${lar.id()}.</a></td>-->
                                            <td align="center" style="padding: 0px;">
                                                <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                                                    <div class="btn-group" role="group"> <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                                        ${lar.id()}.
                                                    </button>
                                                        <ul class="dropdown-menu">
                                                            <li> <a class="dropdown-item" href="/document/appeals/${lar.id()}">Перейти</a> </li>
                                                            <li> <a class="dropdown-item" href="#">Создать связанное обращение</a> </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </td>
                                            <td><a href="/document/appeals/${lar.id()}">${lar.registerTrackNumber()}</a></td>
                                            <td>${lar.internalNumber()}</td>
                                            <td>${lar.type()}</td>
                                            <td>${lar.title()}</td>
                                            <td>${addressRowMap.get(lar.id()).recipient()}</td>
                                            <td>${lar.createDate()}</td>
                                            <td>${lar.dueDate()}</td>
                                            <td class="text-bg-info">${lar.answered()}</td>
                                        </tr>
                                        @if(mapSubAppeal.containsKey(lar.id()))
                                        <tr>
                                            <td colspan="9">
                                                <table class="table" style="border: 1px solid black; ">
                                                    <thead>
                                                    <tr style="width: 100%">
                                                        <th >#</th>
                                                        <th>Номер отправления</th>
                                                        <th>Внутренний номер</th>
                                                        <th>Тип обращения</th>
                                                        <th>Название</th>
                                                        <th>Кому</th>
                                                        <th>Дата создания</th>
                                                        <th>Срок исполнения</th>
                                                        <th>Ответили?</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    @for(AppealRow subLar : mapSubAppeal.get(lar.id()))
                                                    <tr class="align-middle">
                                                        <td><a href="/document/appeals/${subLar.id()}">${subLar.id()}.</a></td>
                                                        <td><a href="/document/appeals/${subLar.id()}">${subLar.registerTrackNumber()}</a></td>
                                                        <td>${subLar.internalNumber()}</td>
                                                        <td>${subLar.type()}</td>
                                                        <td>${subLar.title()}</td>
                                                        <td><!--${addressRowMap.get(subLar.id()).recipient()}--></td>
                                                        <td>${subLar.createDate()}</td>
                                                        <td>${subLar.dueDate()}</td>
                                                        <td class="text-bg-info">${subLar.answered()}</td>
                                                    </tr>
                                                    @endfor
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        @endif
                                        @endfor
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                            <div class="card mb-4">
                                <div class="card-header">Заметки <button type="button" class="btn btn-create" data-bs-toggle="modal" data-bs-target="#createNoteTheme" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Создать</button></div>
                                <div class="card-body" style="padding: 10px">
                                    <div class="modal fade" id="createNoteTheme" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" style="max-width:1000px">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="exampleModalLabel2">Создать заметку к теме "${theme.title()}"</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <form onsubmit="return false;" action="/notes/create/themeId/${theme.id()}" method="POST" enctype="application/x-www-form-urlencoded" id="createNote">
                                                    <div class="modal-body">
                                                        <label class="form-label">Тема заметки</label>
                                                        <input type="text" required class="form-control"name="title" value="">
                                                        <label class="form-label">Содержание</label>
                                                        <textarea class="form-control" rows="5" name="content" id="editor"></textarea>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" id="closeForm_createNote" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                                        <button type="button" class="btn btn-primary" onclick="handleSubmit('createNote')">Сохранить</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div id="note-full-container" class="note-has-grid row">
                                        @for(NoteRow noteRow : noteRows)
                                            <div class="col-md-4 single-note-item all-category note-important" style="">
                                                <div class="card card-body">
                                                    <span class="side-stick"></span>
                                                    <h5 class="note-title text-truncate w-75 mb-0" data-noteheading="Book a Ticket for Movie">${noteRow.title()}<i class="point fa fa-circle ml-1 font-10"></i></h5>
                                                    <p class="note-date font-12 text-muted">${noteRow.createDate()}</p>
                                                    <div class="note-content">
                                                        <p class="note-inner-content text-muted" id="noteContent">$unsafe{noteRow.content()}</p>
                                                    </div>
                                                    <div class="d-flex align-items-center">
                                                        <span class="mr-1"><i class="fa fa-star favourite-note"></i></span>
                                                        <span class="mr-1"><i class="fa fa-trash remove-note" id="note${noteRow.id()}"></i></span>
                                                        <div class="ml-auto">
                                                            <div class="category-selector btn-group">
                                                                <a class="nav-link dropdown-toggle category-dropdown label-group p-0" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="true">
                                                                    <div class="category">
                                                                        <div class="category-business"></div>
                                                                        <div class="category-social"></div>
                                                                        <div class="category-important"></div>
                                                                        <span class="more-options text-dark"><i class="icon-options-vertical"></i></span>
                                                                    </div>
                                                                </a>
                                                                <div class="dropdown-menu dropdown-menu-right category-menu">
                                                                    <a class="note-business badge-group-item badge-business dropdown-item position-relative category-business text-success" href="javascript:void(0);">
                                                                        <i class="mdi mdi-checkbox-blank-circle-outline mr-1"></i>Business
                                                                    </a>
                                                                    <a class="note-social badge-group-item badge-social dropdown-item position-relative category-social text-info" href="javascript:void(0);">
                                                                        <i class="mdi mdi-checkbox-blank-circle-outline mr-1"></i> Social
                                                                    </a>
                                                                    <a class="note-important badge-group-item badge-important dropdown-item position-relative category-important text-danger" href="javascript:void(0);">
                                                                        <i class="mdi mdi-checkbox-blank-circle-outline mr-1"></i> Important
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        @endfor
                                        </div>
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