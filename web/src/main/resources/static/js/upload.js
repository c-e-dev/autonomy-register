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
    document.getElementById('closeForm').click();
    console.log('Отправка!')
}