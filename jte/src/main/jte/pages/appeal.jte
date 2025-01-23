@import java.util.List

@import ru.c_energies.web.models.appeals.AppealRow
@import ru.c_energies.web.models.files.FileRow

@param AppealRow appeal
@param List<FileRow> listFiles

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
                        <button type="button" class="btn btn-create" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Редактировать</button>
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
                                            <div class="modal-body">
                                                <label for="validationCustom04" class="form-label">Категория файла</label>
                                                <select class="form-select" id="validationCustom04" required>
                                                    <option selected disabled value="">Выберите...</option>
                                                    <option>Отправленные</option>
                                                    <option>Ответы</option>
                                                </select>
                                                <div class="input-group mb-3" style="margin-top: 10px;">
                                                    <label class="input-group-text" for="inputGroupFile01">Путь до файла</label>
                                                    <input type="file" class="form-control" id="inputGroupFile01">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                                <button type="button" class="btn btn-primary">Загрузить</button>
                                            </div>
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
                                                        <a href="#" class="list-group-item list-group-item-action">
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
                                            <div class="accordion-body"> <strong>This is the second item's accordion body.</strong> It is hidden by default, until the collapse plugin
                                                adds the appropriate classes that we use to style
                                                each element. These classes control the overall
                                                appearance, as well as the showing and hiding via
                                                CSS transitions. You can modify any of this with
                                                custom CSS or overriding our default variables. It's
                                                also worth noting that just about any HTML can go
                                                within the <code>.accordion-body</code>, though the
                                                transition does limit overflow.
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