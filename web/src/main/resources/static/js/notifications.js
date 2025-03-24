
let currentVersion;

setInterval(function() {
    console.log("notifications");
    console.log("currentVersion = ", currentVersion);

    let version;
    $.get('/notifications/version', function(data){
    	console.log(data);
    	version = data.version;
    	console.log("version = ", version);
        if(version !== currentVersion){
            $("div#version").append('<p>Появилось новое обновление. Обновить</p>');
            currentVersion = version;
            console.log("currentVersion after = ", currentVersion);
        }
    });
}, 10000);