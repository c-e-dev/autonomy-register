@import java.util.List
@import ru.c_energies.web.models.themes.ThemeRow
@import ru.c_energies.web.models.appeals.AppealRow

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
                        <button type="button" class="btn btn-create" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Создать</button>
                    </div> <!-- /.card-header -->
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
                            <tr>
                                <th>Связанные отправления:</th>
                            </tr>
                        </table>

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
                    </div> <!-- /.card-body -->
                </div> <!-- /.card -->

            </div> <!-- /.col -->
        </div> <!--end::Row-->
    </div> <!--end::Container-->
</div> <!--end::App Content-->
`)