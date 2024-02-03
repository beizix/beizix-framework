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

let utilize = {};

utilize.goPageable = goPageable;
utilize.convertToPageableSortValue = convertToPageableSortValue;

/**
 * Public image source 가져오기
 * @param imagePath
 * @param imageFilename
 * @param isCrop
 */
utilize.getPublicImageSrc = function (imagePath, imageFilename, isCrop) {
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

utilize.lineSeparatorToBr = function (contents) {
  return contents.replaceAll('\r\n', '<br/>');
}

utilize.unescape = function (contents) {
  let ret = contents.replace(/&gt;/g, '>');
  ret = ret.replace(/&lt;/g, '<');
  ret = ret.replace(/&quot;/g, '"');
  ret = ret.replace(/&apos;/g, "'");
  ret = ret.replace(/&amp;/g, '&');
  return ret;
}

utilize.sortable = function () {
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
      start: function () {
        if (!conditionCheckFunc()) {
          if (confirm(
              'Sorting 을 하려면 우선 정렬번호 순으로 정렬되어야 합니다. 정렬 조건을 변경하시겠습니까?')) {
            initSortFunc();
          } else {
            location.reload();
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
    orderNoRange.sort(function (a, b) {
      return b - a;
    });
    $(idRange).each(function (idx, id) {
      updateData.push({'id': id, 'orderNo': orderNoRange[idx]});
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
  audit.fadeIn();
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


