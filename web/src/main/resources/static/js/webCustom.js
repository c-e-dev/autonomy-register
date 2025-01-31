function handleSubmit(formId) {
    //event.preventDefault();
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
    setTimeout(
            function(){
                window.location.reload();
            }, 3000
        );
}

/** jQuery
$('form[name=myForm]').trigger('reset');
*/
