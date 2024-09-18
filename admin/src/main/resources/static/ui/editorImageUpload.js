const initEditor = (_selector, _setup) => {
  tinymce.init({
    selector: _selector,
    plugins: 'preview importcss searchreplace autolink autosave save directionality code visualblocks visualchars fullscreen image link media template codesample table charmap pagebreak nonbreaking anchor insertdatetime advlist lists wordcount help charmap quickbars emoticons',
    toolbar: 'undo redo | bold italic underline strikethrough | fontfamily fontsize blocks | alignleft aligncenter alignright alignjustify | outdent indent |  numlist bullist | forecolor backcolor removeformat | pagebreak | charmap emoticons | fullscreen  preview save print | insertfile image media template link anchor codesample | ltr rtl',
    // toolbar_mode: 'floating',
    // images_upload_url: '/editorImage/upload',
    language: 'ko_KR',
    images_upload_handler: editorImageUpload,
    relative_urls: false,
    height: 600,
    quickbars_selection_toolbar: 'bold italic | quicklink h2 h3 blockquote quickimage quicktable',
    toolbar_mode: 'sliding',
    skin: 'oxide-dark',
    content_css: 'dark',
    quickbars_insert_toolbar: '',
    setup: _setup,
    // Tinymce 는 HTML 입력 시 validation 이 동작되기에 필요에 따라 허용 옵션을 지정해줘야 한다.
    // https://www.tiny.cloud/docs/tinymce/6/content-filtering/#valid_children
    valid_children: '+a[div|span]',
    // https://www.tiny.cloud/docs/tinymce/6/content-filtering/#extended_valid_elements
    extended_valid_elements: '#span[*]',
    autosave_ask_before_unload: false,
  });
}

const editorImageUpload = (blobInfo, progress) => new Promise(
    (resolve, reject) => {

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
          resolve(response);
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
