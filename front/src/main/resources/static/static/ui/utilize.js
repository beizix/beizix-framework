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
