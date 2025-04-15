function handleSubmit(formId) {
    tinymce.triggerSave();
    const form = document.getElementById(formId);
    //const form = "/files/3/upload";
    const url = new URL(form.action);
    const formData = new FormData(form);
    const fetchOptions = {
        method: form.method,
        body: formData,
    };
    fetch(url, fetchOptions);
  // Any JS that could fail goes here
    form.reset();
    document.getElementById('closeForm_'+formId).click();
    console.log('Отправка!');
    setTimeout( //TODO заменить на отдельное обновление элементов вместо перегрузки всей страницы
            function(){
                window.location.reload();
            }, 3000
        );
}

/** jQuery
$('form[name=myForm]').trigger('reset');
*/
$('button#tagCreate').on('click', function() {
          var tagName = $("input[type=text][name=tagName]").val();
          var appealId = $("input[type=text][name=appealId]").val();
          console.log("tagName = ", tagName);
          $("div#draftTags").append('<span class="badge text-bg-primary">'+tagName+'</span>');
          $('input[type=text][name=tagName]').val('');
          createTag(appealId, tagName);
          //var tagId = createTag(appealId, tagName);
          //$("td#listTags").append('<span class="badge text-bg-primary" id="tagAppealId'+tagId+'">'+tagName+'</span>');
          $("td#listTags").append('<span class="badge text-bg-primary">'+tagName+'</span>');
});

function createTag(appealId, tagName) {
    const url = new URL(window.location.origin +"/labels/create");
    const formData = new FormData();
    formData.append("appealId", appealId);
    formData.append("tagName", tagName);

    const fetchOptions = {
        method: "POST",
        body: formData,
    };
    console.log('Отправка!');
    let response = fetch(url, fetchOptions);
    /*let sss;
    if(response.ok){
        sss = response.text();
    }
    let text = response.text();
    return text;
    */
}

function deleteTag(labelId){
    const url = new URL(window.location.origin +"/labels/delete?id="+labelId);
    fetch(url);
    console.log('Удалена label = ', labelId);
    $('#tagAppealId'+labelId).remove();
}

//<!-- sidebar -->
$(function($){
    let storage = localStorage.getItem('nav');
    console.log("storage = ", storage);
    if (storage && storage !== "#") {
        console.log(".nav a[href=");
        $('.nav a[href="' + storage + '"]').tab('show');
        console.log("$('a.nav-link.active').parent().parent()", $('a.nav-link.active').parent());
        $('a.nav-link.active').parent().parent().parent().toggleClass('menu-open');
    }
    $('a.nav-link').on('click', function() {
        let href = $(this).attr('href');
        console.log("href = ", href);
        localStorage.setItem('nav', href);
        console.log("$(this) = ", $(this).parent().parent());
        $(this).parent().parent().toggleClass("menu-open");
        let ul = $(this).parent().parent();
        console.log("ul = ", ul.children());
        let listLi = ul.children();
        listLi.each(
            function(){
                console.log("foreach $(this) = ", $(this));
                $(this).removeClass("menu-open");
            }
        );
    });
});

$("div.edit").on('click', function() {
    let edit = $(this).attr("id");
    let num = edit.split('fileName')[1];
    $("button#save"+num).attr("hidden", false);
    $("button#save"+num).attr("disabled", false);
    console.log("num", num);
});

$("button.btn-create.save[id^='save']").on('click', function() {
    console.log("$(this) = ", $(this));
    $(this).each(function(id){
        console.log("id = ", id);
    });
    let edit = $(this).attr("id");
    console.log("edit = ", edit);
    let num = edit.split('save')[1];
    let newName = $("div#fileName"+num).text()
    console.log("newName", newName);

    const url = new URL(window.location.origin +"/files/"+num+"/changename");
    const formData = new FormData();
    formData.append("newName", newName);

    const fetchOptions = {
        method: "PUT",
        body: formData,
    };
    console.log('Отправка!');
    let response = fetch(url, fetchOptions);

    $("button#save"+num).attr("hidden", true);
    $("button#save"+num).attr("disabled", true);
});

function settingSave(formId) {
    const form = document.getElementById(formId);
    const url = new URL(form.action);
    const formData = new FormData(form);

    var object = {};
    formData.forEach((value, key) => object[key] = value);
    var json = JSON.stringify(object);

    const fetchOptions = {
        headers:{
              'Content-Type': 'application/json'
            },
        method: "POST",
        body: json,
    };
    console.log('Отправка!');
    let response = fetch(url, fetchOptions);
}

function checkbox() {
  var checked = false;
  if (document.querySelector('.form-check-input:checked')) {
     checked = true;
  }
  document.getElementById('msg').value = checked;
}

$(".form-check-input").on('click', function() {
    console.log(".form-check-input:checked = ", $(this).is(':checked'));
    if($(this).is(':checked')){
        $(this).val(true);
    }else{
        $(this).val(false);
    }
})

$(".form-check-input").each(function(id){
    console.log("id = ", $(this).val());
    if($(this).val() === "true"){
        $(this).attr("checked", "checked")
    }else{
        $(this).val(false);
    }
});

$(document).ready(function(){
   $('.js-tabularinfo').bootstrapTable({
        escape: false,
        showHeader: false
      });
});