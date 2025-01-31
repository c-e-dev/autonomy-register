@import java.util.List
@import ru.c_energies.databases.entity.themes.ThemeRow

@param List<ThemeRow> list

@template.layout.main(content = @`
    <div class="app-content-header"> <!--begin::Container-->
      <div class="container-fluid"> <!--begin::Row-->
        <div class="row">
          <div class="col-sm-6">
            <h3 class="mb-0">Темы обращений</h3>
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
                <button type="button" data-bs-toggle="modal" data-bs-target="#themeCreate"  class="btn btn-create" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Создать</button>
                <div class="modal fade" id="themeCreate" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog" style="max-width:1000px">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5" id="themeModalLabel2">Редактирование</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <form onsubmit="return false;" action="/document/themes" method="POST" enctype="application/x-www-form-urlencoded" id="createTheme">
                        <div class="modal-body">
                          <label class="form-label">Тема</label>
                          <input type="text" class="form-control" id="inputTheme01" name="title" value="">
                          <label class="form-label">Описание</label>
                          <input type="text" class="form-control" id="inputTheme02" name="description" value="">
                          <label class="form-label">Дата решения:</label>
                          <input type="datetime-local" min="2020-01-01T00:00" class="form-control" id="inputText02" name="decisionDate" value="2020-01-01T00:00">
                          <label class="form-label" for="decisionStatus">Решено:</label>
                          <select class="form-select" id="decisionStatus" required autocomplete="off" name="decisionStatus">
                            <option selected disabled value="">Выберите</option>
                            <option value="1">Не решено</option>
                            <option value="2">Решено</option>
                            <option value="3">Отложено</option>
                          </select>
                        </div>
                        <div class="modal-footer">
                          <button type="button" id="closeForm_createTheme" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                          <button type="button" class="btn btn-primary" onclick="handleSubmit('createTheme')">Сохранить</button>
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
                    <th>Тема</th>
                    <th>Дата создания</th>
                    <th style="width: 40px">Label</th>
                  </tr>
                  </thead>
                  <tbody>
                  @for(ThemeRow tt : list)
                    <tr class="align-middle">
                      <td><a href="/document/themes/${tt.id()}">${tt.id()}.</a></td>
                      <td><a href="/document/themes/${tt.id()}">${tt.title()}</a></td>
                      <td>${tt.createDate()}
                      </td>
                      <td><span class="badge text-bg-danger">55%</span></td>
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