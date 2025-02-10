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

