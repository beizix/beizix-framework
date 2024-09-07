/**
 * 페이징 처리
 * @param {int} page
 * @param {int} size
 * @param {string} sort
 */
function goPageable(page, size, sort) {
  let uri = new Uri(location.href);
  uri.replaceQueryParam('page', page).replaceQueryParam('size', size)
    .replaceQueryParam('sort', convertToPageableSortValue(sort));

  location.href = uri.toString();
}


/**
 * UI 단의 sort 를 JPA pageable 이 인식할 수 있는 정렬값으로 변경
 * createdId: DESC => createdId,DESC
 * @param sort
 * @returns {string}
 */
function convertToPageableSortValue(sort) {
  sort = sort.replace(',', ':');
  const parsedSort = sort.split(':');
  return parsedSort[0].trim() + ',' + parsedSort[1].trim();
}

let uiUtil = {
  // ISO String 포맷을 YYYY-MM-DD HH:mm:ss 로 변환
  isoStrToReadable(isoStr) {
    return isoStr.replace(/T/, ' ').substring(0, 19);
  },
  // Pagination UI 생성 함수
  getPagingUI(curPageNo, size, totalPages, moveHandler) {
    curPageNo = parseInt(curPageNo);

    let frag1 =
      `
        <div class="row">
          <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
              <li class="page-item ${curPageNo === 0 ? 'disabled' : ''}">
                <a class="page-link" data-target-page="${curPageNo === 0 ? null : curPageNo - 1}">이전</a>
              </li>
     `;

    let loopNum = totalPages === 0 ? 0 : totalPages - 1;
    let frag2 = ``;
    for (let i = 0; i <= loopNum; i++) {
      frag2 +=
        `
              <li class="page-item ${i === curPageNo ? 'disabled' : ''}">
                <a class="page-link" data-target-page="${i === curPageNo ? null : i}">
                  ${i + 1}
                </a>
              </li>
        `;
    }

    let frag3 =
      `
              <li class="page-item ${curPageNo == totalPages - 1 || totalPages == 0 ? 'disabled' : ''}">
                <a class="page-link"
                   data-target-page="${curPageNo == totalPages - 1 || totalPages == 0 ? null : curPageNo + 1}">다음</a>
              </li>
            </ul>
          </nav>
        </div>
      `;

    let el = $(frag1 + frag2 + frag3);
    el.find('.page-link').on('click', (evt) => {
      let _this = evt.target;
      let targetPage = $(_this).data('target-page');
      if (targetPage.toString() === 'null') {
        return false;
      }
      moveHandler(targetPage);
    });

    return el;
  },
  // 리스트 상단 정렬 notation 및 갱신 기능 적용
  addSortNotation(sort, sortHandler) {
    let curSortField = sort.split(',')[0],
      curSortDir = sort.split(',')[1];

    $('.sortNotation').remove();

    $('.order-able').each(function (i, el) {
      $(el).removeClass('text-dark');

      let sortField = $(el).data('sort-field');
      // 현재 sort 조건과 동일한 칼럼일 경우
      if (sortField === curSortField) {
        $(el).parent().append(curSortDir === 'DESC' ?
          $('<i class="bi bi-arrow-down-circle-fill ms-1 sortNotation"></i>') :
          $('<i class="bi bi-arrow-up-circle-fill ms-1 sortNotation"></i>'));

        $(el).click(function () {
          sortHandler(0, curSortField + ',' + (curSortDir === 'DESC' ? 'ASC' : 'DESC'));
          return false;
        });
        $(el).addClass('text-dark');
      } else {
        $(el).click(function () {
          sortHandler(0, $(el).data('sort-field') + ',' + 'DESC');
          return false;
        });
      }
    });
  },
  // 파일 참조 URL 생성
  getFileReferURL(referType, accessType, uploadFile) {
    switch (referType) {
      case 'inline':
        return `/content-disposition/${referType}/${accessType}${uploadFile.path}/${uploadFile.name}`;
      case 'attachment':
        return `/content-disposition/${referType}/${accessType}${uploadFile.path}/${uploadFile.name}?originalFilename=${uploadFile.originName}`;
    }
  },
  /**
   * 목록 정렬 표기 및 갱신 기능 적용 - .order-able css class 기반으로 동작한다.
   * @param pageSize
   * @param sort
   */
  applyListSortNotation(pageSize, sort) {
    let curSortField = sort.split(':')[0],
      curSortDir = sort.split(':')[1].trim();

    $('.order-able').each(function (i, el) {
      let sortField = $(el).data('sort-field');
      // 현재 sort 조건과 동일한 칼럼일 경우
      if (sortField === curSortField) {

        $(el).parent().append(curSortDir === 'DESC' ?
          $('<i class="bi bi-arrow-down-circle-fill ms-1"></i>') :
          $('<i class="bi bi-arrow-up-circle-fill ms-1"></i>'));

        $(el).click(function () {
          goPageable(0, pageSize, curSortField + ': ' + (curSortDir === 'DESC' ? 'ASC' : 'DESC'));
          return false;
        });
        $(el).css('color', 'black');
      } else {
        $(el).click(function () {
          goPageable(0, pageSize, $(el).data('sort-field') + ': ' + 'DESC');
          return false;
        });
      }
    });
  },

};

