const editorImageUpload = (blobInfo, progress) => new Promise((resolve, reject) => {

    let formData = new FormData();
    formData.append('editorImage', blobInfo.blob(), blobInfo.filename());

    $.ajax({
        type: 'POST',
        url: '/api/editorImage/upload',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        data: formData,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(
                $("meta[name='_csrf_header']").attr("content"),
                $("meta[name='_csrf']").attr("content"));
        },
        xhr: function () {
            let xhr = $.ajaxSettings.xhr();
            xhr.upload.onprogress = function (e) {
                progress(e.loaded / e.total * 100);
            }
            return xhr;
        },
        success: function (response) {
            // alert(response.message);
            resolve(response.message);
        },
        statusCode: {
            // error
            400: function (response) {
                const resJson = response.responseJSON;
                reject(resJson.message);
            }
        }
    });
});
