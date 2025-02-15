@import java.util.List
@import ru.c_energies.databases.entity.secured_party.SecuredPartyRow

@param List<SecuredPartyRow> securedPartyRows

@template.layout.main(content = @`
    <div class="app-content-header"> <!--begin::Container-->
      <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
          <div class="col-sm-6">
            <h3 class="mb-0">Право требования</h3>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-end">
              <li class="breadcrumb-item"><a href="/">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">Право требования</li>
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
                <button type="button" data-bs-toggle="modal" data-bs-target="#modal_securedPartyCreate" class="btn btn-create" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Создать</button>
                  <!--</ol>-->
                <div class="modal fade" id="modal_securedPartyCreate" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog" style="max-width:1000px">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel2">Создание</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <form onsubmit="return false;" action="/document/secured-party" method="POST" enctype="application/x-www-form-urlencoded" id="securedPartyCreate">
                        <div class="modal-body">
                          <label class="form-label">Название</label>
                          <input type="text" class="form-control" id="inputText01" name="name" value="">
                          <label class="form-label">Сумма</label>
                          <input type="number" class="form-control" id="inputText02" name="amount" value="">
                        </div>
                        <div class="modal-footer">
                          <button type="button" id="closeForm_securedPartyCreate" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                          <button type="button" class="btn btn-primary" onclick="handleSubmit('securedPartyCreate')">Сохранить</button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div> <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>Название</th>
                      <th>Для кого</th>
                      <th>Сумма</th>
                      <th>Обращение</th>
                      <th>Использовано</th>
                      <th>Дата использования</th>
                      <th>Дата создания</th>
                    </tr>
                  </thead>
                  <tbody>
                  @for(SecuredPartyRow securedParty : securedPartyRows)
                    <tr class="align-middle">
                      <td><a href="/document/secured-party/${securedParty.id()}">${securedParty.id()}.</a></td>
                      <td><a href="/document/secured-party/${securedParty.id()}">${securedParty.name()}</a></td>
                      <td></td>
                      <td>${securedParty.amount()}</td>
                      <td>${securedParty.appealId()}</td>
                      <td>${securedParty.used()}</td>
                      <td>${securedParty.applyDate()}</td>
                      <td>${securedParty.createDate()}</td>
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