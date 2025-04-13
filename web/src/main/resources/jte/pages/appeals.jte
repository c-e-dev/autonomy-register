@import java.util.List
@import ru.c_energies.databases.entity.appeals.AppealRow

@param List<AppealRow> list

@template.layout.main(content = @`
    <div class="app-content-header"> <!--begin::Container-->
      <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
          <div class="col-sm-6">
            <h3 class="mb-0">Обращения</h3>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-end">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">Обращения</li>
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

                  <!--<ol class="breadcrumb float-sm-end">-->
                <button type="button" class="btn btn-create" data-bs-toggle="modal" data-bs-target="#createAppealByTheme" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Создать</button>
                <div class="modal fade" id="createAppealByTheme" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog" style="max-width:1000px">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel2">Создать обращение</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <form onsubmit="return false;" action="/document/appeals" method="POST" enctype="application/x-www-form-urlencoded" id="createAppeal">
                        <div class="modal-body">
                          <input type="text" required class="form-control" id="themeId" name="themeId" value="0" readonly hidden="true">
                          <label class="form-label">Тема обращения</label>
                          <input type="text" required class="form-control" id="titleAppeal" name="title" value="">
                          <label class="form-label">Кому</label>
                          <input type="text" class="form-control" id="inputRecipient" name="recipient" value="">
                          <label class="form-label">Куда</label>
                          <input type="text" class="form-control" id="inputAddress" name="address" value="">
                          <label class="form-label">Номер отправления</label>
                          <input type="text" class="form-control" id="registerTrackNumber" name="registerTrackNumber" value="">
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
                            <option selected value="none">Не выбрано</option>
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
                  <!--</ol>-->
              </div> <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-bordered">
                  <thead>
                  <tr>
                    <th style="width: 10px">#</th>
                    <th>Номер отправления (РПО)</th>
                    <th>Внутренний номер</th>
                    <th>Тип обращения</th>
                    <th>Название</th>
                    <th>Дата создания</th>
                  </tr>
                  </thead>
                  <tbody>
                  @for(AppealRow at : list)
                    <tr class="align-middle">
                      <td><a href="/document/appeals/${at.id()}">${at.id()}.</a></td>
                      <td><a href="/document/appeals/${at.id()}">${at.registerTrackNumber()}</a></td>
                      <td>${at.internalNumber()}</td>
                      <td>${at.type()}</td>
                      <td>${at.title()}</td>
                      <td>${at.createDate()}</td>
                    </tr>
                  @endfor
                  </tbody>
                </table>
              </div> <!-- /.card-body -->
              <div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-end">
                  <li class="page-item"> <a class="page-link" href="#">&laquo;</a> </li>
                  <li class="page-item"> <a class="page-link" href="#">1</a> </li>
                  <li class="page-item"> <a class="page-link" href="#">2</a> </li>
                  <li class="page-item"> <a class="page-link" href="#">3</a> </li>
                  <li class="page-item"> <a class="page-link" href="#">&raquo;</a> </li>
                </ul>
              </div>
            </div> <!-- /.card -->

          </div> <!-- /.col -->
        </div> <!--end::Row-->
      </div> <!--end::Container-->
    </div> <!--end::App Content-->
`)