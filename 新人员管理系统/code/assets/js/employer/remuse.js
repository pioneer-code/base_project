$(function () {

  var layer = layui.layer
  var form = layui.form

  initArtCateList()

  // 获取面试者资料的列表
  function initArtCateList () {
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/interview/select',
      success: function (res) {
        var htmlStr = template('tpl-table', res)
        $('tbody').html(htmlStr)
      }
    })
  }
  // 通过代理的形式，为 btn-look 按钮绑定点击事件
  var indexEdit = null
  $('tbody').on('click', '.btn-look', function () {
    // 弹出一个查看面试信息的层
    indexEdit = layer.open({
      type: 1,
      area: ['800px', '400px'],
      title: '查看面试详情',
      content: $('#dialog-look').html()
    })

    var id = $(this).attr('data-id')
    // 发起请求获取对应分类的数据
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/interview/select/status?status=' + id,
      success: function (res) {
        console.log(res);
        form.val('form-look', res.interview)
      }
    })
  })
  
})
