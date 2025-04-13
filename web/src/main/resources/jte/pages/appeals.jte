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

                  <!--<ol class="breadcrumb float-sm-end">-->
                <button type="button" class="btn btn-create" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Создать</button>
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
                    <th>Тема</th>
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