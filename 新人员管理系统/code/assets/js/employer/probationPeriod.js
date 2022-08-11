$(function () {

  initArtCateList()

  // 获取试用期资料的列表
  function initArtCateList () {
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/trial/select',
      success: function (res) {
        var htmlStr = template('tpl-table', res)
        $('tbody').html(htmlStr)
      }
    })
  }

  
})
