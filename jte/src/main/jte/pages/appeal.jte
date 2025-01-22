@import ru.c_energies.web.models.appeals.AppealRow

@param AppealRow appeal

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
                        <div class="card card-primary card-outline mb-4"> <!--begin::Header-->
                            <div class="card-header">
                                <div class="card-title">Файлы</div>
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
                                                    <li class="media mb-3 list-group-item">
                                                        <span class="mr-3 align-self-center img-icon primary-rgba text-primary">.doc</span>
                                                        <div class="media-body">
                                                            <h10 class="font-16 mb-1">Marketing Guidelines<i class="feather icon-download-cloud float-right"></i></h10>
                                                            <p>.doc, 5.3 MB</p>
                                                        </div>
                                                    </li>
                                                    <li class="media mb-3 list-group-item">
                                                        <span class="mr-3 align-self-center img-icon success-rgba text-success">.xls</span>
                                                        <div class="media-body">
                                                            <h10 class="font-16 mb-1">Complete Product Sheet<i class="feather icon-download-cloud float-right"></i></h10>
                                                            <p>.xls, 2.5 MB</p>
                                                        </div>
                                                    </li>
                                                    <li class="media mb-3 list-group-item">
                                                        <span class="mr-3 align-self-center img-icon danger-rgba text-danger">.pdf</span>
                                                        <div class="media-body">
                                                            <h10 class="font-16 mb-1">Annual Sales Report 2018-19<i class="feather icon-download-cloud float-right"></i></h10>
                                                            <p>.pdf, 10.5 MB</p>
                                                        </div>
                                                    </li>
                                                    <li class="media mb-3 list-group-item">
                                                        <span class="mr-3 align-self-center img-icon info-rgba text-info">.zip</span>
                                                        <div class="media-body">
                                                            <h10 class="font-16 mb-1">Brand Photography<i class="feather icon-download-cloud float-right"></i></h10>
                                                            <p>.zip, 53.2 MB</p>
                                                        </div>
                                                    </li>
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