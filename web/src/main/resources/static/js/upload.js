/** @param {Event} event */
function handleSubmit() {
    //event.preventDefault();
    const form = document.getElementById('fileType');
    //const form = "/files/3/upload";
    const url = new URL(form.action);
    const formData = new FormData(form);

    var name = document.getElementById('fileInput');

    console.log('Selected file: ', formData.get('fileContent'));
    console.log('Selected file: ', formData.get('fileContent').name);
    console.log('Selected file: ', formData.get('fileContent').size);
    console.log('Selected file: ', formData.get('fileContent').type);

    const fetchOptions = {
        method: form.method,
        body: formData,
    };
    fetch(url, fetchOptions);
  // Any JS that could fail goes here
    form.reset();
    document.getElementById('closeForm').click();
    console.log('Отправка!')
}

function handleSubmitEditAppeal() {
    //event.preventDefault();
    const form = document.getElementById('editAppeal');
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
    document.getElementById('closeFormEditAppeal').click();
    console.log('Отправка!')
}

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
    document.getElementById('closeForm').click();
    console.log('Отправка!')
}

function handleSubmitCreateAppeal() {
    //event.preventDefault();
    const form = document.getElementById('createAppeal');
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
    document.getElementById('closeFormCreateAppeal').click();
    console.log('Отправка на создание Обращения!')
}

/** jQuery
$('form[name=myForm]').trigger('reset');
*/