uiUtil.goPageable = goPageable;
uiUtil.convertToPageableSortValue = convertToPageableSortValue;

/**
 * 공용 ajax 파일 업로드 함수
 * @param fileUploadType FileUploadType name
 * @param input file input 객체
 */
uiUtil.uploadFile = (fileUploadType, input) => {
  if (!fileUploadType) {
    alert('fileUploadType 을 명시해야 uiUtil.uploadFile 메서드를 실행할 수 있습니다.');
    return;
  }

  const inputGroup = $(input).parent('.input-group');
  if (!inputGroup.length) {
    alert(`${input.name} 앨리먼트의 부모가 input-group 클래스를 가져야 uiUtil.uploadFile 메서드를 실행할 수 있습니다.`);
    return;
  }

  let formData = new FormData();
  formData.append('file', input.files[0]);

  // fileUploadType 을 formData 에 append 한다.
  formData.append('reqVO', new Blob([JSON.stringify({
    fileUploadType
  })], {type: "application/json"}));

  $.ajax({
    method: 'POST',
    url: '/api/file/create',
    data: formData,
    contentType: false,               // * 중요 *
    processData: false,               // * 중요 *
    enctype: 'multipart/form-data',   // * 중요 *
    beforeSend: function (xhr) {
      xhr.setRequestHeader(
        $("meta[name='_csrf_header']").attr("content"),
        $("meta[name='_csrf']").attr("content"));
    },
    success(res) {
      console.log(res);

      // 에러 메세지가 있었다면 초기화
      $(input).removeClass('is-invalid');
      inputGroup.find('.invalid-feedback').text('');

      // 기존 mappingId 가 있다면 초기화
      inputGroup.find('input[name=fileMappingId]').remove();

      // 신규 mappingId 추가
      inputGroup.append(`<input type="hidden" name="fileMappingId" value="${res.id}"/>`);

      // 업로드 파일 정보 보이기
      uiUtil.showFileInfo(input, res, $(input).attr('accept') === 'image/*');
    },
    statusCode: {
      // 실패 - validation fail
      422: (res) => {
        const resJson = res.responseJSON;
        $(input).parent().find('.invalid-feedback').text(resJson.message);
        $(input).addClass('is-invalid');
      }
    }
  });
}

uiUtil.showFileInfo = (input, uploadFile, showImg) => {
  const inputGroup = $(input).parent('.input-group');
  if (!inputGroup.length) {
    alert(`${input.name} 앨리먼트의 부모가 input-group 클래스를 가져야 showFileInfo 메서드를 실행할 수 있습니다.`);
    return;
  }

  inputGroup.find('.fileInfo').remove();

  inputGroup.append(`
    <div class="fileInfo row text-muted fw-normal" style="display: none">
      <img src="${showImg ? uiUtil.getFileReferURL('inline', 'public', uploadFile) : ''}" class="w-75 mt-3 mb-3"/>
      <ul class="row" style="margin-left: 10px;">
        <li class="name">${uploadFile.name}</li>
        <li class="originName">원본 파일명 (${uploadFile.originName})</li>
        <li class="fileLength">${Math.ceil(uploadFile.fileLength / 1024)}KB</li>
        <li class="id">File ID (${uploadFile.id})</li>
      </ul>
    </div>
  `);

  const fileInfo = inputGroup.find('.fileInfo');
  if (!showImg) {
    fileInfo.find('img').remove();
  }

  fileInfo.fadeIn();
}

