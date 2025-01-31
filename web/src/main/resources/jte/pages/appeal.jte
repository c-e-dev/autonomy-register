@import java.util.List

@import ru.c_energies.databases.entity.appeals.AppealRow
@import ru.c_energies.databases.entity.files.FileRow
@import ru.c_energies.web.pages.FilesForm

@param AppealRow appeal
@param List<FileRow> listFiles
@param List<FileRow> listFileAnswered
@param FilesForm ff
@param Integer answered

@template.layout.main(content = @`
<div class="app-content-header"> <!--begin::Container-->
    <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
            <div class="col-sm-6">
                <h3 class="mb-0">${appeal.title()}</h3>
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
                        <button type="button" class="btn btn-create" data-bs-toggle="modal" data-bs-target="#appealEdit" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Редактировать</button>
                        <div class="modal fade" id="appealEdit" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" style="max-width:1000px">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel2">Редактирование</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <form onsubmit="return false;" action="/document/appeals/${appeal.id()}" method="POST" enctype="application/x-www-form-urlencoded" id="editAppeal">
                                        <div class="modal-body">
                                            <label class="form-label">Номер отправления</label>
                                            <input type="text" class="form-control" id="inputText01" name="registerTrackNumber" value="${appeal.registerTrackNumber()}">
                                            <label class="form-label">Срок исполнения</label>
                                            <input type="datetime-local" min="2020-01-01T00:00" class="form-control" id="inputText02" name="dueDate" value="${appeal.dueDate()}">
                                            <label class="form-label" for="getAnsweredable">Получен ответ</label>
                                            <select class="form-select" id="getAnsweredable" required autocomplete="off" name="getAnsweredable">
                                                @if(answered == 0)
                                                <option selected disabled value="">Выберите</option>@else<option disabled value="">Выберите</option>@endif
                                                @if(answered == 1)
                                                <option selected value="1">Нет</option>@else<option value="1">Нет</option>@endif
                                                @if(answered == 2)
                                                <option selected value="2">Да</option>@else<option value="2">Да</option>@endif
                                            </select>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" id="closeForm_editAppeal" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                            <button type="button" class="btn btn-primary" onclick="handleSubmit('editAppeal')">Сохранить</button>
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
                                    <th style="width:15%">Номер отправления:</th><td style="width:35%">${appeal.registerTrackNumber()}</td>
                                    <th style="width:15%">Дата создания:</th><td style="width:35%">${appeal.createDate()}</td>
                                </tr>
                                <tr>
                                    <th>Срок исполнения:</th><td>${appeal.dueDate()}</td>
                                    <th>Получен ответ:</th><td class="text-bg-info">${appeal.answered()}</td>
                                </tr>
                            </table>
                        </div>
                        <div class="card card-primary card-outline mb-4"> <!--begin::Header-->
                            <div class="card-header">
                                <div class="card-title">Файлы</div>
                                <button type="button" class="btn btn-create" data-bs-toggle="modal" data-bs-target="#addFile" style="margin-left: 10px; --bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Добавить</button>
                                <!-- Modal -->
                                <div class="modal fade" id="addFile" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" style="max-width:1000px">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel">Добавить файл</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form onsubmit="return false;" action="/files/${appeal.id()}/upload" method="POST" enctype="multipart/form-data" id="fileType">
                                                <div class="modal-body">
                                                    <label for="validationCustom04" class="form-label">Категория файла</label>
                                                    <select class="form-select" id="validationCustom04" required name="fileCategory" id="fileCategory">
                                                        <option selected disabled value="">Выберите категорию</option>
                                                        <option value="0">Отправленные</option>
                                                        <option value="1">Ответы</option>
                                                    </select>
                                                    <div class="input-group mb-3" style="margin-top: 10px;">
                                                        <label class="input-group-text" for="inputGroupFile01">Путь до файла</label>
                                                        <input type="file" class="form-control" id="inputGroupFile01" name="fileContent">
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" id="closeForm_fileType" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                                    <button type="button" class="btn btn-primary" onclick="handleSubmit('fileType')">Загрузить</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </div> <!--end::Header--> <!--begin::Body-->
                            <div class="card-body">
                                <div class="accordion" id="accordionExample">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header"> <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                            Отправленные файлы
                                        </button> </h2>
                                        <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                                            <div class="card-body">
                                            <div class="product-file-type">

                                                <ul class="list-group list-group-horizontal">
                                                    @for(FileRow fr : listFiles)
                                                        <a href="/files/${fr.id()}" class="list-group-item list-group-item-action">
                                                        <span class="mr-3 align-self-center img-icon primary-rgba text-primary">.${fr.extension()}</span>
                                                        <div class="media-body">
                                                            <h10 class="font-16 mb-1">${fr.name()}<i class="feather icon-download-cloud float-right"></i>
                                                                .${fr.extension()},
                                                                @if(fr.size() > 0 && fr.size() < 1024)
                                                                    ${fr.size()} B
                                                                @endif
                                                                @if(fr.size() > 1024 && fr.size() < (1024*1024))
                                                                    ${fr.size() / 1024} KB
                                                                @endif
                                                                @if(fr.size() > (1024*1024))
                                                                    ${fr.size() / (1024*1024)} MB
                                                                @endif
                                                            </h10>
                                                        </div>
                                                        </a>
                                                    @endfor
                                                </ul>
                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="accordion-item">
                                        <h2 class="accordion-header"> <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            Ответы
                                        </button> </h2>
                                        <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
                                            <div class="card-body">
                                                <div class="product-file-type">

                                                    <ul class="list-group list-group-horizontal">
                                                        @for(FileRow fr : listFileAnswered)
                                                        <a href="/files/${fr.id()}" class="list-group-item list-group-item-action">
                                                            <span class="mr-3 align-self-center img-icon primary-rgba text-primary">.${fr.extension()}</span>
                                                            <div class="media-body">
                                                                <h10 class="font-16 mb-1">${fr.name()}<i class="feather icon-download-cloud float-right"></i>
                                                                    .${fr.extension()},
                                                                    @if(fr.size() > 0 && fr.size() < 1024)
                                                                    ${fr.size()} B
                                                                    @endif
                                                                    @if(fr.size() > 1024 && fr.size() < (1024*1024))
                                                                    ${fr.size() / 1024} KB
                                                                    @endif
                                                                    @if(fr.size() > (1024*1024))
                                                                    ${fr.size() / (1024*1024)} MB
                                                                    @endif
                                                                </h10>
                                                            </div>
                                                        </a>
                                                        @endfor
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> <!--end::Body-->
                        </div> <!--end::Accordion--> <!--begin::Alert-->
                    </div> <!-- /.card-body -->
                </div> <!-- /.card -->

            </div> <!-- /.col -->
        </div> <!--end::Row-->
    </div> <!--end::Container-->
</div> <!--end::App Content-->
`)