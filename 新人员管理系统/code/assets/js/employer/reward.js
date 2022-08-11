$(function () {
  var layer = layui.layer
  var form = layui.form

  initArtCateList(0)

  // 获取奖惩管理的列表
  function initArtCateList (num) {
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employer/reward_punish/select?company_name=软件开发科技有限公司&sign='+num,
      success: function (res) {
        var htmlStr = template('tpl-table', res)
        $('tbody').html(htmlStr)
      }
    })
  }

  //切换奖惩功能
  var num = 1
  $('#btnReset').on('click',function (e) {
    e.preventDefault()
    if(num == 1){
      num = 0
      initArtCateList(num)
    }else{
      num = 1
      initArtCateList(num)
    }
  })


  // 为添加奖惩按钮绑定点击事件
  var indexAdd = null
  $('#btnAddCate').on('click', function () {
    indexAdd = layer.open({
      type: 1,
      area: ['800px', '400px'],
      title: '添加新奖惩',
      content: $('#dialog-add').html()
    })
  })

  // 通过代理的形式，为 form-add 表单绑定 submit 事件
  $('body').on('submit', '#form-add', function (e) {
    e.preventDefault()
    $.ajax({
      method: 'POST',
      url: 'http://119.23.241.76:8000/employer/reward_punish/insert',
      data: $(this).serialize(),
      success: function (res) {
        if (res.insert !== 1)
        {
          return layer.msg('新增奖惩失败！')
        }
        initArtCateList(0)
        layer.msg('新增奖惩成功！')
        // 根据索引，关闭对应的弹出层
        layer.close(indexAdd)
      }
    })
  })


  // 通过代理的形式，为删除按钮绑定点击事件
  $('tbody').on('click', '.btn-delete', function () {
    var id = $(this).attr('data-id')
    var id_number = $(this).attr('data-num')
    // 提示用户是否要删除
    layer.confirm('确认删除?', { icon: 3, title: '提示' }, function (index) {
      $.ajax({
        method: 'GET',
        url: 'http://119.23.241.76:8000/employer/reward_punish/delete/',
        data: {
          id_number: id_number,
          id: id
        },
        success: function (res) {
          if (res.delete !== 1)
          {
            return layer.msg('删除奖惩失败！')
          }
          layer.msg('删除奖惩成功！')
          layer.close(index)
          initArtCateList(0)
        }
      })
    })
  })
})