/**
 * Public image source 가져오기
 * @param imagePath
 * @param imageFilename
 * @param isCrop
 */
uiUtil.getPublicImageSrc = function (imagePath, imageFilename, isCrop) {
  if (!imagePath) {
    return "";
  }

  if (imagePath.indexOf("/static") === 0) {
    return isCrop ? imagePath + '/crop/' + imageFilename : imagePath + '/'
      + imageFilename;
  } else {
    return isCrop ? '/public' + imagePath + '/crop/' + imageFilename : '/public'
      + imagePath + '/' + imageFilename;
  }
}

uiUtil.lineSeparatorToBr = function (contents) {
  return contents.replaceAll('\r\n', '<br/>');
}

uiUtil.unescape = function (contents) {
  let ret = contents.replace(/&amp;/g, '&');
  ret = ret.replace(/&gt;/g, '>');
  ret = ret.replace(/&lt;/g, '<');
  ret = ret.replace(/&quot;/g, '"');
  ret = ret.replace(/&apos;/g, "'");
  return ret;
}

/**
 * 정렬 변경 UI 구성 함수
 */
uiUtil.sortable = function () {
  let orderNoRange = [];
  let idRange = [];
  let updateData = [];
  let updateFunc;

  function _init(parentSelector, handleSelector, conditionCheckFunc,
                 initSortFunc, _updateFunc) {

    $(parentSelector).sortable({
      axis: 'y',
      placeholder: 'ui-state-highlight',
      handle: handleSelector,
      opacity: 0.5,
      start: function () {
        if (!conditionCheckFunc()) {
          if (confirm(
            'Sorting 을 하려면 우선 정렬번호 순으로 정렬되어야 합니다. 정렬 조건을 변경하시겠습니까?')) {
            initSortFunc();
          }
        }
      },
      update: function (event, ui) {
        _order(parentSelector);
      }
    });
    updateFunc = _updateFunc;
  }

  function _order(parentSelector) {
    orderNoRange = [];
    idRange = [];
    updateData = [];
    let children = $(parentSelector).children();
    children.each(function (idx, elem) {
      orderNoRange.push($(elem).data('order-no'));
      idRange.push($(elem).data('id'));
    });
    $(idRange).each(function (idx, id) {
      updateData.push({'id': id, 'orderNo': Math.max(...orderNoRange) - idx});
    });
  }

  function _update() {
    if (!updateData || updateData.length === 0) {
      alert('저장할 변경내역이 없습니다.');
      return;
    }
    updateFunc(updateData);
  }

  return {
    init: _init,
    update: _update
  }
}();

const unescapeText = (str) => {
  str = str || '';

  str = str.replace(/&lt;/g, "<");
  str = str.replace(/&gt;/g, ">");
  str = str.replace(/&nbsp;/g, " ");
  str = str.replace(/&quot;/g, "\"");
  str = str.replace(/&apos;/g, "\'");
  str = str.replace(/&#39;/g, "\'");
  str = str.replace(/&rsquo;/g, "’");
  str = str.replace(/&amp;/g, "&");

  return str;
}

const showAudit = (auditItem, createdBy, createdAt, updatedBy, updatedAt) => {
  const audit = $('.audit-area');
  audit.find('.audit-target').text(auditItem);
  audit.find('.createdBy').text(createdBy);
  audit.find('.createdAt').text(createdAt);
  audit.find('.updatedBy').text(updatedBy);
  audit.find('.updatedAt').text(updatedAt);
  audit.removeAttr('style');
  audit.fadeOut(0);
  audit.fadeIn(800);
}

/**
 * XSS 방지를 위해 escape 처리되어 DB에 담긴 데이터를 화면에 출력할 때 unescape 처리해서 보여준다.
 * - 'unescapeText' css class 를 가진 input 앨리먼트에 적용된다.
 */
(function () {
  document.querySelectorAll(".unescapeText").forEach(function (el) {
    el.hasAttribute('value') ? el.value = unescapeText(el.value) : '';
    //el.textContent ? el.textContent = unescapeText(el.textContent) : '';
  })
})();


